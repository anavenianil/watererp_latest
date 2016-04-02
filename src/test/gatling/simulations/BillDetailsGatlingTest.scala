import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the BillDetails entity.
 */
class BillDetailsGatlingTest extends Simulation {

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

    val scn = scenario("Test the BillDetails entity")
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
            exec(http("Get all billDetailss")
            .get("/api/billDetailss")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new billDetails")
            .post("/api/billDetailss")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "can":"SAMPLE_TEXT", "bill_number":"SAMPLE_TEXT", "bill_date":"2020-01-01T00:00:00.000Z", "bill_time":"SAMPLE_TEXT", "meter_make":"SAMPLE_TEXT", "current_bill_type":"SAMPLE_TEXT", "from_month":"SAMPLE_TEXT", "to_month":"SAMPLE_TEXT", "meter_fix_date":"SAMPLE_TEXT", "initial_reading":"SAMPLE_TEXT", "present_reading":"SAMPLE_TEXT", "units":"SAMPLE_TEXT", "water_cess":"SAMPLE_TEXT", "sewerage_cess":"SAMPLE_TEXT", "service_charge":"SAMPLE_TEXT", "meter_service_charge":"SAMPLE_TEXT", "total_amount":null, "net_payable_amount":null, "telephone_no":"SAMPLE_TEXT", "meter_status":"SAMPLE_TEXT", "mc_met_reader_code":"SAMPLE_TEXT", "bill_flag":"SAMPLE_TEXT", "svr_status":"SAMPLE_TEXT", "terminal_id":"SAMPLE_TEXT", "meter_reader_id":"SAMPLE_TEXT", "user_id":"SAMPLE_TEXT", "mobile_no":"SAMPLE_TEXT", "notice_no":"SAMPLE_TEXT", "lat":"SAMPLE_TEXT", "longI":"SAMPLE_TEXT", "nometer_amt":"SAMPLE_TEXT"}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_billDetails_url")))
            .pause(10)
            .repeat(5) {
                exec(http("Get created billDetails")
                .get("${new_billDetails_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created billDetails")
            .delete("${new_billDetails_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(100) over (1 minutes))
    ).protocols(httpConf)
}