package ca.wonderfish.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/test")
@CrossOrigin
public class testController {

    @GetMapping("")
    public ResponseEntity<?> test(){
        return new ResponseEntity<String>("HelloWorld", HttpStatus.OK);
    }

}
