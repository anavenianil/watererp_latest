import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the ApplicationTxn entity.
 */
class ApplicationTxnGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://127.0.0.1:8080"""

    val httpConf = http
        .baseURL(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connection("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "X-CSRF-TOKEN" -> "${csrf_token}"
    )

    val scn = scenario("Test the ApplicationTxn entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        .check(headerRegex("Set-Cookie", "CSRF-TOKEN=(.*); [P,p]ath=/").saveAs("csrf_token")))
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authentication")
        .headers(headers_http_authenticated)
        .formParam("j_username", "admin")
        .formParam("j_password", "admin")
        .formParam("remember-me", "true")
        .formParam("submit", "Login"))
        .pause(1)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200))
        .check(headerRegex("Set-Cookie", "CSRF-TOKEN=(.*); [P,p]ath=/").saveAs("csrf_token")))
        .pause(10)
        .repeat(2) {
            exec(http("Get all applicationTxns")
            .get("/api/applicationTxns")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new applicationTxn")
            .post("/api/applicationTxns")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "firstName":"SAMPLE_TEXT", "middleName":"SAMPLE_TEXT", "lastName":"SAMPLE_TEXT", "organization":null, "organizationName":"SAMPLE_TEXT", "designation":"SAMPLE_TEXT", "mobileNo":null, "officeNo":null, "email":"SAMPLE_TEXT", "street":"SAMPLE_TEXT", "plotNo":"SAMPLE_TEXT", "blockNo":"SAMPLE_TEXT", "tanescoMeter":"SAMPLE_TEXT", "waterConnectionUse":"SAMPLE_TEXT", "bStreet":"SAMPLE_TEXT", "ward":"SAMPLE_TEXT", "dma":"SAMPLE_TEXT", "bPlotNo":"SAMPLE_TEXT", "registeredMobile":null, "attachedDocType":"SAMPLE_TEXT", "idNumber":"SAMPLE_TEXT", "propertyDoc":"SAMPLE_TEXT", "can":"SAMPLE_TEXT", "photo":"SAMPLE_TEXT", "status":"0", "meterReading":null, "requestedDate":"2020-01-01T00:00:00.000Z", "connectionDate":"2020-01-01T00:00:00.000Z", "remarks":"SAMPLE_TEXT", "meterNo":"SAMPLE_TEXT", "approvedDate":"2020-01-01T00:00:00.000Z"}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_applicationTxn_url")))
            .pause(10)
            .repeat(5) {
                exec(http("Get created applicationTxn")
                .get("${new_applicationTxn_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created applicationTxn")
            .delete("${new_applicationTxn_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(100) over (1 minutes))
    ).protocols(httpConf)
}
