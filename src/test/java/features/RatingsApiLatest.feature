Feature: Latest Rates API functionality


  Scenario Outline:Latest Api ratings responce code
    Given Latest Rates API URL with <validityStatus> is available
    When The Latest API is called by GET method
    Then Success status of the response returned is <responceCode>
    Examples:
      | validityStatus          | responceCode |
      | getLatestRatings        | 200          |
      | getInvalidLatestRatings | 400          |

  Scenario Outline:Latest Api ratings checking content
    Given Latest Rates API URL is available with <symbolsValue> and <baseValue>
    When  Latest Rates API is called by GET method
    Then  Responce base is <responceBaseValue>
    And   Responce ratings available is <responceRatingsList>
    And   Date in responce corresponds to <lastWorkingDay>
    Examples:
      | symbolsValue     | baseValue         | responceBaseValue | responceRatingsList |lastWorkingDay   |
      | USD,GBP          | null              | EUR               | USD,GBP             |2020-05-18       |
      | null             | USD               | USD               | ALL                 |2020-05-18       |
      | EUR,GBP          | USD               | USD               | EUR,GBP             |2020-05-18       |


  Scenario Outline: API by date checking responce code
    Given Rates API URL with <resource> is available
    When The API is called by GET method for specified <specifiedDate>
    Then Responce status of the response returned is <responceCode>
    And Responce data corresponds to <expectedDate>
    Examples:
      | resource                   |specifiedDate  |expectedDate       | responceCode |
      | getRatingsApiByDate        |2020-05-14     | 2020-05-14        | 200          |
      | getRatingsApiByDate        |2020-05-18     | LAST WORKING DAY  | 200          |
      | getRatingsApiByDate        |2022-05-15     | LAST WORKING DAY  | 200          |
      | getRatingsApiByDate        |2020-05-17     | LAST WORKING DAY  | 200          |


  Scenario Outline: API by date checking content
    Given  Rates API URL is available with <requestParamName> and <requestParamValue>
    When   API is called by GET method for <specifiedDate>
    Then   Responce base provided is <responceBaseValue>
    And    Responce ratings available with <responceRatingsList>
    And    Data in responce corresponds to <expectedDate>
    Examples:
      | requestParamName | requestParamValue | specifiedDate | expectedDate      |responceBaseValue|responceRatingsList |
      | symbols          | USD,GBP           | 2020-05-14    | 2020-05-14        | EUR             |USD,GBP             |
      | base             | USD               | 2022-05-17    | LAST WORKING DAY  | USD             |ALL                 |







