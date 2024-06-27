Feature: User Profile Management
  Scenario Outline:  User successfully updates Profile Name
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I tap avatar icon
    And I provide current password "<password>"
    When I provide new name
    And I tap Save
    Then profile name should be changed
    Examples: correct credentials
      | email                          | password |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 |

  @resetPassword
  Scenario Outline: User successfully updates password
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I tap avatar icon
    And I provide current password "<password>"
    When I provide new password "<updated password>"
    And I tap Save
    And I tap Logout button
    Then I should log in with current email "<email>" and new password "<updated password>"
    And I return back old password "<password>"
    Examples: correct credentials and updated password
      | email                          | password | updated password |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | klm25RuD$hw8     |

  Scenario Outline: Unsuccessful password update
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I tap avatar icon
    And I provide current password "<password>"
    When I provide invalid password "<invalid password>" into New Password
    And I tap Save
    Then I should see error message "<password update error>"
    Examples: correct credentials invalid password and error messages
    | email                          | password | invalid password | password update error                                        |
    | yelyzaveta.postnova@testpro.io | YrkeNi92 | asdGdr1245@#hgtd | The new password must not exceed 15 characters.              |
    | yelyzaveta.postnova@testpro.io | YrkeNi92 | bgDf458^&        | The new password must be at least 10 characters.             |
    | yelyzaveta.postnova@testpro.io | YrkeNi92 | kjghyb^#12gh     | The new password must contain at least one uppercase letter. |
    | yelyzaveta.postnova@testpro.io | YrkeNi92 | GFR45$78HGTD     | The new password must contain at least one lowercase letter. |
    | yelyzaveta.postnova@testpro.io | YrkeNi92 | 85214736954%     | The new password must contain at least one letter.           |
    | yelyzaveta.postnova@testpro.io | YrkeNi92 | ghyrbDhRtewU     | The new password must contain at least one symbol.           |
    | yelyzaveta.postnova@testpro.io | YrkeNi92 | klfjh4YdHreW     | The new password must contain at least one symbol.           |
    | yelyzaveta.postnova@testpro.io | YrkeNi92 | GfnT^hfTbnTW     | The new password must contain at least one number.           |

