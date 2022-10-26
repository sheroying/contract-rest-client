package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessageRestController {

    private final RestTemplate restTemplate;
    @Value("${provider.server.port}")
    private int providerServerPort;

    MessageRestController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping("/message/{personId}")
    Person getMessage(@PathVariable("personId") Long personId) {
        Person person = this.restTemplate.getForObject("http://localhost:" + Integer.valueOf(providerServerPort) + "/person/{personId}", Person.class, personId);
        return person;
    }

}
