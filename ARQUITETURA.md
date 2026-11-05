# Arquitetura MVVM - Meus Gastos

## 1. E se a lista vier de uma API em vez da memória?

Eu criaria uma nova implementação de `GastoRepository` para acessar a API e alteraria onde o repositório é escolhido. O `GastosViewModel` continuaria usando a interface, sem mudar as regras de adicionar, remover e filtrar. A `TelaGastos.kt` não precisaria mudar. Se a API fosse assíncrona, aí o contrato de repositório e a sincronização precisariam ser adaptados também.

## 2. Por que o total é calculado?

Porque ele sempre tem que ser a soma dos gastos visíveis. Se fosse uma variável que eu atualizo na mão, eu poderia esquecer de diminuir o valor ao remover um gasto. Por exemplo: eu somo R$ 30 e R$ 20, depois removo R$ 20. A lista estaria com R$ 30, mas o total poderia continuar mostrando R$ 50. Calculando pela lista, isso não acontece.

## 3. Por que o FormularioGasto não recebe o ViewModel?

Porque ele só precisa receber os valores e avisar quando alguém digita ou aperta um botão. Assim eu consigo fazer um `@Preview` com qualquer descrição ou erro, sem criar um ViewModel ou executar o aplicativo. Também consigo usar o mesmo formulário em outra tela sem colocar as regras dentro dele.

## Onde está cada parte?

- **Model:** `Categoria.kt`, `Gasto.kt` e `GastoRepository.kt` (dados e fonte dos dados).
- **ViewModel:** `GastosViewModel.kt` (estado, validação e ações).
- **View:** `TelaGastos.kt` (desenha a interface e envia eventos).

A regra que tentei seguir é: a tela pode conhecer o ViewModel, mas o Model não precisa conhecer a tela.
