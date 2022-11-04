package ca.wonderfish.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin
public class testController {

    @GetMapping("")
    public ResponseEntity<?> test(){
        return new ResponseEntity<String>("HelloWorld", HttpStatus.OK);
    }

}
