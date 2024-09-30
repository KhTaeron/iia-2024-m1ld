package fr.formation.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProduitRequest {
    private String name;
    private BigDecimal price;
}
