package com.tm.ws.rest;

import com.tm.ws.model.MyMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageServiceControllerTest {

    private static final MyMessage TEST_MESSAGE = new MyMessage(123, "test_message");

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void shouldReturnMessageBuiltFromParams() {
        ResponseEntity<MyMessage> response = template
                .getForEntity(localhost("/custom-message-param?id=123&&text=test_message"), MyMessage.class);
        assertEquals(response.getBody(), TEST_MESSAGE);
    }

    @Test
    public void shouldReturnMessageBuiltFromPathParams() {
        ResponseEntity<MyMessage> response = template
                .getForEntity(localhost("/custom-message-path-param/123/test_message"), MyMessage.class);
        assertEquals(response.getBody(), TEST_MESSAGE);
    }

    @Test
    public void shouldReturnSameMessage() {
        ResponseEntity<MyMessage> response = template
                .postForEntity(localhost("/echo-message"), TEST_MESSAGE, MyMessage.class);
        assertEquals(response.getBody(), TEST_MESSAGE);
    }

    public String localhost(String path) {
        return "http://localhost:" + port + path;
    }
}