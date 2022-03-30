package com.studybuddy.api.entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "homework")
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date deadline;

    @Column(columnDefinition="TEXT")
    private String description;
    private String link;

    @ManyToOne() //A relationship is a connection between two types of entities.
    // In the case of a many-to-many relationship, both sides can relate to multiple instances of the other side.
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
