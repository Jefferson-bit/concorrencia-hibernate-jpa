package com.crash.br.request;

import com.crash.br.entity.Quarto;
import com.crash.br.entity.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        if (quarto.isReservado()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "quarto já está reservado");
        }
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

    @Override
    public String toString() {
        return "ReservaRequest{" +
                "nomeDoInquilino='" + nomeDoInquilino + '\'' +
                ", numeroDoQuarto=" + numeroDoQuarto +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
