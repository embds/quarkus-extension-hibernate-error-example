package com.embds.quarkus.example.extension.hibernate.error.it;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.embds.quarkus.example.extension.hibernate.error.runtime.entity.ExampleEntity;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class QuarkusExtensionHibernateErrorExampleResourceTest {

	/**
	 * This case works because the returned entity is the return of the persist method
	 */
    @Test
    public void testSinglePersistAndreturn() {
        ExampleEntity val = given()
                .when().get("/it/single-save-and-return")
                .then()
                .statusCode(200).extract().as(ExampleEntity.class);
        
        Assertions.assertNotNull(val);
        Assertions.assertNotNull(val.getCreationDate());
        Assertions.assertEquals("value : single return", val.getValue());
    }
    
    /**
     * This case doesn't work because the find method is used to get data
     */
    @Test
    public void testSnglePersistAndGet() {
        ExampleEntity val = given()
                .when().get("/it/single-save-and-get")
                .then()
                .statusCode(200).extract().as(ExampleEntity.class);
        
        Assertions.assertNotNull(val);
        Assertions.assertNotNull(val.getCreationDate());
        Assertions.assertEquals("value : single", val.getValue());
    }
	
    /**
     * This case doesn't work because the find method is used to get data
     */
    @Test
    public void testMultiplePersistAndGet() {
        List<ExampleEntity> list = given()
                .when().get("/it/multiple-save-and-get")
                .then()
                .statusCode(200).extract().body().jsonPath().getList(".", ExampleEntity.class);

        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());
    }
}
