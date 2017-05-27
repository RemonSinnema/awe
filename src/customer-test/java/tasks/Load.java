package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import actions.LoadWork;


public class Load implements Task {

  private final String fileName;

  public Load(String fileName) {
    this.fileName = fileName;
  }

  @Override
  @Step("{0} loads the file '#fileName'")
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(LoadWork.from(fileName));
  }

  public static Performable theWorkFrom(String fileName) {
    return instrumented(Load.class, fileName);
  }

}
