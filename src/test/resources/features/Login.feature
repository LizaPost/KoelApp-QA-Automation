Feature: User should be able to login
  Background: User opens Login page
    Given I open Login page

  Scenario: Login success with registered credentials
    When I enter email
    And I enter password
    And I tap Submit
    Then I should be logged in
    And I should be redirected to Home page

  Scenario: Login Failed with unregistered credentials
    When I enter unregistered email
    And I enter unregistered password
    And I tap Submit
    Then I should see login error

  Scenario Outline: Login Failed with incorrect credentials
    When I enter email "<email>"
    And I enter password "<password>"
    And I tap Submit
    Then I should see login error
    Examples: incorrect credentials
      | email           | password           |
      | email_correct   | password_incorrect |
      | email_incorrect | password_correct   |
      | email_empty     | password_empty     |
      | email_correct   | password_empty     |
      | email_empty     | password_correct   |

  @resetEmailAndPassword
  Scenario: Successful login with updated credentials
    And I log into app with valid email and password
    And I tap avatar icon
    And I provide current password
    When I update email address
    And I provide new password
    And I tap Save
    And I tap Logout button
    Then I should be able to login with updated credentials


  @resetEmailAndPassword
  Scenario: Unsuccessful login with old credentials
    And I log into app with valid email and password
    And I tap avatar icon
    And I provide current password
    When I update email address
    And I provide new password
    And I tap Save
    And I tap Logout button
    Then I should not be able to login with old credentials

  @resetEmail
  Scenario: Successful login with updated email and old password after email update only
    And I log into app with valid email and password
    And I tap avatar icon
    And I provide current password
    When I update email address
    And I tap Save
    And I tap Logout button
    Then I should be able to login with updated email and old password

  @resetPassword
  Scenario: Successful login with old email and updated password after password update only
    And I log into app with valid email and password
    And I tap avatar icon
    And I provide current password
    When I provide new password
    And I tap Save
    And I tap Logout button
    Then I should be able to login with old email and updated password

  @resetEmail
  Scenario: Unsuccessful login with old credentials after email updated only
    And I log into app with valid email and password
    And I tap avatar icon
    And I provide current password
    When I update email address
    And I tap Save
    And I tap Logout button
    Then I should not be able to login with old credentials

  @resetPassword
  Scenario: Unsuccessful login with old credentials after password updated only
    And I log into app with valid email and password
    And I tap avatar icon
    And I provide current password
    When I provide new password
    And I tap Save
    And I tap Logout button
    Then I should not be able to login with old credentials
