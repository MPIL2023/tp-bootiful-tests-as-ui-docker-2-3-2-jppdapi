package jppdapi.tprest;

import jppdapi.tprest.service.AdderService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestAdderService {

	@Autowired
	private AdderService adderService;

	@Test
	public void contextLoads() {
		assertThat(adderService).isNotNull();
	}

	@Test
	@Order(1)
	public void currentAtStart() {
		assertThat(adderService.currentBase()).isEqualTo(0);
	}

	@Test
	public void setBase() {
		adderService.baseNum(10);
		assertThat(adderService.currentBase()).isEqualTo(10);
	}

	@Test
	public void add() {
		adderService.baseNum(10);
		assertThat(adderService.add(10)).isEqualTo(20);

		assertThat(adderService.add(1)).isEqualTo(11);
	}

	@Test
	public void accumulate() {
		adderService.baseNum(10);
		assertThat(adderService.accumulate(10)).isEqualTo(20);
		assertThat(adderService.currentBase()).isEqualTo(20);

		assertThat(adderService.accumulate(1)).isEqualTo(21);
		assertThat(adderService.currentBase()).isEqualTo(21);
	}
}
