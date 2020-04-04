package com.lagou.edu.factory;

import com.lagou.edu.annotation.Autowired;
import com.lagou.edu.annotation.Service;
import com.lagou.edu.annotation.Transactional;
import com.lagou.edu.utils.ClassUtil;
import com.lagou.edu.utils.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

public class AnnotationBeanFactory {

    private static Map<String,Object> map = new HashMap<>();  // 存储对象

    static {
        //扫描指定包下的所有类
        Set<String> classPakNameSet = ClassUtil.getClassPakNameSet("com.lagou.edu");
        try {
            for (String classPackName : classPakNameSet) {
                Class<?> clazz = Class.forName(classPackName);
                //获得service注解-实例化对象
                Service serviceAnnotation = clazz.getAnnotation(Service.class);
                if(serviceAnnotation != null){
                    String value = serviceAnnotation.name();
                    value = "".equals(value)? StringUtil.firstCharToLowerCase(clazz.getSimpleName()):value;
                    Object o = clazz.newInstance();
                    map.put(value,o);
                }else{
                    continue;
                }
            }

            //跳过循环依赖的问题实例化所有对象后再设置属性
            for (String name : map.keySet()) {
                Object obj = map.get(name);
                Class<?> clazz = obj.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    Autowired awAnnotation = field.getAnnotation(Autowired.class);
                    if(awAnnotation != null){//注入属性
                        field.setAccessible(Boolean.TRUE);
                        String filedName = awAnnotation.name();
                        field.getGenericType().getClass();
                        filedName = "".equals(filedName)?StringUtil.firstCharToLowerCase(field.getType().getSimpleName()):filedName;
                        //map中获取相应的属性对象
                        Object o = map.get(filedName);
                        field.set(obj,o);
                    }
                }
            }

            //类方法增强
            for (String name : map.keySet()) {
                Object obj = map.get(name);
                Class<?> clazz = obj.getClass();
                Method[] methods = clazz.getMethods();
                Transactional classTransAnnotation = clazz.getAnnotation(Transactional.class);
                if (classTransAnnotation != null) {
                    ProxyFactory proxyFactory = (ProxyFactory) map.get("proxyFactory");
                    //获得事务代理对象
                    Object proxyObj = proxyFactory.getProxy(obj, null);
                    //将代理对象替换掉容器map中的对象
                    map.replace(name, proxyObj);
                }

                for (Method method : methods) {
                    Transactional methodTransAnnotation = method.getAnnotation(Transactional.class);
                    obj = map.get(name);
                    //第二个判断不放在上面if的else中的原因是为了以后兼容其余的自定义注解
                    if (methodTransAnnotation != null && classTransAnnotation == null) {
                        ProxyFactory proxyFactory = (ProxyFactory) map.get("proxyFactory");
                        //获得事务代理对象
                        Object proxyObj = proxyFactory.getProxy(obj, method);
                        //将代理对象替换掉容器map中的对象
                        map.replace(name, proxyObj);
                    }
                }
            }
        }catch (Exception e){

        }
    }

    // 任务二：对外提供获取实例对象的接口（根据id获取）
    public static Object getBean(String id) {
        return map.get(id);
    }

}
