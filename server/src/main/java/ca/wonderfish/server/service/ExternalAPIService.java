package ca.wonderfish.server.service;

import ca.wonderfish.server.domain.PlaceKeyResponse;
import ca.wonderfish.server.domain.RealEstate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class ExternalAPIService {

    @Value("${placeKeyApi}")
    private String placeKeyApi;
    @Value("${PlaceKeyUrl}")
    private String placeKeyUrl;

    //call the external api to get the place key
    //return null if no place key returns
    public String getPlaceKey(RealEstate realEstate){
        var restTemplate = new RestTemplate();

        //set up request hearder
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("apikey", Collections.singletonList(placeKeyApi));

        //set up request body for external api request
        JSONObject queryBody = new JSONObject();
        queryBody.put("street_address", realEstate.getAddress());
        queryBody.put("city", realEstate.getCity());
        queryBody.put("region", realEstate.getState());
        queryBody.put("postal_code", "");
        queryBody.put("iso_country_code", "US");

        JSONObject requestBody = new JSONObject();
        requestBody.put("query",queryBody);

        //set up request
        HttpEntity<String> request =
                new HttpEntity<>(requestBody.toString(), headers);
        //send request
        PlaceKeyResponse response = restTemplate.postForObject(placeKeyUrl, request, PlaceKeyResponse.class);

        //the placekey might be null but it will handled by the caller method
        return response.getPlacekey();
    }

}
