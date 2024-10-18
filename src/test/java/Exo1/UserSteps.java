package Exo1;

import io.cucumber.java.en.*;

import static org.junit.Assert.*;

public class UserSteps {

    private UserService userService = new UserService(); // Service utilisateur simulé
    private boolean accountCreated;
    private boolean loginSuccess;
    private String errorMessage;

    @Given("The user accesses the registration page")
    public void user_accesses_registration_page() {
        System.out.println("User accesses the registration page.");
    }

    @When("The user fills out the registration form with a valid email, username, and password")
    public void user_fills_registration_form_valid() {
        accountCreated = userService.createAccount("newuser", "user@example.com", "password123");
    }

    @When("The user fills out the registration form with an already existing email or username")
    public void user_fills_registration_form_existing() {
        userService.createAccount("existinguser", "existing@example.com", "password123");

        accountCreated = userService.createAccount("existinguser", "existing@example.com", "newpassword456");
        if (!accountCreated) {
            errorMessage = "Email or username already exists.";
        }
    }

    @And("The user submits the form")
    public void user_submits_form() {
        // Simule la soumission du formulaire.
    }

    @Then("The user account is successfully created")
    public void account_is_created() {
        assertTrue(accountCreated);
        System.out.println("Account created successfully.");
    }

    @Then("The user receives a registration confirmation")
    public void user_receives_confirmation() {
        System.out.println("User receives a confirmation email.");
    }

    @Then("An error is returned indicating that the identifier is already in use")
    public void error_is_returned() {
        assertFalse(accountCreated);
        assertEquals("Email or username already exists.", errorMessage);
        System.out.println("Error: " + errorMessage);
    }

    @Given("The user has an account with username {string} and password {string}")
    public void user_has_an_account(String username, String password) {
        userService.createAccount(username, "user@example.com", password);
    }

    @When("The user tries to log in with username {string} and password {string}")
    public void user_tries_to_log_in(String username, String password) {
        loginSuccess = userService.login(username, password);
    }

    @Then("The user successfully logs in")
    public void user_successfully_logs_in() {
        assertTrue(loginSuccess);
        System.out.println("User logged in successfully.");
    }

    @Then("The login fails")
    public void login_fails() {
        assertFalse(loginSuccess);
        System.out.println("Login failed.");
    }
}
