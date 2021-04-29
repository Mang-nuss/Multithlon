Feature: user registration
  I test the registration of a participant with both valid and invalid input

  @newUserRegistration
  Scenario Outline: 
    Given I open the application
    When I write a <username> and choose <event>
    Then I get a status <message>

    Examples: 
      | username | event        | message                                                            |
      | "name"   | "Decathlon"  | "Registration successful. You're now participating in Decathlon."  |
      | "name"   | "Heptathlon" | "Registration successful. You're now participating in Heptathlon." |
#      | "name"   | "Decathlon"  | "Name already exists. Note that you can only register once."       |
#      | "name"   | "Heptathlon" | "Name already exists. Note that you can only register once."       |
      |          | "Decathlon"  | "Please enter a name."                                             |
      |          | "Heptathlon" | "Please enter a name."                                             |
#      | "123"    | "Decathlon"  | "Invalid name, only letters and space are allowed."                |
#      | "_?!"    | "Heptathlon" | "Invalid name, only letters and space are allowed."                |

  @maximumUsersReached
  Scenario: I test the maximum number of registration
    Given There are maximum <number> of participants reached
    When I try to register another <participant>
    Then the registration is unsuccessful
    And The <error message> is displayed
