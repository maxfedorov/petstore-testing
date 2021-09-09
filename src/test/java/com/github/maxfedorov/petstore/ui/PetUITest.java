package com.github.maxfedorov.petstore.ui;

import com.github.maxfedorov.petstore.ui.pages.MainPage;
import com.github.maxfedorov.petstore.ui.pages.RequestBlock;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@DisplayName("UI tests")
public class PetUITest extends TestBase {

    @Feature("UI tests")
    @Story("Pets")
    @DisplayName("Add pet to store")
    @Test
    void addPetToStoreTest() {
        int id = 7862423;
        String name = "Rex";
        String status = "available";
        JSONArray urls = new JSONArray();
        urls.put("url1");
        urls.put("url2");
        JSONArray tags = new JSONArray();
        tags.put(new JSONObject().put("id", 0).put("name", "tag1"));
        tags.put(new JSONObject().put("id", 1).put("name", "tag2"));
        JSONObject body = new JSONObject();
        body.put("id", id);
        body.put("name", name);
        body.put("photoUrls", urls);
        body.put("status", status);
        body.put("tags", tags);

        MainPage mainPage = new MainPage(driver);
        RequestBlock request = mainPage.open()
                .expandBlock("POST", "/pet")
                .tryItOut()
                .setRequestBody(body.toString())
                .execute();
        JSONObject responseBody = new JSONObject(request.getResponseBody());
        step("Verify response", () -> {
            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(request.getResponseStatusCode()).isEqualTo("200");
            softAssertions.assertThat(responseBody.get("id")).isEqualTo(id);
            softAssertions.assertThat(responseBody.get("name")).isEqualTo(name);
            softAssertions.assertThat(responseBody.get("status")).isEqualTo(status);
            softAssertions.assertThat(responseBody.get("photoUrls").toString()).isEqualTo(urls.toString());
            softAssertions.assertThat(responseBody.get("tags").toString()).isEqualTo(tags.toString());
            softAssertions.assertAll();
        });
    }

    @Feature("UI tests")
    @Story("Pets")
    @DisplayName("Find pet (failed test)")
    @Test
    void findPet() {
        String id = "-1";
        MainPage mainPage = new MainPage(driver);
        String responseStatusCode = mainPage.open()
                .expandBlock("GET", "/pet/{petId}")
                .tryItOut()
                .setParameter("petId", id)
                .execute()
                .getResponseStatusCode();
        step("Verify response", () -> Assertions.assertThat(responseStatusCode).isEqualTo("200"));
    }
}
