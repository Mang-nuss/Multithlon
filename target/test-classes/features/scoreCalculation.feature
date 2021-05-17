Feature: score calculation
  I test the possibility and correctness of point calculations according to e 2001 IAAF points tables

  @calculation
  Scenario Outline: 
    Given I am a registered user in <event>
    When I choose a relevant <discipline>
    And I insert correct "<performance>" input
    Then I can see a correct <result>

    Examples: 
      | event        | discipline      | performance | result |
      | "Decathlon"  | "100m"          | 12.45       |    565 |
      | "Decathlon"  | "Discus throw"  | 35.32       |    570 |
      | "Decathlon"  | "Long jump"     |         570 |    523 |
      | "Heptathlon" | "200m"          | 23.20       |   1059 |
      | "Heptathlon" | "High jump"     |         182 |   1003 |
      | "Heptathlon" | "Javelin throw" | 57.18       |   1000 |

  @calculation
  Scenario Outline: git
    Given I am a registered user in <event>
    When I choose a relevant <discipline>
    And I insert invalid <performance> input
    Then I can see an error <message>

    Examples: 
      | event        | discipline      | performance | message                                            |
      | "Decathlon"  | "100m"          | "-12.45"    | "Only positive numbers larger than 0 are allowed." |
      | "Decathlon"  | "Discus throw"  | "0.0"       | "Only positive numbers larger than 0 are allowed." |
      | "Decathlon"  | "Long jump"     | "abc"       | "Invalid input, only numbers are allowed."         |
      | "Heptathlon" | "200m"          | ""          | "You need to input a value in order to continue."  |
      | "Heptathlon" | "High jump"     | "<%"        | "Invalid input, only numbers are allowed."         |
      | "Heptathlon" | "Javelin throw" | "-57.18"    | "Only positive numbers larger than 0 are allowed." |
