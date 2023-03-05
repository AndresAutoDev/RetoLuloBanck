package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnlineCast;
import tasks.DeleteUserById;
import tasks.GetUserById;
import tasks.GetUserByIdNoUserValid;
import tasks.GetUserByIdNoValid;

import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class getByIdStepDefinition {


    @Before
    public void setStage() {
        setTheStage(new OnlineCast());
        theActorCalled("Andres");
    }

    @Given("Se consume la api Get filtrando por un id aleatorio y responde docientos")
    public void seConsumeLaApiGetFiltrandoPorUnIdAleatorioYRespondeDocientos() {
        String id = theActorInTheSpotlight().recall("idUsuarioAleatorio");
        theActorInTheSpotlight().attemptsTo(GetUserById.aleatorios(id));
    }

    @Then("se valida que el id del response sea igual al id aleatorio por el que se filtro")
    public void seValidaQueElIdDelResponseSeaIgualAlIdAleatorioPorElQueSeFiltro() {
        String id = theActorInTheSpotlight().recall("idUsuarioAleatorio");
        String idById = theActorInTheSpotlight().recall("GetById");

        assertThat(idById, equalTo(id));
    }

    @Given("se consume la api get filtrando por un id no valido y reponde codigo cuatrocientos")
    public void seConsumeLaApiGetFiltrandoPorUnIdNoValidoYReponde() {
        theActorInTheSpotlight().attemptsTo(GetUserByIdNoValid.idNoValid("$$$"));
    }
    @Then("se valida el msg de error")
    public void seValidaElMsgDeError() {
        String msgError = "PARAMS_NOT_VALID";
        String msgErrorResponse = theActorInTheSpotlight().recall("getErroById");

        assertThat(msgError, equalTo(msgErrorResponse));

    }


    @Given("se consume la api de eliminar usuario y se busca el id eliminado")
    public void seConsumeLaApiDeEliminarUsuarioYSeBuscaElIdEliminado() {
        String idUsuarioAleatori = theActorInTheSpotlight().recall("idUsuarioAleatorio");
        theActorInTheSpotlight().remember("idAEliminar", idUsuarioAleatori);

        theActorInTheSpotlight().attemptsTo(DeleteUserById.aleatorios(idUsuarioAleatori));
        theActorInTheSpotlight().attemptsTo(GetUserByIdNoUserValid.userNoValid(idUsuarioAleatori));

    }
    @Then("se valida el msg de error de usuario no encontrado")
    public void seValidaElMsgDeErrorDeUsuarioNoEncontrado() {
    String msgError = "RESOURCE_NOT_FOUND";
    String msgErrorResponse = theActorInTheSpotlight().recall("getErroById");
        assertThat(msgError, equalTo(msgErrorResponse));
    }

}
