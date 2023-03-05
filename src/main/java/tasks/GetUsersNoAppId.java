package tasks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Constans.Enpoint.ENDPOINT_BASE;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUsersNoAppId implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetUsersNoAppId.class);
    int limite = (int) (Math.random() * 40) + 5;


    @Override
    public <T extends Actor> void performAs(T actor) {

        Response response = RestAssured.given()
                .baseUri(ENDPOINT_BASE)
                .queryParam("page", "1")
                .queryParam("limit", limite)
                .header("User-Agent", "PostmanRuntime/7.26.8")
                .header("Accept", "*/*")
                .header("Postman-Token", "eb674846-89dd-4bcc-aed0-f62e936eff3c")
                .header("Host", "dummyapi.io")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .when()
                .get();

        if (response.getStatusCode() != 403) {
            throw new RuntimeException("Error al obtener usuarios: " + response.getBody().asString());
        }
        actor.remember("errorNoAppId", response.getBody().jsonPath().getString("error"));

         LOGGER.debug("La respuesta completa de la tarea ObtenerUsuarios es: {}", response.getBody().prettyPrint());
    }

    public static GetUsersNoAppId aleatorios() {
        return instrumented(GetUsersNoAppId.class);
    }
}
