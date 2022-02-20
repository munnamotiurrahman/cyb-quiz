package project.cyb.quiz;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientOption;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AddBookTest {
    public TestRestTemplate restTemplate = new TestRestTemplate(HttpClientOption.ENABLE_COOKIES,
            HttpClientOption.ENABLE_REDIRECTS);

    public String logAppURL = "http://localhost:8027/login";
    public String mainURL = "http://localhost:8027/";

    @BeforeAll
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
    }

    @Test
    public void addBookGetTest() {
        String addBookURL = "http://localhost:8027/addBook";
        String response = restTemplate.getForObject(addBookURL, String.class);
        assertEquals(true, response.contains("<title>Add Book</title>"));
    }

    @Test
    public void addBookPostTest() {
        String addBookURL = "http://localhost:8027/addBook";
        HttpHeaders httpHeaders = generateHeader();
        MultiValueMap<String, String> postValueMap = new LinkedMultiValueMap<>();
        postValueMap.add("name", "Dummy name");
        postValueMap.add("writterName", "Writer Name");
        postValueMap.add("type", "Hello type");
        postValueMap.add("numberOfBooks", "12");
        postValueMap.add("language", "Bangla");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(postValueMap, httpHeaders);
        ResponseEntity<String> result = restTemplate.postForEntity(addBookURL, request, String.class);
        assertEquals(true, result.getBody().contains("Book added successfully"));
    }

    private HttpHeaders generateHeader() {
        HttpHeaders httpFormHeaders = new HttpHeaders();
        httpFormHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpFormHeaders;
    }
}
