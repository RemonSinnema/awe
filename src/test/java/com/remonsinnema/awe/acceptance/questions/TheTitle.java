package com.remonsinnema.awe.acceptance.questions;

import static net.serenitybdd.core.steps.Instrumented.instanceOf;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

import com.remonsinnema.awe.acceptance.ui.WorkPage;


@Subject("the work's title")
public class TheTitle implements Question<String> {

  @Override
  public String answeredBy(Actor actor) {
    return Text.of(WorkPage.TITLE_FIELD)
        .viewedBy(actor)
        .asString();
  }

  public static Question<String> ofTheWork() {
    return instanceOf(TheTitle.class).newInstance();
  }

}
