package com.studybuddy.api.controller;

import com.studybuddy.api.payload.input.HomeworkDto;
import com.studybuddy.api.payload.responses.HomeworkWithSubjectResponseDto;
import com.studybuddy.api.service.HomeworkService;

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
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    @GetMapping()
    public ResponseEntity<List<HomeworkWithSubjectResponseDto>> getHomeworkCollection() {
        return new ResponseEntity<>(homeworkService.findCollection(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeworkWithSubjectResponseDto> getHomework(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(homeworkService.findById(id), HttpStatus.OK);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHomework(@PathVariable Long id, @RequestBody @Valid HomeworkDto data,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(homeworkService.update(id, data), HttpStatus.OK);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteHomework(@PathVariable Long id) {
        try {
            homeworkService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }
}
