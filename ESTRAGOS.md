# Etapa 7 - Quebrando o aplicativo de propósito

A ideia desta etapa é entender **por que** a organização do MVVM faz diferença.
Não deixei os erros no projeto final, porque ele precisa continuar funcionando.

> **Importante:** as explicações abaixo são o que eu espero observar pelo código.
> Não consegui executar o emulador neste ambiente para confirmar as mensagens.
> Para entregar a parte experimental, vou reproduzir cada estrago no Android Studio,
> anotar o que realmente aconteceu e copiar a mensagem literal do Logcat, se aparecer.

## Estrago 1 - Criar o ViewModel diretamente na tela

- **Alteração:** trocar `vm: GastosViewModel = viewModel()` por `vm: GastosViewModel = GastosViewModel()`.
- **Previsão:** quando eu digitar no campo, a tela pode reconstruir e criar outro ViewModel.
- **O que deve acontecer:** as despesas que eu cadastrei no ViewModel anterior desaparecem e a lista volta ao estado inicial. Na versão demo, ela volta com os quatro exemplos.
- **Mensagem literal:** não confirmada (não é necessário que apareça uma exceção).
- **Correção:** voltar a usar `viewModel()`, que mantém a instância entre recomposições.

## Estrago 2 - Trocar a lista observável por uma lista comum

- **Alteração:** mudar `mutableStateListOf<Gasto>()` para `mutableListOf<Gasto>()`.
- **Previsão:** adicionar ou remover itens pode não atualizar a tela imediatamente.
- **O que deve acontecer:** os dados no repositório mudam, mas o Compose não recebe a notificação dessa lista. Uma outra mudança de estado, como digitar uma letra, pode fazer a tela atualizar e mostrar o novo resultado.
- **Mensagem literal:** não confirmada (em geral não é erro de compilação).
- **Correção:** usar `mutableStateListOf` para o estado que a tela observa.

## Estrago 3 - Guardar o total em uma variável

- **Alteração:** usar `var total by mutableStateOf(0.0)` e atualizar só ao adicionar.
- **Previsão:** o total vai ficar errado ao remover uma despesa ou filtrar.
- **O que deve acontecer:** depois de adicionar R$ 10, R$ 20 e R$ 30, se eu remover R$ 20, a lista terá R$ 40, mas a variável ainda pode mostrar R$ 60.
- **Mensagem literal:** não se espera exceção; o problema é no valor exibido.
- **Correção:** usar `get() = gastosVisiveis.sumOf { it.valor }`.

## Estrago 4 - Fazer o ViewModel depender da classe concreta

- **Alteração:** remover a interface do tipo do campo e escrever `private val repositorio = GastoRepositoryMemoria()`.
- **Previsão:** mudar para o repositório demo ficará mais preso à classe específica.
- **O que deve acontecer:** a troca simples da Etapa 6 deixa de ser feita pela abstração. Se eu quiser injetar uma implementação alternativa em testes, também vai ficar mais difícil.
- **Mensagem literal:** não confirmada; apenas essa troca não deve causar erro de compilação.
- **Correção:** declarar `private val repositorio: GastoRepository` no construtor.

## Estrago 5 - Colocar a validação no botão da UI

- **Alteração:** colocar um `if (vm.descricao.isNotBlank())` no `onClick` do botão.
- **Previsão:** a regra de validação fica espalhada na tela.
- **O que deve acontecer:** uma segunda tela de cadastro teria que repetir a verificação. As telas poderiam acabar com comportamentos diferentes.
- **Mensagem literal:** não é esperado um erro literal; é um problema de arquitetura.
- **Correção:** fazer o botão chamar `vm.adicionar()` e deixar o `when` no ViewModel.

## Roteiro de teste no laboratório

1. Salvar uma cópia do arquivo antes de fazer o estrago.
2. Alterar **somente** o trecho indicado.
3. Executar o app, observar e anotar a mensagem exata quando houver.
4. Desfazer a alteração antes de testar o próximo estrago.
5. No fim, confirmar que o projeto volta a compilar e a adicionar/remover gastos.
