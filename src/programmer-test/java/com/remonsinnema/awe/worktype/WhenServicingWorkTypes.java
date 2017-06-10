package com.remonsinnema.awe.worktype;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

import org.junit.Test;


public class WhenServicingWorkTypes {

  private final WorkTypesService service = new DefaultWorkTypesService();

  @Test
  public void shouldReturnTypesFromJson() {
    assertItems("Types", service.getTypes(), "poem", "prose");
  }

  private void assertItems(String message, Iterable<String> actual, String... expected) {
    Collection<String> needed = new TreeSet<>(Arrays.asList(expected));
    for (String value : actual) {
      needed.remove(value);
    }
    assertTrue("Missing items: " + needed, needed.isEmpty());
  }

  @Test
  public void shouldReturnCategoriesForType() {
    assertItems("Categories", service.getCategoriesFor("poem"), "sonnet");
  }

  @Test
  public void shouldReturnSubcategoriesForTypeAndCategory() {
    assertItems("Subcategories", service.getSubcategoriesFor("poem", "sonnet"), "shakespearian");
  }

}
