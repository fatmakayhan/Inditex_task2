package com.inditex;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Ejercicio3_2 {
    @Test
    public void test() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=sold");

        JsonPath jsonPath = response.jsonPath();

        List<Long> ids = jsonPath.getList("id", Long.class);
        List<String> names = jsonPath.getList("name");

        for (int i = 0; i < ids.size(); i++) {
            Long id = ids.get(i);
            String name = names.get(i);
            System.out.println("{" + id + ", " + name + "}");
        }
    }
}
