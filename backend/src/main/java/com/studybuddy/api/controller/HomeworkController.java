package com.studybuddy.api.controller;

import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.payload.HomeworkDto;
import com.studybuddy.api.payload.SubjectDto;
import com.studybuddy.api.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private HomeworkRepository homeworkRepository;

    @GetMapping()
    public List<Homework> getSubjects() {
        return this.homeworkRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Homework> getSubject(@PathVariable Long id) {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(id);
        if (currentHomework.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }
        Homework homework = currentHomework.get();
        return new ResponseEntity<>(homework, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Homework> createSubject(@PathVariable Long id, @RequestBody HomeworkDto data) {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(id);

        if (currentHomework.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }

        Homework homework = currentHomework.get();
        homework.setName(data.getName());
        homework.setDeadline(data.getDeadline());
        homework.setDescription(data.getDescription());
        homework.setLink(data.getLink());
        this.homeworkRepository.save(homework);
        return new ResponseEntity<>(homework, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable Long id) {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(id);

        if (currentHomework.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }

        Homework homework = currentHomework.get();
        this.homeworkRepository.delete(homework);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

