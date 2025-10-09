package com.example.service;

import com.example.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void testGetUserById() {
        Optional<User> user = userService.getUserById(1L);
        assertTrue(user.isPresent());
        assertEquals("John Doe", user.get().getName());
    }

    @Test
    void testGetUserByIdNotFound() {
        Optional<User> user = userService.getUserById(999L);
        assertFalse(user.isPresent());
    }

    @Test
    void testCreateUser() {
        User newUser = new User(null, "Bob Brown", "bob@example.com");
        User createdUser = userService.createUser(newUser);
        
        assertNotNull(createdUser.getId());
        assertEquals("Bob Brown", createdUser.getName());
        assertEquals("bob@example.com", createdUser.getEmail());
        assertEquals(3, userService.getAllUsers().size());
    }

    @Test
    void testUpdateUser() {
        User updatedInfo = new User(null, "John Updated", "john.updated@example.com");
        Optional<User> updatedUser = userService.updateUser(1L, updatedInfo);
        
        assertTrue(updatedUser.isPresent());
        assertEquals("John Updated", updatedUser.get().getName());
        assertEquals("john.updated@example.com", updatedUser.get().getEmail());
    }

    @Test
    void testUpdateUserNotFound() {
        User updatedInfo = new User(null, "New Name", "new@example.com");
        Optional<User> result = userService.updateUser(999L, updatedInfo);
        
        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteUser() {
        boolean deleted = userService.deleteUser(1L);
        assertTrue(deleted);
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    void testDeleteUserNotFound() {
        boolean deleted = userService.deleteUser(999L);
        assertFalse(deleted);
    }
}

