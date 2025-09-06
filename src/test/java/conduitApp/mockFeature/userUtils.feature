#@create
#Scenario: Create User
#Given path 'user'
#And request { name: '#(name)', role: '#(role)' }
#When method post
#Then status 201
#* def result = response
#* print 'Created userId:', response.userId
#* match response == { status: 'User created successfully', userId: '#number', name: '#(name)', role: '#(role)' }
#
#@update
#Scenario: Update User
#Given path 'user', '#(userId)'
#And request { name: '#(newName)', role: '#(newRole)' }
#When method put
#Then status 200
#* def result = response
#* print 'Updated user:', response
#
#@delete
#Scenario: Delete User
#Given path 'user', '#(userId)'
#When method delete
#Then status 200
#* def result = response
#* print 'Deleted user:', response
#
#
#  # Full User Lifecycle
#* def createUserResult = call read('classpath:conduitApp/mockFeature/userUtils.feature@create') { name: 'Pravin', role: 'admin' }
#* def userId = createUserResult.userId
#
#* def updateUserResult = call read('classpath:conduitApp/mockFeature/userUtils.feature@update') { userId: userId, newName: 'Pravin Updated', newRole: 'super-admin' }
#
#* def deleteUserResult = call read('classpath:conduitApp/mockFeature/userUtils.feature@delete') { userId: userId }
