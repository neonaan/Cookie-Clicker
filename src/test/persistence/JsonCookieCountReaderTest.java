package persistence;

import model.CookieCount;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonCookieCountReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CookieCount c = reader.readCookies();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderCookieCount() {
        JsonReader reader = new JsonReader("./data/testReaderCookieCount.json");
        try {
            CookieCount c = reader.readCookies();
            assertEquals(10, c.getCookieCount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
