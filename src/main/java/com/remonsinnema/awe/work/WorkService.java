package com.remonsinnema.awe.work;

import java.io.IOException;


public interface WorkService {

  void setWorkDirectory(String path);

  void save(String fileName, String text) throws IOException;

}
