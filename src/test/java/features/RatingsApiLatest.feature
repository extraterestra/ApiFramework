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


  Scenario Outline: [Api by date] checking responce codes
    Given Rates API URL with <resource> is available
    When The API is called by GET method for specified <specifiedDate>
    Then Responce status of the response returned is <responceCode>
    And Responce date corresponds to <expectedDate>
    Examples:
      | resource                   |specifiedDate  |expectedDate       | responceCode |
      | getRatingsApiByDate        |2020-05-14     | 2020-05-14        | 200          |
      | getRatingsApiByDate        |2020-05-18     | LAST WORKING DAY  | 200          |
      | getRatingsApiByDate        |2022-05-15     | LAST WORKING DAY  | 200          |
      | getRatingsApiByDate        |2020-05-17     | LAST WORKING DAY  | 200          |


  Scenario Outline:  [Api by date] checking content for base and symbols params
    Given  Rates API URL is available with <requestParamName> and <requestParamValue>
    When   API is called by GET method for <specifiedDate>
    Then   Responce base provided is <responceBaseValue>
    And    Responce ratings available with <responceRatingsList>
    And    Data in responce corresponds to <expectedDate>
    Examples:
      | requestParamName | requestParamValue | specifiedDate | expectedDate      |responceBaseValue|responceRatingsList |
      | symbols          | USD,GBP           | 2020-05-14    | 2020-05-14        | EUR             |USD,GBP             |
      | base             | USD               | 2022-05-17    | LAST WORKING DAY  | USD             |ALL                 |







