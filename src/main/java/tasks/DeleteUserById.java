package tasks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Constans.Enpoint.ENDPOINT_BASE;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUserById implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserById.class);
    private final String id;

    public DeleteUserById(String id) {
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String endpoint = ENDPOINT_BASE + "/" + id;
        Response response = RestAssured.given()
                .baseUri(endpoint)
                .header("app-id", "64013f90bb1e4f20bc431c5c")
                .header("User-Agent", "PostmanRuntime/7.26.8")
                .header("Accept", "*/*")
                .header("Postman-Token", "df14bd63-9d51-4d90-826b-a926b32417c2")
                .header("Host", "dummyapi.io")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .when()
                .delete();

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Error al obtener usuarios: " + response.getBody().asString());
        }
        actor.remember("DeleteId", response.getBody().jsonPath().getString("id"));

        LOGGER.debug("La respuesta completa de la tarea ObtenerUsuarios es: {}", response.getBody().prettyPrint());
    }

    public static DeleteUserById aleatorios(String id) {
        return instrumented(DeleteUserById.class, id);
    }
}
