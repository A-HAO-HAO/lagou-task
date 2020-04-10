package com.lagou.edu.controller;


import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @RequestMapping("")
    public String index(){
        return "/resume";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> add(Resume resume){
        Map<String, Object> data = new HashMap<>();
        try{
            resumeService.add(resume);
            data.put("success",true);
        }catch (Exception e){
            data.put("success",false);
        }
        return data;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Resume resume){
        Map<String, Object> data = new HashMap<>();
        try{
            resumeService.update(resume);
            data.put("success",true);
        }catch (Exception e){
            data.put("success",false);
        }
        return data;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(Resume resume){
        Map<String, Object> data = new HashMap<>();
        try{
            resumeService.delete(resume);
            data.put("success",true);
        }catch (Exception e){
            data.put("success",false);
        }
        return data;
    }

    @RequestMapping("/find-all")
    @ResponseBody
    public Map<String,Object> findAll(){
        Map<String, Object> data = new HashMap<>();
        try{
            List<Resume> resumes = resumeService.queryAll();
            data.put("data",resumes);
            data.put("success",true);
        }catch (Exception e){
            data.put("success",false);
        }
        return data;
    }


}
