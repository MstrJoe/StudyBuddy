package com.studybuddy.api.controller;

import com.studybuddy.api.entity.AgendaItem;
import com.studybuddy.api.entity.AgendaItemSubscriber;
import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.input.AgendaItemCreateDto;
import com.studybuddy.api.payload.input.AgendaItemUpdateDto;
import com.studybuddy.api.payload.responses.AgendaItemResponseDto;
import com.studybuddy.api.repository.AgendaItemRepository;
import com.studybuddy.api.repository.AgendaItemSubscriberRepository;
import com.studybuddy.api.repository.HomeworkRepository;

import com.studybuddy.api.service.AgendaItemService;
import com.studybuddy.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendaitem")
public class AgendaItemController {

    @Autowired
    private UserService userService;

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    @Autowired
    private AgendaItemSubscriberRepository agendaItemSubscriberRepository;

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private AgendaItemService agendaItemService;

    @GetMapping()
    public ResponseEntity<List<AgendaItemResponseDto>> getAgendaItemCollection() {
        return new ResponseEntity<>(agendaItemService.getCollection(), HttpStatus.OK);
    }

//    @GetMapping()
//    public ResponseEntity<List<AgendaItemResponseDto>> getAgendaItemCollection() {
//
//        List<AgendaItemResponseDto> collection = this.agendaItemRepository.findByOrderByMomentAsc().stream()
//                .map(item -> new AgendaItemResponseDto(item))
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(collection, HttpStatus.OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<AgendaItemResponseDto> getAgendaItem(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(agendaItemService.getItemById(id), HttpStatus.OK);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<AgendaItemResponseDto> getAgendaItem(@PathVariable Long id) {
//        Optional<AgendaItem> currentAgendaItem = this.agendaItemRepository.findById(id);
//        if (currentAgendaItem.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda-item not found");
//        }
//        AgendaItem agendaItem = currentAgendaItem.get();
//        return new ResponseEntity<>(new AgendaItemResponseDto(agendaItem), HttpStatus.OK);
//    }

    @PostMapping()
    public ResponseEntity<?> createAgendaItem(Principal principal,
            @RequestBody @Valid AgendaItemCreateDto agendaItemDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getByPrincipal(principal);

        AgendaItem agendaItem = new AgendaItem();
        agendaItem.setTitle(agendaItemDto.getTitle());
        agendaItem.setMoment(agendaItemDto.getMoment());
        agendaItem.setDescription(agendaItemDto.getDescription());
        agendaItem.setLink(agendaItemDto.getLink());
        agendaItem.setCreatedBy(user);

        if (agendaItemDto.getHomeworkId().isPresent()) {
            Optional<Homework> currentHomework = this.homeworkRepository.findById(agendaItemDto.getHomeworkId().get());

            if (currentHomework.isEmpty()) {
                return new ResponseEntity<>("Homework not found", HttpStatus.NOT_FOUND);
            }

            Homework homework = currentHomework.get();
            agendaItem.setHomework(homework);

        }

        this.agendaItemRepository.save(agendaItem);

        return new ResponseEntity<>(new AgendaItemResponseDto(agendaItem), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/{id}")
    public ResponseEntity<?> createHomework(@PathVariable Long id,
            @RequestBody @Valid AgendaItemUpdateDto data, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
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

        return new ResponseEntity<>(new AgendaItemResponseDto(agendaItem), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAgendaItem(User user, @PathVariable Long id) {
        try {
            agendaItemService.deleteForUser(user, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }


//    @PreAuthorize("hasAuthority('STUDENT')")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteAgendaItem(Principal principal, @PathVariable Long id) {
//        Optional<AgendaItem> currentAgendaItem = this.agendaItemRepository.findById(id);
//
//        if (currentAgendaItem.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda-item not found");
//        }
//
//        User user = this.userService.getByPrincipal(principal);
//
//        AgendaItem agendaItem = currentAgendaItem.get();
//
//        if (user.getId() != agendaItem.getCreatedBy().getId()) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//        this.agendaItemRepository.delete(agendaItem);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/{id}/subscribe")
    public ResponseEntity<AgendaItemResponseDto> subscribe(Principal principal, @PathVariable Long id) {
        Optional<AgendaItem> currentAgendaItem = this.agendaItemRepository.findById(id);

        if (currentAgendaItem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda-item not found");
        }

        User user = this.userService.getByPrincipal(principal);
        AgendaItem agendaItem = currentAgendaItem.get();

        Optional<AgendaItemSubscriber> currentSubscription = agendaItem.getSubscribers().stream()
                .filter(agendaItemSubscriber -> {
                    return agendaItemSubscriber.getSubscriber().getId() == user.getId();
                }).findFirst();

        // unsubscribe if already subscribed
        if (currentSubscription.isPresent()) {
            this.agendaItemSubscriberRepository.delete(currentSubscription.get());
        } else {
            AgendaItemSubscriber agendaItemSubscriber = new AgendaItemSubscriber();

            agendaItemSubscriber.setAgendaItem(agendaItem);
            agendaItemSubscriber.setSubscriber(user);

            this.agendaItemSubscriberRepository.save(agendaItemSubscriber);
        }

        return new ResponseEntity<>(new AgendaItemResponseDto(agendaItem), HttpStatus.OK);
    }
}
