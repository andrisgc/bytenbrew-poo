package br.edu.cafeteria.modelo.pedido;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.cliente.ClienteStandard;
import br.edu.cafeteria.modelo.cliente.ClienteVip;

import java.util.ArrayList;

public class Pedido {
    private final Cliente clientePedido;
    private final ArrayList <ItemPedido> itensPedido;
    private static int contadorPedido;
    private final int numeroPedido;
    private float valorTotal;

    public Pedido (Cliente clientePedido) {
        itensPedido = new ArrayList<>();
        this.clientePedido = clientePedido;
        numeroPedido = ++contadorPedido;
    }

    public void adicionarItem (ItemPedido itemPedido) throws EstoqueInsuficienteException {
        itemPedido.adicionarQuantidadeItemPedido(1);
        itensPedido.add(itemPedido);
    }

    public void adicionarItem (ItemPedido itemPedido, int quantidadePedido) throws EstoqueInsuficienteException {
        itemPedido.adicionarQuantidadeItemPedido(quantidadePedido);
        itensPedido.add(itemPedido);
    }

    public boolean removerItem (ItemPedido itemPedido) {
        itensPedido.remove(itemPedido);
        return true;
    }

    public void removerItem (ItemPedido itemPedido, int quantidadePedido) {
        if (itemPedido.getQuantidadeItemPedido() > quantidadePedido)
            itemPedido.removerQuantidadeItemPedido(quantidadePedido);
        else if (itemPedido.getQuantidadeItemPedido() == quantidadePedido) {
            itensPedido.remove(itemPedido);
        }
    }

    public boolean acharItem (String codigoIdentificador) {
        for (ItemPedido itemPedido : itensPedido) {
            if (codigoIdentificador.equals(itemPedido.getProdutoItemPedido().getCodigoIdentificador())) {
                return true;
            }
        }
        return false;
    }

    public ItemPedido buscarItem (String codigoIdentificador) {
        for (ItemPedido itemPedido : itensPedido) {
            if (codigoIdentificador.equals(itemPedido.getProdutoItemPedido().getCodigoIdentificador())) {
                return itemPedido;
            }
        }
        return null;
    }

    public void calcularValor () {
        valorTotal = 0;
        for (ItemPedido itemPedido : itensPedido) {
            valorTotal += (itemPedido.getValorItemPedido()) * itemPedido.getQuantidadeItemPedido();
        }
    }

    public void calcularValorDesconto () {
        valorTotal = 0;
        for (ItemPedido itemPedido : itensPedido) {
            valorTotal += (itemPedido.getValorItemPedidoDesconto()) * itemPedido.getQuantidadeItemPedido();
        }
    }

    public void calcularPontos(int pontosNecessarios, ClienteVip clientePedido) {
        clientePedido.removerPontos(pontosNecessarios);
    }

    public void calcularPontos(int pontosNecessarios) throws PontosInsuficientesException {
        if (((ClienteVip)clientePedido).getExpPoints() < pontosNecessarios) {
            throw new PontosInsuficientesException("O cliente não possui pontos suficientes.");
        }
        valorTotal -= ((float)pontosNecessarios) / 10;
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

    public String toStringDesconto() {
        String resposta = "";
        resposta += "NOME DO CLIENTE: " + clientePedido.getNome() + '\n';
        resposta += "NÚMERO DO PEDIDO: " + numeroPedido + '\n';
        resposta += "ITENS: \n";

        int i = 1;
        for (ItemPedido itemPedido : itensPedido) {
            resposta += i + " - ";
            resposta += itemPedido.toStringDesconto();
            i++;
        }

        calcularValorDesconto();
        resposta += "VALOR TOTAL: " + valorTotal + '\n';

        return resposta;
    }

    public Cliente getClientePedido() {
        return clientePedido;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }

}
