package conduitApp.performance

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class ReqResSimulation extends Simulation {

  // 1st way
  // Define the Karate protocol
  val protocol = karateProtocol(
    "/api/users/2" -> Nil,    // GET single user
    "/api/users" -> Nil       // GET all users / POST create user
  )

  // Scenario: GET user
  val getUserScenario = scenario("Get Single User")
    .exec(karateFeature("classpath:conduitApp/features/ReqResAPI.feature"))
    .pause(2, 5)  // random pause between 2 and 5 seconds

  // Scenario: Create User (POST)
  val createUserScenario = scenario("Create User")
    .exec(karateFeature("classpath:conduitApp/features/ReqResAPI.feature"))
    .pause(5, 10) // slower pauses to avoid quota

  // Inject users carefully
  setUp(
    getUserScenario.inject(
      constantUsersPerSec(2) during (1.minute)  // GET requests, low load
    ).protocols(protocol),

    createUserScenario.inject(
      constantUsersPerSec(1) during (1.minute)  // POST requests, very low load
    ).protocols(protocol)
  )

  // 2nd way

//  val httpProtocol = karateProtocol(
//    "/api/users" -> Nil
//  )
//
//  val getSingleUser = scenario("Get Single User")
//    .exec(karateFeature("classpath:conduitApp/features/ReqResAPI.feature"))
//    .pause(1,5)   // simulate user think-time between 1 and 5 seconds
//
//
//
//  // Gatling scenario simulate more users or parallel load for a more realistic performance test
//  setUp(
//    getSingleUser.inject(
//      rampUsers(50).during(100.seconds),           // 50 users gradually over 30 seconds
//      constantUsersPerSec(10) during(5.minutes),  // 10 users per second for 1 minute
//      atOnceUsers(5)                              // optional: 5 users immediately
//    )
//  ).protocols(httpProtocol)


//  setUp(
  ////    getSingleUser.inject(atOnceUsers(1)) // simulate 5 users at once
  //    getSingleUser.inject(rampUsers(5) during(20))
  //  ).protocols(httpProtocol)
}
