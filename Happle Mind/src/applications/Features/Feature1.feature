@FeatureAll
Feature: Feature for Testing

  @Scenario-1
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

  @Scenario-2
  Scenario: Testing Feature 2
    Given I am Rahman

  @Scenario-3
  Scenario: Testing Feature 3
    Given I am Hifzur