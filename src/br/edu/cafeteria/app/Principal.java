package br.edu.cafeteria.app;

public class Principal {
    public static void main(String[] args) {
        // MENU PRINCIPAL
            System.out.println("1 - Realizar pedido");
            System.out.println("2 - Consultar cardápio");
            System.out.println("3 - Acessar painel de admnistrador");
            System.out.println("4 - Sair");

        // 1 - REALIZAR PEDIDO
            System.out.println("O cliente deseja informar o CPF?");
                // SIM ou NÃO
                // Se SIM, verifica se o cliente já está cadastrado.
                    // CLIENTE JÁ ESTÁ CADASTRADO (continua o pedido)
                    // CLIENTE NÃO ESTÁ CADASTRADO
                        System.out.println("O CPF não está cadastrado. Deseja realizar o cadastro?");
                        // SIM ou NÃO
                        // Se SIM, cria cliente standard
                        // Se NÃO, cria cliente casual
                // Se NÃO, cria cliente casual

            System.out.println("Insira o código do produto:");
                // CÓDIGO DO PRODUTO


        // 3 - PAINEL ADMINISTRADOR
            System.out.println("1 - Consultar clientes");
            System.out.println("2 - Adicionar cliente");
            System.out.println("3 - Remover cliente");
            System.out.println("4 - Consultar atendentes");
            System.out.println("5 - Adicionar atendente");
            System.out.println("6 - Remover atendente");
            System.out.println("7 - Sair");



    }
}
