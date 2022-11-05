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

    public String getPlaceKey(RealEstate realEstate)
    {
//        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
//
//        bodyValues.add("street_address", realEstate.getAddress());
//        bodyValues.add("city", realEstate.getCity());
//        bodyValues.add("region", realEstate.getState());
//        bodyValues.add("postal_code", "");
//        bodyValues.add("iso_country_code", "US");

        JSONObject queryBody = new JSONObject();
        queryBody.put("street_address", realEstate.getAddress());
        queryBody.put("city", realEstate.getCity());
        queryBody.put("region", realEstate.getState());
        queryBody.put("postal_code", "");
        queryBody.put("iso_country_code", "US");

        JSONObject requestBody = new JSONObject();
        requestBody.put("query",queryBody);

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
