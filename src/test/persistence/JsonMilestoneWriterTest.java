package persistence;

import model.Milestone;
import model.MilestonesSet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonMilestoneWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MilestonesSet wr = new MilestonesSet();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMilestones() {
        try {
            MilestonesSet ms = new MilestonesSet();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMilestones.json");
            writer.open();
            writer.writeMilestonesSet(ms);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMilestones.json");
            ms = reader.readMilestonesSet();
            assertEquals(0, ms.getLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMilestones() {
        try {
            MilestonesSet ms = new MilestonesSet();
            Milestone milestone1 = new Milestone(4);
            milestone1.setReached();
            ms.addMilestone(milestone1);
            ms.addMilestone(new Milestone(7));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMilestones.json");
            writer.open();
            writer.writeMilestonesSet(ms);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMilestones.json");
            ms = reader.readMilestonesSet();
            List<Milestone> milestones = ms.getMilestones();
            assertEquals(2, milestones.size());
            checkMilestone(4, true, milestones.get(0));
            checkMilestone(7, false, milestones.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
