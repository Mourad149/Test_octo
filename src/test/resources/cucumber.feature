Feature:  videos can be retrieved by Level AND/OR tags
  Scenario Outline: client makes call to GET /api/videos?level="level"&tags=tag1,tag2.

    Given the client calls the api with level <level> and tags <tags>
    Then the client should receive status code of 200

    Examples:
      | level    | tags            |
      | MEDIUM | POURCENTAGE; CALCUL |