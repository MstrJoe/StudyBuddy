package com.studybuddy.api.service;

import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.payload.input.HomeworkDto;
import com.studybuddy.api.payload.input.SubjectDto;
import com.studybuddy.api.payload.responses.SubjectHomeworkResponseDto;
import com.studybuddy.api.payload.responses.SubjectResponseDto;
import com.studybuddy.api.repository.HomeworkRepository;
import com.studybuddy.api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectService {


    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private HomeworkRepository homeworkRepository;

    public List<SubjectResponseDto> findCollection() {
        return this.subjectRepository.findAll().stream().map(SubjectResponseDto::new).collect(Collectors.toList());
    }

    public SubjectResponseDto findById(Long id) throws Exception {
        Optional<Subject> currentSubject = this.subjectRepository.findById(id);

        if (currentSubject.isEmpty()) {
            throw new Exception("Subject not found");
        }

        Subject subject = currentSubject.get();

        return new SubjectResponseDto(subject);
    }

    public SubjectResponseDto postSubject(SubjectDto data) {
        Subject subject = new Subject();
        subject.setName(data.getName());
        subjectRepository.save(subject);

        return new SubjectResponseDto(subject);
    }

    public SubjectResponseDto putSubject(Long id, SubjectDto data) throws Exception {
        Optional<Subject> currentSubject = this.subjectRepository.findById(id);

        if (currentSubject.isEmpty()) {
            throw new Exception("Subject not found");
        }

        Subject subject = currentSubject.get();
        subject.setName(data.getName());
        this.subjectRepository.save(subject);

        return new SubjectResponseDto(subject);
    }

    public void deleteSubject(Long id) throws Exception {
        Optional<Subject> currentSubject = this.subjectRepository.findById(id);

        if (currentSubject.isEmpty()) {
            throw new Exception("Subject not found");
        }

        Subject subject = currentSubject.get();
        this.subjectRepository.delete(subject);

        return;

    }

    public SubjectHomeworkResponseDto createSubjectHomework(Long subjectId, HomeworkDto homeworkData) throws Exception {

        Optional<Subject> currentSubject = this.subjectRepository.findById(subjectId);

        if (currentSubject.isEmpty()) {
            throw new Exception("item not found");
        }
        Subject subject = currentSubject.get();
        Homework homework = new Homework();
        homework.setName(homeworkData.getName());
        homework.setDeadline(homeworkData.getDeadline());
        homework.setDescription(homeworkData.getDescription());
        homework.setLink(homeworkData.getLink());
        homework.setSubject(subject);
        this.homeworkRepository.save(homework);

        return new SubjectHomeworkResponseDto(homework);

    }


}
