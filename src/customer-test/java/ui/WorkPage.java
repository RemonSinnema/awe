package ui;

import net.serenitybdd.screenplay.targets.Target;


public interface WorkPage {

  Target TITLE_FIELD = Target.the("the Title field").locatedBy("#title");
  Target TEXT_FIELD = Target.the("the Text field").locatedBy("#text");
  Target SAVE_BUTTON = Target.the("the Save button").locatedBy("#save");
  Target LOAD_BUTTON = Target.the("the Load button").locatedBy("#load");

}
