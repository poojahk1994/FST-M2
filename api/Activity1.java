package RestAssured_Day1Activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity1 {
    static String Base_URI = "https://petstore.swagger.io/v2/pet";

    @Test
    public void addPet(){
        String reqBody = "{\"id\": 77232,\"name\": \"Riley\",\"status\": \"alive\"}";
        Response response =
                given().contentType(ContentType.JSON).body(reqBody)
                .when().post(Base_URI); // Send POST request

        // Assertion
        response.then().body("id", equalTo(77232));
        response.then().body("name", equalTo("Riley"));
        response.then().body("status", equalTo("alive"));

    }

    @Test
    public void getPet(){
        Response response =
                given().contentType(ContentType.JSON)
                        .when().pathParam("petId", 77232)
                        .get(Base_URI + "/{petId}");

        response.then().body("id", equalTo(77232));
        response.then().body("name", equalTo("Riley"));
        response.then().body("status", equalTo("alive"));
    }
    @Test
    public void removePet(){
        Response response =
                given().contentType(ContentType.JSON)
                        .when().pathParam("petId", 77232)
                        .delete(Base_URI + "/{petId}");

        System.out.println(response.getBody().asPrettyString());
        response.then().statusCode(200);
        response.then().body("message",equalTo("77232"));
    }

}
