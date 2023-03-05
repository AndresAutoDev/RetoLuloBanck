package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnlineCast;
import tasks.GetUsers;
import tasks.GetUsersNoAppId;
import tasks.GetUsersNoValidAppId;

import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class getStepDefinition {


    @Before
    public void setStage() {
        setTheStage(new OnlineCast());
        theActorCalled("Andres");
    }
    @Given("que el usuario realiza una peticion GET a la API y responde docientos")
    public void queElUsuarioRealizaUnaPeticionGetALaApiYRespondeDocientos() {

        theActorInTheSpotlight().attemptsTo(GetUsers.aleatorios());
    }
    @Then("la respuesta debe contener el valor de paginas especificadas y del limite")
    public void laRespuestaDebeContenerElValorDePaginasEspecificadasYDelLimite() {

        int limiteRandom = theActorInTheSpotlight().recall("limiteRandom");
        int limiteResponse = theActorInTheSpotlight().recall("limiteResponse");
        int pageResponse = theActorInTheSpotlight().recall("paginas");

        assertThat(limiteRandom, equalTo(limiteResponse));
        assertThat(pageResponse, equalTo(1));
    }

    @Given("que el usuario realiza una peticion Get sin el header de app-id")
    public void queElUsuarioRealizaUnaPeticionGetSinElHeaderDeAppId() {
    theActorInTheSpotlight().attemptsTo(GetUsersNoAppId.aleatorios());
    }
    @Then("se valida el msg de error de app-id missing")
    public void seValidaElMsgDeErrorDeAppIdMissing() {
    String msgError = "APP_ID_MISSING";
    String msgErrorResponse = theActorInTheSpotlight().recall("errorNoAppId");

        assertThat(msgError, equalTo(msgErrorResponse));
    }
    @Given("que el usuario realiza una peticion Get con un app-id no valido")
    public void queElUsuarioRealizaUnaPeticionGetConUnAppIdNoValido() {
    theActorInTheSpotlight().attemptsTo(GetUsersNoValidAppId.aleatorios());
    }
    @Then("se valida el msg de error de app-id no existente")
    public void seValidaElMsgDeErrorDeAppIdNoExistente() {
    String msgError = "APP_ID_NOT_EXIST";
    String msgErrorResponse = theActorInTheSpotlight().recall("errorNoValidAppId");

        assertThat(msgError, equalTo(msgErrorResponse));
    }


}
