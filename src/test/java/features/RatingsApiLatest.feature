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
    Given Latest Rates API URL is available with <paramName> and <paramValue>
    When  Latest Rates API is called by GET method
    Then  Responce base is <baseValue>
    And   Responce ratings available is/are <ratingsList>

    Examples:
      | paramName   | paramValue  |baseValue|ratingsList|
      | base        | USD         | USD     |ALL        |
      | symbols     | USD,GBP     | EUR     |USD,GBP    |






