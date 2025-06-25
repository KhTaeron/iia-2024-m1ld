package fr.formation.api;

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
import fr.formation.model.Commentaire;
import fr.formation.model.Produit;
import fr.formation.repo.CommentaireRepository;
import fr.formation.repo.ProduitRepository;
import fr.formation.request.CreateOrUpdateProduitRequest;
import fr.formation.response.CommentaireResponse;
import fr.formation.response.ProduitByIdResponse;
import fr.formation.response.ProduitResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produit")
@RequiredArgsConstructor
public class ProduitApiController {
    private final ProduitRepository repository;
    private final CommentaireRepository commentaireRepository;

    @GetMapping
    public List<ProduitResponse> findAll() {
        return this.repository.findAll()
            .stream()
            .map(p -> {
                 int note = (int)this.commentaireRepository.findAllByProduitId(p.getId())
                    .stream()
                    .mapToInt(Commentaire::getNote)
                    .average()
                    .orElse(-1);

                return ProduitResponse.builder()
                    .id(p.getId())
                    .nom(p.getNom())
                    .prix(p.getPrix())
                    .note(note)
                    .build()
                ;
            })
            .toList()
        ;
    }

    @GetMapping("/{id}")
    public ProduitByIdResponse findById(@PathVariable String id) {
        Produit produit = this.repository.findById(id).orElseThrow(ProduitNotFoundException::new);
        List<Commentaire> commentaires = this.commentaireRepository.findAllByProduitId(id);
        ProduitByIdResponse resp = new ProduitByIdResponse();

        int note = (int)commentaires
            .stream()
            .mapToInt(Commentaire::getNote)
            .average()
            .orElse(-1);

        BeanUtils.copyProperties(produit, resp);

        resp.setCommentaires(commentaires
            .stream()
            .map(c -> CommentaireResponse.builder()
                .texte(c.getTexte())
                .note(c.getNote())
                .build()
            )
            .toList()
        );

        resp.setNote(note);

        return resp;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CreateOrUpdateProduitRequest request) {
        Produit produit = new Produit();

        BeanUtils.copyProperties(request, produit);

        this.repository.save(produit);

        return produit.getId();
    }
}
