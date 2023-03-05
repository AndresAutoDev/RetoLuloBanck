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

public class UpdateUser implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUser.class);
    private final String id;

    public UpdateUser(String id) {
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        // Generar valores aleatorios para title, firstName, lastName y email
        String[] titles = {"mr", "ms", "mrs", "miss", "dr"};
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace","Duban","Camilo","Robert"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Lee", "Garcia", "Taylor","Gomez","Sanchez","Ramirez"};
        String randomTitle = titles[new Random().nextInt(titles.length)];
        String randomFirstName = firstNames[new Random().nextInt(firstNames.length)];
        String randomLastName = lastNames[new Random().nextInt(lastNames.length)];

        // Crear el JSON request body
        String requestBody = String.format("{" +
                "    \"title\": \"%s\"," +
                "    \"firstName\": \"%s\"," +
                "    \"lastName\": \"%s\"" +
                "}", randomTitle, randomFirstName, randomLastName);

        System.out.println(requestBody);


        String endpoint = ENDPOINT_BASE + "/" + id;
        Response response = (Response) RestAssured.given()
                .baseUri(endpoint)
                .header("app-id", "64013f90bb1e4f20bc431c5c")
                .header("Content-Type", "application/json")
                .header("User-Agent", "PostmanRuntime/7.26.8")
                .header("Accept", "*/*")
                .header("Postman-Token", "90da5b7e-bd61-4a5c-928e-83d50afe7b0d")
                .header("Host", "dummyapi.io")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(requestBody)
                .when()
                .put();


        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Error al obtener usuarios: " + response.getBody().asString());
        }
        actor.remember("idUpdate", response.getBody().jsonPath().getString("id"));
        actor.remember("titleUpdate", response.getBody().jsonPath().getString("title"));
        actor.remember("firstNameUpdate", response.getBody().jsonPath().getString("firstName"));
        actor.remember("lastNameUpdate", response.getBody().jsonPath().getString("lastName"));


        LOGGER.debug("La respuesta completa de la tarea ObtenerUsuarios es: {}", response.getBody().prettyPrint());
    }

    public static UpdateUser aleatorios(String id) {
        return instrumented(UpdateUser.class, id);
    }
}
