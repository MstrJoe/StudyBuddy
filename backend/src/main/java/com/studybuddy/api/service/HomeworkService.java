package com.studybuddy.api.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.payload.input.HomeworkDto;
import com.studybuddy.api.payload.responses.HomeworkWithSubjectResponseDto;
import com.studybuddy.api.repository.HomeworkRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;

    public List<HomeworkWithSubjectResponseDto> findCollection() {
        List<HomeworkWithSubjectResponseDto> collection = this.homeworkRepository.findAll().stream()
                .map(item -> new HomeworkWithSubjectResponseDto(item)).collect(Collectors.toList());

        return collection;
    }

    public HomeworkWithSubjectResponseDto findById(Long id) throws Exception {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(id);

        if (currentHomework.isEmpty()) {
            throw new Exception("Homework not found");
        }

        Homework homework = currentHomework.get();

        return new HomeworkWithSubjectResponseDto(homework);
    }

    public HomeworkWithSubjectResponseDto update(Long id, HomeworkDto data) throws Exception {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(id);

        if (currentHomework.isEmpty()) {
            throw new Exception("Homework not found");
        }

        Homework homework = currentHomework.get();
        homework.setName(data.getName());
        homework.setDeadline(data.getDeadline());
        homework.setDescription(data.getDescription());
        homework.setLink(data.getLink());
        this.homeworkRepository.save(homework);

        return new HomeworkWithSubjectResponseDto(homework);
    }

    public void delete(Long id) throws Exception {
        Optional<Homework> currentHomework = this.homeworkRepository.findById(id);

        if (currentHomework.isEmpty()) {
            throw new Exception("Homework not found");
        }

        Homework homework = currentHomework.get();
        this.homeworkRepository.delete(homework);

        return;
    }
}
