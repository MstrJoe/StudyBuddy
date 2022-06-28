package com.studybuddy.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.studybuddy.api.payload.responses.HomeworkSubjectResponseDto;
import com.studybuddy.api.payload.responses.HomeworkWithSubjectResponseDto;
import com.studybuddy.api.repository.HomeworkRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HomeworkService.class})
@ExtendWith(SpringExtension.class)
class HomeworkServiceTest {
    @MockBean
    private HomeworkRepository homeworkRepository;

    @Autowired
    private HomeworkService homeworkService;

    @Test
    void testFindCollection() {
        when(this.homeworkRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.homeworkService.findCollection().isEmpty());
        verify(this.homeworkRepository).findAll();
    }

    @Test
    void testFindCollection2() {
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
        when(this.homeworkRepository.findAll()).thenReturn(homeworkList);
        assertEquals(1, this.homeworkService.findCollection().size());
        verify(this.homeworkRepository).findAll();
    }


    @Test
    void testFindCollection3() {
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
        when(this.homeworkRepository.findAll()).thenReturn(homeworkList);
        assertEquals(2, this.homeworkService.findCollection().size());
        verify(this.homeworkRepository).findAll();
    }


    @Test
    void testFindById() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        subject.setCreatedAt(fromResult);
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        subject.setUpdatedAt(fromResult1);

        Homework homework = new Homework();
        homework.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        homework.setCreatedAt(fromResult2);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult3 = Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        homework.setDeadline(fromResult3);
        homework.setDescription("The characteristics of someone or something");
        homework.setId(123L);
        homework.setLink("Link");
        homework.setName("Name");
        homework.setSubject(subject);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult4 = Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        homework.setUpdatedAt(fromResult4);
        Optional<Homework> ofResult = Optional.of(homework);
        when(this.homeworkRepository.findById((Long) any())).thenReturn(ofResult);
        HomeworkWithSubjectResponseDto actualFindByIdResult = this.homeworkService.findById(123L);
        assertSame(fromResult2, actualFindByIdResult.getCreatedAt());
        assertSame(fromResult4, actualFindByIdResult.getUpdatedAt());
        assertEquals("Name", actualFindByIdResult.getName());
        assertSame(fromResult3, actualFindByIdResult.getDeadline());
        assertEquals(123L, actualFindByIdResult.getId());
        assertEquals("Link", actualFindByIdResult.getLink());
        assertEquals("The characteristics of someone or something", actualFindByIdResult.getDescription());
        HomeworkSubjectResponseDto subject1 = actualFindByIdResult.getSubject();
        assertEquals("Name", subject1.getName());
        assertSame(fromResult1, subject1.getUpdatedAt());
        assertSame(fromResult, subject1.getCreatedAt());
        assertEquals(123L, subject1.getId());
        verify(this.homeworkRepository).findById((Long) any());
    }

    @Test
    void testUpdate() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        subject.setCreatedAt(fromResult);
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        subject.setUpdatedAt(fromResult1);

        Homework homework = new Homework();
        homework.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        homework.setCreatedAt(fromResult2);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setDeadline(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        homework.setDescription("The characteristics of someone or something");
        homework.setId(123L);
        homework.setLink("Link");
        homework.setName("Name");
        homework.setSubject(subject);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult3 = Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        homework.setUpdatedAt(fromResult3);
        Optional<Homework> ofResult = Optional.of(homework);

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
        when(this.homeworkRepository.save((Homework) any())).thenReturn(homework1);
        when(this.homeworkRepository.findById((Long) any())).thenReturn(ofResult);

        HomeworkDto homeworkDto = new HomeworkDto();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult4 = Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant());
        homeworkDto.setDeadline(fromResult4);
        homeworkDto.setDescription("The characteristics of someone or something");
        homeworkDto.setLink("Link");
        homeworkDto.setName("Name");
        HomeworkWithSubjectResponseDto actualUpdateResult = this.homeworkService.update(123L, homeworkDto);
        assertSame(fromResult2, actualUpdateResult.getCreatedAt());
        assertSame(fromResult3, actualUpdateResult.getUpdatedAt());
        assertEquals("Name", actualUpdateResult.getName());
        assertSame(fromResult4, actualUpdateResult.getDeadline());
        assertEquals(123L, actualUpdateResult.getId());
        assertEquals("Link", actualUpdateResult.getLink());
        assertEquals("The characteristics of someone or something", actualUpdateResult.getDescription());
        HomeworkSubjectResponseDto subject2 = actualUpdateResult.getSubject();
        assertEquals("Name", subject2.getName());
        assertSame(fromResult1, subject2.getUpdatedAt());
        assertSame(fromResult, subject2.getCreatedAt());
        assertEquals(123L, subject2.getId());
        verify(this.homeworkRepository).save((Homework) any());
        verify(this.homeworkRepository).findById((Long) any());
    }

    @Test
    void testDelete() throws Exception {
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
        Optional<Homework> ofResult = Optional.of(homework);
        doNothing().when(this.homeworkRepository).delete((Homework) any());
        when(this.homeworkRepository.findById((Long) any())).thenReturn(ofResult);
        this.homeworkService.delete(123L);
        verify(this.homeworkRepository).findById((Long) any());
        verify(this.homeworkRepository).delete((Homework) any());
    }

    @Test
    void testDelete2() throws Exception {
        doNothing().when(this.homeworkRepository).delete((Homework) any());
        when(this.homeworkRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> this.homeworkService.delete(123L));
        verify(this.homeworkRepository).findById((Long) any());
    }
}

