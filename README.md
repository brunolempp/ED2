# 📊 Laboratório ED2 — Visualizador de Algoritmos de Ordenação

Projeto desenvolvido para a disciplina de **Estruturas de Dados II** na **Uniube**.

O objetivo é visualizar, em tempo real, o funcionamento de algoritmos de ordenação por meio de uma interface gráfica interativa construída com **Java Swing**.

---

## 🖥️ Interface

A janela principal exibe um conjunto de **80 barras verticais** geradas aleatoriamente com valores entre 1 e 100. Durante a execução de um algoritmo, as barras sendo comparadas são destacadas em **vermelho**, enquanto as demais permanecem em **ciano**.

O usuário pode:
- Selecionar o algoritmo desejado no menu suspenso
- Clicar em **"Dar a Largada"** para iniciar a ordenação animada
- Clicar em **"Embaralhar"** para gerar um novo array aleatório a qualquer momento

---

## 📁 Estrutura do Projeto

```
src/
├── Main.java                          # Ponto de entrada da aplicação
├── algoritmos/
│   ├── AlgoritmoOrdenacao.java        # Interface base para todos os algoritmos
│   ├── BubbleSort.java                # Implementação do Bubble Sort
│   └── SelectionSort.java             # Implementação do Selection Sort
└── visuals/
    ├── JanelaPrincipal.java           # Janela principal (JFrame) com menu e controles
    └── Visualizador.java              # Painel gráfico de renderização das barras (JPanel)
```

---

## ⚙️ Algoritmos Implementados

| # | Algoritmo | Complexidade (Pior caso) | Status |
|---|-----------|--------------------------|--------|
| 1 | Bubble Sort | O(n²) | ✅ Implementado |
| 2 | Selection Sort | O(n²) | 🚧 Em desenvolvimento |
| — | *Próximos algoritmos...* | — | 🔜 Em breve |

> Novos algoritmos serão adicionados conforme solicitado pelo professor.
> Para adicionar um novo, basta criar uma classe no pacote `algoritmos/` implementando a interface `AlgoritmoOrdenacao`.

---

## 🔌 Interface `AlgoritmoOrdenacao`

Todos os algoritmos implementam a interface abaixo:

```java
public interface AlgoritmoOrdenacao {
    void ordenar(int[] array, Visualizador v);
}
```

O parâmetro `Visualizador v` permite que cada algoritmo acione as animações durante a ordenação, utilizando:
- `v.atualizarDestaques(i, j)` — destaca as barras nos índices `i` e `j` em vermelho
- `v.pausar(ms)` — pausa a execução por `ms` milissegundos, criando o efeito de animação

---

## 🚀 Como Executar

### Pré-requisitos
- **Java 21+** (o projeto utiliza Virtual Threads via `Thread.startVirtualThread`)
- **IntelliJ IDEA** (recomendado) ou qualquer IDE com suporte a Java

### Passos
1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   ```
2. Abra o projeto na sua IDE
3. Execute a classe `Main.java`

---

## 🛠️ Tecnologias

- **Java 21+**
- **Java Swing** — interface gráfica
- **Virtual Threads** — execução assíncrona da animação sem travar a UI

---

## 📚 Disciplina

> Estruturas de Dados II — Universidade de Uberaba (Uniube)

