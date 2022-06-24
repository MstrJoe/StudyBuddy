package com.studybuddy.api.controller;

import com.studybuddy.api.payload.input.HomeworkDto;
import com.studybuddy.api.payload.input.SubjectDto;
import com.studybuddy.api.payload.responses.SubjectResponseDto;
import com.studybuddy.api.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> getSubjectCollection() {
        return new ResponseEntity<>(subjectService.findCollection(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> getSubject(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(subjectService.findById(id), HttpStatus.OK);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping()
    public ResponseEntity<?> createSubject(@RequestBody @Valid SubjectDto data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(subjectService.postSubject(data), HttpStatus.OK);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @RequestBody @Valid SubjectDto data,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(subjectService.putSubject(id, data), HttpStatus.OK);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }


    //Delete mapping omgezet naar service
    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable Long id) {
        try {
            subjectService.deleteSubject(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }


    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/{subjectId}/homework")
    public ResponseEntity<?> createSubject(@PathVariable Long subjectId, @RequestBody @Valid HomeworkDto homeworkData,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } try {
            return new ResponseEntity<>(subjectService.createSubjectHomework(subjectId, homeworkData), HttpStatus.OK);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }

    }

}
