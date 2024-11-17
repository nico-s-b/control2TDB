package com.example.control2.services;

import com.example.control2.models.User;
import com.example.control2.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserByUsername() {
        // Given
        String testUsername = "testUser";
        User mockUser = new User();
        mockUser.setUsername(testUsername);
        mockUser.setPassword("encryptedPassword");
        mockUser.setRol("USER");

        // Comportamiento del mock
        Mockito.when(userRepository.findUserByUsername(testUsername)).thenReturn(mockUser);

        // When
        User result = userService.getUserByUsername(testUsername);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(testUsername, result.getUsername());
        Assertions.assertEquals("encryptedPassword", result.getPassword());
        Assertions.assertEquals("USER", result.getRol());
    }
}
