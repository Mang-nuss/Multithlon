Feature: export of data to Excel
  I test the possibility to export and view the inserted data (names,scores,points) in Excel

  Scenario: exporting the scores and points to excel
    Given There is at least one user registered
    And   The score has been entered
    When  I choose the possibility to export the data
    Then  I am able to create and save a excel file with relevant data


  Scenario: displaying the scores and points in excel
    Given There at least one user registered
    And   The score has been entered
    When  I choose the possibility to view the data
    Then  I am able to view an excel file with relevant data??????

  Scenario: entering and exporting scores??? not sure about this one
    Given I am a registered user
    And   I entered score
    When  I choose the possibility to export the data
    Then  I am able to create and save a excel file with only my scores and points??????

  Scenario: entering and exporting  scores or about this one either :D
    Given I have entered score
    And   I have saved them in excel
    When  I enter another score
    Then  the score table is updated.
    And   I am able to save the updated file. WHAT??????

