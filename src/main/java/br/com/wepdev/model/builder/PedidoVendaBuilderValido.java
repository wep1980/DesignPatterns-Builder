package br.com.wepdev.model.builder;

import br.com.wepdev.model.PedidoVenda;

public class PedidoVendaBuilderValido {

    private PedidoVenda instancia;

    public PedidoVendaBuilderValido(PedidoVenda instancia) {
        this.instancia = instancia;
    }

    public PedidoVenda construir() {
        return this.instancia;
    }
}
