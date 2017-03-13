package com.wikia.webdriver.testcases.signuptests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.signup.RegisterArea;
import com.wikia.webdriver.elements.oasis.components.notifications.BannerNotifications;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AuthModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;

@Test(groups = "auth-signUp")
public class SignUpTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "SignUp_anonCanNotSignUpIfSheIsYoungerThanTwelve")
  public void anonCanNotSignUpIfSheIsYoungerThanTwelve() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.navigateToSpecialSignUpPage(wikiURL);
    RegisterArea register = new RegisterArea(false);
    register.typeUsername(register.getTimeStamp());
    register.typeEmailAddress(credentials.emailQaart1);
    register.typePassword(register.getTimeStamp());
    Calendar currentDate = Calendar.getInstance();
    register.typeBirthdate(
        // +1 because months are numerated from 0
        Integer.toString(currentDate.get(Calendar.MONTH) + 1),
        Integer.toString(currentDate.get(Calendar.DAY_OF_MONTH)),
        Integer.toString(currentDate.get(Calendar.YEAR) - PageContent.MIN_AGE));
    register.clickSignUpSubmitButton();
    register.verifyBirthdateError();
  }

  @Test(groups = "SignUp_anonCanNotSignUpIfTheUsernameAlreadyExists")
  public void anonCanNotSignUpIfTheUsernameAlreadyExists() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.navigateToSpecialSignUpPage(wikiURL);
    RegisterArea register = new RegisterArea(false);
    String password = "Pass" + register.getTimeStamp();
    String email = credentials.emailQaart2;
    register.typeEmailAddress(email);
    register.typeUsername(credentials.userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);

    register.clickSignUpSubmitButton();
    Assertion.assertTrue(register.doesErrorMessageContainText());

  }

  @Test(groups = "SignUp_anonCanSignUpOnNewAuthModalFromGlobalNav")
  public void anonCanSignUpOnNewAuthModalFromGlobalNav() {
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar registerLink = new NavigationBar(driver);
    registerLink.clickOnRegister();
    RegisterArea register = registerLink.getRegisterArea();
    register.switchToAuthModalHandle();
    String userName = "User" + register.getTimeStamp();
    String password = "Pass" + register.getTimeStamp();
    String email = credentials.emailQaart2;
    register.typeEmailAddress(email);
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);

    register.clickSignUpSubmitButton();
    register.switchToMainWindowHandle();

    base.verifyUserLoggedIn(userName);
  }

  @Test(groups = "SignUp_anonCanSignUpWithoutConfirmingVerificationEmail")
  public void anonCanSignUpWithoutConfirmingVerificationEmail() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUp = base.navigateToSpecialSignUpPage(wikiURL);

    String userName = "User" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    RegisterArea register = new RegisterArea(false);
    register.typeEmailAddress(email);
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.clickSignUpSubmitButton();
    base.verifyUserLoggedIn(userName);
    BannerNotifications notification = new BannerNotifications();
    String bannerNotifMessageEN = "Oh no! Your email address has not yet been confirmed. "
                                  + "You should have a confirmation message in your inbox. "
                                  + "Didn't get it? Click here and we'll send a new one. "
                                  + "If you need to change your address, "
                                  + "head to your Preferences page.";
    Assertion.assertEquals(
        notification.getBannerNotificationTextEmailNotConfirmed(), bannerNotifMessageEN);
  }

  @Test(groups = "SignUp_userCanLoginWithoutConfirmingVerificationEmail")
  public void userCanLoginWithoutConfirmingVerificationEmail() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUp = base.navigateToSpecialSignUpPage(wikiURL);

    String userName = "User" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    RegisterArea register = new RegisterArea(false);
    register.typeEmailAddress(email);
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.clickSignUpSubmitButton();
    base.verifyUserLoggedIn(userName);
    base.logOut();

    NavigationBar signInLink = new NavigationBar(driver);
    signInLink.clickOnSignIn();
    AuthModal authModal = signInLink.getAuthModal();
    Assert.assertTrue(authModal.isSignInOpened());

    authModal.login(userName, password);
  }

  @Test(groups = "SignUp_anonCanSignUpWithUsernameContainingJapaneseSpecialCharacters")
  @Execute(onWikia = "ja.ja-test")
  public void anonCanSignUpWithUsernameContainingJapaneseSpecialCharacters() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUp = base.navigateToSpecialSignUpPage(wikiURL);
    signUp.disableCaptcha();
    String userName = "ユーザー" + signUp.getTimeStamp();
    String password = "パス" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;

    RegisterArea register = new RegisterArea(false);
    register.typeEmailAddress(email);
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.clickSignUpSubmitButton();
    base.verifyUserLoggedIn(userName);
    BannerNotifications notification = new BannerNotifications();
    String banerNotifMessageJapanese = "メールアドレスの認証が完了していないようです。受信トレイの確認メー"
                                       + "ルをチェックしてみてください。確認メールが見つからない場合は、こ"
                                       + "こをクリックすると新しい確認メールが送信されます。メールアドレス"
                                       + "を変更する必要がある場合は、"
                                       + "「個人設定」ページにアクセスしてください。";
    Assertion.assertEquals(
        notification.getBannerNotificationTextEmailNotConfirmed(), banerNotifMessageJapanese);
  }
}
