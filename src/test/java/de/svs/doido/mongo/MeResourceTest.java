package svs.doido.mongo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class MeResourceTest {
    @Test
    void testTestTextMeEndpoint() {
        given()
          .when().get("/me")
          .then()
             .statusCode(200)
             .body(is("It's me the POD"));
    }

}
