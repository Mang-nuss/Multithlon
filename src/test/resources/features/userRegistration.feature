Feature: user registration
  I test the registration of a participant with both valid and invalid input

  @Users
  Scenario Outline: 
    Given I choose <event>
    When I write a <name> and press Create
    Then I get a status <message>

    Examples: 
      | name          | event        | message                                                            |
      | "male name"   | "Decathlon"  | "Registration successful. You're now participating in Decathlon."  |
      | "female name" | "Heptathlon" | "Registration successful. You're now participating in Heptathlon." |
      | "copy"        | "Decathlon"  | "Name already exists. Note that you can only register once."       |
      | "copy"        | "Heptathlon" | "Name already exists. Note that you can only register once."       |
      | ""            | "Decathlon"  | "Please enter a name."                                             |
      | ""            | "Heptathlon" | "Please enter a name."                                             |
      | "invalid 123" | "Decathlon"  | "Invalid name, only letters and space are allowed."                |
      | "invalid _?%" | "Heptathlon" | "Invalid name, only letters and space are allowed."                |

  @Users
  Scenario Outline: I test the maximum number of registration
    Given There are <number> of participants in <event>
    When I try to register another <participant>
    Then The <message> is displayed

    Examples: 
      | number | event        | participant      | message                                                            |
      |     20 | "Decathlon"  | "male name"      | "Registration failed. The maximum nr of participants is reached."  |
      |     20 | "Heptathlon" | "female name"    | "Registration failed. The maximum nr of participants is reached."  |
      |     19 | "Decathlon"  | "Göran Grängsjö" | "Registration successful. You're now participating in Decathlon."  |
      |     19 | "Heptathlon" | "Carolina Klüft" | "Registration successful. You're now participating in Heptathlon." |
