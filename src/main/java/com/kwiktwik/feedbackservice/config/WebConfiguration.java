package com.kwiktwik.feedbackservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAsync
public class WebConfiguration extends WebMvcConfigurerAdapter {

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasename("messages/messages");
    source.setUseCodeAsDefaultMessage(true);
    source.setDefaultEncoding("UTF-8");
    return source;
  }
}


