package br.com.wepdev.model.builder;

import br.com.wepdev.model.Cliente;
import br.com.wepdev.model.ItemPedido;
import br.com.wepdev.model.PedidoVenda;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PedidoVendaBuilder {

    private PedidoVenda instancia;

    public PedidoVendaBuilder() {
        this.instancia = new PedidoVenda();
    }

    public PedidoVendaBuilder comClienteVip(String nome) {
        definirCliente(nome, true);
        return this;
    }

    public PedidoVendaBuilder comClienteNormal(String nome) {
        definirCliente(nome, false);
        return this;
    }

    private void definirCliente(String nome, boolean vip) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setVip(true);
        this.instancia.setCliente(cliente);
    }

    public PedidoVendaBuilder comItem(String nome, Integer quantidade, String valorUnitario) {
        ItemPedido item = new ItemPedido();
        item.setNome(nome);
        item.setValorUnitario(new BigDecimal(valorUnitario));
        item.setQuantidade(quantidade);

        if (this.instancia.getItensPedido() == null) {
            this.instancia.setItensPedido(new ArrayList<ItemPedido>());
        }
        this.instancia.getItensPedido().add(item);

        return this;
    }

    /**
     * Metodo que obriga o pedido a ter um numero.
     * ESSE ERRO SO OCORRE EM TEMPO DE COMPILAÇÃO
     * @return
     */
    public PedidoVenda construir(){
        if(this.instancia.getNumero() == null){
            throw new IllegalStateException("O número do pedido e obrigatório!!");
        }
        return this.instancia;
    }

    /**
     * E Obrigatorio passar o numero do pedido, pois o metodo que constroi o objeto esta dentro
     * do PedidoVendaBuilderValido.
     * DESTA FORMA O ERRO DE O PEDIDO TER UM NUMERO PASSA A SER EM TEMPO DE EXECUÇÃO
     * @param numero
     * @return
     */
    public PedidoVendaBuilderValido comNumero(String numero) {
        this.instancia.setNumero(numero);
        return new PedidoVendaBuilderValido(instancia);
    }


}
