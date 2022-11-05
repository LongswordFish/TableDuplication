package ca.wonderfish.server.controller;

import ca.wonderfish.server.domain.RealEstate;
import ca.wonderfish.server.service.ExternalAPIService;
import ca.wonderfish.server.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/real-estate")
@CrossOrigin
public class RealEstateController {

    @Autowired
    private RealEstateService realEstateService;

    @Autowired
    private ExternalAPIService externalAPIService;

    //Get all the real estates from table a
    @GetMapping("/A")
    public ResponseEntity<List<RealEstate>> getAllRealEstateFromTableA(){
        List<RealEstate> realEstateFromTableA = realEstateService.getRealEstateFromTableA();
        return new ResponseEntity<List<RealEstate>>(realEstateFromTableA, HttpStatus.OK);
    }

    //Get all the real estates from table b
    @GetMapping("/B")
    public ResponseEntity<List<RealEstate>> getAllRealEstateFromTableB(){
        List<RealEstate> realEstateFromTableB = realEstateService.getRealEstateFromTableB();
        return new ResponseEntity<List<RealEstate>>(realEstateFromTableB, HttpStatus.OK);
    }

    //Remove duplicates in list B from list A
    @PostMapping("/remove-duplicates/{table_b}/{table_a}")
    public ResponseEntity<?> removeDuplicatesInBFromA(@PathVariable String table_a, @PathVariable String table_b){
        //use table name to deduplicate instead of asking the front-end to post two lists
        if("table_a".equals(table_a) && "table_b".equals(table_b)){
            List<RealEstate> realEstateFromTableA = realEstateService.getRealEstateFromTableA();
            List<RealEstate> realEstateFromTableB = realEstateService.getRealEstateFromTableB();

            List<RealEstate> deduplicatedResult = new ArrayList<>();
            List<String> placeKeys = new ArrayList<>();

            //get placekey for each address of the real estate and put it in the list
            for(RealEstate rs:realEstateFromTableA){

            }
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //Get all the real estates from table b
    @GetMapping("/test")
    public ResponseEntity<List<String>> getPlaceKeys(){
        List<RealEstate> realEstateFromTableB = realEstateService.getRealEstateFromTableB();
        List<String> placeKeys = new ArrayList<>();
        for(RealEstate rs:realEstateFromTableB){
            placeKeys.add(this.externalAPIService.getPlaceKey(rs));
        }
        return new ResponseEntity<>(placeKeys, HttpStatus.OK);
    }
}
