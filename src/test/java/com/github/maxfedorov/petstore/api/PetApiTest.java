package com.github.maxfedorov.petstore.api;

import com.github.maxfedorov.petstore.api.models.ApiResponse;
import com.github.maxfedorov.petstore.api.models.Pet;
import com.github.maxfedorov.petstore.api.models.Tag;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayName("API tests")
public class PetApiTest {

    @Feature("API tests")
    @Story("Pets")
    @DisplayName("Get pet by id")
    @Test
    void getPetTest() {
        int id = 7862423;
        List<String> urls = new ArrayList<>();
        urls.add("url1");
        urls.add("url2");
        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag();
        tag1.setName("tag1");
        tag1.setId(0);
        Tag tag2 = new Tag();
        tag2.setName("tag2");
        tag2.setId(1);
        tags.add(tag1);
        tags.add(tag2);

        Pet expectedResponse = new Pet();
        expectedResponse.setId(id);
        expectedResponse.setName("Rex");
        expectedResponse.setPhotoUrls(urls);
        expectedResponse.setStatus("available");
        expectedResponse.setTags(tags);

        Pet response = new Specs().requestSpecification()
                .get("/pet/" + id)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/pet.json"))
                .extract().as(Pet.class);

        Assertions.assertThat(response).isEqualTo(expectedResponse);
    }

    @Feature("API tests")
    @Story("Pets")
    @DisplayName("Delete pet")
    @Test
    void deletePetTest() {
        String id = "7862423";
        ApiResponse expectedResponse = new ApiResponse();
        expectedResponse.setCode(200);
        expectedResponse.setMessage(id);
        expectedResponse.setType("unknown");

        ApiResponse response = new Specs().requestSpecification()
                .delete("/pet/" + id)
                .then()
                .statusCode(200)
                .extract().as(ApiResponse.class);
        Assertions.assertThat(response).isEqualTo(expectedResponse);
    }
}
