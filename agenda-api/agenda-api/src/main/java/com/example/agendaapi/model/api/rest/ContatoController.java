package com.example.agendaapi.model.api.rest;

import com.example.agendaapi.model.entity.Contato;
import com.example.agendaapi.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ContatoController {


    private final ContatoRepository repository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save(@RequestBody Contato contato) {
    return repository.save(contato);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping
    public List<Contato> list(){
        return repository.findAll();
    }


    @PatchMapping({"{id}/favorito"})
    public void favorite( @PathVariable Integer id){
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent( c -> {
            boolean favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
            repository.save(c);
        });
    }
}
