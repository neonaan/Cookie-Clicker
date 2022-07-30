package persistence;

import model.CookieCount;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonCookieCountWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            CookieCount cookies = new CookieCount();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterCookieCount() {
        try {
            CookieCount cookies = new CookieCount();
            cookies.incrementCookies(8);
            JsonWriter writer = new JsonWriter("./data/testWriterCookieCount.json");
            writer.open();
            writer.writeCookieCount(cookies);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCookieCount.json");
            cookies = reader.readCookies();
            assertEquals(8, cookies.getCookieCount());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
