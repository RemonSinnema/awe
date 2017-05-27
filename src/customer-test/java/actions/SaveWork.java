package actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;

import ui.Prompt;
import ui.WorkPage;


public class SaveWork implements Interaction {

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Click.on(WorkPage.SAVE_BUTTON),
        Click.on(Prompt.OK_BUTTON));
  }

  public static Performable toTheServer() {
    return instrumented(SaveWork.class);
  }

}
