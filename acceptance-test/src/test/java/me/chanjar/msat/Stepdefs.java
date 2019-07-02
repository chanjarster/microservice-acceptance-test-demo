package me.chanjar.msat;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jboss.byteman.agent.submit.Submit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;

public class Stepdefs {

  private static final Logger LOGGER = LoggerFactory.getLogger(Stepdefs.class);

  private static final String BYTEMAN_HOST = "byteman.host";
  private static final String PRODUCT_ADDRESS = "product.address";
  private static final String PRODUCT_PRICE_ADDRESS = "product-price.address";
  private static final String PRODUCT_PRICE_BYTEMAN_PORT = "product-price.byteman.port";

  private List<Map<String, String>> answer;

  private Submit cachedSubmit;

  private Function<Map, Map<String, String>> mapConverter = map -> {

    Map<String, String> newmap = new HashMap<>();
    for (Object key : map.keySet()) {
      Object value = map.get(key);
      if (value == null) {
        newmap.put(key.toString(), "");
      } else {
        newmap.put(key.toString(), value.toString());
      }
    }
    return newmap;
  };

  @Given("^Product Service is up and running$")
  public void productServiceIsUpAndRunning() {
    probe("Product Service", System.getProperty(PRODUCT_ADDRESS));
  }

  @And("^Product Price Service is up and running$")
  public void productPriceServiceIsUpAndRunning() {
    probe("Product Price Service", System.getProperty(PRODUCT_PRICE_ADDRESS));
    Submit bm = getBytemanSubmit();
    try {
      bm.deleteAllRules();
    } catch (Exception e) {
    }
  }

  @When("^User query product list$")
  public void queryProductList() {
    String address = System.getProperty(PRODUCT_ADDRESS);

    answer = new ArrayList<>();

    List<Map> result = given()
      .when()
      .get(address + "/products")
      .then()
      .statusCode(is(200))
      .extract()
      .body()
      .jsonPath()
      .getList("products", Map.class);

    result.stream()
      .map(mapConverter)
      .forEach(newmap -> answer.add(newmap));

  }

  @Given("^Install the byteman script ([A-Za-z0-9_\\.]+) to Product Price Service$")
  public void injectExceptionIntoProductPriceService(String bytemanScript) throws Exception {
    LOGGER.info("Install the byteman script {} to Product Price Service", bytemanScript);
    Submit bm = getBytemanSubmit();
    bm.addRulesFromFiles(Collections.singletonList("target/test-classes/" + bytemanScript));
  }

  @Then("^Get following products$")
  public void compareResult(List<Map<String, String>> expected) {
    assertThat(answer).containsExactlyInAnyOrderElementsOf(expected);
  }

  private void probe(String service, String address) {
    LOGGER.info("Probing service is healthy: " + service + "(" + address + "/health)");
    given()
      .when()
      .get(address + "/health")
      .then()
      .statusCode(is(200));
  }

  private Submit getBytemanSubmit() {
    if (cachedSubmit != null) {
      return cachedSubmit;
    }
    String bytemanHost = System.getProperty(BYTEMAN_HOST);
    String port = System.getProperty(PRODUCT_PRICE_BYTEMAN_PORT);
    cachedSubmit = new Submit(bytemanHost, Integer.parseInt(port));
    return cachedSubmit;
  }

}
