package br.unipar.trabalho1B.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Itens_Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tamanho;
    private int quantidade;
    private double valor_unitario;
    private double valor_total;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Pizza pizza;

    @ManyToOne
    private Borda borda;

    public Itens_Pedido(String tamanho, int quantidade, double valor_unitario, double valor_total, Pedido pedido, Pizza pizza, Borda borda) {
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.valor_unitario = valor_unitario;
        this.valor_total = valor_total;
        this.pedido = pedido;
        this.pizza = pizza;
        this.borda = borda;
    }

    public Itens_Pedido() {}
}
