package actions;

import static org.openqa.selenium.Keys.TAB;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;

import ui.WorkPage;


public final class Set {

  private Set() {
    // Utility class
  }

  public static Performable theTitleTo(String title) {
    return instrumented(SetTitle.class, title);
  }

  public static Performable theContentsTo(String contents) {
    return instrumented(SetContents.class, contents);
  }


  public static class SetTitle implements Interaction {

    private final String title;

    public SetTitle(String title) {
      this.title = title;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
      actor.attemptsTo(Enter.theValue(title)
          .into(WorkPage.TITLE_FIELD)
          .thenHit(TAB));
    }
  }


  public static class SetContents implements Interaction {

    private final String contents;

    public SetContents(String contents) {
      this.contents = contents;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
      actor.attemptsTo(Enter.theValue(contents)
          .into(WorkPage.TEXT_FIELD)
          .thenHit(TAB));
    }
  }

}
