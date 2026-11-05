package br.unasp.meusgastos.model

// Contrato: qualquer repositorio tem que saber listar, adicionar e remover.
interface GastoRepository {
    fun listar(): List<Gasto>
    fun adicionar(descricao: String, valor: Double, categoria: Categoria)
    fun remover(id: Long)
}

// Primeira implementacao: a lista comeca vazia e fica so na memoria.
class GastoRepositoryMemoria : GastoRepository {
    private val gastos = mutableListOf<Gasto>()
    private var proximoId = 1L

    override fun listar(): List<Gasto> = gastos.toList()

    override fun adicionar(descricao: String, valor: Double, categoria: Categoria) {
        gastos.add(Gasto(proximoId++, descricao, valor, categoria))
    }

    override fun remover(id: Long) {
        gastos.removeAll { it.id == id }
    }
}


// Segunda implementacao: lista com os quatro gastos da etapa 6.
// Pode ser trocada pela versao vazia sem alterar a interface nem a tela.
class GastoRepositoryDemo : GastoRepository {
    private val gastos = mutableListOf(
        Gasto(1, "Almoço no bandejão", 18.50, Categoria.ALIMENTACAO),
        Gasto(2, "Passagem de ônibus", 9.20, Categoria.TRANSPORTE),
        Gasto(3, "Conta de luz", 142.00, Categoria.MORADIA),
        Gasto(4, "Cinema", 32.00, Categoria.LAZER)
    )
    private var proximoId = 5L

    override fun listar(): List<Gasto> = gastos.toList()

    override fun adicionar(descricao: String, valor: Double, categoria: Categoria) {
        gastos.add(Gasto(proximoId++, descricao, valor, categoria))
    }

    override fun remover(id: Long) {
        gastos.removeAll { it.id == id }
    }
}
