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
