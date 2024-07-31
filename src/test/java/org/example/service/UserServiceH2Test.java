package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceH2Test {

  @Autowired private UserService userService;

  @Autowired private UserRepository userRepository;

  @Test
  public void createUserTest() {
    User user = new User();
    user.setUsername("testUser");
    user.setEmail("testUser@test.com");
    User createdUser = userService.createUser(user);

    assertNotNull(createdUser);
    assertEquals("testUser", createdUser.getUsername());
    assertEquals("testUser@test.com", createdUser.getEmail());

    assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));
  }

  @Test
  public void getUserTest() {
    User foundedUser = userService.getUser(1L);
    assertNotNull(foundedUser);
    assertEquals("testUser", foundedUser.getUsername());
    assertEquals("testUser@test.com", foundedUser.getEmail());
  }

  @Test
  public void deleteUserTest() {
    userService.deleteUser(1L);
    assertNull(userService.getUser(1L));
  }

  @Test
  public void deleteUserExceptionTest() {
    assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(2L));
  }
}
