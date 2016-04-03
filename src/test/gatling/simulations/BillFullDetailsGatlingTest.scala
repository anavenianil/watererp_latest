import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the BillFullDetails entity.
 */
class BillFullDetailsGatlingTest extends Simulation {

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

    val scn = scenario("Test the BillFullDetails entity")
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
            exec(http("Get all billFullDetailss")
            .get("/api/billFullDetailss")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new billFullDetails")
            .post("/api/billFullDetailss")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "can":"SAMPLE_TEXT", "divCode":"SAMPLE_TEXT", "secCode":"SAMPLE_TEXT", "secName":"SAMPLE_TEXT", "metReaderCode":"SAMPLE_TEXT", "connDate":"2020-01-01T00:00:00.000Z", "consName":"SAMPLE_TEXT", "houseNo":"SAMPLE_TEXT", "address":"SAMPLE_TEXT", "city":"SAMPLE_TEXT", "pinCode":"SAMPLE_TEXT", "category":"SAMPLE_TEXT", "pipeSize":null, "boardMeter":"SAMPLE_TEXT", "sewerage":"SAMPLE_TEXT", "meterNo":"SAMPLE_TEXT", "prevBillType":"SAMPLE_TEXT", "prevBillMonth":"2020-01-01T00:00:00.000Z", "prevAvgKl":null, "metReadingDt":"2020-01-01T00:00:00.000Z", "prevReading":null, "metReadingMo":"2020-01-01T00:00:00.000Z", "metAvgKl":null, "arrears":null, "reversalAmt":null, "installment":null, "otherCharges":null, "surcharge":null, "hrsSurcharge":"SAMPLE_TEXT", "resUnits":null, "metCostInstallment":null, "intOnArrears":null, "lastPymtDt":"2020-01-01T00:00:00.000Z", "lastPymtAmt":null, "billNumber":"SAMPLE_TEXT", "billDate":"2020-01-01T00:00:00.000Z", "billTime":"SAMPLE_TEXT", "meterMake":"SAMPLE_TEXT", "currentBillType":"SAMPLE_TEXT", "fromMonth":"SAMPLE_TEXT", "toMonth":"SAMPLE_TEXT", "meterFixDate":"2020-01-01T00:00:00.000Z", "initialReading":null, "presentReading":null, "units":null, "waterCess":null, "sewerageCess":null, "serviceCharge":null, "meterServiceCharge":null, "totalAmount":null, "netPayableAmount":null, "telephoneNo":"SAMPLE_TEXT", "meterStatus":"SAMPLE_TEXT", "billFlag":"SAMPLE_TEXT", "svrStatus":"SAMPLE_TEXT", "terminalId":"SAMPLE_TEXT", "meterReaderId":"SAMPLE_TEXT", "userId":"SAMPLE_TEXT", "mobileNo":"SAMPLE_TEXT", "noticeNo":"SAMPLE_TEXT", "lat":"SAMPLE_TEXT", "longi":"SAMPLE_TEXT", "noMeterAmt":null}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_billFullDetails_url")))
            .pause(10)
            .repeat(5) {
                exec(http("Get created billFullDetails")
                .get("${new_billFullDetails_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created billFullDetails")
            .delete("${new_billFullDetails_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(100) over (1 minutes))
    ).protocols(httpConf)
}
