package com.example.addictionbreaker.model;

import org.junit.Before;

import static org.junit.Assert.*;

public class UserTest {
    User user;

    @Before
    public void setUp() {
        user = new User("Thinh","Cig",23,4,2);
    }
    @org.junit.Test
    public void TestGetName() {
        assertEquals(user.getName(),"Thinh");
    }
}