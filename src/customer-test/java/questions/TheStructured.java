package questions;

import static net.serenitybdd.core.steps.Instrumented.instanceOf;

import java.util.List;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import ui.WorkPage;


public final class TheStructured {

  private TheStructured() {
    // Utility class
  }

  public static Question<List<String>> editors() {
    return instanceOf(TopLevelEditors.class).newInstance();
  }


  public static class TopLevelEditors implements Question<List<String>> {

    @Override
    public List<String> answeredBy(Actor actor) {
      return Id.of(WorkPage.TOPLEVEL_EDITORS).viewedBy(actor).resolveAll();
    }

  }

}
