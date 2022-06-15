package com.studybuddy.api.service;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AgendaItemService {

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    @Autowired
    private AgendaItemSubscriberRepository agendaItemSubscriberRepository;

    public List<AgendaItemResponseDto> getCollection() {
        List<AgendaItemResponseDto> collection = this.agendaItemRepository.findByOrderByMomentAsc().stream()
                .map(item -> new AgendaItemResponseDto(item))
                .collect(Collectors.toList());
        return collection;
    }

    public AgendaItemResponseDto getItemById(Long id) {
        Optional<AgendaItem> currentAgendaItem = this.agendaItemRepository.findById(id);

        if (currentAgendaItem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda-item not found");
        }
        AgendaItem agendaItem = currentAgendaItem.get();

        return new AgendaItemResponseDto(agendaItem);
    }

    public AgendaItemResponseDto createForUser(AgendaItemCreateDto agendaItemDto, User user) throws Exception {
        AgendaItem agendaItem = new AgendaItem();
        agendaItem.setTitle(agendaItemDto.getTitle());
        agendaItem.setMoment(agendaItemDto.getMoment());
        agendaItem.setDescription(agendaItemDto.getDescription());
        agendaItem.setLink(agendaItemDto.getLink());
        agendaItem.setCreatedBy(user);

        if (agendaItemDto.getHomeworkId().isPresent()) {
            Optional<Homework> currentHomework = this.homeworkRepository.findById(agendaItemDto.getHomeworkId().get());

            if (currentHomework.isEmpty()) {
                throw new Exception("Homework not found");
            }

            Homework homework = currentHomework.get();
            agendaItem.setHomework(homework);

        }

        this.agendaItemRepository.save(agendaItem);

        return new AgendaItemResponseDto(agendaItem);
    }

    public AgendaItem create(Long id, AgendaItemUpdateDto data) throws Exception {

        Optional<AgendaItem> currentAgendaItem = this.agendaItemRepository.findById(id);

        if (currentAgendaItem.isEmpty()) {
            throw new Exception("item not found");
        }

        AgendaItem agendaItem = currentAgendaItem.get();
        agendaItem.setTitle(data.getTitle());
        agendaItem.setMoment(data.getMoment());
        agendaItem.setDescription(data.getDescription());
        agendaItem.setLink(data.getLink());
        this.agendaItemRepository.save(agendaItem);

        return agendaItem;
    }

    public void deleteForUser(User user, Long id) throws Exception {
        Optional<AgendaItem> currentAgendaItem = this.agendaItemRepository.findById(id);

        if (currentAgendaItem.isEmpty()) {
            throw new Exception("Agenda-item not found");
        }

        AgendaItem agendaItem = currentAgendaItem.get();

        if (user.getId() != agendaItem.getCreatedBy().getId()) {
            throw new Exception("This user is not allowed to delete");
        }

        this.agendaItemRepository.delete(agendaItem);

        return;
    }

    public AgendaItem subscribeForUser(User user, Long id) {
        Optional<AgendaItem> currentAgendaItem = this.agendaItemRepository.findById(id);

        if (currentAgendaItem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda-item not found");
        }

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

        return agendaItem;
    }
}
