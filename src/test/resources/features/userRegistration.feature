Feature: user registration
  I test the registration of a participant with both valid and invalid input



  @newUserRegistration
  Scenario Outline: 
    Given I choose <event>
    When I write a <name> and press Create
    Then I get a status <message>

    Examples: 
      | name               | event        | message                                                            |
      | "male name"        | "Decathlon"  | "Registration successful. You're now participating in Decathlon."  |
      | "female name"      | "Heptathlon" | "Registration successful. You're now participating in Heptathlon." |
      | "already exists 1" | "Decathlon"  | "Name already exists. Note that you can only register once."       |
      | "already exists 2" | "Heptathlon" | "Name already exists. Note that you can only register once."       |
      | "empty"            | "Decathlon"  | "Please enter a name."                                             |
      | "empty"            | "Heptathlon" | "Please enter a name."                                             |
      | "invalid name 1"   | "Decathlon"  | "Invalid name, only letters and space are allowed."                |
      | "invalid name 2"   | "Heptathlon" | "Invalid name, only letters and space are allowed."                |


  @maximumUsersReached
  Scenario: I test the maximum number of registration
    Given There are maximum <number> of participants reached
    When I try to register another <participant>
    Then the registration is unsuccessful
    And The <error message> is displayed
