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
Feature: Test Book Analytics

  Scenario: Open Chrome Browser
    When Open "Chrome" Browser
    Then Navigate to LogIn Page 'https://create.kitaboo.com/home.xhtml'

  Scenario Outline: Test the login page with given data
    And User enters "<username>" and "<password>" on textbox
    And click on login button
    Then verify the url it's redirect to the home page "https://create.kitaboo.com/jsp/books/books.xhtml"

    Examples: 
      | username                     | password    |
      | automation.test1@yopmail.com | kitaboo!123 |

  Scenario: Test the login page with given data
    Then create a book with data

  Scenario: Close Chrome Browser
    When Close Browser
