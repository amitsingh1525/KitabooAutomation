#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Test Login Page

  Scenario Outline: Test the login page with given data
   	When inert given username "<username>"
    And insert the given password "<password>"
    And click on login button
    Then verify the url it's not redirect to the home page

    Examples: 
      | username  | password |
      | username1 |     pass@123 |
      | username2 |     pass@123 |
