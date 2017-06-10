package com.remonsinnema.awe.worktype;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/work-types")
public class WorkTypesController {

  private static final String NL = System.getProperty("line.separator");
  private static final String START_ROW = "<tr><td>";
  private static final String START_CELL = "</td><td>";
  private static final String END_ROW = "</td></tr>";

  private WorkTypesService service;

  @Autowired
  void setService(WorkTypesService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
  public String getWorkTypes() {
    StringBuilder result = new StringBuilder(512);
    result.append("<table>").append(NL).append(START_ROW).append(labelFor("type", "Type"))
        .append(START_CELL).append("<select id='type' onchange='typeChanged()'>");
    for (String type : service.getTypes()) {
      result.append("<option value='").append(type).append("'>").append(type).append("</option>");
    }
    result.append("</select>").append(END_ROW).append(NL).append(START_ROW).append(labelFor("category", "Category"))
        .append(START_CELL).append("<select id='category' onchange='categoryChanged()'></select>").append(END_ROW)
        .append(NL).append(START_ROW).append(labelFor("subcategory", "Sub-category"))
        .append(START_CELL).append("<select id='subcategory'></select>").append(END_ROW)
        .append(NL).append("</table>").append(NL);
    return result.toString();
  }

  private String labelFor(String id, String label) {
    String key = label.substring(0, 1);
    return String.format("<label for='%s' accesskey='%s'><u>%s</u>%s:</label>", id, key, key, label.substring(1));
  }

  @RequestMapping(path = "/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public String getCategoriesFor(@PathVariable("type") String type) {
    return toJsonArrayAsString(service.getCategoriesFor(type));
  }

  @SuppressWarnings("unchecked")
  private String toJsonArrayAsString(Iterable<String> items) {
    JSONArray result = new JSONArray();
    items.forEach(result::add);
    return result.toJSONString();
  }

  @RequestMapping(path = "/{type}/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public String getSubcategoriesFor(@PathVariable("type") String type, @PathVariable("category") String category) {
    return toJsonArrayAsString(service.getSubcategoriesFor(type, category));
  }

}
