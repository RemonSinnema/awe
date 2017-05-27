package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import actions.ShutDown;


public class Stop implements Task {

  @Override
  @Step("{0} stops writing")
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(ShutDown.theServer());
  }

  public static Performable writing() {
    return instrumented(Stop.class);
  }

}
