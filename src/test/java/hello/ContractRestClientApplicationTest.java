package hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.example:contract-rest-service:+:8100", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ContractRestClientApplicationTest {

//  //if set @AutoConfigureStubRunner, then no need set this stubRunner config
//	@RegisterExtension
//	public StubRunnerExtension stubRunner = new StubRunnerExtension()
//		.downloadStub("com.example", "contract-rest-service", "0.0.1-SNAPSHOT", "stubs")
//		.withPort(8100)
//		.stubsMode(StubRunnerProperties.StubsMode.LOCAL);

	@Autowired
	private MessageRestController messageRestController;

	@Test
	public void get_person_from_service_contract() {

		// when:
		Person person = messageRestController.getMessage(1L);

		// then:
		then(person.getId()).isEqualTo(1L);
		then(person.getName()).isEqualTo("foo");
		then(person.getSurname()).isEqualTo("bee");
	}
}
