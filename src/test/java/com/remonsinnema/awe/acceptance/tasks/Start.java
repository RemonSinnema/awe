package com.remonsinnema.awe.acceptance.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

import com.remonsinnema.awe.acceptance.action.StartUp;


public class Start implements Task {

  @Override
  @Step("{0} starts writing")
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(StartUp.theServer(),
        Open.url(StartUp.URL));
  }

  public static Performable writing() {
    return instrumented(Start.class);
  }

}
