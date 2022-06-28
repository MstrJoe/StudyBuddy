package com.studybuddy.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.studybuddy.api.entity.AgendaItem;
import com.studybuddy.api.entity.AgendaItemSubscriber;
import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Role;
import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.entity.User;
import com.studybuddy.api.repository.AgendaItemRepository;
import com.studybuddy.api.repository.AgendaItemSubscriberRepository;
import com.studybuddy.api.repository.HomeworkRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {AgendaItemService.class})
@ExtendWith(SpringExtension.class)
class AgendaItemServiceTest {
    @MockBean
    private AgendaItemRepository agendaItemRepository;

    @Autowired
    private AgendaItemService agendaItemService;

    @MockBean
    private AgendaItemSubscriberRepository agendaItemSubscriberRepository;

    @MockBean
    private HomeworkRepository homeworkRepository;


    @Test
    void testGetCollection() {
        when(this.agendaItemRepository.findByOrderByMomentDesc()).thenReturn(new ArrayList<>());
        assertTrue(this.agendaItemService.getCollection().isEmpty());
        verify(this.agendaItemRepository).findByOrderByMomentDesc();
    }


    @Test
    void testGetCollection2() {
        Role role = new Role();
        role.setDisplayName("Display Name");
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setAgendaItemSubscribed(new HashSet<>());
        user.setAgendaItemsCreated(new ArrayList<>());
        user.setAvatar("Avatar");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole(role);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");

        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

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
        homework.setSubject(subject);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));

        AgendaItem agendaItem = new AgendaItem();
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem.setCreatedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        agendaItem.setCreatedBy(user);
        agendaItem.setDescription("The characteristics of someone or something");
        agendaItem.setHomework(homework);
        agendaItem.setId(123L);
        agendaItem.setLink("Link");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem.setMoment(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        agendaItem.setSubscribers(new HashSet<>());
        agendaItem.setTitle("Dr");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem.setUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));

        ArrayList<AgendaItem> agendaItemList = new ArrayList<>();
        agendaItemList.add(agendaItem);
        when(this.agendaItemRepository.findByOrderByMomentDesc()).thenReturn(agendaItemList);
        assertEquals(1, this.agendaItemService.getCollection().size());
        verify(this.agendaItemRepository).findByOrderByMomentDesc();
    }

    @Test
    void testGetCollection3() {
        when(this.agendaItemRepository.findByOrderByMomentDesc()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, () -> this.agendaItemService.getCollection());
        verify(this.agendaItemRepository).findByOrderByMomentDesc();
    }

    @Test
    void testGetCollection4() {
        Role role = new Role();
        role.setDisplayName("Display Name");
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setAgendaItemSubscribed(new HashSet<>());
        user.setAgendaItemsCreated(new ArrayList<>());
        user.setAvatar("Avatar");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole(role);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");

        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

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
        homework.setSubject(subject);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));

        AgendaItem agendaItem = new AgendaItem();
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem.setCreatedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        agendaItem.setCreatedBy(user);
        agendaItem.setDescription("The characteristics of someone or something");
        agendaItem.setHomework(homework);
        agendaItem.setId(123L);
        agendaItem.setLink("Link");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem.setMoment(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        agendaItem.setSubscribers(new HashSet<>());
        agendaItem.setTitle("Dr");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem.setUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));

        Role role1 = new Role();
        role1.setDisplayName("Display Name");
        role1.setId(123L);
        role1.setName("Name");

        User user1 = new User();
        user1.setAgendaItemSubscribed(new HashSet<>());
        user1.setAgendaItemsCreated(new ArrayList<>());
        user1.setAvatar("Avatar");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setRole(role1);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");

        Subject subject1 = new Subject();
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject1.setCreatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        subject1.setHomework(new ArrayList<>());
        subject1.setId(123L);
        subject1.setName("Name");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject1.setUpdatedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework1 = new Homework();
        homework1.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework1.setCreatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework1.setDeadline(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        homework1.setDescription("The characteristics of someone or something");
        homework1.setId(123L);
        homework1.setLink("Link");
        homework1.setName("Name");
        homework1.setSubject(subject1);
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework1.setUpdatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));

        AgendaItem agendaItem1 = new AgendaItem();
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem1.setCreatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        agendaItem1.setCreatedBy(user1);
        agendaItem1.setDescription("The characteristics of someone or something");
        agendaItem1.setHomework(homework1);
        agendaItem1.setId(123L);
        agendaItem1.setLink("Link");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem1.setMoment(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        agendaItem1.setSubscribers(new HashSet<>());
        agendaItem1.setTitle("Dr");
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem1.setUpdatedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));

        ArrayList<AgendaItem> agendaItemList = new ArrayList<>();
        agendaItemList.add(agendaItem1);
        agendaItemList.add(agendaItem);
        when(this.agendaItemRepository.findByOrderByMomentDesc()).thenReturn(agendaItemList);
        assertEquals(2, this.agendaItemService.getCollection().size());
        verify(this.agendaItemRepository).findByOrderByMomentDesc();
    }


}

