Feature: Rates API by date functionality

  Scenario Outline: [Api by date] checking responce codes
    Given Rates API URL with <resource> is available
    When The API is called by GET method for specified <specifiedDate>
    Then Responce status of the response returned is <responceCode>
    And Responce date corresponds to <expectedDate>
    Examples:
      | resource                   |specifiedDate  |expectedDate       | responceCode |
      | getRatingsApiByDate        |2020-05-14     | 2020-05-14        | 200          |
      | getRatingsApiByDate        |2020-05-18     | 2020-05-15        | 200          |
      | getRatingsApiByDate        |2022-05-15     | LAST WORKING DAY  | 200          |
      | getRatingsApiByDate        |2020-05-17     | 2020-05-15        | 200          |


  Scenario Outline:  [Api by date] checking content for base and symbols params
    Given  Rates API URL is available with <requestParamName> and <requestParamValue>
    When   API is called by GET method for <specifiedDate>
    Then   Responce base provided is <responceBaseValue>
    And    Responce ratings available with <responceRatingsList>
    And    Data in responce corresponds to <expectedDate>
    Examples:
      | requestParamName | requestParamValue | specifiedDate | expectedDate      |responceBaseValue|responceRatingsList |
      | symbols          | USD,GBP           | 2020-05-14    | 2020-05-14        | EUR             |USD,GBP             |
      | base             | USD               | 2022-05-17    | 2022-05-15        | USD             |ALL                 |




