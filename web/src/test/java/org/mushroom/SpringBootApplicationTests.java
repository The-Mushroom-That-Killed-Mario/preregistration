package org.mushroom;

import org.junit.jupiter.api.Test;
import org.mushroom.repository.BreakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootApplicationTests {
    @Autowired
    BreakRepository breakRepository;

    @Test
    void contextLoads() {
        System.out.println("context is up");
    }

    @Test
    void testRepos(){
        System.out.println(breakRepository.findAll());
    }
}
