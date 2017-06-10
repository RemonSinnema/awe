package com.remonsinnema.awe.work;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class DefaultWorkService implements WorkService {

  private static final Logger LOG = Logger.getLogger(DefaultWorkService.class);

  @Value("${work.directory}")
  private String workPath;
  private File workDirectory;

  @PostConstruct
  public void init() {
    setWorkDirectory(workPath);
  }

  @Override
  public void setWorkDirectory(String workDirectory) {
    this.workDirectory = ensureDirectory(workDirectory);
    LOG.info("work directory: " + this.workDirectory);
  }

  private File ensureDirectory(String path) {
    File result = new File(path);
    if (!result.isDirectory() && !result.mkdirs()) {
      throw new IllegalArgumentException("Could not create directory: " + path);
    }
    return result;
  }

  @Override
  public void save(String fileName, String text) throws IOException {
    try (Writer writer = new OutputStreamWriter(new FileOutputStream(fileOf(fileName)), StandardCharsets.UTF_8)) {
      writer.write(text);
    }
  }

  private File fileOf(String fileName) throws IOException {
    File result = new File(workDirectory, fileName);
    if (!result.getCanonicalPath().startsWith(workDirectory.getCanonicalPath() + File.separator)) {
      throw new IllegalArgumentException("Invalid file name: " + fileName);
    }
    return result;
  }

  @Override
  public String load(String fileName) throws IOException {
    try (InputStream input = new FileInputStream(fileOf(fileName))) {
      return IOUtils.toString(input, StandardCharsets.UTF_8);
    }
  }

}
