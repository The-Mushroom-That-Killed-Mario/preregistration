//package org.mushroom.repositories;
//
//import org.junit.jupiter.api.Test;
//import org.mushroom.model.AdvancedAppointment;
//import org.mushroom.model.Service;
//import org.mushroom.model.Terminal;
//import org.mushroom.model.User;
//import org.mushroom.repository.AdvancedAppointmentRepository;
//import org.mushroom.repository.BreakRepository;
//import org.mushroom.repository.DayScheduleRepository;
//import org.mushroom.repository.RoleRepository;
//import org.mushroom.repository.ServiceRepository;
//import org.mushroom.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//@SpringBootTest
//class RepositoriesTests {
//    @Autowired
//    BreakRepository breakRepository;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    RoleRepository roleRepository;
//    @Autowired
//    DayScheduleRepository dayScheduleRepository;
//    @Autowired
//    AdvancedAppointmentRepository advancedAppointmentRepository;
//
//    @Test
//    void testUserRepository() {
//    }
//
//    @Test
//    void testRoleRepository() {
////        System.out.println(breakRepository.findAll().get(0).);
//    }
////--
//
//    @Test
//    void testDayScheduleRepository() {
//        System.out.println(dayScheduleRepository.findAll());
////        dayScheduleRepository.findAll().forEach(x-> System.out.println(x.getBreaks().toString()));
//
//    }
//
//    @Test
//    void createDataTable() {
//        int n = 1;
//        advancedAppointmentRepository.save(new AdvancedAppointment()
//                .builder()
//                .user(new User().builder()
//                        .name("Ivan" + n)
//                        .surname("Petrov" + n)
//                        .login("loginIvana" + n)
//                        .password("pass" + n)
//                        .phoneNumber("111111111" + n)
//                        .email("ivan" + n + "@mail.ru")
//                        .created(LocalDateTime.now())
//                        .changed(LocalDateTime.now())
//                        .deleted(LocalDateTime.now())
//                        .build()
//                )
//                .terminal(
//                        new Terminal().builder()
//                                .name("Terminal 1")
//                                .ipAddress("10.0.1." + n)
//                                .physicalAddress("Minsk, ul kosmonavtov " + n)
//                                .port((short) n)
//                                .created(LocalDateTime.now())
//                                .changed(LocalDateTime.now())
//                                .deleted(LocalDateTime.now())
//                                .build()
//                )
//                .service(
//                        new Service().builder()
//                                .name("Получение денежных переводов")
//                                .duration(10)
//                                .created(LocalDateTime.now())
//                                .changed(LocalDateTime.now())
//                                .deleted(LocalDateTime.now())
//                                .build()
//                )
//                .date(LocalDate.now())
//                .timeFrom(LocalTime.now())
//                .timeTo(LocalTime.now())
//                .created(LocalDateTime.now())
//                .changed(LocalDateTime.now())
//                .isActual(true)
//                .build());
//    }
//
//    @Autowired
//    ServiceRepository serviceRepository;
//    @Test
//    void testService() {
//
//        serviceRepository.save(new Service().builder()
//                .name("Получение денежных переводов")
//                .duration(10)
//                .created(LocalDateTime.now())
//                .changed(LocalDateTime.now())
//                .deleted(LocalDateTime.now())
//                .build());
//
//    }
//}
