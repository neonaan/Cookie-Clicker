package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookieCountTest {
    private CookieCount cookies;

    @BeforeEach
    public void setup() {
        cookies = new CookieCount();
    }

    @Test
    public void testCookieCount() {
        assertEquals(0, cookies.getCookieCount());
    }

    @Test
    public void testIncrementCookie() {
        cookies.incrementCookies(1);
        assertEquals(1, cookies.getCookieCount());

        cookies.incrementCookies(2);
        assertEquals(3, cookies.getCookieCount());
    }

}
