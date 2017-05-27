package com.remonsinnema.awe.work;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;


public class WhenServicingWorks {

  private static final String WORK_DIRECTORY = "build/unit-tests/work";

  private final WorkService service = new WorkServiceImpl();

  @Before
  public void init() throws IOException {
    service.setWorkDirectory(WORK_DIRECTORY);
  }

  @Test
  public void shouldSaveWorkToFileSystem() throws IOException {
    String fileName = "ape.txt";
    String contents = "bear cheatah dingo";

    service.save(fileName, contents);

    File file = new File(WORK_DIRECTORY, fileName);
    assertTrue("Work not saved to file", file.isFile());
    try (InputStream input = new FileInputStream(file)) {
      assertEquals("File contents", contents, IOUtils.toString(input, StandardCharsets.UTF_8));
    }
  }

}
