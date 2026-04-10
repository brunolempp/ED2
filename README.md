# 📊 Laboratório ED2 — Visualizador de Algoritmos de Ordenação

Projeto da disciplina de **Estruturas de Dados II** (**Uniube**) para visualizar, em tempo real, algoritmos de ordenação com **Java Swing**.

---

## 🖥️ Interface

A aplicação exibe barras verticais representando os valores de um array gerado aleatoriamente.

### Configurações atuais
- Tamanho padrão da array: **1000**
- Tamanho ajustável pela interface: **10 a 10000**
- Faixa dos valores gerados: **1 até tamanho × 10**
- Barras em comparação: **vermelho**
- Demais barras: **ciano**

### Controles disponíveis
- Campo para alterar o **tamanho da array**
- Combo para selecionar o algoritmo, iniciando em **"Escolha"**
- Slider para controlar a **velocidade do gráfico** em tempo real
- Botão **"Dar a Largada"** para iniciar a ordenação
- Botão **"Embaralhar"** para gerar um novo array aleatório
- Botão **"Abortar"** para interromper a execução em andamento
- Animação de conclusão: barras ficam **verdes** em sequência ao terminar a ordenação

---

## ✅ Adições já efetuadas

- Inclusão do **Insertion Sort** no projeto e na interface
- Inclusão do **Shell Sort** na interface
- Combo de algoritmos iniciado com a opção neutra **"Escolha"**
- Campo para alterar o tamanho do array sem editar o código
- Execução da ordenação em **Virtual Thread**, mantendo a UI responsiva
- Botão **Abortar** para interromper o motor em execução
- Slider de velocidade para ajustar o ritmo da animação em tempo real
- Pausas adaptativas: em arrays grandes, a animação fica mais rápida automaticamente
- Ao concluir, o gráfico recebe uma animação final do menor ao maior, pintando as barras de verde
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
│   ├── InsertionSort.java
│   └── ShellSort.java
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
| Shell Sort | depende do gap utilizado | ✅ |

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
- `v.pausar(ms)` para controlar a velocidade visual

### Animação adaptativa

A pausa visual é reduzida automaticamente quando o array cresce muito:
- arrays menores mantêm a animação mais visível
- arrays grandes quase eliminam a pausa, deixando a ordenação mais rápida

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
