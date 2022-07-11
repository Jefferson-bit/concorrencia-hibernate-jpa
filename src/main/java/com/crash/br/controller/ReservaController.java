package com.crash.br.controller;

import com.crash.br.entity.Quarto;
import com.crash.br.entity.Reserva;
import com.crash.br.repository.QuartoRepository;
import com.crash.br.repository.ReservaRepository;
import com.crash.br.request.ReservaRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final QuartoRepository quartoRepository;

    public ReservaController(ReservaRepository reservaRepository, QuartoRepository quartoRepository) {
        this.reservaRepository = reservaRepository;
        this.quartoRepository = quartoRepository;
    }

    @PostMapping(value = "quartos/{id}/reservas")
    @Transactional
    public ResponseEntity<?> reservaQuarto(@RequestBody ReservaRequest request, @PathVariable Long id) {

        Quarto quarto = quartoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto com id:" + "não encontrado"));

        Reserva reserva = request.toModel(quarto);
        System.out.println(request.toString());
        quarto.adiciona(reserva);
        quartoRepository.saveAndFlush(quarto);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand("id", reserva.getId()).toUri();

        return ResponseEntity.created(uri).body(request);
    }
}
