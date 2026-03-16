# 📊 Laboratório ED2 — Visualizador de Algoritmos de Ordenação

Projeto da disciplina de **Estruturas de Dados II** (**Uniube**) para visualizar, em tempo real, algoritmos de ordenação com **Java Swing**.

---

## 🖥️ Interface

A aplicação exibe barras verticais representando os valores de um array gerado aleatoriamente.

- Quantidade atual de elementos: **80**
- Faixa dos valores: **1 a 100**
- Barras em comparação: **vermelho**
- Demais barras: **ciano**

Controles disponíveis:
- Selecionar o algoritmo no combo box
- Clicar em **"Dar a Largada"** para iniciar a ordenação
- Clicar em **"Embaralhar"** para gerar um novo array aleatório

---

## ✅ Adições já efetuadas

- Inclusão do **Insertion Sort** no projeto e na interface
- Ajuste das pausas para animação mais rápida (**2 ms** por passo visual)
- Execução da ordenação em **Virtual Thread**, mantendo a UI responsiva
- Método de embaralhamento com geração aleatória a cada clique

---

## 📁 Estrutura do Projeto

```text
src/
├── Main.java
├── algoritmos/
│   ├── AlgoritmoOrdenacao.java
│   ├── BubbleSort.java
│   ├── SelectionSort.java
│   └── InsertionSort.java
└── visuals/
    ├── JanelaPrincipal.java
    └── Visualizador.java
```

---

## ⚙️ Algoritmos Implementados

| Algoritmo | Complexidade (pior caso) | Status |
|---|---|---|
| Bubble Sort | O(n²) | ✅ |
| Selection Sort | O(n²) | ✅ |
| Insertion Sort | O(n²) | ✅ |

> Novos algoritmos podem ser adicionados conforme as próximas atividades da disciplina.

---

## 🔌 Interface de ordenação

```java
public interface AlgoritmoOrdenacao {
    void ordenar(int[] array, Visualizador v);
}
```

O `Visualizador` é usado para animação durante o algoritmo:
- `v.atualizarDestaques(i, j)` para destacar índices
- `v.pausar(ms)` para controlar velocidade visual

---

## 🚀 Como executar

### Pré-requisitos
- **Java 21 ou superior** (necessário para `Thread.startVirtualThread`)

### IntelliJ IDEA
1. Abra o projeto
2. Execute a classe `Main.java`

### Terminal (Windows / PowerShell)
```powershell
cd "C:\Users\bruno\OneDrive\Projetos Eng. Computação\ED2"
mkdir out -ErrorAction SilentlyContinue
javac -d out src\Main.java src\algoritmos\*.java src\visuals\*.java
java -cp out Main
```

---

## 📚 Disciplina

**Estruturas de Dados II — Universidade de Uberaba (Uniube)**
