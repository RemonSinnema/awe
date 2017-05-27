package com.remonsinnema.awe.work;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/work", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkController {

  private WorkService service;

  @Autowired
  void setService(WorkService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.POST)
  public void save(@RequestBody WorkDto work) throws IOException {
    service.save(work.getFileName(), work.getText());
  }

}
