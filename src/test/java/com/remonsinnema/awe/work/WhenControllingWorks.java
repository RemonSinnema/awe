package com.remonsinnema.awe.work;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
    WorkDto work = new WorkDto();
    work.setFileName(fileName);
    work.setText(text);

    controller.save(work);

    verify(service).save(fileName, text);
  }

}
