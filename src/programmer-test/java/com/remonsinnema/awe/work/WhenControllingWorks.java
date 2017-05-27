package com.remonsinnema.awe.work;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;


public class WhenControllingWorks {

  private final WorkController controller = new WorkController();
  private final WorkService service = mock(WorkService.class);

  @Before
  public void init() {
    controller.setService(service);
  }

  @Test
  public void shouldSaveWorkViaService() throws IOException {
    String fileName = "zebra.txt";
    String text = "yak";

    controller.save(fileName, text);

    verify(service).save(fileName, text);
  }

  @Test
  public void shouldLoadWorkViaService() throws IOException {
    String fileName = "whale.txt";
    String text = "velociraptor";
    when(service.load(fileName)).thenReturn(text);

    String actual = controller.load(fileName);

    assertEquals("Text", text, actual);
  }

}
