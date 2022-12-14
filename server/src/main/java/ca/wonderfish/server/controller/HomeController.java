package ca.wonderfish.server.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class HomeController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("")
    public ResponseEntity<?> home(){
        logger.info("GET /api");
        JSONObject responseBody = new JSONObject();
        responseBody.put("API version", "0.01");
        responseBody.put("Created By", "Robin Yu");

        return new ResponseEntity<>(responseBody.toString(), HttpStatus.OK);
    }
}
