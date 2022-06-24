package com.studybuddy.api.controller;

import com.studybuddy.api.entity.AgendaItem;
import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.input.AgendaItemCreateDto;
import com.studybuddy.api.payload.input.AgendaItemUpdateDto;
import com.studybuddy.api.payload.responses.AgendaItemResponseDto;

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

@RestController
@RequestMapping("/agendaitem")
public class AgendaItemController {
§
    @Autowired
    private UserService userService;

    @Autowired
    private AgendaItemService agendaItemService;


    @GetMapping()
    public ResponseEntity<List<AgendaItemResponseDto>> getAgendaItemCollection() {
        return new ResponseEntity<>(agendaItemService.getCollection(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AgendaItemResponseDto> getAgendaItem(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(agendaItemService.getItemById(id), HttpStatus.OK);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }


    @PostMapping()
    public ResponseEntity<?> createAgendaItem(Principal principal,
                                              @RequestBody @Valid AgendaItemCreateDto agendaItemDto,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getByPrincipal(principal);

        try {
            AgendaItemResponseDto agendaItem = this.agendaItemService.createForUser(agendaItemDto,user);
            return new ResponseEntity<>(agendaItem, HttpStatus.CREATED);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }

    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/{id}")
    public ResponseEntity<?> createAgendaItem(@PathVariable Long id, @RequestBody @Valid AgendaItemUpdateDto data,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(agendaItemService.create(id, data), HttpStatus.OK);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }

    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgendaItem(Principal principal, @PathVariable Long id) {
        try {
            User user = this.userService.getByPrincipal(principal);

            this.agendaItemService.deleteForUser(user, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/{id}/subscribe")
    public ResponseEntity<AgendaItemResponseDto> subscribe(Principal principal, @PathVariable Long id) {
        try {
            User user = this.userService.getByPrincipal(principal);
            AgendaItem agendaItem = this.agendaItemService.subscribeForUser(user, id);
            return new ResponseEntity<>(new AgendaItemResponseDto(agendaItem), HttpStatus.OK);
        } catch (Exception err) {
            throw err;
        }
    }
}
