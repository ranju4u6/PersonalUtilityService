Feature: Task Management
  I want to add a new task
  
  Background: Login as user
  	Given I am in personal utility page
    When I enter user as "user" and password as "user"
    Then I click the login button
    And I click on "Utilities" tab
    And I select "TO-DO" option

  Scenario: Add a new user
  	Then The taskadd button should be disabled
    When I enter the task details
    |2019-10-10|Some future task|
    Then the taskadd button should be enabled
    When I click on taskadd button
    Then The task "Some future task" should be present in the list.
