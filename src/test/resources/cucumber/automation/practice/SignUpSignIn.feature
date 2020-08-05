Feature: Sign up and sign in functionality tests

  @dev @desktopBrowser
  Scenario Outline: User is able to create new account
    Given User is signed out
    And User is on the homepage
    When User clicks sign in button
    Then User is redirected to the Authentication page
    When User inputs a random valid email address and clicks create account button
    Then User is redirected to the Sign up page
    And User sees email field filled with previously provided email
    When User inputs "<firstName>" into User's first name field
    Then User sees address first name field filled with previously provided user first name
    When User inputs "<lastName>" into User's last name field
    Then User sees address last name field filled with previously provided user last name
    When User inputs "<password>" into the password field in sign up page
    And User fills all fields other than first, last name and password fields with random valid input
    And User clicks Register button
    Then User is signed in
    And User sees his full name next to sing out button

  Examples:
    | firstName | lastName | password  |
    | John      | Smith    | johnSmith |
    | Jane      | Doe      | janeDoe   |

