package com.example.shoppinglist

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import kotlinx.android.synthetic.main.cadastro_activity.btn_inserir
import kotlinx.android.synthetic.main.cadastro_activity.img_foto_produto
import kotlinx.android.synthetic.main.cadastro_activity.txt_produto
import kotlinx.android.synthetic.main.cadastro_activity.txt_qtd
import kotlinx.android.synthetic.main.cadastro_activity.txt_valor

class CadastroActivity : ComponentActivity() {
    val COD_IMAGE = 101
    var imageBitMap: Bitmap? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                // Lendo a uri com a imagem
                val uri = data.data // Use 'data.data' em vez de 'data.getData()'
                if (uri != null) {
                    // Transformando o resultado em bitmap
                    val inputStream = contentResolver.openInputStream(uri)
                    imageBitMap = BitmapFactory.decodeStream(inputStream)

                    println(imageBitMap)

                    // Exibir a imagem no aplicativo
                    img_foto_produto.setImageBitmap(imageBitMap)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_activity)

        btn_inserir.setOnClickListener {
            //pegando o valor digitado pelo usuário
            val produto = txt_produto.text.toString()
            val qtd = txt_qtd.text.toString()
            val valor = txt_valor.text.toString()
            if (produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()) {
                //enviado o item para a lista
                val prod = Product(produto, qtd.toInt(), valor.toDouble(), imageBitMap)
                produtosGlobal.add(prod)
                // limpando texto
                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()
            } else {
                txt_produto.error = if (txt_produto.text.isEmpty())
                    "Preencha o nome do produto" else null
                txt_qtd.error = if (txt_qtd.text.isEmpty()) "Preencha a quantidade" else null
                txt_valor.error = if (txt_valor.text.isEmpty()) "Preencha o valor" else null
            }
        }

        img_foto_produto.setOnClickListener {
            abrirGaleria()
        }

    }

    fun abrirGaleria() {
        //definindo a ação de conteúdo
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        //definindo filtro para imagens
        intent.type = "image/*"
        //inicializando a activity com resultado
        startActivityForResult(
            Intent.createChooser(intent, "Selecione uma imagem"),
            COD_IMAGE
        )
    }
}
