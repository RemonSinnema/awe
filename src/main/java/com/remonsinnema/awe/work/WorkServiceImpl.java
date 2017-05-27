package com.remonsinnema.awe.work;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class WorkServiceImpl implements WorkService {

  private static final Logger LOG = Logger.getLogger(WorkServiceImpl.class);

  @Value("${work.directory}")
  private String workPath;
  private File workDirectory;

  @PostConstruct
  public void init() throws IOException {
    setWorkDirectory(workPath);
  }

  @Override
  public void setWorkDirectory(String workDirectory) throws IOException {
    this.workDirectory = ensureDirectory(workDirectory);
    LOG.info("work directory: " + this.workDirectory);
  }

  private File ensureDirectory(String path) throws IOException {
    File result = new File(path);
    if (!result.isDirectory() && !result.mkdirs() && !result.isDirectory()) {
      throw new IOException("Could not create directory: " + path);
    }
    return result;
  }

  @Override
  public void save(String fileName, String text) throws IOException {
    try (Writer writer = new OutputStreamWriter(new FileOutputStream(fileOf(fileName)), StandardCharsets.UTF_8)) {
      writer.write(text);
    }
  }

  private File fileOf(String fileName) {
    return new File(workDirectory, fileName);
  }

}