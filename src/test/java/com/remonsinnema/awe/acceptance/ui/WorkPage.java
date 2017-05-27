package com.remonsinnema.awe.acceptance.ui;

import net.serenitybdd.screenplay.targets.Target;


public interface WorkPage {

  Target TITLE_FIELD = Target.the("the title field").locatedBy("#title");
  Target CONTENTS_FIELD = Target.the("the contents field").locatedBy("#text");

}
