package project.cyb.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)

public class RegistrationTest {

    public String regAppURL = "http://localhost:8027/registration";
    public TestRestTemplate restTemplate = new TestRestTemplate(HttpClientOption.ENABLE_COOKIES,
            HttpClientOption.ENABLE_REDIRECTS);

    @Test
    public void QuizRegistrationTestGet() {
        String response = restTemplate.getForObject(regAppURL, String.class);
        assertEquals(true, response.contains("<title>Registration</title>"));
    }

    @Test
    public void testRegistration() {
        HttpHeaders httpFormHeaders = generateHeader();

        MultiValueMap<String, String> postValueMap = new LinkedMultiValueMap<>();
        postValueMap.add("email", "munna.cse.ju@gmail.com");
        postValueMap.add("password", "munna");
        postValueMap.add("confirmPassword", "munna");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(postValueMap, httpFormHeaders);
        ResponseEntity<String> result = restTemplate.postForEntity(regAppURL, request,
                String.class);
        assertThat(result.getStatusCode().equals(HttpStatus.PERMANENT_REDIRECT));
    }

    private HttpHeaders generateHeader() {
        HttpHeaders httpFormHeaders = new HttpHeaders();
        httpFormHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpFormHeaders;
    }

}
