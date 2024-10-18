Feature: User account management

  Scenario: Creating a new user account
    Given The user accesses the registration page
    When The user fills out the registration form with a valid email, username, and password
    And The user submits the form
    Then The user account is successfully created
    And The user receives a registration confirmation

  Scenario: Error when creating an account with an existing identifier
    Given The user accesses the registration page
    When The user fills out the registration form with an already existing email or username
    And The user submits the form
    Then An error is returned indicating that the identifier is already in use

  Scenario: Successful user login
    Given The user has an account with username "testuser" and password "password123"
    When The user tries to log in with username "testuser" and password "password123"
    Then The user successfully logs in

  Scenario: Failed user login due to incorrect password
    Given The user has an account with username "testuser" and password "password123"
    When The user tries to log in with username "testuser" and password "wrongpassword"
    Then The login fails