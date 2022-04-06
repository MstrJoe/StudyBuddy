package com.studybuddy.api.payload.responses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.studybuddy.api.entity.AgendaItem;

@Data
public class AgendaItemResponseDto implements Serializable {
    private long id;
    private String title;
    private Date moment;
    private String description;
    private String link;
    private HomeworkWithSubjectResponseDto homework;
    private UserResponseDto createdBy;
    private Set<UserResponseDto> subscribers;
    private Date createdAt;
    private Date updatedAt;

    public AgendaItemResponseDto(AgendaItem entity) {
        this.setId(entity.getId());
        this.setTitle(entity.getTitle());
        this.setMoment(entity.getMoment());
        this.setDescription(entity.getDescription());
        this.setLink(entity.getLink());
        this.setHomework(new HomeworkWithSubjectResponseDto(entity.getHomework()));
        this.setCreatedBy(new UserResponseDto(entity.getCreatedBy()));
        this.setSubscribers(
                entity.getSubscribers().stream().map(item -> new UserResponseDto(item)).collect(Collectors.toSet()));
    }
}
