package com.studybuddy.api.seeder;

import com.studybuddy.api.entity.Role;
import com.studybuddy.api.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            this.createRole("TEACHER");
            this.createRole("STUDENT");
        }
    }

    private void createRole(String name) {
        Role role = new Role();
        role.setName(name);
        roleRepository.save(role);
        System.out.println(String.format("Role %s created", name));
    }
}
