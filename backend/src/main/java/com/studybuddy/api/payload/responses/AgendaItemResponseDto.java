package com.studybuddy.api.payload.responses;

import lombok.Data;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.studybuddy.api.entity.AgendaItem;

@Data
public class AgendaItemResponseDto {
    private long id;
    private String title;
    private Date moment;
    private String description;
    private String link;
    private Optional<HomeworkWithSubjectResponseDto> homework;
    private UserResponseDto createdBy;
    private Set<SubscriberResponseDto> subscribers;
    private Date createdAt;
    private Date updatedAt;

    public AgendaItemResponseDto(AgendaItem entity) {
        this.setId(entity.getId());
        this.setTitle(entity.getTitle());
        this.setMoment(entity.getMoment());
        this.setDescription(entity.getDescription());
        this.setLink(entity.getLink());
        if( entity.getHomework() != null) {
            this.setHomework(Optional.of(new HomeworkWithSubjectResponseDto(entity.getHomework())));
        }
        this.setCreatedBy(new UserResponseDto(entity.getCreatedBy()));
        this.setSubscribers(
                entity.getSubscribers().stream().map(item -> new SubscriberResponseDto(item))
                        .collect(Collectors.toSet()));
        this.setCreatedAt(entity.getCreatedAt());
        this.setUpdatedAt(entity.getUpdatedAt());
    }
}
