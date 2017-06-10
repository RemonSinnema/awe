package com.remonsinnema.awe.worktype;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.json.simple.JSONArray;
import org.junit.Before;
import org.junit.Test;


public class WhenControllingWorkTypes {

  private static final String SELECTS
      = "<table>%n"
      + "<tr><td><label for='type' accesskey='T'><u>T</u>ype:</label></td>"
      + "<td><select id='type' onchange='typeChanged()'>%s</select></td></tr>%n"
      + "<tr><td><label for='category' accesskey='C'><u>C</u>ategory:</label></td><td>"
      + "<select id='category' onchange='categoryChanged()'></select></td></tr>%n"
      + "<tr><td><label for='subcategory' accesskey='S'><u>S</u>ub-category:</label></td>"
      + "<td><select id='subcategory'></select></td></tr>%n"
      + "</table>%n";

  private final WorkTypesService service = mock(WorkTypesService.class);
  private final WorkTypesController controller = new WorkTypesController();

  @Before
  public void init() {
    controller.setService(service);
  }

  @Test
  public void shouldFormatTypesAsHtmlSelect() {
    when(service.getTypes()).thenReturn(Arrays.asList("ape", "bear"));

    String html = controller.getWorkTypes();

    assertEquals("HTML", String.format(SELECTS, "<option value='ape'>ape</option><option value='bear'>bear</option>"),
        html);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void shouldReturnCategoriesForType() {
    String type = "cheetah";
    String category1 = "dingo";
    String category2 = "elephant";
    when(service.getCategoriesFor(type)).thenReturn(Arrays.asList(category1, category2));
    JSONArray expected = new JSONArray();
    expected.add(category1);
    expected.add(category2);

    String actual = controller.getCategoriesFor(type);

    assertEquals("JSON", expected.toJSONString(), actual);
  }

}
