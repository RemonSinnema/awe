package ui;

import net.serenitybdd.screenplay.targets.Target;


public interface Prompt  {

  Target OK_BUTTON = Target.the("the OK button").locatedBy("#ok");
  Target ANSWER_FIELD = Target.the("the Answer field").locatedBy("#answer");

}
