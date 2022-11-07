package ca.wonderfish.server.payload;

import ca.wonderfish.server.domain.RealEstate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeduplicateRequest {
    @NotNull
    private List<RealEstate> tableA;
    @NotNull
    private List<RealEstate> tableB;
}
