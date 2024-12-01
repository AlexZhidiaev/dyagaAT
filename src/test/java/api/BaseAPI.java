package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static config.ConfigValue.URL_API;

public abstract class BaseAPI {

    /*public static void createCookieApi(String login, String password) {
        Request request = new Request(login, password);
        Cookies cookies = auth(request);
        COOKIE_STORE.set(cookies);
    }

    private static Cookies auth(Request request) {
        return given()
                .relaxedHTTPSValidation()
                .spec(requestSpecification)
                .filter(withHiddenPasswordTemplates())
                .basePath(SIGN_IN.getValue())
                .body(request)
                .when()
                .post()
                .then()
                .extract().response().getDetailedCookies();
    }*/

    private static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setRelaxedHTTPSValidation()
            .setContentType(ContentType.JSON)
            .setBaseUri(URL_API.getValue())
            .build();

    protected static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setBaseUri(URL_API.getValue())
                //.addCookies(COOKIE_STORE.get())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

}
