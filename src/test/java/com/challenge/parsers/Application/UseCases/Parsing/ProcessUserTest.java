package com.challenge.parsers.Application.UseCases.Parsing;

import com.challenge.parsers.Application.UseCases.Parsing.ProcessUser;
import com.challenge.parsers.DTO.FileContentDTO;
import com.challenge.parsers.core.Domain.Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProcessUserTest {
    private ProcessUser processUser;
    private List<User> users;
    private FileContentDTO fileContentDTO;

    @BeforeEach
    void setUp() {
        processUser = new ProcessUser();
        users = new ArrayList<>();
        fileContentDTO = new FileContentDTO();
    }

    @Test
    void testExecute_UserExists() throws Exception {
        User existingUser = new User("1", "John Doe");
        users.add(existingUser);
        fileContentDTO.setUserId("1");
        fileContentDTO.setUserName("John Doe");

        processUser.execute(users, fileContentDTO);

        assertEquals(1, users.size());
        assertEquals("John Doe", users.get(0).getName());
    }

    @Test
    void testExecute_UserDoesNotExist() {
        fileContentDTO.setUserId("2");
        fileContentDTO.setUserName("Jane Doe");

        processUser.execute(users, fileContentDTO);

        assertEquals(1, users.size());
        assertEquals("Jane Doe", users.get(0).getName());
    }

    @Test
    void testExecute_ExceptionHandling() {
        fileContentDTO.setUserId(null);

        processUser.execute(users, fileContentDTO);

        assertTrue(users.isEmpty());
    }
}
