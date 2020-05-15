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
    Given <baseRequested> specified in request
    When Latest Rates API was called
    Then value of <baseInResponce> received in responce
    Examples:
      | baseRequested| baseInResponce |
      | USD          | USD            |


