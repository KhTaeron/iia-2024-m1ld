package fr.formation.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.model.Fournisseur;
import fr.formation.repo.FournisseurRepository;
import fr.formation.request.CreateFournisseurRequest;
import fr.formation.response.FournisseurResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fournisseur")
@RequiredArgsConstructor
public class FournisseurApiController {
    private final FournisseurRepository repository;

    @GetMapping
    public List<FournisseurResponse> findAll() {
        return this.repository.findAll()
            .stream()
            .map(this::convert)
            .toList()
        ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CreateFournisseurRequest request) {
        Fournisseur fournisseur = new Fournisseur();

        BeanUtils.copyProperties(request, fournisseur);

        this.repository.save(fournisseur);

        return fournisseur.getId();
    }

    private FournisseurResponse convert(Fournisseur fournisseur) {
        FournisseurResponse resp = FournisseurResponse.builder().build();

        BeanUtils.copyProperties(fournisseur, resp);

        return resp;
    }
}
