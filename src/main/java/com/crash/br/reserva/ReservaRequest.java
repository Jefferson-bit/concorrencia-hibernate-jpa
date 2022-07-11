package com.crash.br.reserva;

import com.crash.br.quarto.Quarto;
import com.crash.br.reserva.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservaRequest {

    @NotBlank(message = "nome_do_inquilino não pode ser nulo")
    private String nomeDoInquilino;

    @NotNull(message = "numero_do_quarto não pode ser nulo")
    private Integer numeroDoQuarto;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate checkIn;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate checkOut;

    public ReservaRequest() {
    }

    public ReservaRequest(Integer numeroDoQuarto, LocalDate checkIn, LocalDate checkOut) {
        this.numeroDoQuarto = numeroDoQuarto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Reserva toModel(Quarto quarto) {
        return new Reserva(nomeDoInquilino, numeroDoQuarto, checkIn, checkOut, quarto);
    }

    public Integer getNumeroDoQuarto() {
        return numeroDoQuarto;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public String getNomeDoInquilino() {
        return nomeDoInquilino;
    }

}
