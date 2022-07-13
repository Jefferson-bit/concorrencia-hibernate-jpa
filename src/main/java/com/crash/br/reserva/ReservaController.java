package com.crash.br.reserva;

import com.crash.br.quarto.Quarto;
import com.crash.br.quarto.QuartoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ReservaController {

    private final QuartoRepository quartoRepository;

    private final TransactionTemplate transactionTemplate;

    public ReservaController(QuartoRepository quartoRepository, TransactionTemplate transactionTemplate) {
        this.quartoRepository = quartoRepository;
        this.transactionTemplate = transactionTemplate;
    }

    @PostMapping(value = "quartos/{id}/reservas")
    public ResponseEntity<?> reservar(@RequestBody ReservaRequest request, @PathVariable Long id) {
        return transactionTemplate.execute(transaction -> {
            Quarto quarto = quartoRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto com id:" + "não encontrado"));

            Reserva reserva = request.toModel(quarto);
            quarto.addReserva(reserva);

            quartoRepository.saveAndFlush(quarto);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand("id", reserva.getId()).toUri();

            return ResponseEntity.created(uri).body(request);
        });
    }
}
