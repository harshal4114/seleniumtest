package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class LoginTests extends NewTestTemplate {

    private String expectedErrorMessage = "We don't recognize these credentials. Try again or register a new account.";

    @Test(groups = "login-anonCanLogInAsRegisteredUser")
    public void anonCanLogInAsRegisteredUser() {
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .clickSignInButton()
            .getLoginArea()
            .typeUsername(Configuration.getCredentials().userName10)
            .typePassword(Configuration.getCredentials().password10)
            .clickSignInButtonToSignIn()
            .getTopbar()
            .openNavigation()
            .isUserAvatarVisible();
    }

    @Test(groups = "login-anonCanNotLogInWithInvalidPassword")
    public void anonCanNotLogInWithInvalidPassword() {
         new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .clickSignInButton()
            .getLoginArea()
            .typeUsername(Configuration.getCredentials().userName10)
            .typePassword(Configuration.getCredentials().password11)
            .clickSignInButtonToGetError()
            .verifyErrorMessage(expectedErrorMessage);
    }

    @Test(groups = "login-anonCanNotLogInWithBlankPassword")
    public void anonCanNotLogInWithBlankPassword() {
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .clickSignInButton()
            .getLoginArea()
            .typeUsername(Configuration.getCredentials().userName10)
            .verifySignInButtonNotClickable();
    }

    @Test(groups = "login-anonCanNotLogInWithInvalidUsername")
    public void anonCanNotLogInWithInvalidUsername() {
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .clickSignInButton()
            .getLoginArea()
            .typeUsername(String.valueOf(DateTime.now().getMillis()))
            .typePassword(Configuration.getCredentials().password10)
            .clickSignInButtonToGetError()
            .verifyErrorMessage(expectedErrorMessage);
    }

    @Test(groups = "login-anonCanNotLogInWithBlankUsername")
    public void anonCanNotLogInWithBlankUsername() {
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .clickSignInButton()
            .getLoginArea()
            .typePassword(Configuration.getCredentials().password10)
            .verifySignInButtonNotClickable();
    }

}
