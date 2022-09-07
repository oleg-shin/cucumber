Feature: Login feature
  @sprint19
  Scenario: Valid admin login
    # to comment use #
    #Given user is navigated to HRMS application

    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in


  Scenario: Valid ess login
    #Given user is navigated to HRMS application
    When user enters ess username and password
    And user clicks on login button
    Then ess user is successfully logged in


  Scenario: Invalid login
    #Given user is navigated to HRMS application
    When user enters invalid username and password
    And user clicks on login button
    Then user is able to see error message

  @login
  Scenario Outline: Negative login test
    When user eneters different "<username>" and "<password>" and verify the "<error>"
    Examples:
      | username | password | error |
      |admin     |xyz       |Invalid credentials|
      |cristiano |Hum@n     |Invalid credentials|
      |          |Hum@nhrm123|Username cannot be empty|
      |admin     |           |Password cannot be empty|
