Feature: Registration of a new participant
  I test the registration of a participant with both valid and invalid input

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

    Scenario Outline: Registration

    When I write a username as "<username>"
    And  I choose one of the events as "<events>"
    Then I can see a correct "<message>" based on respective input


      Examples:
        | username           | events       | message |
        | valid username   | decathlon  |  Your registration has been successful                          |
        | invalid username | heptathlon |  You wrote and invalid form of a user name                        |
        | usernameTaken    | decathlon  |  Username is already taken. Note that you can only register once. |
        | noUsername        | heptathlon |  please enter a username                                         |


  @maximumUsersReached
  Scenario: I test the maximum number of registration
    Given There are maximum <number> of participants reached
    When  I try to register another <participant>
    Then  the registration is unsuccessful
    And   The <error message> is displayed


