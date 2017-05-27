package features;

import static org.hamcrest.CoreMatchers.is;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
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

import abilities.BrowseTheFileSystem;
import questions.TheContents;
import questions.TheText;
import questions.TheTitle;
import tasks.Load;
import tasks.Name;
import tasks.Save;
import tasks.Start;
import tasks.Stop;
import tasks.Write;


@RunWith(SerenityRunner.class)
public class WhenPersistingWritings {

  private static final String TITLE = "Once upon a time";
  private static final String TEXT = "The end.";
  private static final String FILE_NAME = "once-upon-a-time.txt";

  @Managed(driver = "phantomjs")
  private WebDriver theBrowser;
  private final Actor allen = Actor.named("Allen");

  @Before
  public void init() {
    allen.can(BrowseTheWeb.with(theBrowser));
    allen.can(BrowseTheFileSystem.in("build/acceptance-tests"));
    givenThat(allen).wasAbleTo(Start.writing());
  }

  @After
  public void done() {
    allen.attemptsTo(Stop.writing());
  }

  @Test
  public void shouldSaveAndLoadWork() {
    when(allen).attemptsTo(Name.theWork(TITLE));
    then(allen).should(seeThat(TheTitle.ofTheWork(), is(TITLE)));

    when(allen).attemptsTo(Write.text(TEXT));
    then(allen).should(seeThat(TheText.ofTheWork(), is(TEXT)));

    when(allen).attemptsTo(Save.theWork());
    then(allen).should(seeThat(TheContents.ofTheFile(FILE_NAME), is(TEXT)));

    when(allen).attemptsTo(
        Name.theWork("Er was eens"),
        Write.text("En ze leefden nog lang en gelukkig."),
        Load.theWorkFrom(FILE_NAME));
    then(allen).should(
        seeThat(TheTitle.ofTheWork(), is(TITLE)),
        seeThat(TheText.ofTheWork(), is(TEXT)));
  }

}
