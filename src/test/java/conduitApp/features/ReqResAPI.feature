Feature: ReqRes API testing

  Background:
    * url 'https://reqres.in/api'
    * headers headers

#  Scenario: Get single user
#    Given path 'users', userId
#    When method get
#    Then status 200

#  Scenario: Get user list
#    Given path 'users'
#    And param page = page
#    When method get
#    Then status 200

  Scenario: Get all users
    Given path 'users'
    When method get
    Then status 200

  Scenario: Create a new user (requires key)
    Given path 'user'
    And request { name: 'Jane Doe', job: 'Engineer' }
    When method post
    Then status 201