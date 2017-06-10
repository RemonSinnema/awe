package features;

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

import tasks.Start;
import tasks.StartNewWork;
import tasks.Stop;


@RunWith(SerenityRunner.class)
public class WhenWritingWithStructure {

  @Managed(driver = "phantomjs")
  private WebDriver hisBrowser;
  private final Actor allen = Actor.named("Allen");

  @Before
  public void init() {
    allen.can(BrowseTheWeb.with(hisBrowser));

    givenThat(allen).wasAbleTo(Start.writing());
  }

  @After
  public void done() {
    allen.attemptsTo(Stop.writing());
  }

  @Test
  public void shouldSaveAndLoadWorkOfFixedStructure() {
    when(allen).attemptsTo(StartNewWork.ofType("poem", "sonnet", "shakespearian"));
    // TODO: then(allen).should(seeThat(TheStructured.editors(), contains("h1", "st1", "st2", "st3", "st4")));
  }

}
