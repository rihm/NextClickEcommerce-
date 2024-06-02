package com.nextclick.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nextclick.test.R
import com.nextclick.test.db.CartItem
import com.nextclick.test.db.Product

class CartAdapter(val context: Context,
val cartClickListener:CartClickListeners ): RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private val cartItem=ArrayList<CartItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(
            R.layout.cart_items,parent,false
        )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartItem.size
    }

    override fun onBindViewHolder(holder: CartAdapter.MyViewHolder, position: Int) {

        holder.cart_name.text = cartItem.get(position).name
        holder.cart_rate.text = cartItem.get(position).rate
        holder.item_count.text = cartItem.get(position).quantity.toString()
        holder.total_rate.text = cartItem.get(position).totalItemRate.toString()

        holder.cart_add.setOnClickListener(OnClickListener {

            cartClickListener.onPlusClicked(cartItem.get(position))
        })
        holder.cart_minus.setOnClickListener(OnClickListener {
            cartClickListener.onMinusClicked(cartItem.get(position))
        })
        holder.cart_delete.setOnClickListener(OnClickListener {
            cartClickListener.onDeleteClicked(cartItem.get(position))
        })

        holder.cart_delete.setOnClickListener(OnClickListener {
            cartClickListener.onDeleteClicked(cartItem.get(position))
        })

    }
    fun updateList(newList: List<CartItem>) {
        cartItem.clear()
        cartItem.addAll(newList)
        notifyDataSetChanged()
    }
    public class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val cart_name=itemView.findViewById<TextView>(R.id.prod_name)
        val cart_rate=itemView.findViewById<TextView>(R.id.prod_rate)
        val item_count=itemView.findViewById<TextView>(R.id.item_count)
        val total_rate=itemView.findViewById<TextView>(R.id.total_rate)
        val cart_add=itemView.findViewById<ImageView>(R.id.img_add)
        val cart_minus=itemView.findViewById<ImageView>(R.id.img_minus)
        val cart_delete=itemView.findViewById<ImageView>(R.id.delete_cart_item)

    }
    interface CartClickListeners{
        fun onDeleteClicked(cartItem: CartItem)
        fun onPlusClicked(cartItem: CartItem)
        fun onMinusClicked(cartItem: CartItem)
    }
}