package fr.formation.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ProduitResponse {
    private String id;
    private String name;
    private BigDecimal price;
    
    @JsonFormat(pattern = "dd/MM/YYYY HH:mm:ss")
    private LocalDateTime date;

    private String fournisseurId;
}
