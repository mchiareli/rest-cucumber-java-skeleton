Feature: Posts Resource

  API clients should be able to read posts filtered by id, all posts, delete and update a post using an id.

  Scenario: Read a post by id
    When I get the resource "/posts/1"
    Then the status code should be 200
    And response data "$.id" should be "1"
    And response data "$.userId" should be "1"
    And response data "$.title" should be "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"

  Scenario: Read a list of posts
    When I get the resource "/posts"
    Then the status code should be 200
    And response data "$.length()" should be "100"

  Scenario: Create a new post
    When I post the resource "/posts" with body
      """
      {
        "title": "foo",
        "body": "bar",
        "userId": 1
      }
      """
    Then the status code should be 201
    And response data "$.id" should be "101"

  Scenario: Update a post
    When I put the resource "/posts/1" with body
      """
      {
        "title": "foo",
        "body": "bar",
        "userId": 1
      }
      """
    Then the status code should be 200
    And response data "$.title" should be "foo"

  Scenario: Delete a valid resource
    When I delete the resource "/posts/1" with body
    Then the status code should be 200