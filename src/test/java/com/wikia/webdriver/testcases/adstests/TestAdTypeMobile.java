package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdTypeDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * https://www.google.com/dfp/5441#delivery/OrderDetail/orderId=245575332
 */
public class TestAdTypeMobile extends MobileTestTemplate {

  private static final String SRC = "mobile";

  @Test(
      groups = "AdTypeMercury",
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncSuccessWithAd"
  )
  public void adsAdTypeAsyncSuccessWithAd(String wikiName, String article, String adUnit,
                                          String slotName, String imgUrl) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.waitForSlot(slotName);
    ads.verifyGptIframe(adUnit, slotName, SRC);
    ads.verifyImgAdLoadedInSlot(slotName, imgUrl);
  }

  @Test(
      groups = "AdTypeMercury",
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncHopNoAd"
  )
  public void adsAdTypeAsyncHopWithoutAd(String wikiName, String article, String adUnit,
                                       String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotName));
    ads.verifyGptIframe(adUnit, slotName, SRC);
    ads.verifyNoAdInSlot(slotName);
  }

  @Test(
      groups = "AdTypeMercury",
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncSuccessNoAd"
  )
  public void adsAdTypeAsyncSuccessNoAd(String wikiName, String article, String adUnit,
                                              String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotName));
    ads.verifyGptIframe(adUnit, slotName, SRC);
    ads.verifySlotExpanded(slotName);
  }

  @Test(
      groups = "AdTypeMercury",
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncHopWithAd"
  )
  public void adsAdTypeAsyncHopWithAd(String wikiName, String article, String adUnit,
                                           String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotName));
    ads.verifyGptIframe(adUnit, slotName, SRC);
    ads.verifyNoAdInSlot(slotName);
  }

  @Test(
      groups = "AdTypeMercury",
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncSuccessAndHop"
  )
  public void adsAdTypeAsyncSuccessAndHop(
      String wikiName, String article, String adUnit, String slotNameWithAd, String imgUrl,
      String slotNameWithoutAd
  ) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotNameWithAd));
    ads.wait.forElementPresent(By.id(slotNameWithoutAd));
    ads.verifyGptIframe(adUnit, slotNameWithAd, SRC);
    ads.verifyGptIframe(adUnit, slotNameWithoutAd, SRC);
    ads.verifyImgAdLoadedInSlot(slotNameWithAd, imgUrl);
    ads.verifyNoAdInSlot(slotNameWithoutAd);
  }

  @Test(
      groups = "AdTypeMercury",
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "forcedSuccessNoAd"
  )
  public void adsAdTypeAsyncForcedSuccess(String wikiName, String article, String adUnit,
                                               String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.waitForSlot(slotName);
    ads.verifyGptIframe(adUnit, slotName, SRC);
    ads.verifySlotExpanded(slotName);
  }

  @Test(
      groups = "AdTypeMercury",
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "inspectIframeImg"
  )
  public void adsAdTypeInspectIframe(String wikiName, String article, String adUnit,
                                                 String slotName, String imgUrl) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotName));
    ads.verifyGptIframe(adUnit, slotName, SRC);
    ads.verifyImgAdLoadedInSlot(slotName, imgUrl);
  }
}
