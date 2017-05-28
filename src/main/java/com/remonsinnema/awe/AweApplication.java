package com.remonsinnema.awe;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication
@Controller
public class AweApplication {

  public static void main(String[] args) {
    SpringApplication.run(AweApplication.class, args);
  }

  @RequestMapping(path = "/")
  public void index(HttpServletResponse response) throws IOException {
    staticResource("index.html", response);
  }

  @RequestMapping(path = "/static/{name}")
  public void staticResource(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
    try (InputStream content = getClass().getResourceAsStream("/static/" + name)) {
      IOUtils.copy(content, response.getOutputStream());
    }
  }

}
