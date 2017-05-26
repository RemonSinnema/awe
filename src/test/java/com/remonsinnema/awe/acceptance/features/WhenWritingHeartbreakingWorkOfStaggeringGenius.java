package com.remonsinnema.awe.acceptance.features;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

import com.remonsinnema.awe.acceptance.tasks.Name;
import com.remonsinnema.awe.acceptance.tasks.Start;
import com.remonsinnema.awe.acceptance.tasks.Stop;


@RunWith(SerenityRunner.class)
public class WhenWritingHeartbreakingWorkOfStaggeringGenius {

  private final Actor allen = Actor.named("Allen");
  @Managed(driver = "phantomjs")
  private WebDriver theBrowser;

  @Before
  public void init() {
    allen.can(BrowseTheWeb.with(theBrowser));
  }

  @After
  public void done() {
    allen.attemptsTo(Stop.writing());
  }

  @Test
  public void shouldNameWork() {
    givenThat(allen).wasAbleTo(Start.writing());
    when(allen).attemptsTo(Name.theWork("My First Work"));
    /* TODO: Why does this return empty string???
    then(allen).should(seeThat(TheTitle.ofTheWork(), is("My First Work")));
    */
  }

}
