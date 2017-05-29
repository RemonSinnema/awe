package actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;

import ui.WorkPage;


public class StartNew implements Interaction {

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Click.on(WorkPage.NEW_BUTTON));
  }

  public static Performable work() {
    return instrumented(StartNew.class);
  }

}
