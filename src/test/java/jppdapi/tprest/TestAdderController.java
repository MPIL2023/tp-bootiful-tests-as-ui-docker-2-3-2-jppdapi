package jppdapi.tprest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestAdderController {

	@LocalServerPort
	private int port;

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	@Order(1)
	void current() {
		assertThat(restTemplate.getForObject(
				"http://localhost:" + port + "/adder/current",
				String.class
		)).isNotNull().isEqualTo("0");
	}

	@Test
	void add() {
		assertThat(restTemplate.postForObject(
				"http://localhost:" + port + "/adder",
				1,
				String.class
		)).isNotNull().isEqualTo("1");

		assertThat(restTemplate.getForObject(
				"http://localhost:" + port + "/adder/current",
				String.class
		)).isNotNull().isEqualTo("0");
	}

	@Test
	void accumulate() {
		assertThat(restTemplate.postForObject(
				"http://localhost:" + port + "/adder/acc",
				1,
				String.class
		)).isNotNull().isEqualTo("1");

		assertThat(restTemplate.getForObject(
				"http://localhost:" + port + "/adder/current",
				String.class
		)).isNotNull().isEqualTo("1");
	}
}
