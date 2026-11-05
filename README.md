# Meus Gastos - Programação Mobile (MVVM)

Projeto Android Studio em **Kotlin + Jetpack Compose**, seguindo o tutorial da disciplina. A ideia é usar MVVM sem adicionar complexidade desnecessária: nada de Room, API, Hilt, Navigation ou banco de dados.

## Como abrir e rodar

1. Faça o clone do repositório ou baixe seu ZIP.
2. Abra o **Android Studio**, clique em **Open** e selecione a pasta do projeto (que contém `settings.gradle.kts`).
3. Aguarde o **Gradle Sync**. É necessário ter internet para baixar as dependências.
4. Se solicitado, instale o **Android SDK API 36** e configure **JDK 17**.
5. Execute o aplicativo em um emulador (API 23+) ou celular Android.

## Funcionalidades

- Adicionar gastos com descrição, valor e categoria.
- Validar descrição vazia, valor inválido ou menor ou igual a zero.
- Listar gastos e remover pelo ID.
- Filtrar por categoria ou mostrar todas.
- Calcular total e quantidade de gastos visíveis.
- Guardar os dados em memória (sem banco de dados).

## Dados de demonstração

O projeto está na versão final da Etapa 6, com o `GastoRepositoryDemo()`. Os quatro gastos iniciais somam **R$ 201,70**. Para começar com a lista vazia, troque somente `GastoRepositoryDemo()` por `GastoRepositoryMemoria()` no construtor de `GastosViewModel`.

## Estrutura MVVM

```text
app/src/main/java/br/unasp/meusgastos/
├── MainActivity.kt
├── model/
│   ├── Categoria.kt
│   ├── Gasto.kt
│   └── GastoRepository.kt
├── viewmodel/
│   └── GastosViewModel.kt
└── ui/
    └── TelaGastos.kt
```

## Previews da Etapa 5

No final de `TelaGastos.kt`:
1. `ResumoTotalPreview`
2. `LinhaGastoPreview`
3. `FormularioComErroPreview`
4. `FiltroCategoriasPreview`
5. `FormularioVazioPreview`

## Documentação da entrega

- `ARQUITETURA.md`: respostas das três perguntas da Etapa 8.
- `ESTRAGOS.md`: previsões e análise dos cinco experimentos da Etapa 7. **Os resultados reais devem ser conferidos no emulador** e registrados.
- `DIARIO_IA.md`: registro do uso de IA.

## Histórico

O histórico da branch main contém oito commits separados, de `etapa 1` a `etapa 8`, após o commit inicial do repositório.

## Atenção antes de entregar

Não foi possível executar o build Android nem o emulador neste ambiente. Faça a sincronização e execute o app no Android Studio para conferir a tela e os experimentos.
