@FeatureAll
Feature: Feature for Testing

  @Scenario1
  Scenario Outline: Testing Feature 1
    Given I am Shaikh with "<User>" and "<Password>"
      | Surname | FirstName    | LastName | Native | Hometown |
      | Shaikh  | Hifzur       | Rahman   | TN     | Mumbai   |
      | Shaikh  | Hafeezur     | Rahman   | TN     | Mumbai   |
      | Shaikh  | Mahadiyathul | Khatija  | TN     | Chennai  |
      | Sayed   | Najmussahar  | -        | TN     | Mumbai   |
    Then I am COC champ
    When I am Hifzur
    Then I am cool
    When I am smarty
    Then I am COC war hero
    When I am Rahman
    Then I am good boy

    Examples: 
      | User | Password |
      |    1 | password |

  @SO-Scenario-Team2-Sprint-A-part2-1
  Scenario: Testing Feature 2
    Given I am Rahman