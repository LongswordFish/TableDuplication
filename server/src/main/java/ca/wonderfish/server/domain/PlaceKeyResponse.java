package ca.wonderfish.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceKeyResponse {
    private String query_id;
    private String placekey;
}
