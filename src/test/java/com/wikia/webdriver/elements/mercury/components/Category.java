package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.pages.CategoryPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Category {

  @FindBy(css = ".article-footer .collapsible-menu div")
  private WebElement categoryMenu;

  @FindBy(css = ".article-footer .collapsible-menu ul")
  private WebElement categoryList;

  private WebDriver driver;
  private Wait wait;
  private Loading loading;

  public Category(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);
    this.loading = new Loading(driver);

    PageFactory.initElements(driver, this);
  }

  public Category toggleMenu() {
    wait.forElementClickable(categoryMenu);
    categoryMenu.click();

    PageObjectLogging.logInfo("Category component was toggled");

    return this;
  }

  public CategoryPage openCategoryPage(String name) {
    WebElement link = categoryList.findElement(
        By.cssSelector(String.format("a[title=\"%s\"]", name))
    );

    PageObjectLogging.logInfo(String.format("Open category page named: \"%s\".", name));
    wait.forElementClickable(link);
    link.click();
    loading.handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("/wiki/Category:"),
                         "Url is different than /wiki/Category:");
    PageObjectLogging.logInfo("You were redirected to /wiki/Category:");

    return new CategoryPage();
  }
}
