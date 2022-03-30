package com.studybuddy.api.controller;

import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.payload.HomeworkDto;
import com.studybuddy.api.payload.SubjectDto;
import com.studybuddy.api.payload.SubjectHomeworkResponseDto;
import com.studybuddy.api.payload.SubjectResponseDto;
import com.studybuddy.api.repository.HomeworkRepository;
import com.studybuddy.api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    public List<SubjectResponseDto> getSubjects() {
        List<SubjectResponseDto> response = new ArrayList<>();

        List<Subject> subjects = this.subjectRepository.findAll();

        for (Subject subject : subjects) {
            response.add(new SubjectResponseDto(subject));
        }

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> getSubject(@PathVariable Long id) {
        Optional<Subject> currentSubject = this.subjectRepository.findById(id);
        if (currentSubject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }
        Subject subject = currentSubject.get();

        return new ResponseEntity<>(new SubjectResponseDto(subject), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping()
    public ResponseEntity<SubjectResponseDto> createSubject(@RequestBody SubjectDto data) {
        Subject subject = new Subject();
        subject.setName(data.getName());
        subjectRepository.save(subject);
        return new ResponseEntity<>(new SubjectResponseDto(subject), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> createSubject(@PathVariable Long id, @RequestBody SubjectDto data) {
        Optional<Subject> currentSubject = this.subjectRepository.findById(id);

        if (currentSubject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }

        Subject subject = currentSubject.get();
        subject.setName(data.getName());
        this.subjectRepository.save(subject);
        return new ResponseEntity<>(new SubjectResponseDto(subject), HttpStatus.OK);
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
    public ResponseEntity<SubjectHomeworkResponseDto> createSubject(@PathVariable Long subjectId, @RequestBody HomeworkDto homeworkData) {
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
        return new ResponseEntity<>(new SubjectHomeworkResponseDto(homework), HttpStatus.CREATED);
    }

}
