Feature: Login to personal utility
  Test the login functionality of personal utility

  Scenario: Login as Admin
    Given I am in personal utility page
    And I enter user as "admin" and password as "admin"
    When I click the login button
    Then The "Settings" tab should be visible

  Scenario: Login as User
    Given I am in personal utility page
    And I enter user as "user" and password as "user"
    When I click the login button
    Then The "Utilities" tab should be visible
