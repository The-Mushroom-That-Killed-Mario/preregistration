package org.mushroom;

import org.junit.jupiter.api.Test;
import org.mushroom.repository.BreakRepository;
import org.mushroom.repository.RoleRepository;
import org.mushroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootApplicationTests {
    @Autowired
    BreakRepository breakRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Test
    void contextLoads() {
        System.out.println("context is up");
    }

    @Test
    void testRepos(){
////        System.out.println(breakRepository.findById(1L));
//        System.out.println("-----");
//        System.out.println(userRepository.findByRoleId(1));
//        System.out.println("-----");
////        System.out.println(roleRepository.findAll());
//        System.out.println("-----");


    }
}
