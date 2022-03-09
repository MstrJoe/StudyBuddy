package com.studybuddy.api.controller;

import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping()
    public List<Subject> getSubjects() {
        return this.subjectRepository.findAll();
    }

}