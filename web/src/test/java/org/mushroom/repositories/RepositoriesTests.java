package org.mushroom.repositories;

import org.junit.jupiter.api.Test;
import org.mushroom.entity.AdvancedAppointment;
import org.mushroom.entity.DaySchedule;
import org.mushroom.entity.Role;
import org.mushroom.entity.Service;
import org.mushroom.entity.Terminal;
import org.mushroom.entity.User;
import org.mushroom.repository.AdvancedAppointmentRepository;
import org.mushroom.repository.BreakRepository;
import org.mushroom.repository.DayScheduleRepository;
import org.mushroom.repository.RoleRepository;
import org.mushroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

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
    @Autowired
    AdvancedAppointmentRepository advancedAppointmentRepository;

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
    void createDataTable(){


        advancedAppointmentRepository.save(new AdvancedAppointment()
                .builder()
                .user(new User().builder()
                        .name("")
                        .surname("")
                        .login("")
                        .password("")
                        .phoneNumber("")
                        .email("")
                        .created(LocalDateTime.now())
                        .changed(LocalDateTime.now())
                        .deleted(LocalDateTime.now())
                        .build()
                )
                .terminal(
                        new Terminal().builder()
                                .name("")
                                .ipAddress("")
                                .physicalAddress("")
                                .port((short) 3)
                                .created(LocalDateTime.now())
                                .changed(LocalDateTime.now())
                                .deleted(LocalDateTime.now())
                                .build()
                )
                .service(
                        new Service().builder()
                                .name("")
                                .duration(10)
                                .created(LocalDateTime.now())
                                .changed(LocalDateTime.now())
                                .deleted(LocalDateTime.now())
                                .build()
                )
                .date(LocalDate.now())
                .timeFrom(LocalTime.now())
                .timeTo(LocalTime.now())
                .created(LocalDateTime.now())
                .changed(LocalDateTime.now())
                .isActual(true)
                .build());
    }


}
