package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceMockTest {

  @Mock private UserRepository userRepository;

  @InjectMocks private UserService userService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void createUserTest() {
    User user = new User();
    user.setUsername("user");
    user.setEmail("user@example.com");
    when(userRepository.save(any(User.class))).thenReturn(user);
    User savedUser = userService.createUser(user);
    assertEquals(user.getUsername(), savedUser.getUsername());
    assertEquals(user.getEmail(), savedUser.getEmail());
    verify(userRepository, times(1)).save(user);
  }

  @Test
  public void getUserTest() {
    User user = new User();
    user.setUsername("user");
    user.setEmail("user@example.com");

    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    User foundedUser = userService.getUser(1L);
    assertEquals(user.getUsername(), foundedUser.getUsername());
    assertEquals(user.getEmail(), foundedUser.getEmail());
    verify(userRepository, times(1)).findById(1L);
  }

  @Test
  public void deleteUserTest() {
    when(userRepository.existsById(1L)).thenReturn(true);
    doNothing().when(userRepository).deleteById(1L);
    userService.deleteUser(1L);
    verify(userRepository, times(1)).deleteById(1L);
  }
}
