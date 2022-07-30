package persistence;

import model.Milestone;
import model.MilestonesSet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonMilestoneReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MilestonesSet ms = reader.readMilestonesSet();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMilestones() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMilestones.json");
        try {
            MilestonesSet ms = reader.readMilestonesSet();
            assertEquals(0, ms.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMilestones() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMilestones.json");
        try {
            MilestonesSet ms = reader.readMilestonesSet();
            List<Milestone> milestones = ms.getMilestones();
            assertEquals(2, milestones.size());
            checkMilestone(3, true, milestones.get(0));
            checkMilestone(5, false, milestones.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}