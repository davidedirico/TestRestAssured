import io.restassured.RestAssured;
import model.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class LC_userPostTests {

    private static String BASE_URI = "https://jsonplaceholder.typicode.com";
    private User testUser;

//    potrei inserire il setBaseUri in uno static block piuttosto che in beforeAll ma non è una best practice
//    static {RestAssured.baseURI = BASE_URI;}

    @BeforeAll
    static void setBaseUri (){
        RestAssured.baseURI = BASE_URI;
    }

    @BeforeEach
    public void creteTestUser (){
        testUser = new User();
    }

    @Test
    public void testStatusCode() {

        given()
                .contentType("application/json")
                .body(testUser)
                .when()
                .post("/users")
                .then()
                .statusCode(201);
    }

    @Test
    public void testUserName() {

        testUser.setName("Luca");
        given()
                .contentType("application/json")
                .body(testUser)
                .when()
                .post("/users")
                .then()
                .body("name", equalTo("Luca"));
    }
}
