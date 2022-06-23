package com.studybuddy.api.controller;

import com.studybuddy.api.payload.responses.RoleResponseDto;
import com.studybuddy.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping()
    public ResponseEntity<List<RoleResponseDto>> getRoles() {
        List<RoleResponseDto> collection = this.roleService.getCollection();
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

}