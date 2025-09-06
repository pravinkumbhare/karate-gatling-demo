package conduitApp.performance

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class mockUsersSimulation extends Simulation{


  // Protocol
  val httpProtocol = karateProtocol()

  val feeder = csv("data/users.csv").circular

  // Scenario

  // scenario to Create User (POST)
  val createUserScenario = scenario("Create User")
    .feed(feeder)           // inject CSV data into session
    .exec(karateFeature("classpath:conduitApp/mockFeature/mockUsers.feature"))
    .pause(5, 10)  // random pause between 5 and 10 seconds

  // scenario to Update User (PUT)
  val updateUserScenario = scenario("Update User")
    .feed(feeder)
    .exec(karateFeature("classpath:conduitApp/mockFeature/mockUsers.feature"))
    .pause(5, 10)   // random pause between 5 and 10 seconds

  // scenario to Delete User (DELETE)
  val deleteUserScenario = scenario("Delete User")
  .exec(karateFeature("classpath:conduitApp/mockFeature/mockUsers.feature"))
    .pause(5, 10)

  // scenario to get single User (GET)
  val getSingleUserScenario = scenario("Get Single User")
  .exec(karateFeature("classpath:conduitApp/mockFeature/mockUsers.feature"))
    .pause(5, 10)

  // scenario to get all Users (GET)
  val getAllUsersScenario = scenario("Get All Users")
    .exec(karateFeature("classpath:conduitApp/mockFeature/mockUsers.feature"))
    .pause(5, 10)

  // setUp
  setUp(
    createUserScenario.inject(
//      constantUsersPerSec(1) during (1.minute)  // POST requests, very low load
//      rampUsersPerSec(1) to 2 during(1.minute)

//        rampUsersPerSec(1).to(20).during(30.second),       // ramp-up
//        constantUsersPerSec(20).during(60.second),         // steady load
//        rampUsersPerSec(20).to(0).during(20.second)        // ramp-down

      rampUsersPerSec(1).to(5).during(30.second),       // ramp-up
      constantUsersPerSec(5).during(60.second),         // steady load
      rampUsersPerSec(5).to(0).during(20.second)        // ramp-down


    ).protocols(httpProtocol),

    updateUserScenario.inject(

//      rampUsersPerSec(1).to(20).during(30.second),       // ramp-up
//      constantUsersPerSec(20).during(60.second),         // steady load
//      rampUsersPerSec(20).to(0).during(20.second)        // ramp-down

      rampUsersPerSec(1).to(5).during(30.second),       // ramp-up
      constantUsersPerSec(5).during(60.second),         // steady load
      rampUsersPerSec(5).to(0).during(20.second)        // ramp-down

    ).protocols(httpProtocol),

    deleteUserScenario.inject(

//      rampUsersPerSec(1).to(20).during(30.second),       // ramp-up
//      constantUsersPerSec(20).during(60.second),         // steady load
//      rampUsersPerSec(20).to(0).during(20.second)        // ramp-down

      rampUsersPerSec(1).to(5).during(30.second),       // ramp-up
      constantUsersPerSec(5).during(60.second),         // steady load
      rampUsersPerSec(5).to(0).during(20.second)        // ramp-down

    ).protocols(httpProtocol),

    getSingleUserScenario.inject(

//      rampUsersPerSec(1).to(20).during(30.second),       // ramp-up
//      constantUsersPerSec(20).during(60.second),         // steady load
//      rampUsersPerSec(20).to(0).during(20.second)        // ramp-down

      rampUsersPerSec(1).to(5).during(30.second),       // ramp-up
      constantUsersPerSec(5).during(60.second),         // steady load
      rampUsersPerSec(5).to(0).during(20.second)        // ramp-down

    ).protocols(httpProtocol),

    getAllUsersScenario.inject(

//      rampUsersPerSec(1).to(20).during(30.second),       // ramp-up
//      constantUsersPerSec(20).during(60.second),         // steady load
//      rampUsersPerSec(20).to(0).during(20.second)        // ramp-down

      rampUsersPerSec(1).to(5).during(30.second),       // ramp-up
      constantUsersPerSec(5).during(60.second),         // steady load
      rampUsersPerSec(5).to(0).during(20.second)        // ramp-down

    ).protocols(httpProtocol)
  )
    .assertions(
      global.responseTime.percentile3.lt(3000),    // 95% requests < 3s
      global.successfulRequests.percent.gt(95)     // > 95% success
    )

}
