package com.remonsinnema.awe.acceptance.questions;

import static net.serenitybdd.core.steps.Instrumented.instanceOf;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Value;

import com.remonsinnema.awe.acceptance.ui.WorkPage;


@Subject("the work's text")
public class TheText implements Question<String> {

  @Override
  public String answeredBy(Actor actor) {
    return Value.of(WorkPage.CONTENTS_FIELD)
        .viewedBy(actor)
        .asString();
  }

  public static Question<String> ofTheWork() {
    return instanceOf(TheText.class).newInstance();
  }

}
