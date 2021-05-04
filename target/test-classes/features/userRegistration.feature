Feature: user registration
  I test the registration of a participant with both valid and invalid input

<<<<<<< HEAD
@newUserRegistration

    Scenario Outline:
    Given I have chosen an <event>
    When  I write a username as <username>
    Then  I can see a correct <message> based on respective input


      Examples:
        | username           | event        | message                                                             |
        | "valid username"   | "decathlon"  |  "Your registration has been successful"                            |
        | "valid username"   | "heptathlon" |  "Your registration has been successful"                            |
        | "usernameTaken"    | "decathlon"  |  "Username is already taken. Note that you can only register once." |
        | "usernameTaken"    | "heptathlon" |  "Username is already taken. Note that you can only register once." |
        | "noUsername        | "heptathlon" |  "please enter a username"                                          |
        | "noUsername        | "decathlon"  |  "please enter a username"                                          |
        | "invalid username" | "decathlon"  |  "You wrote and invalid form of a user name"                        |
        | "invalid username" | "heptathlon" |   "You wrote and invalid form of a user name"                       |

=======
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
>>>>>>> 9241b5991e1e236ba39b3d1f54b968d165662565

  @maximumUsersReached
  Scenario: I test the maximum number of registration
    Given There are maximum <number> of participants reached
    When I try to register another <participant>
    Then the registration is unsuccessful
    And The <error message> is displayed
