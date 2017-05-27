package com.remonsinnema.awe.acceptance.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import com.remonsinnema.awe.acceptance.action.Set;


public class Write implements Task {

  private final String text;

  public Write(String text) {
    this.text = text;
  }

  @Override
  @Step("{0} writes '#text'")
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Set.theContentsTo(text));
  }

  public static Performable text(String contents) {
    return instrumented(Write.class, contents);
  }

}
