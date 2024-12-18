package fr.formation.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.exception.ProduitNotFoundException;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;
import fr.formation.request.CreateProduitRequest;
import fr.formation.response.ProduitByIdResponse;
import fr.formation.response.ProduitResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produit")
@RequiredArgsConstructor
public class ProduitApiController {
    private final ProduitRepository repository;

    @GetMapping
    public List<ProduitResponse> findAll() {
        return this.repository.findAll()
            .stream()
            .map(this::convert)
            .toList()
        ;
    }

    @GetMapping("/{id}")
    public ProduitByIdResponse findById(@PathVariable String id) {
        Produit produit = this.repository.findById(id).orElseThrow(ProduitNotFoundException::new);
        ProduitByIdResponse resp = ProduitByIdResponse.builder().build();

        BeanUtils.copyProperties(produit, resp);

        if (produit.getFournisseur() != null) {
            resp.setFournisseurId(produit.getFournisseur().getId());
            resp.setFournisseurName(produit.getFournisseur().getName());
        }

        return resp;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody CreateProduitRequest request) {
        Produit produit = new Produit();
        Fournisseur fournisseur = new Fournisseur();

        // produit.setName(request.getName());
        // produit.setPrice(request.getPrice());
        BeanUtils.copyProperties(request, produit);

        produit.setDate(LocalDateTime.now());
        produit.setFournisseur(fournisseur);

        fournisseur.setId(request.getFournisseurId());

        this.repository.save(produit);

        return produit.getId();
    }

    private ProduitResponse convert(Produit produit) {
        ProduitResponse resp = ProduitResponse.builder().build();

        BeanUtils.copyProperties(produit, resp);

        if (produit.getFournisseur() != null) {
            resp.setFournisseurId(produit.getFournisseur().getId());
        }

        return resp;
    }
}
