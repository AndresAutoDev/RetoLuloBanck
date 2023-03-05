package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnlineCast;
import questions.IsDifferenceOne;
import tasks.*;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
public class postRandomUserStepDefinition {

    @Before
    public void setStage() {
        setTheStage(new OnlineCast());
        theActorCalled("Andres");
    }

    @Given("Se consume la api Post para crear un usuario aleatorio y responde docientos")
    public void seConsumeLaApiPostParaCrearUnUsuarioAleatorioYRespondeDocientos() {
        theActorInTheSpotlight().attemptsTo(PostRandomUser.conDatosAleatorios());


        int totalUserPre = theActorInTheSpotlight().recall("totalUser");
        theActorInTheSpotlight().remember("totalUserPre", totalUserPre);
        theActorInTheSpotlight().attemptsTo(GetUsers.aleatorios());
        int totalUserPost = theActorInTheSpotlight().recall("totalUser");
        theActorInTheSpotlight().remember("totalUserPost", totalUserPost);

        String id = theActorInTheSpotlight().recall("idPost");

        theActorInTheSpotlight().attemptsTo(GetUserById.aleatorios(id));

        String titleById = theActorInTheSpotlight().recall("titleById");

        theActorInTheSpotlight().remember("titleById", titleById);

        String firstNameById = theActorInTheSpotlight().recall("firstNameById");

        theActorInTheSpotlight().remember("firstNameById", firstNameById);

        String lastNameById = theActorInTheSpotlight().recall("lastNameById");

        theActorInTheSpotlight().remember("lastNameById", lastNameById);



    }

    @Then("se valida que el numero de usuario sea superior al anterior y se filtra por su id generado para validar el usuario")
    public void seValidaQueElNumeroDeUsuarioSeaSuperiorAlAnteriorYSeFiltraPorSuIdGeneradoParaValidarElUsuario() {
        int prePostUser = theActorInTheSpotlight().recall("totalUserPre");
        int postUser = theActorInTheSpotlight().recall("totalUserPost");
        String titleByPost = theActorInTheSpotlight().recall("titlePost");
        String firstNameByPost = theActorInTheSpotlight().recall("firstNamePost");
        String lastNameByPost = theActorInTheSpotlight().recall("lastNamePost");

        String titleById = theActorInTheSpotlight().recall("titleById");
        String firstNameById = theActorInTheSpotlight().recall("firstNameById");
        String lastNameById = theActorInTheSpotlight().recall("lastNameById");

        theActorInTheSpotlight().should(seeThat(IsDifferenceOne.of(postUser, prePostUser), is(true)));

        assertThat(titleByPost, equalTo(titleById));
        assertThat(firstNameByPost, equalTo(firstNameById));
        assertThat(lastNameByPost, equalTo(lastNameById));

    }

    @Given("se consume la api Post con un body no valido y responde cuatrocientos cuatro")
    public void seConsumeLaApiPostConUnBodyNoValidoYRespondeCuatrocientosCuatro() {
    theActorInTheSpotlight().attemptsTo(PostRandomUserNoValid.conDatosAleatorios());
    }
    @Then("se visualiza el msg de error")
    public void seVisualizaElMsgDeError() {
    String msgError = "BODY_NOT_VALID";
    String msgErrorResponse = theActorInTheSpotlight().recall("getErroByUserPost");

        assertThat(msgError, equalTo(msgErrorResponse));
    }

    @Given("se consume la api Post con un path no valido y responde codigo cuatrocientos cuatro")
    public void seConsumeLaApiPostConUnPathNoValidoYRespondeCodigoCuatrocientosCuatro() {
    theActorInTheSpotlight().attemptsTo(PostRandomUserNoPathValid.conDatosAleatorios());
    }

    @Then("se valida el msg de error para el path no ecnotnrado")
    public void seValidaElMsgDeErrorParaElPathNoEcnotnrado() {
        String msgError = "PATH_NOT_FOUND";
        String msgErrorResponse = theActorInTheSpotlight().recall("getErroByUserPost");

        assertThat(msgError, equalTo(msgErrorResponse));
    }

}
