package com.github.maxfedorov.petstore.ui.pages;

import com.github.maxfedorov.petstore.ui.helpers.ScrollHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RequestBlock extends BasePage {
    By block;

    public RequestBlock(WebDriver driver, By selector) {
        super(driver);
        this.block = selector;
        webDriverWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(block, TRY_IT_OUT_BUTTON));
    }

    private final By TRY_IT_OUT_BUTTON = cssSelector("button.try-out__btn");
    private final By BODY_TEXT_AREA = cssSelector("textarea.body-param__text");
    private final By EXECUTE_BUTTON = cssSelector("button.execute");
    private final By CANCEL_BUTTON = cssSelector("button.btn-clear");
    private final By RESPONSE_TABLE = xpath(".//h4[text()='Server response']/following-sibling::*[contains(@class, 'responses-table')]");
    private final By STATUS_CODE = cssSelector("tr.response .response-col_status");
    private final By BODY = xpath(".//h5[text()='Response body']/following-sibling::*//code");
    private final String PARAMETER_FIELD = ".//div[contains(@class, 'parameter__name') and text()='%s']/ancestor::tr//input";

    @Step("Click try it out")
    public RequestBlock tryItOut() {
        block().findElement(TRY_IT_OUT_BUTTON).click();
        return this;
    }

    @Step("Set request body")
    public RequestBlock setRequestBody(String body) {
        WebElement bodyField = block().findElement(BODY_TEXT_AREA);
        bodyField.clear();
        bodyField.sendKeys(body);
        Allure.addAttachment("Body", body);
        return this;
    }

    @Step("Set parameter {param} to {value}")
    public RequestBlock setParameter(String param, String value) {
        block().findElement(xpath(format(PARAMETER_FIELD, param))).sendKeys(value);
        return this;
    }

    @Step("Click execute")
    public RequestBlock execute() {
        block().findElement(EXECUTE_BUTTON).click();
        webDriverWait().until(visibilityOfElementLocated(CANCEL_BUTTON));
        return this;
    }

    @Step("Get response status code")
    public String getResponseStatusCode() {

        WebElement status = getResponse().findElement(STATUS_CODE);
        new ScrollHelper(driver).scrollToElement(status);
        return status.getText().replaceAll("\nUndocumented", "");
    }

    @Step("Get response body")
    public String getResponseBody() {
        WebElement body = getResponse().findElement(BODY);
        new ScrollHelper(driver).scrollToElement(body);
        return body.getText();
    }

    private WebElement block() {
        WebElement blockElement = driver.findElement(block);
        new ScrollHelper(driver).scrollToElement(blockElement);
        return blockElement;
    }

    private WebElement getResponse() {
        return block().findElement(RESPONSE_TABLE);
    }

}
