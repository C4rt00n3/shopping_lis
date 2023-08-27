package com.example.shoppinglist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.txt_total
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter(context: Context): ArrayAdapter<Product>(context, 0) {
     override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v:View

        if(convertView != null){
            v = convertView
        }else{
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent,
                false)
        }

        val item = getItem(position)
        val txt_produto = v.findViewById<TextView>(R.id.txt_item_produto)
        val txt_qtd = v.findViewById<TextView>(R.id.txt_item_qtd)
        val txt_valor = v.findViewById<TextView>(R.id.txt_item_valor)
        val img_produto = v.findViewById<ImageView>(R.id.img_item_foto)

        txt_qtd.text = item?.count.toString()
        txt_produto.text = item?.name

        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        txt_valor.text = f.format(item?.value)

        if (item?.image != null){
            img_produto.setImageBitmap(item?.image)
        }

        return v
    }
}