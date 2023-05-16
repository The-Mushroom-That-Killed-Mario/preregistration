package org.mushroom.repositories;

import org.junit.jupiter.api.Test;
import org.mushroom.entity.DaySchedule;
import org.mushroom.repository.BreakRepository;
import org.mushroom.repository.DayScheduleRepository;
import org.mushroom.repository.RoleRepository;
import org.mushroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RepositoriesTests {
    @Autowired
    BreakRepository breakRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    DayScheduleRepository dayScheduleRepository;


    @Test
    void testUserRepository(){

    }

    @Test
    void testRoleRepository(){

    }
//--

    @Test
    void testDayScheduleRepository(){
        System.out.println(dayScheduleRepository.findAll());
//        dayScheduleRepository.findAll().forEach(x-> System.out.println(x.getBreaks().toString()));

    }
    @Test
    void testBreakRepository(){
        System.out.println();
    }


}
