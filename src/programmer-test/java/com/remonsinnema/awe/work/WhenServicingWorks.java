package com.remonsinnema.awe.work;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class WhenServicingWorks {

  private static final String WORK_DIRECTORY = "build/unit-tests/work";

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
  private final WorkService service = new DefaultWorkService();

  @Before
  public void init() {
    service.setWorkDirectory(WORK_DIRECTORY);
  }

  @Test
  public void shouldSaveWorkToFileSystem() throws IOException {
    String fileName = "ape.txt";
    String text = "bear cheatah dingo";

    service.save(fileName, text);

    File file = new File(WORK_DIRECTORY, fileName);
    assertTrue("Work not saved to file", file.isFile());
    try (InputStream input = new FileInputStream(file)) {
      assertEquals("File contents", text, IOUtils.toString(input, StandardCharsets.UTF_8));
    }
  }

  @Test
  public void shouldNotAllowPathManipulation() throws IOException {
    thrown.expect(IllegalArgumentException.class);
    service.save("../foo", "text");
  }

  @Test
  public void shouldNotAllowInvalidPathsForTheWorkingDirectory() {
    thrown.expect(IllegalArgumentException.class);
    service.setWorkDirectory("build/b\0/ar");
  }

  @Test
  public void shouldLoadWorkFromFileSystem() throws IOException {
    String fileName = "elephant.txt";
    String text = "fox giraffe hyena";
    File file = new File(WORK_DIRECTORY, fileName);
    try (Writer output = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
      output.write(text);
    }

    String actual = service.load(fileName);

    assertEquals("Work not loaded from file", text, actual);
  }

}
