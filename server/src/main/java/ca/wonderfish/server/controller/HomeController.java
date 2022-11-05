package ca.wonderfish.server.controller;

import org.json.JSONObject;
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
    @GetMapping("")
    public ResponseEntity<?> test(){
        JSONObject responseBody = new JSONObject();
        responseBody.put("API version", "0.01");
        responseBody.put("Created By", "Robin Yu");

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
