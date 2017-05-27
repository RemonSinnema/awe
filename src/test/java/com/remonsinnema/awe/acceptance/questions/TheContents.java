package com.remonsinnema.awe.acceptance.questions;

import static net.serenitybdd.core.steps.Instrumented.instanceOf;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import com.remonsinnema.awe.acceptance.abilities.BrowseTheFileSystem;


@Subject("the contents of the file '#fileName'")
public class TheContents implements Question<String> {

  private final String fileName;

  @Override
  public String answeredBy(Actor actor) {
    return actor.abilityTo(BrowseTheFileSystem.class).readContentsOf("work/" + fileName);
  }

  public TheContents(String fileName) {
    this.fileName = fileName;
  }

  public static Question<String> ofTheFile(String fileName) {
    return instanceOf(TheContents.class).withProperties(fileName);
  }

}
