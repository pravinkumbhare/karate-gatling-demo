package mock;

import com.intuit.karate.junit5.Karate;

class UsersApiTest {
    @Karate.Test
    Karate testUsersApi() {
        return Karate.run("classpath:conduitApp/mockFeature/mockUsers.feature");  // path to your feature file
    }
}
