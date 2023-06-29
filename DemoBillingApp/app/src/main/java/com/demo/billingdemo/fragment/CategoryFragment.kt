package com.demo.billingdemo.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.billingdemo.CategoryModelClass
import com.demo.billingdemo.SqliteDatabaseHelper
import com.demo.billingdemo.SwipeToDeleteCallback
import com.demo.billingdemo.adapter.CategoryAdapterClass
import com.demo.billingdemo.databinding.DialogCategoryAddBinding
import com.demo.billingdemo.databinding.DialogCategoryUpdateBinding
import com.demo.billingdemo.databinding.FragmentCategoryBinding
import com.google.android.material.snackbar.Snackbar


class CategoryFragment : Fragment() {

    lateinit var categoryBinding: FragmentCategoryBinding
    lateinit var adapter: CategoryAdapterClass

    lateinit var db: SqliteDatabaseHelper

    companion object {
        var categoryList = ArrayList<CategoryModelClass>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryBinding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        db = SqliteDatabaseHelper(requireContext())
        infoFunction()   //define function

        categoryBinding.imgBack.setOnClickListener {

        }
        initView()


        return categoryBinding.root
    }

    private fun initView() {

        deleteData()
        categoryBinding.imgAdd.setOnClickListener {  //data add

            val dialog = Dialog(requireContext())   //dialog set
            val dialogBinding = DialogCategoryAddBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)  //dialog in xml file set

            dialogBinding.btnSet.setOnClickListener {
                var id = 0
                var itemName = dialogBinding.edtItem.text.toString()   //variable define
                var costPrice = dialogBinding.edtPPrice.text.toString()   //variable define
                var salePrice = dialogBinding.edtSPrice.text.toString()   //variable define
//                addProducts(dialogBinding)
                db.insertCategory(
                    itemName,
                    costPrice,
                    salePrice
                )      //data store in sqlite database
                categoryList.add(CategoryModelClass(id, itemName, costPrice, salePrice))
                Toast.makeText(context, "your data save", Toast.LENGTH_SHORT).show()

                categoryList = db.displayCategory()    //data display
                adapter.update(categoryList)  //list pass in adapter class in function
                dialog.dismiss()
            }
            dialogBinding.btnCancel.setOnClickListener {

                dialog.dismiss()
            }
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))   //dialog box TRANSPARENT
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
        }

    }

    private fun deleteData() {
        val swipeToDeleteCallback: SwipeToDeleteCallback = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val item: CategoryModelClass = adapter.getData().get(position)
                adapter.removeItem(position)
                val snackbar = Snackbar
                    .make(
                        categoryBinding.coordinatorLayout,
                        "Item was removed from the list.",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction("UNDO") {
                    adapter.restoreItem(item, position)
                    categoryBinding.rcvCategory.scrollToPosition(position)
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }

        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(   categoryBinding.rcvCategory)

    }



    private fun infoFunction() {   //set function



        adapter = CategoryAdapterClass(requireContext()) { click ->

            val updateDialog = Dialog(requireContext())   //dialog define
            val dialogBinding = DialogCategoryUpdateBinding.inflate(layoutInflater)
            updateDialog.setContentView(dialogBinding.root)  //set xml file

            updateDialog.window?.setGravity(Gravity.BOTTOM)//dialog position set

            dialogBinding.txtItemDialog.text = click.itemName   //text view in set value
            dialogBinding.txtPPriceDialog.text = click.costPrice  //text view in set value
            dialogBinding.txtSPriceDialog.text = click.salePrice  //text view in set value

            dialogBinding.imgEdite.setOnClickListener {


                updateFunction(click.id, click.itemName, click.costPrice, click.salePrice)
                updateDialog.dismiss()
            }
            updateDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))   //dialog box TRANSPARENT
            updateDialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            updateDialog.show()

        }


        var manger = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        categoryBinding.rcvCategory.layoutManager = manger
        categoryBinding.rcvCategory.adapter = adapter
        categoryList = db.displayCategory()    //data display
        adapter.update(categoryList)  //list pass in adapter class in function


    }

    private fun updateFunction(id: Int, itemName: String, costPrice: String, salePrice: String) {
        val dialog = Dialog(requireContext())   //dialog set
        val dialogBinding = DialogCategoryAddBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)  //dialog in xml file set

        dialogBinding.txtTitle.text = "Update"


        dialogBinding.edtItem.setText(itemName) //variable define
        dialogBinding.edtPPrice.setText(costPrice)   //variable define
        dialogBinding.edtSPrice.setText(salePrice) //variable define


        dialogBinding.btnSet.setOnClickListener {

            val newItemName =
                dialogBinding.edtItem.text.toString() //variable define
            val newCostPrice =
                dialogBinding.edtPPrice.text.toString()   //variable define
            val newSalePrice =
                dialogBinding.edtSPrice.text.toString() //variable define
//
            db.updateRecord(
                newItemName,
                newCostPrice,
                newSalePrice,
                id
            )      //data update in sqlite database


            Toast.makeText(context, "your data is Update", Toast.LENGTH_SHORT).show()

            categoryList = db.displayCategory()    //data display
            adapter.update(categoryList)  //list pass in adapter class in function
            dialog.dismiss()
        }
        dialogBinding.btnCancel.setOnClickListener {

            dialog.dismiss()
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))   //dialog box TRANSPARENT
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }



}