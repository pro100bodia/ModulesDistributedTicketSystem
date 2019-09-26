Feature: Get All Users
  Sending request for all users with different roles

  Scenario: I have or not access to all users resource
    When I try to access all users
    Then Receive response with code of 401
