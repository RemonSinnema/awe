package questions;

import static net.serenitybdd.core.steps.Instrumented.instanceOf;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Value;

import ui.WorkPage;


@Subject("the work's title")
public class TheTitle implements Question<String> {

  @Override
  public String answeredBy(Actor actor) {
    return Value.of(WorkPage.TITLE_FIELD)
        .viewedBy(actor)
        .asString();
  }

  public static Question<String> ofTheWork() {
    return instanceOf(TheTitle.class).newInstance();
  }

}
