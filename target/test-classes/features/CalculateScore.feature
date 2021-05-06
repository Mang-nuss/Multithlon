Feature: 
  To calculate the future score for athlete.

  Scenario: To calculate the score for with valid data for registered user.
    Given the user is registered
    When the user chooses an event
    And the user enters a valid value
    And the user clicks on the calculate button
    Then the scores should be saved in Excel file

  Scenario: To calculate the score without entering value  for registered user.
    Given the user is registered
    When the user chooses and event
    And the user doesnt enter a value
    And the user clicks on the calculate button
    Then the error should be displyed please enter a value

  Scenario: To calculate the score with negative value  for registered user.
    Given the user is registered
    When the user chooses an event
    And the user enters a negative value
    And the user clics on the calculate button
    Then the error should be displyed only positive values are allowed.

  Scenario: To calculate the score by entering  letters  for registered user.
    Given the user is registered
    When the user chooses an event
    And the user enters a value in letters
    And the user clicks on the calculate button
    Then the error should be displyed only numbers  are allowed.

  Scenario: To calculate the score by entering special characters for registered user.
    Given the user is registered
    When the user chooses an event
    And the user enters a value in special characters
    And the user clicks on the calculate button
    Then the error should be displyed only numbers  are allowed.

  Scenario: Unregistered user can not choose an event
    Given the user is not registered
    When the user cant choose an event
    And the user enters a value
    And the user clicks on the calculate button
    Then the error should be displyed only registered user are allowed

  Scenario: To calculate the score without choosing and event for registered user.
    Given the user is registered
    When the user does not choose an event
    And the user enters a value
    And the user clicks on the calculate button
    Then the error should be displyed please select an event
    
    |
