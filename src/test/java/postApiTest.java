import model.user.User;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class postApiTest {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    @Test
    public void testAddUserStatusCode() {

        User user = new User();
        user.setName("luca");

        given()
                .baseUri(BASE_URI)
                .contentType("application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201);
    }

    @Test
    public void testAddedUserName() {

        User user = new User();
        user.setName("mario");



        given()
                .baseUri(BASE_URI)
                .contentType("application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .body("name", equalTo("mario"));

    }
}