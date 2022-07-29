package RestAssured_Day1Activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;

//import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.equalTo;

public class Activity2 {
    static String ROOT_URI = "https://petstore.swagger.io/v2/user";

    @Test
    public void AddUser() throws IOException {
        File file = new File("src/test/resources/input.json");
        FileInputStream inputJson = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        inputJson.read(bytes);
        String reqBody = new String(bytes, "UTF-8");
        System.out.println(reqBody);
        Response response =
                given().contentType(ContentType.JSON)
                        .body(reqBody).when().post(ROOT_URI);

        // Print response of POST request
        String body = response.getBody().asPrettyString();
        System.out.println(body);
        inputJson.close();
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("20236"));

    }

    @Test
    public void getUserInfo() {
        File outputJSON = new File("src/test/resources/getResponse.json");
        Response response =
                given().contentType(ContentType.JSON)
                        .pathParam("username", "poojahk")
                        .when().get(ROOT_URI + "/{username}");

        // Get response body
        String resBody = response.getBody().asPrettyString();

        try {
            // Create JSON file
            outputJSON.createNewFile();
            // Write response body to external file
            FileWriter writer = new FileWriter(outputJSON.getPath());
            writer.write(resBody);
            writer.close();
        } catch (IOException excp) {
            excp.printStackTrace();
        }

        // Assertion
        response.then().body("id", equalTo(20236));
        response.then().body("username", equalTo("poojahk"));
        response.then().body("firstName", equalTo("Pooja"));
        response.then().body("lastName", equalTo("HK"));
        response.then().body("email", equalTo("pooja.h.k@email.com"));
        response.then().body("password", equalTo("password123"));
        response.then().body("phone", equalTo("9164833990"));
    }
    @Test
    public void removeUser() throws IOException {
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .pathParam("username", "poojahk") // Add path parameter
                        .when().delete(ROOT_URI + "/{username}"); // Send POST request

        // Assertion
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("poojahk"));
    }
}
