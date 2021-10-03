Feature: User Management
  I want to add a new user
  
  Background: Login as admin
  	Given I am in personal utility page
    When I enter user as "admin" and password as "admin"
    Then I click the login button
    And I click on "Settings" tab

  Scenario: Add a new user
  	Then The "Add" button should be disabled
    When I enter the user registration details
    |temp_user|temp_user_pwd|temp_user_pwd|USER|
    Then the "Add" button should be enabled
    When I click on "Add" button
    Then The user "temp_user" should be present in the list.
