Feature: User should be able to logout
  Background: User opens Login page
    Given I open Login page

  Scenario Outline: Logout button is displayed
    When I log into app with valid "<email>" and "<password>"
    Then I should see Logout button
    Examples: correct credentials
      | email                          | password    |
      | yelyzaveta.postnova@testpro.io | YrkeNi92    |
      | demo@class.com                 | te$t$tudent |

  Scenario Outline: Logout Success
    And I log into app with valid "<email>" and "<password>"
    When I tap Logout button
    Then I should be logged out
    Examples: correct credentials
      | email                          | password    |
      | yelyzaveta.postnova@testpro.io | YrkeNi92    |
      | demo@class.com                 | te$t$tudent |

  Scenario Outline: User is on login page after logging out
    And I log into app with valid "<email>" and "<password>"
    When I tap Logout button
    Then I should be redirected to Login page
    Examples: correct credentials
      | email                          | password    |
      | yelyzaveta.postnova@testpro.io | YrkeNi92    |
      | demo@class.com                 | te$t$tudent |