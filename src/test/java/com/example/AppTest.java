package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.App;
import com.entity.*;
import com.infrastructure.InMemoryUserRepository;

public class AppTest {

    @Test
    public void testExchangeGetters() {
        Exchange ex = new ExchangeImp("NYSE", "New York Stock Exchange", "New York");
        assertEquals("NYSE", ex.getCode());
        assertEquals("New York Stock Exchange", ex.getName());
        assertEquals("New York", ex.getLocation());
    }
}