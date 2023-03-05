package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnlineCast;
import tasks.GetUserById;
import tasks.UpdateUser;

import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;


public class updateStepDefinition {

    @Before
    public void setStage() {
        setTheStage(new OnlineCast());
        theActorCalled("Andres");
    }

    @Given("se consume el servisio de update con un usuario aleatorio y responde docientos")
    public void seConsumeElServisioDeUpdateConUnUsuarioAleatorioYRespondeDocientos() {
        String id = theActorInTheSpotlight().recall("idUsuarioAleatorio");
        theActorInTheSpotlight().attemptsTo(GetUserById.aleatorios(id));

        String titlePreIpd = theActorInTheSpotlight().recall("titleById");
        theActorInTheSpotlight().remember("titlePreUpd", titlePreIpd);

        String firstNamePreUpd = theActorInTheSpotlight().recall("firstNameById");
        theActorInTheSpotlight().remember("firstNamePreUpd", firstNamePreUpd);

        String lastNamePreUpd = theActorInTheSpotlight().recall("lastNameById");
        theActorInTheSpotlight().remember("lastNamePreUpd", lastNamePreUpd);


        theActorInTheSpotlight().attemptsTo(UpdateUser.aleatorios(id));

        theActorInTheSpotlight().attemptsTo(GetUserById.aleatorios(id));
    }

    @Then("se valida que la informacion cambiada si se aplique")
    public void seValidaQueLaInformacionCambiadaSiSeAplique() {

        String firstNamePostUpd = theActorInTheSpotlight().recall("firstNameById");
        String firtNamePreUpd = theActorInTheSpotlight().recall("firstNamePreUpd");

        assertThat(firstNamePostUpd, not(equalTo(firtNamePreUpd)));

        String lastNamePostUpd = theActorInTheSpotlight().recall("lastNameById");
        String lastNamePreUpd = theActorInTheSpotlight().recall("lastNamePreUpd");

        assertThat(lastNamePostUpd, not(equalTo(lastNamePreUpd)));

    }

}
