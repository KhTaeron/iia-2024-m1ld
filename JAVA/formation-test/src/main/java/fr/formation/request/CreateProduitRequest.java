package fr.formation.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProduitRequest {
    @NotEmpty
    private String name;

    private BigDecimal price;
    private String fournisseurId;
}
