package ca.wonderfish.server.controller;

import ca.wonderfish.server.domain.RealEstate;
import ca.wonderfish.server.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/real-estate")
@CrossOrigin
public class RealEstateController {

    @Autowired
    private RealEstateService realEstateService;

    @GetMapping("")
    public ResponseEntity<List<RealEstate>> getAllRealEstateFromTableA(){
        List<RealEstate> realEstateFromTableA = realEstateService.getRealEstateFromTableA();
        return new ResponseEntity<List<RealEstate>>(realEstateFromTableA, HttpStatus.OK);
    }
}
