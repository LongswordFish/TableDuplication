package ca.wonderfish.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstate {
    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
    private String state;
}
