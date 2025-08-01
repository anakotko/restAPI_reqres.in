package com.demoqa.helpers;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomAllureListener {

    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured withCustomTemplates(){
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("request.ftl");
        return FILTER;
    }
}
