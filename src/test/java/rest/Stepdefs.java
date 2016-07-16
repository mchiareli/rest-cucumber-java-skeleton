package rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Objects;

import static com.jayway.jsonpath.JsonPath.parse;

public class Stepdefs {
    private final Environment environment;
    private HttpResponse lastResponse;

    public Stepdefs() {
        this.environment = Environment.get();
        Unirest.setDefaultHeader("Content-Type", "application/json");
    }

    @When("^I get the resource \"([^\"]*)\"$")
    public void i_GET_the_resources(String url) throws Throwable {
        this.lastResponse = Unirest.get(environment.createUrl(url)).asString();
    }

    @Then("^the status code should be (\\d+)$")
    public void the_status_code_should_be(int statusCode) throws Throwable {
        assert Objects.nonNull(lastResponse) && lastResponse.getStatus() == statusCode;
    }

    @Then("^response data \"([^\"]*)\" should be \"([^\"]*)\"$")
    public void response_data_should_be(String expression, String expectedValue) throws Throwable {
        String response = (String) lastResponse.getBody();
        assert parse(response).read(expression, String.class).equals(expectedValue);
    }

    @When("^I post the resource \"([^\"]*)\" with body$")
    public void i_post_the_resource_with_body(String url, String body) throws Throwable {
        this.lastResponse = Unirest.post(environment.createUrl(url)).body(body).asString();
    }

    @When("^I put the resource \"([^\"]*)\" with body$")
    public void i_put_the_resource_with_body(String url, String body) throws Throwable {
        this.lastResponse = Unirest.put(environment.createUrl(url)).body(body).asString();
    }

    @When("^I delete the resource \"([^\"]*)\" with body$")
    public void i_delete_the_resource_with_body(String url) throws Throwable {
        this.lastResponse = Unirest.put(environment.createUrl(url)).asString();
    }
}
