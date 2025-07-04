package fr.formation.api;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.model.Commentaire;
import fr.formation.repo.CommentaireRepository;
import fr.formation.response.CommentaireByIdResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/commentaire")
@RequiredArgsConstructor
public class CommentaireApiController {
    private final CommentaireRepository repository;

    @GetMapping("/{id}")
    public CommentaireByIdResponse findById(@PathVariable String id) {
        Commentaire commentaire = this.repository.findById(id).orElseThrow();
        CommentaireByIdResponse resp = new CommentaireByIdResponse();

        BeanUtils.copyProperties(commentaire, resp);

        resp.setProduitNom(commentaire.getProduit().getNom());

        return resp;
    }
}
