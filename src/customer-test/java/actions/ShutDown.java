package actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import org.springframework.web.client.RestTemplate;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;


public class ShutDown implements Interaction {

  @Override
  @Step("{0} shuts down the server")
  public <T extends Actor> void performAs(T actor) {
    new RestTemplate().postForLocation(StartUp.URL + "shutdown", null);
  }

  public static Performable theServer() {
    return instrumented(ShutDown.class);
  }

}
