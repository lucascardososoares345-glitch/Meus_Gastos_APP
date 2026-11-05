package br.unasp.meusgastos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.unasp.meusgastos.model.Categoria
import br.unasp.meusgastos.model.Gasto
import br.unasp.meusgastos.viewmodel.GastosViewModel
import java.text.NumberFormat
import java.util.Locale

// Cores parecidas com o modelo fornecido pelo professor.
private val Azul = Color(0xFF153D5E)
private val AzulClaro = Color(0xFFE7F0F8)
private val Fundo = Color(0xFFF0F5F9)
private val Laranja = Color(0xFFF2694B)
private val AzulTexto = Color(0xFF6B89A4)

private fun dinheiro(valor: Double): String =
    NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(valor)

// A tela principal conversa com o ViewModel, mas nao decide regras de negocio.
@Composable
fun TelaGastos(
    vm: GastosViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().background(Fundo),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Meus Gastos", color = Azul, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
        item {
            FormularioGasto(
                descricao = vm.descricao,
                valorTexto = vm.valorTexto,
                categoria = vm.categoriaSelecionada,
                erro = vm.erro,
                aoDigitarDescricao = vm::aoDigitarDescricao,
                aoDigitarValor = vm::aoDigitarValor,
                aoSelecionarCategoria = vm::aoSelecionarCategoria,
                aoAdicionar = vm::adicionar
            )
        }
        item {
            FiltroCategorias(selecionada = vm.filtro, aoFiltrar = vm::aoFiltrar)
        }
        item {
            ResumoTotal(total = vm.total, quantidade = vm.quantidade)
        }
        if (vm.gastosVisiveis.isEmpty()) {
            item { Text("Nenhum gasto nesta categoria.", color = AzulTexto) }
        }
        items(vm.gastosVisiveis, key = { it.id }) { gasto ->
            LinhaGasto(gasto = gasto, aoRemover = { vm.remover(gasto.id) })
        }
    }
}

// Componente sem estado: recebe textos e funcoes; nao conhece o ViewModel.
@Composable
fun FormularioGasto(
    descricao: String,
    valorTexto: String,
    categoria: Categoria,
    erro: String?,
    aoDigitarDescricao: (String) -> Unit,
    aoDigitarValor: (String) -> Unit,
    aoSelecionarCategoria: (Categoria) -> Unit,
    aoAdicionar: () -> Unit
) {
    Surface(color = Color.White, shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = descricao,
                onValueChange = aoDigitarDescricao,
                placeholder = { Text("Descrição do gasto", color = AzulTexto) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = valorTexto,
                    onValueChange = aoDigitarValor,
                    placeholder = { Text("0,00", color = AzulTexto) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = aoAdicionar,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Azul)
                ) { Text("Adicionar") }
            }
            if (erro != null) {
                Text(erro, color = Color(0xFFB53627), fontSize = 13.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Categoria.entries.forEach { item ->
                    Etiqueta(
                        texto = item.rotulo,
                        selecionada = (categoria == item),
                        aoClicar = { aoSelecionarCategoria(item) }
                    )
                }
            }
        }
    }
}

@Composable
fun FiltroCategorias(selecionada: Categoria?, aoFiltrar: (Categoria?) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Etiqueta("Todas", selecionada == null, aoClicar = { aoFiltrar(null) })
        Categoria.entries.forEach { item ->
            Etiqueta(item.rotulo, selecionada == item, aoClicar = { aoFiltrar(item) })
        }
    }
}

@Composable
fun ResumoTotal(total: Double, quantidade: Int) {
    Surface(color = Azul, shape = RoundedCornerShape(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Total", fontSize = 12.sp, color = Color(0xFFB0C5D8))
                Text(dinheiro(total), fontSize = 23.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Text("$quantidade gasto(s)", color = Color(0xFFB0C5D8), fontSize = 13.sp)
        }
    }
}

@Composable
fun LinhaGasto(gasto: Gasto, aoRemover: () -> Unit) {
    Surface(color = Color.White, shape = RoundedCornerShape(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp, top = 8.dp, bottom = 8.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    gasto.descricao,
                    color = Azul,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(gasto.categoria.rotulo, color = AzulTexto, fontSize = 12.sp)
            }
            Spacer(Modifier.width(4.dp))
            Text(dinheiro(gasto.valor), color = Azul, fontSize = 13.sp)
            TextButton(onClick = aoRemover, contentPadding = androidx.compose.foundation.layout.PaddingValues(4.dp)) {
                Text("remover", color = Laranja, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun Etiqueta(texto: String, selecionada: Boolean, aoClicar: () -> Unit) {
    val corFundo = if (selecionada) Laranja else AzulClaro
    val corTexto = if (selecionada) Color.White else AzulTexto

    Box(
        modifier = Modifier
            .background(corFundo, RoundedCornerShape(24.dp))
            .clickable(onClick = aoClicar)
            .padding(horizontal = 13.dp, vertical = 7.dp)
    ) {
        Text(texto, color = corTexto, fontSize = 12.sp,
            fontWeight = if (selecionada) FontWeight.Bold else FontWeight.Normal)
    }
}
