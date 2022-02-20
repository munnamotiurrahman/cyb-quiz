package project.cyb.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientOption;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class LoginTest {
    public TestRestTemplate restTemplate = new TestRestTemplate(HttpClientOption.ENABLE_COOKIES,
            HttpClientOption.ENABLE_REDIRECTS);

    public String logAppURL = "http://localhost:8027/login";
    public String mainURL = "http://localhost:8027/";

    @BeforeAll
    public void QuizLoginTestGet() {
        String response = restTemplate.getForObject(logAppURL, String.class);
        assertEquals(true, response.contains("<title>Login</title>"));
    }

    @Test
    public void QuizLoginTest() {
        HttpHeaders httpFormHeaders = generateHeader();

        MultiValueMap<String, String> postValueMap = new LinkedMultiValueMap<>();
        postValueMap.add("username", "munna.cse.ju@gmail.com");
        postValueMap.add("password", "munna");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(postValueMap, httpFormHeaders);
        ResponseEntity<String> result = restTemplate.postForEntity(logAppURL,
                request,
                String.class);
        assertEquals(true, result.getStatusCode().is3xxRedirection());
        String response = restTemplate.getForObject(mainURL, String.class);
        assertEquals(true, response.contains("Welcome to Quiz Project"));
        ;

    }

    private HttpHeaders generateHeader() {
        HttpHeaders httpFormHeaders = new HttpHeaders();
        httpFormHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpFormHeaders;
    }
}