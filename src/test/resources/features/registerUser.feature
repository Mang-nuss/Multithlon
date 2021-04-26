Feature: Registration of a new participant
  I test the registration of a participant with both valid and invalid input

@newUserRegistration
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



