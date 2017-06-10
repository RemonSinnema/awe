package com.remonsinnema.awe.worktype;


public interface WorkTypesService {

  Iterable<String> getTypes();

  Iterable<String> getCategoriesFor(String type);

  Iterable<String> getSubcategoriesFor(String type, String category);

}
