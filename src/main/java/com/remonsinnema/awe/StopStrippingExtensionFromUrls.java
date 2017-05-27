package com.remonsinnema.awe;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class StopStrippingExtensionFromUrls extends WebMvcConfigurerAdapter {

  @Override
  public void configurePathMatch(PathMatchConfigurer matcher) {
    matcher.setUseSuffixPatternMatch(false);
  }

}
