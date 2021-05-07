Feature: score calculation
  I test the possibility and correctness of point calculations according to e 2001 IAAF points tables


  @calculatingPoints
  Scenario Outline:
    Given I am a registered user
    When  I choose a relevant <discipline>
    And   I insert correct <performance> input
    Then  I can see a correct <result> based on respective input


    Examples:
      | discipline | performance | result |
      | 100m run   | 12.452      |        |
      | Disc throw | 35.32       |        |
      | Long jump  | 2.20        |        |
      | 200m run   | 23.20       |        |
      | High jump  | 1.82        |        |
      | Javelin    | 57.18       |        |



  @calculationInvalidInput

  Scenario Outline:
    Given I am a registered user
    When  I choose a relevant <discipline>
    And   I insert  <incorrect input>
    Then  I can see a correct <message> based on respective input


    Examples:
      | discipline | incorrect input | message |
      | 100m run   | negative value  |  "Please enter a valid value, only numbers allowed     |
      | Disc throw | no value        |  "Please enter a valid value, only numbers allowed     |
      | Long jump  | letters         |  "Please enter a valid value, only numbers allowed     |
      | 200m run   | special char    |  "Please enter a valid value, only numbers allowed     |
      | High jump  | no value        |  "Please enter a valid value, only numbers allowed     |
      | Javelin    | negative value  |  "Please enter a valid value, only numbers allowed     |






  Scenario: user not registered
    Given I am not a registered user
    When  I want to enter the performance input
    Then  I get an error message




