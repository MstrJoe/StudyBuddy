package com.studybuddy.api.service;

import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.payload.input.SubjectDto;
import com.studybuddy.api.payload.responses.SubjectResponseDto;
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

    //Putmapping


}
