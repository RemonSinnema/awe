package ui;

import net.serenitybdd.screenplay.targets.Target;


public interface NewWorkPage {

  Target TYPE_FIELD = Target.the("the Type dropdown").locatedBy("#type");
  Target CATEGORY_FIELD = Target.the("the Type dropdown").locatedBy("#category");
  Target SUBCATEGORY_FIELD = Target.the("the Type dropdown").locatedBy("#subcategory");

}
