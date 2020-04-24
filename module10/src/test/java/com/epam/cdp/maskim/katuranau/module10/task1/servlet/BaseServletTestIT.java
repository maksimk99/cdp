package com.epam.cdp.maskim.katuranau.module10.task1.servlet;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class BaseServletTestIT {

    @Test
    public void doGet() {
        given().cookie("views", "0")
                .when().get("http://localhost:8787/messages")
                .then().cookie("views", is("1"))
                .statusCode(200);
    }

    @Test
    public void doPost() {
        String message = "text message";
        given().cookie("views", "1").formParams("message", message)
                .when().post("http://localhost:8787/messages/post")
                .then().cookie("views", is("2"))
                .statusCode(200);
    }

    @Test
    public void doPut() {
        String messageId = "3";
        String message = "text message";
        given().cookie("views", "1").formParams("messageId", messageId, "message", message)
                .when().put("http://localhost:8787/messages/put")
                .then().cookie("views", is("2"))
                .statusCode(200);
    }

    @Test
    public void doDelete() {
        String messageId = "3";
        given().cookie("views", "1").formParams("messageId", messageId)
                .when().delete("http://localhost:8787/messages/delete")
                .then().cookie("views", is("2"))
                .statusCode(200);
    }
}