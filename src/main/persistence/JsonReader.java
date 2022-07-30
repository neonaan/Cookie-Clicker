package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.CookieCount;
import model.Milestone;
import model.MilestonesSet;
import org.json.*;

// Represents a reader that reads MilestonesSet from JSON data stored in file
// based off of the JsonSterilizationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads MilestonesSet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MilestonesSet readMilestonesSet() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMilestonesSet(jsonObject);
    }

    // EFFECTS: reads MilestonesSet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CookieCount readCookies() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCookies(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses MilestonesSet from JSON object and returns it
    private MilestonesSet parseMilestonesSet(JSONObject jsonObject) {
        MilestonesSet ms = new MilestonesSet();
        addMilestones(ms, jsonObject);
        return ms;
    }

    // MODIFIES: ms
    // EFFECTS: parses milestones from JSON object and adds them to MilestonesSet
    private void addMilestones(MilestonesSet ms, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("milestones");
        for (Object json : jsonArray) {
            JSONObject nextMilestone = (JSONObject) json;
            addMilestone(ms, nextMilestone);
        }
    }

    // MODIFIES: ms
    // EFFECTS: parses milestone from JSON object and adds it to MilestoneSet
    private void addMilestone(MilestonesSet ms, JSONObject jsonObject) {
        int goal = jsonObject.getInt("goal");
        boolean status = jsonObject.getBoolean("status");
        Milestone milestone = new Milestone(goal);
        if (status) {
            milestone.setReached();
        }
        ms.addMilestone(milestone);
    }

    // EFFECTS: parses cookieCount from JSON object and returns it
    private CookieCount parseCookies(JSONObject jsonObject) {
        CookieCount cookieCount = new CookieCount();
        setCookieCount(cookieCount, jsonObject);
        return cookieCount;
    }

    // MODIFIES: cookies
    // EFFECTS: parses cookieCount from JSON object and sets it as the new cookieCount
    private void setCookieCount(CookieCount cookies, JSONObject jsonObject) {
        int goal = (int) jsonObject.get("cookie count");
        cookies.setCookieCount(goal);
    }
}
