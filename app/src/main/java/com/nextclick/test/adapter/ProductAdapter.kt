package com.nextclick.test.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationBarItemView
import com.nextclick.test.R
import com.nextclick.test.db.Product
import org.w3c.dom.Text

class ProductAdapter(val context: Context,
    val productClickInterface: ProductClickInterface): RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    private  val allProducts = ArrayList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(
            R.layout.product_items,
            parent,false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.prodImage.setImageBitmap(byteArrayToBitmap(allProducts.get(position).img))
//        holder.prodImage.setImageResource(R.drawable.products)
        holder.prodName.text=(allProducts.get(position).name)
        holder.prodRate.setText(allProducts.get(position).rate)
        holder.container.setOnClickListener(View.OnClickListener {
            productClickInterface.onProductClick(allProducts.get(position))

        })


    }

    override fun getItemCount(): Int {
        return allProducts.size
    }

    public class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val prodName=itemView.findViewById<TextView>(R.id.name)
        val prodRate=itemView.findViewById<TextView>(R.id.rate)
        val prodImage=itemView.findViewById<ImageView>(R.id.image)
        val container=itemView.findViewById<LinearLayout>(R.id.lay_container)

    }

    fun byteArrayToBitmap(data: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(data, 0, data.size)
    }

    fun updateList(newList: List<Product>) {
        allProducts.clear()
        allProducts.addAll(newList)
        notifyDataSetChanged()
    }

    interface ProductClickInterface {
        // creating a method for click action
        // on recycler view item for updating it.
        fun onProductClick(product: Product)
    }
}