package apiTests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Translations {

    @Test
    public void verifyTranslationsResponse(){

        get("https://api.picsart.com/localizations/en/messages?project=reusable_components,photo_editor")
                .then().assertThat().body(matchesJsonSchemaInClasspath("translation-schema.json"));
    }

}
