package tasks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static Constans.Enpoint.ENDPOINT_BASE;
import static net.serenitybdd.screenplay.Tasks.instrumented;


public class PostRandomUser implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostRandomUser.class);

    @Override
    public <T extends Actor> void performAs(T actor) {
        String picture = "https://randomuser.me/api/portraits/med/women/56.jpg";

        // Generar valores aleatorios para title, firstName, lastName y email
        String[] titles = {"mr", "ms", "mrs", "miss", "dr"};
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Lee", "Garcia", "Taylor"};
        String randomTitle = titles[new Random().nextInt(titles.length)];
        String randomFirstName = firstNames[new Random().nextInt(firstNames.length)];
        String randomLastName = lastNames[new Random().nextInt(lastNames.length)];
        String randomEmail = "user" + new Random().nextInt(10000) + "@example.com";

        // Crear el JSON request body
        String requestBody = String.format("{" +
                "    \"title\": \"%s\"," +
                "    \"firstName\": \"%s\"," +
                "    \"lastName\": \"%s\"," +
                "    \"email\": \"%s\"," +
                "    \"picture\": \"%s\"" +
                "}", randomTitle, randomFirstName, randomLastName, randomEmail, picture);
        System.out.println(requestBody);
        String endpoint = ENDPOINT_BASE + "/create";
        Response response = RestAssured.given()
                .baseUri(endpoint)
                .header("app-id", "64013f90bb1e4f20bc431c5c")
                .header("Content-Type", "application/json")
                .header("User-Agent", "PostmanRuntime/7.26.8")
                .header("Accept", "*/*")
                .header("Postman-Token", "7bd0c0d7-dcc7-47b2-a1b0-7133a3d05f28")
                .header("Host", "dummyapi.io")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(requestBody)
                .when()
                .post();

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Error al crear el usuario: " + response.getBody().asString());
        }
        actor.remember("idPost", response.getBody().jsonPath().getString("id"));
        actor.remember("titlePost", response.getBody().jsonPath().getString("title"));
        actor.remember("firstNamePost", response.getBody().jsonPath().getString("firstName"));
        actor.remember("lastNamePost", response.getBody().jsonPath().getString("lastName"));

        LOGGER.debug("La respuesta completa de la tarea ObtenerUsuarios es: {}", response.getBody().prettyPrint());

    }

    public static PostRandomUser conDatosAleatorios() {
        return instrumented(PostRandomUser.class);
    }

}
