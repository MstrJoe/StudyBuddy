package com.studybuddy.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.studybuddy.api.entity.Role;
import com.studybuddy.api.payload.responses.RoleResponseDto;
import com.studybuddy.api.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoleService.class})
@ExtendWith(SpringExtension.class)
class RoleServiceTest {
    @MockBean
    private FileUploadService fileUploadService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Test
    void testGetCollection() {
        when(this.roleRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.roleService.getCollection().isEmpty());
        verify(this.roleRepository).findAll();
    }

    @Test
    void testGetCollection2() {
        Role role = new Role();
        role.setDisplayName("Display Name");
        role.setId(123L);
        role.setName("Name");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        when(this.roleRepository.findAll()).thenReturn(roleList);
        List<RoleResponseDto> actualCollection = this.roleService.getCollection();
        assertEquals(1, actualCollection.size());
        RoleResponseDto getResult = actualCollection.get(0);
        assertEquals("Display Name", getResult.getDisplayName());
        assertEquals("Name", getResult.getName());
        assertEquals(123L, getResult.getId());
        verify(this.roleRepository).findAll();
    }

    @Test
    void testGetCollection3() {
        Role role = new Role();
        role.setDisplayName("Display Name");
        role.setId(123L);
        role.setName("Name");

        Role role1 = new Role();
        role1.setDisplayName("Display Name");
        role1.setId(123L);
        role1.setName("Name");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role1);
        roleList.add(role);
        when(this.roleRepository.findAll()).thenReturn(roleList);
        List<RoleResponseDto> actualCollection = this.roleService.getCollection();
        assertEquals(2, actualCollection.size());
        RoleResponseDto getResult = actualCollection.get(0);
        assertEquals("Name", getResult.getName());
        RoleResponseDto getResult1 = actualCollection.get(1);
        assertEquals("Name", getResult1.getName());
        assertEquals(123L, getResult1.getId());
        assertEquals("Display Name", getResult1.getDisplayName());
        assertEquals(123L, getResult.getId());
        assertEquals("Display Name", getResult.getDisplayName());
        verify(this.roleRepository).findAll();
    }
}

