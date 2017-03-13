package com.wikia.webdriver.common.remote.operations;

import com.google.common.collect.ImmutableMap;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.context.CategoryContext;
import com.wikia.webdriver.common.remote.operations.http.PostRemoteOperation;
import org.json.JSONObject;

public class RenameCategory {

  public static final String RENAME_CATEGORY_URL_SUFFIX = "%s/forums/%s";

  private final PostRemoteOperation remoteOperation;

  RenameCategory(User user) {
    remoteOperation = new PostRemoteOperation(user);
  }

  public void execute(final CategoryContext context) {
    JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
        .put("name", context.getCategoryName())
        .build());

    remoteOperation.execute(buildUrl(context), jsonObject);
  }

  private String buildUrl(final CategoryContext context) {
    return Discussions.service(String.format(RENAME_CATEGORY_URL_SUFFIX, context.getSiteId(), context.getCategoryId()));
  }
}
