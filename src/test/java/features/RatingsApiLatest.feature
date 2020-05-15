Feature: Latest Rates API functionality


  Scenario Outline:
    Given Latest Rates API was called
    When The API is available
    Then Success status of the response returned is <errorCode>
    Examples:
      | errorCode |
      | 200       |

  Scenario Outline:
    Given <baseRequested> specified in request
    When Latest Rates API was called
    Then value of <baseInResponce> received in responce
    Examples:
      | baseRequested| baseInResponce |
      | USD          | USD            |


