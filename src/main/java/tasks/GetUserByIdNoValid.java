package tasks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Constans.Enpoint.ENDPOINT_BASE;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUserByIdNoValid implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserByIdNoValid.class);
    private final String id;

    public GetUserByIdNoValid(String id) {
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String endpoint = ENDPOINT_BASE + "/" + id;
        Response response = RestAssured.given()
                .baseUri(endpoint)
                .header("User-Agent", "PostmanRuntime/7.26.8")
                .header("Accept", "*/*")
                .header("Postman-Token", "eb674846-89dd-4bcc-aed0-f62e936eff3c")
                .header("Host", "dummyapi.io")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("app-id", "64013f90bb1e4f20bc431c5c")
                .when()
                .get();

        if (response.getStatusCode() != 400) {
            throw new RuntimeException("Error al obtener usuarios: " + response.getBody().asString());
        }
        actor.remember("getErroById", response.getBody().jsonPath().getString("error"));

        LOGGER.debug("La respuesta completa de la tarea ObtenerUsuarios es: {}", response.getBody().prettyPrint());
    }

    public static GetUserByIdNoValid idNoValid(String id) {
        return instrumented(GetUserByIdNoValid.class, id);
    }
}
