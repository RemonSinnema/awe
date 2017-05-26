package com.remonsinnema.awe.acceptance.action;

import static org.openqa.selenium.Keys.TAB;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;

import com.remonsinnema.awe.acceptance.ui.WorkPage;


public class Set implements Interaction {

  private final String title;

  public Set(String title) {
    this.title = title;
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Enter.theValue(title)
        .into(WorkPage.TITLE_FIELD)
        .thenHit(TAB));
  }

  public static Performable theTitleTo(String title) {
    return instrumented(Set.class, title);
  }

}
