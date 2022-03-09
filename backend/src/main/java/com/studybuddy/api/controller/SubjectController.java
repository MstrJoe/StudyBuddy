package com.studybuddy.api.controller;

import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.payload.SubjectDto;
import com.studybuddy.api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<Subject> createSubject(@RequestBody SubjectDto data) {
        Subject subject = new Subject();
        subject.setName(data.getName());
        subjectRepository.save(subject);
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }

}