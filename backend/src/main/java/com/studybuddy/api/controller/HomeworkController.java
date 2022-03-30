package com.studybuddy.api.controller;

import com.studybuddy.api.entity.AgendaItem;
import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.AgendaItemDto;
import com.studybuddy.api.payload.HomeworkDto;
import com.studybuddy.api.payload.SubjectDto;
import com.studybuddy.api.repository.HomeworkRepository;
import com.studybuddy.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<Homework> getHomeworkCollection() {
        return this.homeworkRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Homework> getHomework(@PathVariable Long id) {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(id);
        if (currentHomework.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Homework not found");
        }
        Homework homework = currentHomework.get();
        return new ResponseEntity<>(homework, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<Homework> createHomework(@PathVariable Long id, @RequestBody HomeworkDto data) {
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
        return new ResponseEntity<>(homework, HttpStatus.OK);
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

    @PostMapping("/{homeworkId}/agendaitem")
    public ResponseEntity<Homework> createAgendaItem(Principal principal, @PathVariable Long homeworkId, @RequestBody AgendaItemDto agendaItemData) {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(homeworkId);

        if (currentHomework.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Homework not found");
        }

        User user = this.userRepository.findByUsernameOrEmail(principal.getName(), principal.getName()).get();

        Homework homework = currentHomework.get();
        AgendaItem agendaItem = new AgendaItem();
        agendaItem.setTitle(agendaItemData.getTitle());
        agendaItem.setMoment(agendaItemData.getMoment());
        agendaItem.setDescription(agendaItemData.getDescription());
        agendaItem.setLink(agendaItemData.getLink());
        agendaItem.setCreatedBy(user);
        this.homeworkRepository.save(homework);
        return new ResponseEntity<>(homework, HttpStatus.CREATED);
    }
}

