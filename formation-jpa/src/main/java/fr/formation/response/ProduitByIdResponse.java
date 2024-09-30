package fr.formation.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ProduitByIdResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private LocalDateTime date;
    private String fournisseurId;
    private String fournisseurName;
}
