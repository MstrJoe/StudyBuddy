package com.studybuddy.api.controller;

import com.studybuddy.api.entity.AgendaItem;
import com.studybuddy.api.payload.AgendaItemDto;
import com.studybuddy.api.repository.AgendaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendaitem")
public class AgendaItemController {

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    @GetMapping()
    public List<AgendaItem> getAgendaItemCollection() {
        return this.agendaItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaItem> getAgendaItem(@PathVariable Long id) {
        Optional<AgendaItem> currentAgendaItem = this.agendaItemRepository.findById(id);
        if (currentAgendaItem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda-item not found");
        }
        AgendaItem agendaItem = currentAgendaItem.get();
        return new ResponseEntity<>(agendaItem, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaItem> createHomework(@PathVariable Long id, @RequestBody AgendaItemDto data) {
        Optional<AgendaItem> currentAgendaItem = this.agendaItemRepository.findById(id);

        if (currentAgendaItem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda-item not found");
        }

        AgendaItem agendaItem = currentAgendaItem.get();
        agendaItem.setTitle(data.getTitle());
        agendaItem.setMoment(data.getMoment());
        agendaItem.setDescription(data.getDescription());
        agendaItem.setLink(data.getLink());
        this.agendaItemRepository.save(agendaItem);
        return new ResponseEntity<>(agendaItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAgendaItem(@PathVariable Long id) {
        Optional<AgendaItem> currentAgendaItem = this.agendaItemRepository.findById(id);

        if (currentAgendaItem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda-item not found");
        }

        AgendaItem agendaItem = currentAgendaItem.get();
        this.agendaItemRepository.delete(agendaItem);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
