package pom.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

public class RESTClient {
    final public static String uri = "https://automationintesting.online";
    final public static String authRoute = "/auth/login";
    final public static String roomRoute = "/room";

    /**
     *  Fetch all existing room IDs.
     */
    public static List<Number> getRoomIds(){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().get(uri + roomRoute);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/);

        String strResponse = response.getBody().asString();
        System.out.println();

        List<Number> roomIds = new ArrayList<>();
        Gson gson = new Gson();
        RoomsResponse room = gson.fromJson(strResponse, RoomsResponse.class);
        for(Room r: room.rooms)
        {
            roomIds.add(r.roomid);
        }

        return roomIds;
    }

    /**
     *  Admin post login
     */
    public static Response adminPostLogin(String username, String password){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObject.toString())
                .when().post(uri + authRoute);

        return response;
    }
}

class RoomsResponse {
    public Room[] rooms;
    RoomsResponse(Room[] rooms){
        this.rooms = rooms;
    }
}

class Room {
    public int roomid;
    public String roomName;
    Room(int roomid, String roomName){
        this.roomid = roomid;
        this.roomName = roomName;
    }
}
