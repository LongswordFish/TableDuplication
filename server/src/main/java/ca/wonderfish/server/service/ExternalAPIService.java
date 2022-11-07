package ca.wonderfish.server.service;

import ca.wonderfish.server.domain.PlaceKeyResponse;
import ca.wonderfish.server.domain.RealEstate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalAPIService {

    @Autowired
    WebClient webClient;

    @Value("${placeKeyApi}")
    private String placeKeyApi;

    //call the external api to get the place key
    //return null if no place key returns
    public String getPlaceKey(RealEstate realEstate)
    {
        //create request body for the post
        JSONObject queryBody = new JSONObject();
        queryBody.put("street_address", realEstate.getAddress());
        queryBody.put("city", realEstate.getCity());
        queryBody.put("region", realEstate.getState());
        queryBody.put("postal_code", "");
        queryBody.put("iso_country_code", "US");

        JSONObject requestBody = new JSONObject();
        requestBody.put("query",queryBody);

        //execute post reqeust
        PlaceKeyResponse pr =  webClient.post()
                .uri("")
                .header("apikey", placeKeyApi)
                .header("Content-Type","application/json")
                .body(BodyInserters.fromValue(requestBody.toString()))
                .retrieve()
                .bodyToMono(PlaceKeyResponse.class)
                .block();

        return pr.getPlacekey();
    }

}
