package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnlineCast;
import questions.IsDifferenceOne;
import tasks.DeleteUserById;
import tasks.GetUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class deleteStepDefinition {


    @Before
    public void setStage() {
        setTheStage(new OnlineCast());
        theActorCalled("Andres");
    }

    @Given("se elimina un usuario de forma aleatoria y responde docientos la Api")
    public void seEliminaUnUsuarioDeFormaAleatoriaYRespondeDocientosLaApi() {
        int totalUserPre = theActorInTheSpotlight().recall("totalUser");
        theActorInTheSpotlight().remember("userPreDelete", totalUserPre);

        String idUsuarioAleatori = theActorInTheSpotlight().recall("idUsuarioAleatorio");
        theActorInTheSpotlight().remember("idAEliminar", idUsuarioAleatori);

        theActorInTheSpotlight().attemptsTo(DeleteUserById.aleatorios(idUsuarioAleatori));

        theActorInTheSpotlight().attemptsTo(GetUsers.aleatorios());
        int totalUserPostDelete = theActorInTheSpotlight().recall("totalUser");
        theActorInTheSpotlight().remember("userPostDelete", totalUserPostDelete);

    }

    @Then("se valida el id del usuario eliminado y que el numero de usuario registrados halla disminuido en un usuario")
    public void seValidaElIdDelUsuarioEliminadoYQueElNumeroDeUsuarioRegistradosHallaDisminuidoEnUnUsuario() {
        int preDelete = theActorInTheSpotlight().recall("userPreDelete");
        int postDelete = theActorInTheSpotlight().recall("userPostDelete");
        String idDeUsuarioAEliminar = theActorInTheSpotlight().recall("idAEliminar");
        String idEliminado = theActorInTheSpotlight().recall("DeleteId");

        theActorInTheSpotlight().should(seeThat(IsDifferenceOne.of(preDelete, postDelete), is(true)));

        assertThat(idDeUsuarioAEliminar, equalTo(idEliminado));
    }

}
