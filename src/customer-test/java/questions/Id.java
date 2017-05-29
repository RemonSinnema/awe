package questions;

import static net.serenitybdd.screenplay.questions.UIFilter.visible;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;

import java.util.List;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.TargetedUIState;
import net.serenitybdd.screenplay.questions.UIStateReaderBuilder;
import net.serenitybdd.screenplay.targets.Target;


public class Id extends TargetedUIState<String> {

  public Id(Target target, Actor actor) {
    super(target, actor);
  }

  public static UIStateReaderBuilder<Id> of(Target target) {
    return new UIStateReaderBuilder<>(target, Id.class);
  }

  @Override
  public String resolve() {
    return target.resolveFor(actor).getAttribute("id");
  }

  @Override
  public List<String> resolveAll() {
    return extract(visible(target.resolveAllFor(actor)), on(WebElementFacade.class).getAttribute("id"));
  }

}
