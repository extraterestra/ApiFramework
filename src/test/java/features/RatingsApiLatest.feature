Feature: Latest Rates API functionality


  Scenario Outline:
    Given Latest Rates API URL with <validityStatus> is available
    When The Latest API is called by GET method
    Then Success status of the response returned is <responceCode>
    Examples:
      | validityStatus          | responceCode |
      | getLatestRatings        | 200          |
      | getInvalidLatestRatings | 400          |

  Scenario Outline:
    Given Latest Rates API URL is available with <requestParamName> and <requestParamValue>
    When  Latest Rates API is called by GET method
    Then  Responce base is <responceBaseValue>
    And   Responce ratings available is <responceRatingsList>
    Examples:
      | requestParamName | requestParamValue | responceBaseValue | responceRatingsList |
      | symbols          | USD,GBP           | EUR               | USD,GBP             |
      | base             | USD               | USD               | ALL                 |


  Scenario Outline:
    Given Rates API URL with <resource> is available
    When The API is called by GET method for specified <specifiedDate>
    Then Responce status of the response returned is <responceCode>
    And Responce data corresponds to <expectedDate>
    Examples:
      | resource             |specifiedDate  |expectedDate | responceCode |
      | getRatingsApi        |2020-05-14     | 2020-05-14  | 200          |
      | getRatingsApi        |2020-05-18     | 2020-05-15  | 200          |
      | getRatingsApi        |2022-05-15     | 2020-05-15  | 200          |
      | getRatingsApi        |2020-05-17     | 2020-05-15  | 200          |








