package com.example.JavaMartiniApp;

import com.example.JavaMartiniApp.Interface.CocktailDataAccessService;
import com.example.JavaMartiniApp.Model.Cocktail;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.restassured.RestAssured.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {DataAccessServiceTest.Initializer.class})
public class DataAccessServiceTest {

    @ClassRule
    public static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("postgres");

    @Value("http://localhost:${local.server.port}")
    String baseUrl;

    @Autowired
    CocktailDataAccessService cocktailDataAccessService;

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgres.getJdbcUrl(),
                    "spring.datasource.username=" + postgres.getUsername(),
                    "spring.datasource.password=" + postgres.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    public void TestDefaultCocktailIsInsideDatabase() {
        List<Cocktail> defaultCocktail = cocktailDataAccessService.selectAllCocktails();
        Assert.assertEquals("TestMartini", defaultCocktail.get(0).getName());
        Assert.assertEquals("eb274988-381c-4714-af6a-6be609c4cdc8", defaultCocktail.get(0).getId().toString());
    }


    @Test
    public void TestInsertAndDeleteCocktail() {
        UUID foundId = null;
        UUID generatedTestId = UUID.randomUUID();
        cocktailDataAccessService.insertCocktail(new Cocktail(generatedTestId, "IntegrationTestMockito400"));
        Optional<Cocktail> testData = cocktailDataAccessService.selectCocktailById(generatedTestId);
        if(testData.isPresent()){
            foundId = testData.get().getId();
        }
        Assert.assertEquals(generatedTestId, foundId);
        cocktailDataAccessService.deleteCocktailById(generatedTestId); // Data cleanup
    }

    @Test
    public void TestGetDataReturns200() {
        when().
                get(baseUrl + "/").
                then()
                .statusCode(200);
    }
}
