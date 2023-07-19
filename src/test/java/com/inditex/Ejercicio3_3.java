package com.inditex;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ejercicio3_3{
    private Map<String, Integer> mascotaCount;

    public Ejercicio3_3(JsonPath jsonPath) {
        List<Long> petIds = jsonPath.getList("id");
        List<String> petNames = jsonPath.getList("name");
        this.mascotaCount= new HashMap<>();
        countPetNames(petNames);
    }

    private void countPetNames(List<String> petNames) {
        for (String name : petNames) {
            mascotaCount.put(name, mascotaCount.getOrDefault(name, 0) + 1);
        }
    }

    public void imprimirMascotaCantidad() {
        for (Map.Entry<String, Integer> entry : mascotaCount.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            System.out.println("\"" + name + "\": " + count);
        }
    }

    public static void main(String[] args) {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=sold");

        JsonPath jsonPath = response.jsonPath();

        Ejercicio3_3 mascotaData = new Ejercicio3_3(jsonPath);
       mascotaData.imprimirMascotaCantidad();
    }
}



