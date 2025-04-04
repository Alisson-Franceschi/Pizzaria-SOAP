package br.unipar.trabalho1B.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double valorTotal;
    private String observacoes;
    private String status;

    @ManyToOne
    private Cliente cliente;

    public Pedido(Double valorTotal, String observacoes, String status, Cliente cliente) {
        this.valorTotal = valorTotal;
        this.observacoes = observacoes;
        this.status = status;
        this.cliente = cliente;
    }

    public Pedido() {}
}
