package abilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import net.serenitybdd.screenplay.Ability;


public class BrowseTheFileSystem implements Ability {

  private final String root;

  public BrowseTheFileSystem(String root) {
    this.root = root;
  }

  public static Ability in(String root) {
    return new BrowseTheFileSystem(root);
  }

  public String readContentsOf(String fileName) {
    File file = new File(root, fileName);
    if (!file.isFile()) {
      return "";
    }
    try (InputStream input = new FileInputStream(file)) {
      return IOUtils.toString(input, StandardCharsets.UTF_8);
    } catch (IOException e) {
      return "";
    }
  }

}
