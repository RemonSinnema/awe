package actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.targets.Target;

import ui.NewWorkPage;


public final class Choose {

  private Choose() {
    // Utility class
  }

  public static Performable type(String value) {
    return instrumented(ChooseItem.class, value, NewWorkPage.TYPE_FIELD);
  }

  public static Performable category(String value) {
    return instrumented(ChooseItem.class, value, NewWorkPage.CATEGORY_FIELD);
  }

  public static Performable subcategory(String value) {
    return instrumented(ChooseItem.class, value, NewWorkPage.SUBCATEGORY_FIELD);
  }


  public static class ChooseItem implements Interaction {

    private final String value;
    private final Target dropDownList;

    public ChooseItem(String type, Target dropDownList) {
      this.value = type;
      this.dropDownList = dropDownList;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
      actor.attemptsTo(SelectFromOptions.byValue(value).from(dropDownList));
    }

  }

}
