package br.unasp.meusgastos.model

// Um gasto tem um ID para nao confundir despesas com nomes iguais.
data class Gasto(
    val id: Long,
    val descricao: String,
    val valor: Double,
    val categoria: Categoria
)
