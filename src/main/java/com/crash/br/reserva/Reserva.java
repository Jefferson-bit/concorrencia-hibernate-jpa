package com.crash.br.reserva;

import com.crash.br.quarto.Quarto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeDoInquilino;

    @Column
    private Integer numeroDoQuarto;

    @Column
    private LocalDate checkIn;

    @Column
    private LocalDate checkOut;

    @Column(nullable = false)
    private LocalDateTime instanteDaReserva = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @Deprecated
    public Reserva() {
    }

    public Reserva(String nomeDoInquilino, Integer numeroDoQuarto, LocalDate checkIn, LocalDate checkOut, Quarto quarto) {
        this.nomeDoInquilino = nomeDoInquilino;
        this.numeroDoQuarto = numeroDoQuarto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.quarto = quarto;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getInstanteDaReserva() {
        return instanteDaReserva;
    }

    public String getNomeDoInquilino() {
        return nomeDoInquilino;
    }

    public Quarto getQuarto() {
        return quarto;
    }
}
