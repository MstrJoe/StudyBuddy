package com.studybuddy.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.payload.input.HomeworkDto;
import com.studybuddy.api.payload.input.SubjectDto;
import com.studybuddy.api.payload.responses.SubjectHomeworkResponseDto;
import com.studybuddy.api.payload.responses.SubjectResponseDto;
import com.studybuddy.api.repository.HomeworkRepository;
import com.studybuddy.api.repository.SubjectRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SubjectService.class})
@ExtendWith(SpringExtension.class)
class SubjectServiceTest {
    @MockBean
    private HomeworkRepository homeworkRepository;

    @MockBean
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectService subjectService;

    @Test
    void testFindCollection() {
        when(this.subjectRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.subjectService.findCollection().isEmpty());
        verify(this.subjectRepository).findAll();
    }


    @Test
    void testFindById() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        subject.setCreatedAt(fromResult);
        ArrayList<Homework> homeworkList = new ArrayList<>();
        subject.setHomework(homeworkList);
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        subject.setUpdatedAt(fromResult1);
        Optional<Subject> ofResult = Optional.of(subject);
        when(this.subjectRepository.findById((Long) any())).thenReturn(ofResult);
        SubjectResponseDto actualFindByIdResult = this.subjectService.findById(123L);
        assertSame(fromResult, actualFindByIdResult.getCreatedAt());
        assertSame(fromResult1, actualFindByIdResult.getUpdatedAt());
        assertEquals("Name", actualFindByIdResult.getName());
        assertEquals(123L, actualFindByIdResult.getId());
        assertEquals(homeworkList, actualFindByIdResult.getHomework());
        verify(this.subjectRepository).findById((Long) any());
    }


    @Test
    void testFindById2() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework = new Homework();
        homework.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setDeadline(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        homework.setDescription("The characteristics of someone or something");
        homework.setId(123L);
        homework.setLink("Link");
        homework.setName("Name");
        homework.setSubject(subject);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));

        ArrayList<Homework> homeworkList = new ArrayList<>();
        homeworkList.add(homework);

        Subject subject1 = new Subject();
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        subject1.setCreatedAt(fromResult);
        subject1.setHomework(homeworkList);
        subject1.setId(123L);
        subject1.setName("Name");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant());
        subject1.setUpdatedAt(fromResult1);
        Optional<Subject> ofResult = Optional.of(subject1);
        when(this.subjectRepository.findById((Long) any())).thenReturn(ofResult);
        SubjectResponseDto actualFindByIdResult = this.subjectService.findById(123L);
        assertSame(fromResult, actualFindByIdResult.getCreatedAt());
        assertSame(fromResult1, actualFindByIdResult.getUpdatedAt());
        assertEquals("Name", actualFindByIdResult.getName());
        assertEquals(123L, actualFindByIdResult.getId());
        assertEquals(1, actualFindByIdResult.getHomework().size());
        verify(this.subjectRepository).findById((Long) any());
    }

    @Test
    void testFindById3() throws Exception {
        when(this.subjectRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> this.subjectService.findById(123L));
        verify(this.subjectRepository).findById((Long) any());
    }

    @Test
    void testFindById4() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework = new Homework();
        homework.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setDeadline(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        homework.setDescription("The characteristics of someone or something");
        homework.setId(123L);
        homework.setLink("Link");
        homework.setName("Name");
        homework.setSubject(subject);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));

        Subject subject1 = new Subject();
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject1.setCreatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        subject1.setHomework(new ArrayList<>());
        subject1.setId(123L);
        subject1.setName("Name");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject1.setUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework1 = new Homework();
        homework1.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework1.setCreatedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework1.setDeadline(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        homework1.setDescription("The characteristics of someone or something");
        homework1.setId(123L);
        homework1.setLink("Link");
        homework1.setName("Name");
        homework1.setSubject(subject1);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework1.setUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));

        ArrayList<Homework> homeworkList = new ArrayList<>();
        homeworkList.add(homework1);
        homeworkList.add(homework);

        Subject subject2 = new Subject();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        subject2.setCreatedAt(fromResult);
        subject2.setHomework(homeworkList);
        subject2.setId(123L);
        subject2.setName("Name");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        subject2.setUpdatedAt(fromResult1);
        Optional<Subject> ofResult = Optional.of(subject2);
        when(this.subjectRepository.findById((Long) any())).thenReturn(ofResult);
        SubjectResponseDto actualFindByIdResult = this.subjectService.findById(123L);
        assertSame(fromResult, actualFindByIdResult.getCreatedAt());
        assertSame(fromResult1, actualFindByIdResult.getUpdatedAt());
        assertEquals("Name", actualFindByIdResult.getName());
        assertEquals(123L, actualFindByIdResult.getId());
        assertEquals(2, actualFindByIdResult.getHomework().size());
        verify(this.subjectRepository).findById((Long) any());
    }

    @Test
    void testPostSubject() {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        ArrayList<Homework> homeworkList = new ArrayList<>();
        subject.setHomework(homeworkList);
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.subjectRepository.save((Subject) any())).thenReturn(subject);

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        SubjectResponseDto actualPostSubjectResult = this.subjectService.postSubject(subjectDto);
        assertNull(actualPostSubjectResult.getCreatedAt());
        assertNull(actualPostSubjectResult.getUpdatedAt());
        assertEquals("Name", actualPostSubjectResult.getName());
        assertEquals(0L, actualPostSubjectResult.getId());
        assertEquals(homeworkList, actualPostSubjectResult.getHomework());
        verify(this.subjectRepository).save((Subject) any());
    }

    @Test
    void testPutSubject() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        subject.setCreatedAt(fromResult);
        ArrayList<Homework> homeworkList = new ArrayList<>();
        subject.setHomework(homeworkList);
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        subject.setUpdatedAt(fromResult1);
        Optional<Subject> ofResult = Optional.of(subject);

        Subject subject1 = new Subject();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        subject1.setHomework(new ArrayList<>());
        subject1.setId(123L);
        subject1.setName("Name");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.subjectRepository.save((Subject) any())).thenReturn(subject1);
        when(this.subjectRepository.findById((Long) any())).thenReturn(ofResult);

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        SubjectResponseDto actualPutSubjectResult = this.subjectService.putSubject(123L, subjectDto);
        assertSame(fromResult, actualPutSubjectResult.getCreatedAt());
        assertSame(fromResult1, actualPutSubjectResult.getUpdatedAt());
        assertEquals("Name", actualPutSubjectResult.getName());
        assertEquals(123L, actualPutSubjectResult.getId());
        assertEquals(homeworkList, actualPutSubjectResult.getHomework());
        verify(this.subjectRepository).save((Subject) any());
        verify(this.subjectRepository).findById((Long) any());
    }

    @Test
    void testPutSubject2() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework = new Homework();
        homework.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setDeadline(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        homework.setDescription("The characteristics of someone or something");
        homework.setId(123L);
        homework.setLink("Link");
        homework.setName("Name");
        homework.setSubject(subject);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));

        ArrayList<Homework> homeworkList = new ArrayList<>();
        homeworkList.add(homework);

        Subject subject1 = new Subject();
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        subject1.setCreatedAt(fromResult);
        subject1.setHomework(homeworkList);
        subject1.setId(123L);
        subject1.setName("Name");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant());
        subject1.setUpdatedAt(fromResult1);
        Optional<Subject> ofResult = Optional.of(subject1);

        Subject subject2 = new Subject();
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject2.setCreatedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        subject2.setHomework(new ArrayList<>());
        subject2.setId(123L);
        subject2.setName("Name");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject2.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.subjectRepository.save((Subject) any())).thenReturn(subject2);
        when(this.subjectRepository.findById((Long) any())).thenReturn(ofResult);

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        SubjectResponseDto actualPutSubjectResult = this.subjectService.putSubject(123L, subjectDto);
        assertSame(fromResult, actualPutSubjectResult.getCreatedAt());
        assertSame(fromResult1, actualPutSubjectResult.getUpdatedAt());
        assertEquals("Name", actualPutSubjectResult.getName());
        assertEquals(123L, actualPutSubjectResult.getId());
        assertEquals(1, actualPutSubjectResult.getHomework().size());
        verify(this.subjectRepository).save((Subject) any());
        verify(this.subjectRepository).findById((Long) any());
    }


    @Test
    void testPutSubject3() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.subjectRepository.save((Subject) any())).thenReturn(subject);
        when(this.subjectRepository.findById((Long) any())).thenReturn(Optional.empty());

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        assertThrows(Exception.class, () -> this.subjectService.putSubject(123L, subjectDto));
        verify(this.subjectRepository).findById((Long) any());
    }


    @Test
    void testPutSubject4() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework = new Homework();
        homework.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setDeadline(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        homework.setDescription("The characteristics of someone or something");
        homework.setId(123L);
        homework.setLink("Link");
        homework.setName("Name");
        homework.setSubject(subject);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));

        Subject subject1 = new Subject();
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject1.setCreatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        subject1.setHomework(new ArrayList<>());
        subject1.setId(123L);
        subject1.setName("Name");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject1.setUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework1 = new Homework();
        homework1.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework1.setCreatedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework1.setDeadline(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        homework1.setDescription("The characteristics of someone or something");
        homework1.setId(123L);
        homework1.setLink("Link");
        homework1.setName("Name");
        homework1.setSubject(subject1);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework1.setUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));

        ArrayList<Homework> homeworkList = new ArrayList<>();
        homeworkList.add(homework1);
        homeworkList.add(homework);

        Subject subject2 = new Subject();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        subject2.setCreatedAt(fromResult);
        subject2.setHomework(homeworkList);
        subject2.setId(123L);
        subject2.setName("Name");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant());
        subject2.setUpdatedAt(fromResult1);
        Optional<Subject> ofResult = Optional.of(subject2);

        Subject subject3 = new Subject();
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject3.setCreatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        subject3.setHomework(new ArrayList<>());
        subject3.setId(123L);
        subject3.setName("Name");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject3.setUpdatedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.subjectRepository.save((Subject) any())).thenReturn(subject3);
        when(this.subjectRepository.findById((Long) any())).thenReturn(ofResult);

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        SubjectResponseDto actualPutSubjectResult = this.subjectService.putSubject(123L, subjectDto);
        assertSame(fromResult, actualPutSubjectResult.getCreatedAt());
        assertSame(fromResult1, actualPutSubjectResult.getUpdatedAt());
        assertEquals("Name", actualPutSubjectResult.getName());
        assertEquals(123L, actualPutSubjectResult.getId());
        assertEquals(2, actualPutSubjectResult.getHomework().size());
        verify(this.subjectRepository).save((Subject) any());
        verify(this.subjectRepository).findById((Long) any());
    }


    @Test
    void testDeleteSubject() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Subject> ofResult = Optional.of(subject);
        doNothing().when(this.subjectRepository).delete((Subject) any());
        when(this.subjectRepository.findById((Long) any())).thenReturn(ofResult);
        this.subjectService.deleteSubject(123L);
        verify(this.subjectRepository).findById((Long) any());
        verify(this.subjectRepository).delete((Subject) any());
    }


    @Test
    void testDeleteSubject2() throws Exception {
        doNothing().when(this.subjectRepository).delete((Subject) any());
        when(this.subjectRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> this.subjectService.deleteSubject(123L));
        verify(this.subjectRepository).findById((Long) any());
    }


    @Test
    void testCreateSubjectHomework() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Subject> ofResult = Optional.of(subject);
        when(this.subjectRepository.findById((Long) any())).thenReturn(ofResult);

        Subject subject1 = new Subject();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        subject1.setHomework(new ArrayList<>());
        subject1.setId(123L);
        subject1.setName("Name");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework = new Homework();
        homework.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setCreatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setDeadline(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        homework.setDescription("The characteristics of someone or something");
        homework.setId(123L);
        homework.setLink("Link");
        homework.setName("Name");
        homework.setSubject(subject1);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.homeworkRepository.save((Homework) any())).thenReturn(homework);

        HomeworkDto homeworkDto = new HomeworkDto();
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant());
        homeworkDto.setDeadline(fromResult);
        homeworkDto.setDescription("The characteristics of someone or something");
        homeworkDto.setLink("Link");
        homeworkDto.setName("Name");
        SubjectHomeworkResponseDto actualCreateSubjectHomeworkResult = this.subjectService.createSubjectHomework(123L,
                homeworkDto);
        assertNull(actualCreateSubjectHomeworkResult.getCreatedAt());
        assertNull(actualCreateSubjectHomeworkResult.getUpdatedAt());
        assertEquals("Name", actualCreateSubjectHomeworkResult.getName());
        assertEquals("Link", actualCreateSubjectHomeworkResult.getLink());
        assertEquals(0L, actualCreateSubjectHomeworkResult.getId());
        assertEquals("The characteristics of someone or something", actualCreateSubjectHomeworkResult.getDescription());
        assertSame(fromResult, actualCreateSubjectHomeworkResult.getDeadline());
        verify(this.subjectRepository).findById((Long) any());
        verify(this.homeworkRepository).save((Homework) any());
    }


    @Test
    void testCreateSubjectHomework2() throws Exception {
        when(this.subjectRepository.findById((Long) any())).thenReturn(Optional.empty());

        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework = new Homework();
        homework.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setDeadline(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        homework.setDescription("The characteristics of someone or something");
        homework.setId(123L);
        homework.setLink("Link");
        homework.setName("Name");
        homework.setSubject(subject);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.homeworkRepository.save((Homework) any())).thenReturn(homework);

        HomeworkDto homeworkDto = new HomeworkDto();
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homeworkDto.setDeadline(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        homeworkDto.setDescription("The characteristics of someone or something");
        homeworkDto.setLink("Link");
        homeworkDto.setName("Name");
        assertThrows(Exception.class, () -> this.subjectService.createSubjectHomework(123L, homeworkDto));
        verify(this.subjectRepository).findById((Long) any());
    }

}

