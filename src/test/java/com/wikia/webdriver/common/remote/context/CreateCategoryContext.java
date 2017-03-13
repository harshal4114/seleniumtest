package com.wikia.webdriver.common.remote.context;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateCategoryContext extends RemoteContext {

  private final String categoryName;

  @Builder
  public CreateCategoryContext(String siteId, String categoryName) {
    super(siteId);
    this.categoryName = categoryName;
  }

  public static CreateCategoryContext defaultContextUsing(final String siteId, final String categoryName) {
    return CreateCategoryContext.builder()
        .siteId(siteId)
        .categoryName(categoryName)
        .build();
  }

}
