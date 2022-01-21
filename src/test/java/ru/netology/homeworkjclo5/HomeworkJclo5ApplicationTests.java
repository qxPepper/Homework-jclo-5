package ru.netology.homeworkjclo5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeworkJclo5ApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static GenericContainer<?> devApp = new GenericContainer<>("devapp:1.0")
            .withExposedPorts(8080);
    private static GenericContainer<?> prodApp = new GenericContainer<>("prodapp:1.0")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void contextLoads() {
        Integer devAppPort = devApp.getMappedPort(8080);
        ResponseEntity<String> fromDevApp = restTemplate.getForEntity("http://localhost:" + devAppPort, String.class);
        System.out.println("From devapp: " + fromDevApp.getBody());

        Integer prodAppPort = prodApp.getMappedPort(8081);
        ResponseEntity<String> fromProdApp = restTemplate.getForEntity("http://localhost:" + prodAppPort, String.class);
        System.out.println("From prodApp: " + fromProdApp.getBody());
    }

}
