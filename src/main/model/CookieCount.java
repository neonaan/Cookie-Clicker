package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;

public class CookieCount implements Writable {
    private int cookies;

    //EFFECTS: constructs a counter of cookies starting with 0 cookies
    public CookieCount() {
        this.cookies = 0;
    }

    //EFFECTS: returns amount of cookies acquired
    public int getCookieCount() {
        return cookies;
    }

    //EFFECTS: sets cookies to amount
    public void setCookieCount(int amount) {
        cookies = amount;
    }

    //MODIFIES: this
    //EFFECTS: increases amount of cookies by amount
    public void incrementCookies(int amount) {
        cookies += amount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cookie count", cookies);
        return json;
    }
}
