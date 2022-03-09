package com.studybuddy.api.controller;

import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.payload.SubjectDto;
import com.studybuddy.api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping()
    public List<Subject> getSubjects() {
        return this.subjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        Optional<Subject> currentSubject = this.subjectRepository.findById(id);
        if (currentSubject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }
        Subject subject = currentSubject.get();
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Subject> createSubject(@RequestBody SubjectDto data) {
        Subject subject = new Subject();
        subject.setName(data.getName());
        subjectRepository.save(subject);
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> createSubject(@PathVariable Long id, @RequestBody SubjectDto data) {
        Optional<Subject> currentSubject = this.subjectRepository.findById(id);

        if (currentSubject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }

        Subject subject = currentSubject.get();
        subject.setName(data.getName());
        this.subjectRepository.save(subject);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable Long id) {
        Optional<Subject> currentSubject = this.subjectRepository.findById(id);

        if (currentSubject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }

        Subject subject = currentSubject.get();
        this.subjectRepository.delete(subject);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }
}
