package com.studybuddy.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
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

    @ManyToOne()
    @JoinColumn(name = "homework_id")
    private Homework homework;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User createdBy;

    @ManyToMany(mappedBy = "agendaItemsSubscribed")
    Set<User> subscribers = new HashSet<User>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

}
