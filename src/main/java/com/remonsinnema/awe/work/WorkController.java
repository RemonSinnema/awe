package com.remonsinnema.awe.work;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/work/{fileName}")
public class WorkController {

  private WorkService service;

  @Autowired
  void setService(WorkService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
  public String load(@PathVariable("fileName") String fileName) throws IOException {
    return service.load(fileName);
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.TEXT_PLAIN_VALUE)
  public void save(@PathVariable("fileName") String fileName, @RequestBody String text) throws IOException {
    service.save(fileName, text);
  }

}
