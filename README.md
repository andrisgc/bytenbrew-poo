# Sistema Byte&Brew
 
## 1. Associações
 
O sistema é composto por relacionamentos entre classes de diferentes níveis: associação simples, agregação e composição.
 
### 1.1. Associação simples
 
**`Pedido` → `Cliente`**
A classe `Pedido` mantém uma referência ao cliente que realizou a compra através do atributo `clientePedido`.

**`ItemPedido` → `Produto`**
A classe `ItemPedido` guarda uma referência ao produto comprado através do atributo `produtoItemPedido`.
 
### 1.2. Agregação

**`CadastroCliente`** ◇── **`Cliente`** (via `ArrayList<Cliente> cadastroCliente`)

**`CadastroProduto`** ◇── **`Produto`** (via `ArrayList<Produto> cadastroProduto`)

**`CadastroPedido`** ◇── **`Pedido`** (via `ArrayList<Pedido> listaPedidos`)
 
### 1.3. Composição
 
**`Pedido` ◆── `ItemPedido`**
A classe `Pedido` mantém sua lista de itens através do atributo `itensPedido` (`ArrayList<ItemPedido>`).
 
---
 
## 2. Heranças
 
O sistema utiliza herança em duas hierarquias principais de domínio, além da hierarquia (obrigatória pela linguagem) das exceções customizadas.
 
### 2.1. Hierarquia de clientes
 
```
Cliente (superclasse)
 ├── ClienteStandard
 └── ClienteVip
```
 
A classe `Cliente` **não é abstrata** e representa o caso de um **cliente casual, sem cadastro**, ele possui apenas `nome`, e seu método `getCpf()` retorna `null` por padrão, já que não faz sentido um cliente sem cadastro possuir CPF.
 
`ClienteStandard` e `ClienteVip` herdam de `Cliente` e adicionam o que é exclusivo de quem tem cadastro: `cpf` e `expPoints` (pontos de experiência). Cada uma sobrescreve `getCpf()` para retornar o CPF real armazenado, e sobrescreve `toString()` para acrescentar as informações específicas de cada categoria (`"CATEGORIA: Aventureiro Iniciante"` para Standard, `"CATEGORIA: Mestre da Guilda"` para Vip) ao texto já construído pela superclasse (`super.toString()`).
 
### 2.2. Hierarquia de produtos
 
```
Produto (superclasse)
 ├── Comida
 └── Bebida
```
 
`Produto` concentra os atributos e comportamentos comuns a qualquer item genérico: `codigoIdentificador`, `nomeProduto`, `estoque`, `preco`, além dos métodos `somarEstoque()`, `subtrairEstoque()`.
 
`Comida` estende `Produto` adicionando `tempoPreparo` e `indicadorRestricao` (vegano, sem glúten, etc.). `Bebida` estende `Produto` adicionando `tamanhoBebida` e `cafeinaBebida`.
 
---
 
## 3. Polimorfismo Aplicado
 
O sistema aplica polimorfismo de quatro tipos diferentes: **polimorfismo por sobreescrita de métodos**, **polimorfismo por sobrecarga de métodos**, **polimorfismo por coerção** e **polimorfismo por inclusão**. Em todo o sistema é possível encontrar várias ocorrências desses tipos de polimorfismo, mas serão citadas apenas as mais relevantes.

- **Polimorfismo por sobreescrita:** as classes `ClienteStandard` e `ClienteVip` sobreescrevem os métodos `adicionarPontos()` e `converterPontos()` implementados na interface `Conversao`. Ocorre pois são métodos de mesma assinatura mas que agem de formas diferentes por conta do atributo `taxaConversao`, que são diferentes para cada classe.
- **Polimorfismo por sobrecarga:**: a classe `CadastroProduto` sobrecarregam o método `cadastrarProduto()` para diferentes tipos de produtos que o usuário deseja cadastrar. Na classe `Pedido` também há a sobrecarga dos métodos `adicionarItem()` e `removerItem()` para diferentes tipos de uso. No método `adicionarItem(ItemPedido itemPedido)` acontece a adição de apenas uma unidade do item, já no método `adicionarItem(ItemPedido itemPedido, int quantidadePedido)` o usuário escolhe quantas unidades ele deseja adicionar. No método `removerItem(ItemPedido itemPedido)` acontece a remoção total do item no pedido, já no método `removerItem(ItemPedido itemPedido, int quantidadePedido)` o usuário escolhe quantas unidades ele deseja remover.
- **Polimorfismo por inclusão**: na classe `itemPedido` ocorre o polimorfismo por inclusão no atributo `produtoItemPedido`, pois a referência apesar de ser do tipo `Produto`, também pode apontar para suas subclasses `Comida` e `Bebida`. Também ocorre na classe `cadastroProduto` e `cadastroCliente` nos `ArrayList<Produto>` e `ArrayList<Cliente>`, que apesar dos ArrayList armazenarem `Produto` e `Cliente`, eles também armazenam suas subclasses sem problemas.
- **Polimorfismo por coerção**: esse tipo ocorre diversas vezes ao longo do código, principalmente no pacote `view`, onde ocorre mais vezes com referências do tipo `Cliente` que precisam passar pelo casting explícito para resolver certos tipos de operações ao longo da criação do pedido. Como `(Conversao) cliente`, `(ClienteStandard) cliente` ou `(ClienteVip) cliente`.
 
---
 
## 4. Justificativa das Exceções Customizadas
 
O sistema define quatro exceções customizadas, todas como **checked**:
 
| Exceção | Lançada em | Situação |
|---|---|---|
| `EstoqueInsuficienteException` | `ItemPedido.adicionarQuantidadeItemPedido()` / `Pedido.adicionarItem()` | Quantidade solicitada maior que o estoque disponível do produto |
| `PontosInsuficientesException` | `Pedido.calcularPontos(int)` | `ClienteVip` tenta resgatar pontos em desconto, mas o saldo de XP é insuficiente |
| `ClienteInexistenteException` | `CadastroCliente.buscarCliente()` | Busca por CPF que não corresponde a nenhum cliente cadastrado |
| `ProdutoInexistenteException` | `CadastroProduto.buscarProduto()` | Busca por código identificador que não corresponde a nenhum produto cadastrado |
 
### 4.1. Checked ou Unchecked
 
A escolha por **checked exceptions** se justifica porque as quatro situações representam **violações de regras esperadas**.
 
- **São previsíveis:** é esperado um cliente pedir mais unidades de um produto do que há em estoque, ou tentar resgatar pontos que ainda não possui. Por esses motivos essas exceções precisam de tratamento específico.
- **O sistema deve conseguir se recuperar:** nesses quatro casos não faz sentido o programa encerrar.
- **O compilador reforça o tratamento em todo o sistema:** como checked exceptions obrigam `throws` na assinatura ou `try/catch` no corpo do método, o compilador impede que qualquer trecho do código não trate essas situações. 

---

## 5. Diagrama UML

O diagrama UML foi construído de forma diferente do usual.  \
Foi escolhido pelo grupo representar os pacotes através de cores para facilitar a organização no diagrama.  \
Pelo tamanho do diagrama maior que o usual, é recomendado que abra o arquivo `bytenbrew.drawio` ou abrir a imagem do diagrama disponível no repositório.  \
![Diagrama UML](/bytenbrew.png)


