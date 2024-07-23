import br.com.wepdev.model.Cliente;
import br.com.wepdev.model.ItemPedido;
import br.com.wepdev.model.PedidoVenda;
import br.com.wepdev.model.builder.PedidoVendaBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PedidoVendaTest {


    @Test
    public void deveCalcularValorTotalPedidoParaClienteVip() {
        PedidoVenda pedidoVenda = new PedidoVenda();

        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setVip(true);
        pedidoVenda.setCliente(cliente);

        ItemPedido item1 = new ItemPedido();
        item1.setNome("Calculadora");
        item1.setValorUnitario(new BigDecimal("200"));
        item1.setQuantidade(2);

        ItemPedido item2 = new ItemPedido();
        item2.setNome("Mochila");
        item2.setValorUnitario(new BigDecimal("200"));
        item2.setQuantidade(1);

        List<ItemPedido> itensPedido = Arrays.asList(item1, item2);
        pedidoVenda.setItensPedido(itensPedido);

        BigDecimal valorTotal = pedidoVenda.getValorTotal();

        Assert.assertEquals(new BigDecimal("576").doubleValue(), valorTotal.doubleValue(), 0.0001);
    }

    @Test
    public void deveCalcularValorTotalPedidoParaClienteVipComBuilder() {
        PedidoVenda pedidoVenda = new PedidoVendaBuilder()
                .comClienteVip("João Silva")
                .comItem("Calculadora", 2, "200")
                .comItem("Mochila", 1, "200")
                .comNumero("02020")
                /**
                 * O contruir() so pode ser invocado se to metodo acima for utilizado, pois ele retorna um PedidoVendaBuilderValido
                 * e assim o construir pode ser chamado
                 */
                .construir();

        BigDecimal valorTotal = pedidoVenda.getValorTotal();

        // delta para margem de erro caso o valor seja um pouco a mais ou a menos 0.0001, para questões de arredondamento
        Assert.assertEquals(new BigDecimal("576").doubleValue(), valorTotal.doubleValue(), 0.0001);
    }
}
