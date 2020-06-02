Feature: Latest Rates API functionality


  Scenario Outline: [Latest Api] checking responce codes
    Given Latest Rates API URL with <validityStatus> is available
    When The Latest API is called by GET method
    Then Success status of the response returned is <responceCode>
    Examples:
      | validityStatus          | responceCode |
      | getLatestRatings        | 200          |
      | getInvalidLatestRatings | 400          |

  Scenario Outline: [Latest Api] checking content for base and symbols params
    Given Latest Rates API URL is available with <requestParamName> and <requestParamValue>
    When  Latest Rates API is called by GET method
    Then  Responce base is <responceBaseValue>
    And   Responce ratings available is <responceRatingsList>
    And   Date in responce corresponds to <lastWorkingDay>
    Examples:
      | requestParamName | requestParamValue | responceBaseValue | responceRatingsList |lastWorkingDay   |
      | symbols          | USD,GBP           | EUR               | USD,GBP             |LAST WORKING DAY |
      | base             | USD               | USD               | ALL                 |LAST WORKING DAY |




