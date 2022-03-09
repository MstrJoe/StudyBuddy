package com.studybuddy.api.repository;

import com.studybuddy.api.entity.Subject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}