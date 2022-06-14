package com.studybuddy.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "agenda_item")
public class AgendaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Date moment;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String link;

    @ManyToOne(optional = true)
    @JoinColumn(name = "homework_id")
    private Homework homework;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User createdBy;

    @OneToMany(mappedBy = "agendaItem")
    Set<AgendaItemSubscriber> subscribers = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

}
