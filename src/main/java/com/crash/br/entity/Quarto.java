package com.crash.br.entity;

import com.crash.br.enums.TipoQuarto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;


@Entity
@Table(name = "tb_quarto")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private BigDecimal valorDaDiaria;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoQuarto tipoQuarto;
    private Boolean ativo = false;

    @OneToMany(mappedBy = "quarto", cascade = {PERSIST, MERGE})
    private List<Reserva> reservas = new ArrayList<>();

    @Deprecated
    public Quarto() {
    }

    public Quarto(String descricao, BigDecimal valorDaDiaria, TipoQuarto tipoQuarto) {
        this.descricao = descricao;
        this.valorDaDiaria = valorDaDiaria;
        this.tipoQuarto = tipoQuarto;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorDaDiaria() {
        return valorDaDiaria;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public boolean isReservado(){
        return ativo;
    }

    public void reservar() {
        this.ativo = true;
    }

    public void adiciona(Reserva reserva) {
        this.reservas.add(reserva);
    }
}
