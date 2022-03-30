package com.studybuddy.api.controller;

import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.payload.HomeworkDto;
import com.studybuddy.api.payload.SubjectDto;
import com.studybuddy.api.repository.HomeworkRepository;
import com.studybuddy.api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private HomeworkRepository homeworkRepository;

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

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping()
    public ResponseEntity<Subject> createSubject(@RequestBody SubjectDto data) {
        Subject subject = new Subject();
        subject.setName(data.getName());
        subjectRepository.save(subject);
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
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

    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable Long id) {
        Optional<Subject> currentSubject = this.subjectRepository.findById(id);

        if (currentSubject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }

        Subject subject = currentSubject.get();
        this.subjectRepository.delete(subject);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/{subjectId}/homework")
    public ResponseEntity<Homework> createSubject(@PathVariable Long subjectId, @RequestBody HomeworkDto homeworkData) {
        Optional<Subject> currentSubject = this.subjectRepository.findById(subjectId);

        if (currentSubject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }

        Subject subject = currentSubject.get();
        Homework homework = new Homework();
        homework.setName(homeworkData.getName());
        homework.setDeadline(homeworkData.getDeadline());
        homework.setDescription(homeworkData.getDescription());
        homework.setLink(homeworkData.getLink());
        homework.setSubject(subject);
        this.homeworkRepository.save(homework);
        return new ResponseEntity<>(homework, HttpStatus.CREATED);
    }

}
