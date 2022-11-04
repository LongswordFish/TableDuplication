package ca.wonderfish.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstate {
    @Id
    private String address;
    private String city;
    private String state;
}
