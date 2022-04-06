package com.studybuddy.api.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

// inspiration: https://www.baeldung.com/jpa-many-to-many
@Getter
@Setter
@Entity
@Table(name = "agenda_items_subscribers")
public class AgendaItemSubscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User subscriber;

    @ManyToOne
    @JoinColumn(name = "agenda_item_id")
    AgendaItem agendaItem;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
}
