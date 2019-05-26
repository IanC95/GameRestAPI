Feature: Testing report functionality

  Scenario: Get Report
    Given url 'http://localhost:8080/report'
    When method GET
    Then status 200
    And match $ == {user_with_most_comments: #notnull, highest_rated_game: #notnull, average_likes_per_game: #notnull}
