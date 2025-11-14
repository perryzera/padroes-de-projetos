## Como Executar

**Pré-requisito:** Ter o **JDK** (Java Development Kit) instalado.

1.  Abra seu terminal na pasta do projeto.

2.  Acesse a pasta do exercício desejado (ex01):
    ```bash
    cd ex01
    ```

3.  Compile todos os arquivos `.java`:
    ```bash
    javac *.java
    ```

4.  Execute a classe `Main`:
    ```bash
    java Main.java
    ```



### ❓Ex01 – Strategy

Sistema financeiro que calcula risco usando diferentes algoritmos (VaR, Expected Shortfall, Stress Test).

Solução:
Cada algoritmo foi implementado como uma estratégia (RiskStrategy), e o motor (RiskEngine) pode trocar a estratégia em tempo de execução.

Por que Strategy?

Permite mudar o comportamento sem alterar o código principal — ideal quando há várias formas de cálculo.

### ❓Ex02 – Adapter

Integração com um sistema bancário legado que usa HashMap e tipos antigos.

Solução:
O AdapterProcessadorTransacoes traduz a interface moderna para a legada e vice-versa, garantindo compatibilidade.

Por que Adapter?
Resolve a incompatibilidade entre sistemas antigos e novos sem reescrever o legado.

### ❓Ex03 – State

Controle de estados de uma usina nuclear (DESLIGADA, NORMAL, ALERTA, EMERGÊNCIA, MANUTENÇÃO).

Solução:
Cada estado tem sua própria classe com regras de transição.
O contexto (UsinaNuclear) apenas delega o controle para o estado atual.

Por que State?
Deixa as regras de mudança de estado isoladas e claras, evitando if/else gigantes e facilitando manutenção.

### ❓Ex04 – Chain of Responsibility

Validação de NF-e com múltiplas etapas (XML, certificado, regras fiscais, banco, SEFAZ).

Solução:
Cada validador é um elo da corrente.
A cadeia aplica timeouts, condições de pulo, rollback e um circuit breaker após 3 falhas.

Por que Chain of Responsibility?
Permite executar várias validações de forma flexível e desacoplada, sem precisar de uma sequência fixa de if/else.


