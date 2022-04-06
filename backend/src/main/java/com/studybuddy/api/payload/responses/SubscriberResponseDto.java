package com.studybuddy.api.payload.responses;

import java.util.Date;

import com.studybuddy.api.entity.AgendaItemSubscriber;

import lombok.Data;

@Data
public class SubscriberResponseDto {
    private long id;
    UserResponseDto subscriber;
    private Date createdAt;

    public SubscriberResponseDto(AgendaItemSubscriber agendaSubscriber) {
        this.setId(agendaSubscriber.getId());
        this.setSubscriber(new UserResponseDto(agendaSubscriber.getSubscriber()));
        this.setCreatedAt(agendaSubscriber.getCreatedAt());
    }
}
