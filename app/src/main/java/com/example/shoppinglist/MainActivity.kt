package com.example.shoppinglist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.list_view_produtos
import kotlinx.android.synthetic.main.cadastro_activity.btn_inserir
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onResume() {
        super.onResume()
        val adapter = list_view_produtos.adapter as ProductAdapter
        adapter.clear()
        adapter.addAll(produtosGlobal)

        val soma = produtosGlobal.sumByDouble { it.value * it.count }
        println(produtosGlobal)

        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        txt_total.text = "TOTAL: ${ f.format(soma)}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val produtosAdapter = ProductAdapter(this)
        list_view_produtos.adapter = produtosAdapter

        btn_adicionar.setOnClickListener {
            //Criando a Intent explícita
            val intent = Intent(this, CadastroActivity::class.java)
            //iniciando a atividade
            startActivity(intent)
        }

        list_view_produtos.setOnItemLongClickListener{
                adapterView: AdapterView<*>, view: View, position: Int, id: Long ->
                    //buscando o item clicado
                    val item = produtosAdapter.getItem(position)
                    //removendo o item clicado da lista
                    produtosAdapter.remove(item)
                    produtosGlobal.remove(item)

                    // atualizando valor
                    val soma = produtosGlobal.sumByDouble { it.value * it.count }

                    val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                    txt_total.text = "TOTAL: ${ f.format(soma)}"

                    //retorno indicando que o click foi realizado com sucesso

                    true
        }
    }
}

