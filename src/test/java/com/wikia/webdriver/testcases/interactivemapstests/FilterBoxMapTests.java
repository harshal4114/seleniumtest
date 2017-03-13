package com.wikia.webdriver.testcases.interactivemapstests;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

import org.testng.annotations.Test;

public class FilterBoxMapTests extends NewTestTemplate {

  @Test(groups = {"FilterBoxMapTests_001", "FilterBoxMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "SUS-140", comment = "test is unstable, check locally or test manually")
  public void FilterBoxMapTests_001_VerifyUncheckedSingleCategory() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickOnSingleEnabledCategory();
    selectedMap.verifyAllPinTypesIsUncheck();
  }

  @Test(groups = {"FilterBoxMapTests_002", "FilterBoxMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "SUS-140", comment = "test is unstable, check locally or test manually")
  public void FilterBoxMapTests_002_VerifyClickOnUncheckedCategory() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickOnSingleEnabledCategory();
    selectedMap.verifyAllPinTypesIsUncheck();
    selectedMap.clickOnSingleDisabledCategory();
    selectedMap.verifyAllPinTypesIsCheck();
  }

  @Test(groups = {"FilterBoxMapTests_003", "FilterBoxMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "SUS-140", comment = "test is unstable, check locally or test manually")
  public void FilterBoxMapTests_003_VerifyClickAllCategoriesUncheckCategories() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickOnAllPinTypes();
    selectedMap.verifyAllPinTypesIsUncheck();
    selectedMap.verifyPinTypesAreUncheck();
  }

  @Test(groups = {"FilterBoxMapTests_004", "FilterBoxMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "SUS-140", comment = "test is unstable, check locally or test manually")
  public void FilterBoxMapTests_004_VerifyClickAllCategoriesCheckAllPinTypes() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickOnSingleEnabledCategory();
    selectedMap.verifyAllPinTypesIsUncheck();
    selectedMap.clickOnAllPinTypes();
    selectedMap.verifyAllPinTypesIsCheck();
    selectedMap.verifyPinTypesAreCheck();
  }
}
