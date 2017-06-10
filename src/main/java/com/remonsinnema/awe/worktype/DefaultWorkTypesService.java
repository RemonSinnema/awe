package com.remonsinnema.awe.worktype;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;


@Service
public class DefaultWorkTypesService implements WorkTypesService {

  private static final Logger LOG = Logger.getLogger(DefaultWorkTypesService.class);

  private final Collection<JSONObject> cachedWorkTypes = new ArrayList<>();

  @Override
  public Iterable<String> getTypes() {
    return propertyValues(workTypes(), "type");
  }

  private List<String> propertyValues(Stream<JSONObject> objects, String property) {
    return objects
        .filter(object -> object.containsKey(property))
        .map(object -> object.get(property).toString())
        .sorted()
        .collect(Collectors.toList());
  }

  private Stream<JSONObject> workTypes() {
    if (cachedWorkTypes.isEmpty()) {
      JSONParser jsonParser = new JSONParser();
      PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      try {
        for (Resource resource : resolver.getResources("classpath:worktypes/*.json")) {
          try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            cachedWorkTypes.add((JSONObject)jsonParser.parse(reader));
          }
        }
      } catch (IOException | ParseException e) {
        LOG.error("Failed to retrieve work types", e);
      }
    }
    return cachedWorkTypes.stream();
  }

  @Override
  public Iterable<String> getCategoriesFor(String type) {
    return propertyValues(workTypes()
        .filter(workType -> isType(workType, type)), "category");
  }

  private boolean isType(JSONObject workType, String type) {
    return isProperty(workType, "type", type);
  }

  private boolean isProperty(JSONObject object, String name, String value) {
    return value.equals(object.get(name).toString());
  }

  @Override
  public Iterable<String> getSubcategoriesFor(String type, String category) {
    return propertyValues(workTypes()
        .filter(workType -> isType(workType, type) && isCategory(workType, category)), "sub-category");
  }

  private boolean isCategory(JSONObject workType, String category) {
    return isProperty(workType, "category", category);
  }

}
