Feature: score calculation
  I test the possibility and correctness of point calculations according to e 2001 IAAF points tables

  @calculation
  Scenario Outline: 
    Given I am a registered user in <event>
    When I choose a relevant <discipline>
    And I insert correct <performance> input
    Then I can see a correct <result> based on respective input

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
      | event        | discipline      | performance | message                                           |
      | "Decathlon"  | "100m"          | -12.45      | "Only positive numbers larger than 0 are allowed." |
      | "Decathlon"  | "Discus throw"  |             | "Please enter a valid value, only numbers allowed" |
      | "Decathlon"  | "Long jump"     | abc      | "Please enter a valid value, only numbers allowed" |
      | "Heptathlon" | "200m"          | _?<      | "Please enter a valid value, only numbers allowed" |
      | "Heptathlon" | "High jump"     |             | "Please enter a valid value, only numbers allowed" |
      | "Heptathlon" | "Javelin throw" |         -56 | "Only positive numbers larger than 0 are allowed." |
