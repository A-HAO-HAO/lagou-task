1、Mybatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？
   1.1：动态sql是根据逻辑判断动态的生成sql，然后用来处理条件可变化的sql，从而解决了写死sql不够通用的弊端。
   1.2：动态sql标签有：if、choose when otherwise、trim、when、foreach、where等。
   1.3：解析xml文件拼接sql关键字。
        找到mappedStatement里面的参数类型获取到传入的params对应的字段值，
        占位符替换#{}，第二步中获取到的相应的值直接替换${}。
        设置占位符对应的参数值。
        执行sql。

2、Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？
    2.1：支持一对一或者一对多里面的@many和@one标签所关联对象的延迟加载。
    2.2：当发现有相应的配置时会返回一个代理对象，当有使用到这个对象的时候再去执行sql获得相应的结果。
    
3、Mybatis都有哪些Executor执行器？它们之间的区别是什么？
    SimpleExecutor：每次crud都是一个创建新的Statement对象。
    BatchExecutor：执行增删改的时候将所有的sql添加到batch处理中统一处理，缓存了多个Statement对象。处理完毕后释放。
    ReuseExecutor：以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后放入到map中供下次使用。
    
4、简述下Mybatis的一级、二级缓存（分别从存储结构、范围、失效场景。三个方面来作答）？
                       	 结构              		范围            			  失效场景
               一级     Map               sqlSession        close,commit,clearCache
               二级     Map               statement         过期时间，commit      

5、简述Mybatis的插件运行原理，以及如何编写一个插件？
    基本原理：mybatis创建四大对象时会经过拦截器，插件都实现了interceptor接口，然后为拦截器创建代理对象，从而实现功能的增强。
    如何编写：
            1：创建一个类实现interceptor接口，实现其方法，配置@Intercepts注解内容指定需要拦截的对象及方法
            2：sqlMapConﬁg.xml中配置interceptor信息。