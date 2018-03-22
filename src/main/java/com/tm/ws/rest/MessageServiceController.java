package com.tm.ws.rest;

import com.tm.ws.model.MyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceController.class);

    @RequestMapping("/custom-message-param")
    @ResponseBody
    public MyMessage customMessageParameterized(@RequestParam(name="id") long id, @RequestParam(name = "text") String text) {
        LOGGER.info("/custom-message-param called with text=" + text);
        return new MyMessage(id, text);
    }

    @RequestMapping("/custom-message-path-param/{id}/{text}")
    @ResponseBody
    public MyMessage customMessagePathParameterized(@PathVariable long id, @PathVariable String text) {
        LOGGER.info("/custom-message-path-param called with id=" + id);
        return new MyMessage(id, text);
    }

    @PostMapping("/log-message")
    @ResponseStatus(value = HttpStatus.OK)
    public void randomMessage() {
        LOGGER.info("/log-message called");
    }

    @PostMapping("/echo-message")
    @ResponseBody
    public MyMessage echo(@RequestBody MyMessage message) {
        LOGGER.info("/echo-message called with message=" + message);
        return message;
    }
}
