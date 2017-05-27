package actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import ui.Prompt;
import ui.WorkPage;


public class LoadWork implements Interaction {

  private final String fileName;

  public LoadWork(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Click.on(WorkPage.LOAD_BUTTON),
        Enter.theValue(fileName).into(Prompt.ANSWER_FIELD),
        Click.on(Prompt.OK_BUTTON));
  }

  public static Performable from(String fileName) {
    return instrumented(LoadWork.class, fileName);
  }

}
