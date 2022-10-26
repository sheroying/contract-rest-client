package hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@AutoConfigureWireMock(port = 8100)
public class IntegrationRestClientApplicationTest {

	@Autowired
	private MessageRestController messageRestController;

	@Test
	public void get_person_from_service_contract() {
		// given:
		stubFor(get("/person/1").willReturn(okJson(
				"{\"id\": 1,\n" +
				"\"aaaname\": \"foo\",\n" +
				"\"surname\" : \"bee\"}")));

		// when:
		Person person = messageRestController.getMessage(1L);

		// then:
		then(person.getId()).isEqualTo(1L);
		then(person.getName()).isEqualTo("foo");
		then(person.getSurname()).isEqualTo("bee");
	}
}
