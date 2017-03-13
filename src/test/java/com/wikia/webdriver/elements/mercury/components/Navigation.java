package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.elements.mercury.pages.login.RegisterPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Navigation {

  @FindBy(css = ".wikia-nav--login")
  private WebElement signInRegisterButton;

  @FindBy(css = ".wikia-nav__back")
  private WebElement backButton;

  @FindBy(css = ".nav-menu__item.nav-menu--root")
  private List<WebElement> subMenuLinks;

  @FindBy(css = "li.nav-menu__item a")
  private List<WebElement> localNavPageLinks;

  @FindBy(css = ".wikia-nav__header")
  private WebElement navigationMainHeader;

  @FindBy(css = ".nav-menu--games")
  private WebElement gamesHub;

  @FindBy(css = ".nav-menu--movies")
  private WebElement moviesHub;

  @FindBy(css = ".nav-menu--tv")
  private WebElement tvHub;

  @FindBy(css = ".wikia-nav__avatar")
  private WebElement userAvatar;

  @FindBy(css = ".wikia-nav--profile-link")
  private WebElement userProfileLink;

  @FindBy(css = ".wikia-nav--logout")
  private WebElement logoutLink;

  @FindBy(css = ".nav-menu__header")
  private WebElement exploreWikiHeader;

  private By navigationComponent = By.cssSelector(".side-nav-menu");
  private WebDriver driver;
  private Wait wait;
  private Loading loading;

  public Navigation(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);
    this.loading = new Loading(driver);

    PageFactory.initElements(driver, this);
  }

  public RegisterPage clickOnSignInRegisterButton() {
    PageObjectLogging.logInfo("Open login page");
    wait.forElementClickable(signInRegisterButton);
    signInRegisterButton.click();

    return new RegisterPage(driver);
  }

  public Navigation clickBackButton() {
    PageObjectLogging.logInfo("Go back to previous navigation level");
    wait.forElementClickable(backButton);
    backButton.click();

    return this;
  }

  public Navigation clickExploreWikiHeader(Skin fromSkin) {
    PageObjectLogging.logInfo("Click 'Explore Wiki' header");
    wait.forElementClickable(exploreWikiHeader);

    exploreWikiHeader.click();

    // Mobile wiki opens the main page using AJAX, Mercury reloads the page and opens Mobile Wiki
    if (fromSkin == Skin.MOBILE_WIKI) {
      loading.handleAsyncPageReload();
    } else {
      new SkinHelper(driver).isSkin(Skin.MERCURY);
    }

    return this;
  }

  public Navigation closeSubMenu() {
    PageObjectLogging.logInfo("Close sub-menu");
    wait.forElementClickable(backButton);
    backButton.click();

    return this;
  }

  public Navigation openSubMenu(int index) {
    PageObjectLogging.logInfo("Open sub-menu no.: " + index);
    WebElement wikiMenuLink = subMenuLinks.get(index);
    wait.forElementClickable(wikiMenuLink);
    wikiMenuLink.click();

    return this;
  }

  public Navigation openPageLink(int index) {
    String oldUrl = driver.getCurrentUrl();

    PageObjectLogging.logInfo("Open link to page no.: " + index);
    WebElement pageLink = localNavPageLinks.get(index);
    wait.forElementClickable(pageLink);
    pageLink.click();
    loading.handleAsyncPageReload();

    PageObjectLogging.logInfo("Navigation is closed");
    wait.forElementNotVisible(navigationComponent);

    Assertion.assertFalse(oldUrl.equalsIgnoreCase(driver.getCurrentUrl()),
                          "Navigation to selected page failed");
    PageObjectLogging.logInfo("Successfully navigated to selected page");


    return this;
  }

  public boolean isMainHeaderVisible() {
    return isElementVisible(navigationMainHeader);
  }

  public boolean isBackButtonVisible() {
    return isElementVisible(backButton);
  }

  public boolean isUserAvatarVisible() {
    return isElementVisible(userAvatar);
  }

  public boolean isUserProfileLinkVisible() {
    return isElementVisible(userProfileLink);
  }

  public boolean isLogoutLinkVisible() {
    return isElementVisible(logoutLink);
  }

  public boolean areHubLinksVisible() {
    return isElementVisible(gamesHub)
        && isElementVisible(moviesHub)
        && isElementVisible(tvHub);
  }

  private boolean isElementVisible(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public String getNavigationHeaderText() {
    wait.forElementVisible(navigationMainHeader);

    return navigationMainHeader.getText();
  }

}
