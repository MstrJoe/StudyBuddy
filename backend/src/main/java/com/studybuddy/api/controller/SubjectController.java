package com.studybuddy.api.controller;

import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.payload.input.HomeworkDto;
import com.studybuddy.api.payload.input.SubjectDto;
import com.studybuddy.api.payload.responses.SubjectHomeworkResponseDto;
import com.studybuddy.api.payload.responses.SubjectResponseDto;
import com.studybuddy.api.repository.HomeworkRepository;
import com.studybuddy.api.repository.SubjectRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private SubjectService subjectService;

    //Get all Omgezet naar service
    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> getSubjectCollection() {
        return new ResponseEntity<>(subjectService.findCollection(), HttpStatus.OK);
    }


//    @GetMapping()
//    public ResponseEntity<List<SubjectResponseDto>> getSubjectCollection() {
//
//        List<SubjectResponseDto> collection = this.subjectRepository.findAll().stream().map(
//                item -> new SubjectResponseDto(item)).collect(Collectors.toList());
//
//        return new ResponseEntity<>(collection, HttpStatus.OK);
//    }


    //Get by ID omgezet naar service
    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> getSubject(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(subjectService.findById(id), HttpStatus.OK);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<SubjectResponseDto> getSubject(@PathVariable Long id) {
//        Optional<Subject> currentSubject = this.subjectRepository.findById(id);
//        if (currentSubject.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
//        }
//
//        Subject subject = currentSubject.get();
//
//        return new ResponseEntity<>(new SubjectResponseDto(subject), HttpStatus.OK);
//    }

    //Postmapping omgezet naar service
    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping
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


    //    @PreAuthorize("hasAuthority('TEACHER')")
//    @PostMapping()
//    public ResponseEntity<?> createSubject(@RequestBody @Valid SubjectDto data, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
//        }
//        Subject subject = new Subject();
//        subject.setName(data.getName());
//        subjectRepository.save(subject);
//
//        return new ResponseEntity<>(new SubjectResponseDto(subject), HttpStatus.CREATED);
//    }
    //Putmapping omgezet naar service
    @PreAuthorize("hasAuthority('TEACHER')")
    @PutMapping
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


//    @PreAuthorize("hasAuthority('TEACHER')")
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateSubject(@PathVariable Long id, @RequestBody @Valid SubjectDto data,
//                                           BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
//        }
//
//        Optional<Subject> currentSubject = this.subjectRepository.findById(id);
//
//        if (currentSubject.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
//        }
//
//        Subject subject = currentSubject.get();
//        subject.setName(data.getName());
//        this.subjectRepository.save(subject);
//
//        return new ResponseEntity<>(new SubjectResponseDto(subject), HttpStatus.OK);
//    }

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


//
//    @PreAuthorize("hasAuthority('TEACHER')")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable Long id) {
//        Optional<Subject> currentSubject = this.subjectRepository.findById(id);
//
//        if (currentSubject.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
//        }
//
//        Subject subject = currentSubject.get();
//        this.subjectRepository.delete(subject);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

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

//    @PreAuthorize("hasAuthority('TEACHER')")
//    @PostMapping("/{subjectId}/homework")
//    public ResponseEntity<?> createSubject(@PathVariable Long subjectId, @RequestBody @Valid HomeworkDto homeworkData,
//                                           BindingResult bindingResult) {
//        Optional<Subject> currentSubject = this.subjectRepository.findById(subjectId);
//
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
//        }
//
//        if (currentSubject.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
//        }
//
//        Subject subject = currentSubject.get();
//        Homework homework = new Homework();
//        homework.setName(homeworkData.getName());
//        homework.setDeadline(homeworkData.getDeadline());
//        homework.setDescription(homeworkData.getDescription());
//        homework.setLink(homeworkData.getLink());
//        homework.setSubject(subject);
//        this.homeworkRepository.save(homework);
//
//        return new ResponseEntity<>(new SubjectHomeworkResponseDto(homework), HttpStatus.CREATED);
//    }

}
