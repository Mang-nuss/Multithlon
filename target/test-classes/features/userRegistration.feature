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
      | "copy"        | "Decathlon"  | "Name already exists. Note that you can only register once."       |
      | "copy"      | "Heptathlon" | "Name already exists. Note that you can only register once."       |
      | ""            | "Decathlon"  | "Please enter a name."                                             |
      | ""            | "Heptathlon" | "Please enter a name."                                             |
      | "invalid name 1"   | "Decathlon"  | "Invalid name, only letters and space are allowed."                |
      | "invalid name 2"   | "Heptathlon" | "Invalid name, only letters and space are allowed."                |


  @maximumUsersReached
  Scenario Outline: I test the maximum number of registration
    Given There are <number> of participants reached for <event>
    When I try to register another <participant>
    Then The <message> is displayed

    Examples:
    | number  | event       | participant | message |
    | 20      | "Decathlon" | "male name" | "Registration failed. The maximum nr of participants is reached." |
    | 20      | "Heptathlon" | "female name" | "Registration failed. The maximum nr of participants is reached." |
    | 19      | "Decathlon" | "male name" | "Registration successful. You're now participating in Decathlon." |
