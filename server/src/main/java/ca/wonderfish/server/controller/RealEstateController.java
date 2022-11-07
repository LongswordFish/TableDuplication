package ca.wonderfish.server.controller;

import ca.wonderfish.server.domain.RealEstate;
import ca.wonderfish.server.payload.DeduplicateRequest;
import ca.wonderfish.server.service.ExternalAPIService;
import ca.wonderfish.server.service.MapValidationErrorService;
import ca.wonderfish.server.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

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

    //Remove duplicate method 1
    //Remove duplicates in list B from list A by table name
    @PostMapping("/remove-duplicates/{table_b}/{table_a}")
    public ResponseEntity<?> removeDuplicatesInBFromA(@PathVariable String table_b, @PathVariable String table_a){
        //use table name to deduplicate instead of asking the front-end to post two lists
        if("table_a".equals(table_a) && "table_b".equals(table_b)){
            List<RealEstate> realEstateFromTableA = realEstateService.getRealEstateFromTableA();
            List<RealEstate> realEstateFromTableB = realEstateService.getRealEstateFromTableB();
            List<RealEstate> deduplicatedResult = deduplicate(realEstateFromTableB, realEstateFromTableA);

            return new ResponseEntity<>(deduplicatedResult, HttpStatus.OK);
        }

        if("table_b".equals(table_a) && "table_a".equals(table_b)){
            List<RealEstate> realEstateFromTableA = realEstateService.getRealEstateFromTableA();
            List<RealEstate> realEstateFromTableB = realEstateService.getRealEstateFromTableB();
            List<RealEstate> deduplicatedResult = deduplicate(realEstateFromTableA, realEstateFromTableB);

            return new ResponseEntity<>(deduplicatedResult, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not supported table names", HttpStatus.BAD_REQUEST);
    }

    //Remove duplicate method 2
    //Remove duplicates in list B from list A by request body
    @PostMapping("/remove-duplicates/")
    public ResponseEntity<?> removeDuplicatesInBFromAWithBody(@Valid @RequestBody DeduplicateRequest deduplicateRequest,
                                                              BindingResult result){
        ResponseEntity<?> hasErrors= mapValidationErrorService.MapValidationService(result);
        if(hasErrors==null){
            List<RealEstate> realEstateFromTableA = deduplicateRequest.getTableA();
            List<RealEstate> realEstateFromTableB = deduplicateRequest.getTableB();

            List<RealEstate> deduplicatedResult = deduplicate(realEstateFromTableB, realEstateFromTableA);

            return new ResponseEntity<>(deduplicatedResult, HttpStatus.OK);

        }else{
            return hasErrors;
        }

    }

    //remove from table B the elements that also appear in table A
    //return the deduplicated table B
    private List<RealEstate> deduplicate(List<RealEstate> listB,List<RealEstate> listA){
        List<RealEstate> deduplicatedResult = new ArrayList<>();
        List<String> uniqueKeys = new ArrayList<>();

        //get placekey for each address of the real estate and put it in the list
        for(RealEstate rs:listA){
            // get placekey for each real estate in table A
            String uniqueKey = this.externalAPIService.getPlaceKey(rs);
            //if no the place key returns, put the address as the unique key instead
            if(uniqueKey!=null){
                uniqueKeys.add(uniqueKey);
            }else{
                uniqueKeys.add(rs.getAddress());
            }
        }

        //deduplicate real estates in table B
        for(RealEstate rs:listB){
            //get place key for each real estate in table B
            String uniqueKey = this.externalAPIService.getPlaceKey(rs);

            //check if the place key already exists
            // only put the real estate into the result list if it doesn't exist
            if(uniqueKey != null){
                if(!uniqueKeys.contains(uniqueKey)){
                    deduplicatedResult.add(rs);
                }
            }else{
                if(!uniqueKeys.contains(rs.getAddress())){
                    deduplicatedResult.add(rs);
                }
            }
        }

        return deduplicatedResult;
    }

}
