package cucumber.examples.java.calculator;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Date;

/**
 * This file is copy/pasted from cucumber-jvm java calculator example
 */
public class DateStepdefs {
    private String result;
    private DateCalculator calculator;

    @Given("today is {}")
    public void today_is(Date date) {
        calculator = new DateCalculator(date);
    }

    @When("I ask if {} is in the past")
    public void I_ask_if_date_is_in_the_past(Date date) {
        result = calculator.isDateInThePast(date);
    }

    @Then("the result should be {}")
    public void the_result_should_be(String expectedResult) {
        assertEquals(expectedResult, result);
    }
}
