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

public class GetUsers implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetUsers.class);
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
                .header("app-id", "64013f90bb1e4f20bc431c5c")
                .when()
                .get();

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Error al obtener usuarios: " + response.getBody().asString());
        }
        actor.remember("usuarios", response.getBody().jsonPath().getList("data"));
        actor.remember("limiteResponse", response.getBody().jsonPath().getInt("limit"));
        actor.remember("paginas", response.getBody().jsonPath().getInt("page"));
        actor.remember("limiteRandom", limite);

        String idAleatorio = response.getBody().jsonPath().getList("data.id").get(new Random().nextInt(limite)).toString();
        actor.remember("idUsuarioAleatorio", idAleatorio);

        actor.remember("totalUser", response.getBody().jsonPath().getInt("total"));

        //  LOGGER.debug("La respuesta completa de la tarea ObtenerUsuarios es: {}", response.getBody().prettyPrint());
    }

    public static GetUsers aleatorios() {
        return instrumented(GetUsers.class);
    }
}
