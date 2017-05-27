package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import actions.Set;


public class Name implements Task {

  private final String title;

  public Name(String title) {
    this.title = title;
  }

  @Override
  @Step("{0} names the work '#title'")
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Set.theTitleTo(title));
  }

  public static Performable theWork(String title) {
    return instrumented(Name.class, title);
  }

}
