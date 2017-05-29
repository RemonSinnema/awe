package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import actions.Choose;
import actions.StartNew;


public class StartNewWork implements Task {

  private final String type;
  private final String category;
  private final String subCategory;

  public StartNewWork(String type, String category, String subCategory) {
    this.type = type;
    this.category = category;
    this.subCategory = subCategory;
  }

  @Override
  @Step("{0} creates a #type of type #subcategory #category")
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(StartNew.work(),
        Choose.type(type),
        Choose.category(category),
        Choose.subcategory(subCategory));
  }

  public static Performable ofType(String type, String category, String subCategory) {
    return instrumented(StartNewWork.class, type, category, subCategory);
  }

}
