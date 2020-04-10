package com.lagou.edu.service;

import com.lagou.edu.pojo.Resume;

import java.util.List;

public interface ResumeService {

    Resume add(Resume resume);

    void delete(Resume resume);

    Resume update(Resume resume);

    List<Resume> queryAll();

}
