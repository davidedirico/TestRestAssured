import io.restassured.path.json.JsonPath;
import model.user.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class apiTest {

    @Test
    public void testUsers() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users")
        .then()
                .statusCode(200);
    }

    //estraggo un item con "find {it.id== ?}" e prendo il campo nome per confrontarlo con il valore che voglio
    @Test
    public void testUser5() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
               .when()
               .get("/users?id=5")
                .then()
                .body("size()", equalTo(1))
                .body("find {it.id==5}.name", equalTo("Chelsey Dietrich"));
    }

    //stessa cosa con extract per utilizzare valore in java
    @Test
    public void testUser5WithGPath() {

        JsonPath jsonResponse = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users?id=5")
                .then()
                .extract()
                .jsonPath();

        System.out.println("name value = " + jsonResponse.getString("name"));;
    }

    // extract per utilizzare deserializzazione json con POJO e controllare lunghezza array e presenza nome specifico
    @Test
    public void testUser5WithPojo(){
        User[] users =
                given()
                        .baseUri("https://jsonplaceholder.typicode.com")
                        .when()
                        .get("/users")
                        .then()
                        .extract()
                        .as(User[].class);

        assertTrue(users.length >0);
        assertTrue(Arrays.stream(users).anyMatch(user -> user.getName().equals("Chelsey Dietrich")));
    }


}