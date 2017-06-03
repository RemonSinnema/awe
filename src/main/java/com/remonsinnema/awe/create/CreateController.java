package com.remonsinnema.awe.create;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/create")
public class CreateController {

  /*
  private CreateService service;

  @Autowired
  void setService(CreateService service) {
    this.service = service;
  }
  */

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
  public String startCreateWizard() {
    // TODO: call service to get types, categories, and sub-categories. Then build suitable HTML for selecting those.
    return "<select id='type'/>";
  }

}
