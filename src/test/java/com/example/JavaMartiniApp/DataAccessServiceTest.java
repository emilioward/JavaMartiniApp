package com.example.JavaMartiniApp;

import com.example.JavaMartiniApp.Interface.CocktailDataAccessService;
import com.example.JavaMartiniApp.Model.Cocktail;
import org.assertj.core.api.Assertions;
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
    public void TestGetAllCocktailsInsideDatabase() {
        List<Cocktail> all = cocktailDataAccessService.selectAllCocktails();
        Assertions.assertThat(all.get(0).getName()).isEqualTo("TestMartini");
    }

    @Test
    public void TestGetHomepageReturns200() {

        when().
                get(baseUrl + "/home").
                then()
                .statusCode(200);
    }

}
