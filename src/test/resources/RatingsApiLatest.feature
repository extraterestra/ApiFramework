Feature: Latest Rates API functionality


  Scenario Outline:
    Given Latest Rates API was called
    When The API is available
    Then Success status of the response returned is <errorCode>
    Examples:
      | errorCode |
      | 200       |