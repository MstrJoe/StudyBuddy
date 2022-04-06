package com.studybuddy.api.controller;

import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.payload.input.HomeworkDto;
import com.studybuddy.api.payload.responses.HomeworkWithSubjectResponseDto;
import com.studybuddy.api.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private HomeworkRepository homeworkRepository;

    @GetMapping()
    public ResponseEntity<List<HomeworkWithSubjectResponseDto>> getHomeworkCollection() {
        List<HomeworkWithSubjectResponseDto> collection = this.homeworkRepository.findAll().stream()
                .map(item -> new HomeworkWithSubjectResponseDto(item)).collect(Collectors.toList());

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeworkWithSubjectResponseDto> getHomework(@PathVariable Long id) {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(id);

        if (currentHomework.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Homework not found");
        }

        Homework homework = currentHomework.get();

        return new ResponseEntity<>(new HomeworkWithSubjectResponseDto(homework), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<HomeworkWithSubjectResponseDto> createHomework(@PathVariable Long id,
            @RequestBody HomeworkDto data) {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(id);

        if (currentHomework.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Homework not found");
        }

        Homework homework = currentHomework.get();
        homework.setName(data.getName());
        homework.setDeadline(data.getDeadline());
        homework.setDescription(data.getDescription());
        homework.setLink(data.getLink());
        this.homeworkRepository.save(homework);

        return new ResponseEntity<>(new HomeworkWithSubjectResponseDto(homework), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteHomework(@PathVariable Long id) {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(id);

        if (currentHomework.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Homework not found");
        }

        Homework homework = currentHomework.get();
        this.homeworkRepository.delete(homework);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
