Feature: Testing ability to retrieve game by id

  Scenario: Requested Game Exists
    Given url 'http://localhost:8080/games/1'
    When method GET
    Then status 200
    And match $ == read('../templates/game_id_1.json')

  Scenario: Requested id is not integer
    Given url 'http://localhost:8080/games/NOTANDID'
    When method GET
    Then status 400

  Scenario: Requested id is negative integer
    Given url 'http://localhost:8080/games/-5'
    When method GET
    Then status 400

  Scenario: Requested Game Does Not Exist
    Given url 'http://localhost:8080/games/9999'
    When method GET
    Then status 404