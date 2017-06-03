package com.remonsinnema.awe;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
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


  private final Map<String, String> contentTypeByExtension = new HashMap<>();

  @PostConstruct
  public void init() {
    contentTypeByExtension.put("css", "text/css");
    contentTypeByExtension.put("html", "text/html");
    contentTypeByExtension.put("js", "text/javascript");
    contentTypeByExtension.put("png", "image/png");
  }

  @RequestMapping(path = "/")
  public void index(HttpServletResponse response) throws IOException {
    staticResource("index.html", response);
  }

  @RequestMapping(path = "/static/{name}")
  public void staticResource(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
    response.setHeader("Content-Type", typeOf(name));
    try (InputStream content = getClass().getResourceAsStream("/static/" + name)) {
      IOUtils.copy(content, response.getOutputStream());
    }
  }

  private String typeOf(String name) {
    String ext = name.substring(1 + name.lastIndexOf('.'));
    return contentTypeByExtension.getOrDefault(ext, "text/plain");
  }

}
