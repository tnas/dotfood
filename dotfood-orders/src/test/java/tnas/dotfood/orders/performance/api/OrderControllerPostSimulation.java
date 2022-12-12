package tnas.dotfood.orders.performance.api;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class OrderControllerPostSimulation extends Simulation {
	
	HttpProtocolBuilder httpProtocol = HttpDsl.http
			.baseUrl("http://localhost:8080/ms-orders/orders")
			.acceptHeader("application/json")
			.userAgentHeader("Gattling/ Performance Test");
	
	String json = """
			{
			  "items": [
			    {
			      "amount": #{amount},
			      "description": "Gatling Hamburger"
			    }
			  ] 
			}
			"""; 
	
	Iterator<Map<String, Object>> feeder = Stream.generate((Supplier<Map<String, Object>>)
			() -> Collections.singletonMap("amount", 3))
		.iterator();
	
	ScenarioBuilder scn = CoreDsl.scenario("Load Test")
			.feed(feeder)
			.exec(HttpDsl.http("create-order-request")
					.post("/")
					.header("Content-Type", "application/json")
					.body(CoreDsl.StringBody(json))
					.check(HttpDsl.status().is(201))
			);
	
	public OrderControllerPostSimulation() {
		this.setUp(scn.injectOpen(CoreDsl
				.incrementUsersPerSec(20)
				.times(5)
				.eachLevelLasting(Duration.ofSeconds(5))
				.startingFrom(20))
			).protocols(httpProtocol);
	}
}
