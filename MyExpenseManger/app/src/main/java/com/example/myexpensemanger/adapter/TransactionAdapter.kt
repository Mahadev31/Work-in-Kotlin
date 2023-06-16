package com.example.myexpensemanger.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myexpensemanger.R
import com.example.myexpensemanger.modelclass.IncomeExpenseModelClass

class TransactionAdapter(

    var context: Context,
    var listTransaction: ArrayList<IncomeExpenseModelClass>,
    var edit: (IncomeExpenseModelClass) -> Unit,
    var delete: (Int) -> Unit,
    var total: () -> Unit
) : RecyclerView.Adapter<TransactionAdapter.MyViewHolder>() {
    var income = 0
    var expense = 0


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //
//        var id: TextView = itemView.findViewById(R.id.txtIdTra)
        var amount: TextView = itemView.findViewById(R.id.txtAmountTra)
        var categoryName: TextView = itemView.findViewById(R.id.txtCategoryTra)
        var date: TextView = itemView.findViewById(R.id.txtDateTra)
        var mode: TextView = itemView.findViewById(R.id.txtModeSelectedTra)
        var note: TextView = itemView.findViewById(R.id.txtNoteTra)
        var type: TextView = itemView.findViewById(R.id.txtTypeTra)

        var edit: ImageView = itemView.findViewById(R.id.imgEdit)
        var delete: ImageView = itemView.findViewById(R.id.imgDelete)
        var layout: LinearLayout = itemView.findViewById(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_data_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listTransaction.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
//        holder.id.text = listTransaction[position].id.toString()
        holder.amount.text = listTransaction[position].amount
        holder.categoryName.text = listTransaction[position].selectedCategory
        holder.date.text = listTransaction[position].date
        holder.mode.text = listTransaction[position].mode
        holder.note.text = listTransaction[position].note
        holder.type.text = listTransaction[position].page

        holder.edit.setOnClickListener {
            edit.invoke(listTransaction[position])

        }
        holder.delete.setOnClickListener {

            delete.invoke(listTransaction[position].id)

        }
        if (holder.type.text.toString() == "Income") {
            holder.amount.setTextColor(Color.WHITE)
            holder.layout.setBackgroundColor(Color.parseColor("#049E2F"))
            holder.type.setTextColor(Color.WHITE)
            val incomeAmount = holder.amount.text.toString()
            income = income + incomeAmount.toInt()
            Log.e("TAG", "onIncome: $income")


        } else {
            holder.amount.setBackgroundColor(Color.RED)
            holder.amount.setTextColor(Color.WHITE)

            holder.layout.setBackgroundColor(Color.RED)
            holder.type.setTextColor(Color.WHITE)
            val expenseAmount = holder.amount.text.toString()
            expense = expense + expenseAmount.toInt()
            Log.e("TAG", "onExpense: $expense")
        }

        if (position == listTransaction.size - 1) {
            total.invoke()
        }
    }

    fun updateData(listTransaction: ArrayList<IncomeExpenseModelClass>) {
        this.listTransaction = ArrayList()
        this.listTransaction.addAll(listTransaction)
        notifyDataSetChanged()
    }

    fun incomeFunction(): Int {

        Log.e("function", "income: " + income)
        return income

    }

    fun expenseFunction(): Int {
        Log.e("function", "expense: " + expense)
        return expense


    }
}