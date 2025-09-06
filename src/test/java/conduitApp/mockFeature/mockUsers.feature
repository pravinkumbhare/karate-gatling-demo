Feature: Mock Users API with WireMock - Full CRUD Flow

  Background:
#    * url 'http://localhost:8081/'
  * url baseUrl

  Scenario: Full CRUD flow for a single user

  ##################################################
  # CREATE a new user
  ##################################################
    Given path 'user'
    And request
  """
  {
    name: '#(name)',
    role: '#(role)'
  }
  """
    When method post
    Then status 201
    * print 'Create Response:', response
    * def createdUserId = response.userId
    And match response contains { status: '#string', name: '#string', role: '#string' }
    And match response.userId == '#number'


  ##################################################
  # UPDATE the user
  ##################################################
#    * def updatedName = name + '-Updated'
    Given path 'user', createdUserId
    And request
  """
  {
    name: '#(updatedName)',
    role: 'super-admin'
  }
  """
    When method put
    Then status 200
    * print 'Update Response:', response
    And match response contains { status: '#string', name: '#string', role: '#string' }
    And match response.userId == '#string'


  ##################################################
  # GET single user
  ##################################################
    Given path 'user', createdUserId
    When method get
    Then status 200
    * print 'Get Single User Response:', response
    And match response == { userId: '#number', name: '#string', role: 'super-admin' }


  ##################################################
  # DELETE the user
  ##################################################
    Given path 'user', createdUserId
    When method delete
    Then status 200
    * print 'Delete Response:', response
    And match response contains { status: '#string' }
    And match response.userId == '#string'


  ##################################################
  # GET all users (optional verification)
  ##################################################
    Given path 'users'
    When method get
    Then status 200
    * print 'Get All Users Response:', response
