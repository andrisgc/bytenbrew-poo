package br.edu.cafeteria.modelo.pedido;

import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.cliente.ClienteStandard;
import br.edu.cafeteria.modelo.cliente.ClienteVip;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final Cliente clientePedido;
    private final List <ItemPedido> itensPedido;
    private static int contadorPedido = 0;
    private final int numeroPedido;
    private float valorTotal;

    public Pedido (Cliente clientePedido) {
        itensPedido = new ArrayList<>();
        this.clientePedido = clientePedido;
        contadorPedido++;
        numeroPedido = contadorPedido;
    }

    public void adicionarItem (ItemPedido itemPedido) {
        itensPedido.add(itemPedido);
    }

    public void adicionarItem (ItemPedido itemPedido, int quantidadePedido) {
        itemPedido.setQuantidadePedido(quantidadePedido);
        itensPedido.add(itemPedido);
    }

    public boolean removerItem (ItemPedido itemPedido) {
        itensPedido.remove(itemPedido);
        return true;
    }

    public boolean removerItem (ItemPedido itemPedido, int quantidadePedido) {
        if (itemPedido.getQuantidadePedido() > quantidadePedido)
            return itemPedido.removerQuantidadeItemPedido(quantidadePedido);
        else if (itemPedido.getQuantidadePedido() == quantidadePedido) {
            return itensPedido.remove(itemPedido);
        }
        return false;
    }

    public boolean acharItem (String codigoIdentificador) {
        for (ItemPedido itemPedido : itensPedido) {
            if (codigoIdentificador.equals(itemPedido.produtoPedido.getCodigoIdentificador())) {
                return true;
            }
        }
        return false;
    }

    public ItemPedido buscarItem (String codigoIdentificador) {
        for (ItemPedido itemPedido : itensPedido) {
            if (codigoIdentificador.equals(itemPedido.produtoPedido.getCodigoIdentificador())) {
                return itemPedido;
            }
        }
        return null;
    }

    public void calcularValor () {
        for (ItemPedido itemPedido : itensPedido) {
            valorTotal = 0;
            valorTotal += (itemPedido.produtoPedido.getPreco()) * itemPedido.getQuantidadePedido();
        }
    }

    public void calcularDesconto (int pontosNecessarios, ClienteVip clientePedido) {
        clientePedido.removerPontos(pontosNecessarios);
        valorTotal -= (float) pontosNecessarios / 10;
    }

    public int verificarPontosNecessarios() {
        return (int)(valorTotal*10);
    }

    public int verificarPontos (Cliente clientePedido) {
        int pontosCliente = 0;
        if (clientePedido instanceof ClienteStandard) {
            pontosCliente = ((ClienteStandard) clientePedido).getExpPoints();
        } else if (clientePedido instanceof ClienteVip) {
            pontosCliente = ((ClienteVip) clientePedido).getExpPoints();
        }

        return pontosCliente;
    }

    public String toString() {
        String resposta = "";
        resposta += "NOME DO CLIENTE: " + clientePedido.getNome() + '\n';
        resposta += "NÚMERO DO PEDIDO: " + numeroPedido + '\n';
        resposta += "ITENS: \n";

        int i = 1;
        for (ItemPedido itemPedido : itensPedido) {
            resposta += i + " - ";
            resposta += itemPedido.toString();
            i++;
        }

        calcularValor();
        resposta += "VALOR TOTAL: " + valorTotal + '\n';

        return resposta;
    }

    public Cliente getClientePedido() {
        return clientePedido;
    }

    public float getValorTotal() {
        return valorTotal;
    }

}
