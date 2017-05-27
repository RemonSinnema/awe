package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import actions.SaveWork;


public class Save implements Task {

  @Override
  @Step("{0} saves the work")
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(SaveWork.toTheServer());
  }

  public static Performable theWork() {
    return instrumented(Save.class);
  }

}
