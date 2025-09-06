#Feature: User Workflow
#
#  Background:
#    * url 'http://localhost:8081/'
#
#  Scenario: Full User Lifecycle
#    # Create user
#    * def createUserResult = call read('classpath:conduitApp/mockFeature/userUtils.feature@create') { name: 'Pravin', role: 'admin' }
#    * def userId = createUserResult.userId
#
#    # Update user
#    * def updateUserResult = call read('classpath:conduitApp/mockFeature/userUtils.feature@update') { userId: userId, newName: 'Pravin Updated', newRole: 'super-admin' }
#
#    # Delete user
#    * def deleteUserResult = call read('classpath:conduitApp/mockFeature/userUtils.feature@delete') { userId: userId }