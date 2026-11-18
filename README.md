# Meus Gastos — Android | Kotlin e MVVM

Aplicativo acadêmico de controle de gastos, desenvolvido no contexto da disciplina de Programação Mobile. Utiliza **Kotlin, Jetpack Compose e MVVM** para separar a interface, o estado da tela e os dados.

O foco é aprender arquitetura de software e organização de código, sem adicionar Room, API, Hilt, Navigation ou banco de dados.

## Como abrir e rodar

1. Faça o clone do repositório ou baixe seu ZIP.
2. Abra o **Android Studio**, clique em **Open** e selecione a pasta do projeto (que contém `settings.gradle.kts`).
3. Confira o [Gradle Wrapper](gradle/wrapper/SOBRE_O_WRAPPER.md): o JAR não está presente nesta cópia do repositório. Gere o wrapper conforme as instruções antes de executar os scripts. Aguarde o **Gradle Sync**; é necessário ter internet para baixar as dependências.
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

## Aprendizado e uso de IA

O projeto explora separação de responsabilidades, gerenciamento de estado, validação de entradas e interface declarativa. O [diário de IA](DIARIO_IA.md) registra o apoio do ChatGPT no desenvolvimento e na documentação. Trata-se de desenvolvimento assistido por IA, não de um aplicativo com funcionalidades de IA integradas.

## Histórico

As etapas de desenvolvimento e documentação estão registradas no histórico de commits do repositório.

## Atenção antes de entregar

Não foi possível executar o build Android nem o emulador neste ambiente. Faça a sincronização e execute o app no Android Studio para conferir a tela e os experimentos.

## Outros projetos

- [Trilha Python — estudos DIO](https://github.com/lucascardososoares345-glitch/trilha-python-dio)
- [Desafio Python — sistema bancário](https://github.com/lucascardososoares345-glitch/Desafio-Python)
