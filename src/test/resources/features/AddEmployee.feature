Feature: This feature is going to add employee in HRMS application

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on add employee option

  @test
  Scenario: Add an employee
    When user enters firstName, middleName and lastName
    And user click on save button
    Then employee added successfully

  @mixname
  Scenario: Adding one employee from cucumber feature file
    When user enters "Taraman", "Frandul", "Tarmid"
    And user click on save button
    Then employee added successfully

  @dataprovider
  Scenario Outline: Adding multiple employees from cucumber feature file
    When user enters "<firstName>", "<middleName>" and "<lastName>"
    And user click on save button
    Then employee added successfully
    Examples:
      | firstName | middleName | lastName |
      | romid     | ms         | zarif    |
      | rokan     | ms         | elisa    |
      | mama      | tarindi    | jamu     |

  @datatable
  Scenario: Adding multiple employees using data table
    When user adds multiple employees and verify they are added
      | firstName | middleName | lastName  |
      | khryswana | MS         | jaman     |
      | zamis     | MS         | Gaukhar   |
      | tamis     | MS         | microsoft |

  @excel
  Scenario: Adding employees from excel file
    When user adds multiple employees from excel file using "employeeData" sheet
