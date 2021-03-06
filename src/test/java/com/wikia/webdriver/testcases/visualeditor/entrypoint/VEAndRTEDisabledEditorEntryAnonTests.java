package com.wikia.webdriver.testcases.visualeditor.entrypoint;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VEAndRTEDisabledEditorEntryAnonTests extends NewTestTemplate {

  WikiBasePageObject base;
  String wikiURL;

  @BeforeMethod(alwaysRun = true)
  public void setup_VEPreferred() {
    wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_AND_RTE_DISABLED_WIKI);
    base = new WikiBasePageObject();
  }

  @Test(
      groups = {"VEAndRTEDisabledEditorEntryAnonTests", "VEAndRTEDisabledEditorEntryAnonTests_001",
                "createPageEntry"}
  )
  public void VEAndRTEDisabledEditorEntryAnonTests_001_CreatePageEntry() {
    String articleName = base.getNameForArticle();
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditorPageObject ve = article.createArticleInVEUsingDropdown(articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEAndRTEDisabledEditorEntryAnonTests", "VEAndRTEDisabledEditorEntryAnonTests_002",
                "articleEditEntry"}
  )
  public void VEAndRTEDisabledEditorEntryAnonTests_002_MainEditEntry() {
    ArticlePageObject article =
        new ArticlePageObject().open(base.getNameForArticle());
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEAndRTEDisabledEditorEntryAnonTests", "VEAndRTEDisabledEditorEntryAnonTests_003",
                "redlinkEntry"}
  )
  public void VEAndRTEDisabledEditorEntryAnonTests_003_RedlinkEntry() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.TESTINGPAGE);
    VisualEditorPageObject ve = article.openVEModeWithRedLinks(0);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEAndRTEDisabledEditorEntryAnonTests", "VEAndRTEDisabledEditorEntryAnonTests_004",
                "sectionEditEntry"}
  )
  public void VEAndRTEDisabledEditorEntryAnonTests_004_SectionEditEntry() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.TESTINGPAGE);
    VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEAndRTEDisabledEditorEntryAnonTests", "VEAndRTEDisabledEditorEntryAnonTests_005",
                "veactionURLEntry"}
  )
  public void VEAndRTEDisabledEditorEntryAnonTests_005_URLEntry() {
    VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEAndRTEDisabledEditorEntryAnonTests", "VEAndRTEDisabledEditorEntryAnonTests_006",
                "listEntry"}
  )
  public void VEAndRTEDisabledEditorEntryAnonTests_006_ListNamespace() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.LIST_PAGE);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEAndRTEDisabledEditorEntryAnonTests", "VEAndRTEDisabledEditorEntryAnonTests_007",
                "categoryEntry"}
  )
  public void VEAndRTEDisabledEditorEntryAnonTests_007_CategoryNamespace() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.CATEGORY_PAGE);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEAndRTEDisabledEditorEntryAnonTests", "VEAndRTEDisabledEditorEntryAnonTests_008",
                "templateEntry"}
  )
  public void VEAndRTEDisabledEditorEntryAnonTests_008_TemplateNamespace() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.TEMPLATE_PAGE);
    SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
    src.verifySourceOnlyMode();
  }

  @Test(
      groups = {"VEAndRTEDisabledEditorEntryAnonTests", "VEAndRTEDisabledEditorEntryAnonTests_009",
                "actionURLEntry"}
  )
  public void VEAndRTEDisabledEditorEntryAnonTests_009_actionEdit() {
    SourceEditModePageObject src =
        base.navigateToArticleEditPageSrc(wikiURL, base.getNameForArticle());
    src.verifySourceOnlyMode();
  }
}
