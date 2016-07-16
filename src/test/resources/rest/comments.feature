Feature: Comments Resource
  
  As a developer I want to read a list of all comments filtered by postId
  
  Scenario: Read a comment by id using path variable
    When I get the resource "/posts/1/comments"
    Then the status code should be 200
    And response data "$.length()" should be "5"

  Scenario: Read a comment by id using query param
    When I get the resource "/comments?postId=1"
    Then the status code should be 200
    And response data "$.length()" should be "5"