package com.demo.billingdemo.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.billingdemo.R
import com.demo.billingdemo.databinding.DialogCategoryAddBinding
import com.demo.billingdemo.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
lateinit var categoryBinding:FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryBinding= FragmentCategoryBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        initView()
        return categoryBinding.root
    }

    private fun initView() {

        categoryBinding.imgAdd.setOnClickListener {  //data add

            val dialog = Dialog(requireContext())   //dialog set
            val dialogBinding = DialogCategoryAddBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)  //dialog in xml file set

            dialogBinding.btnSet.setOnClickListener {
                var id = 0
                var itemName = dialogBinding.edtItem.text.toString()   //variable define
                var p_Price = dialogBinding.edtPPrice.text.toString()   //variable define
                var s_Price = dialogBinding.edtSPrice.text.toString()   //variable define
//                addProducts(dialogBinding)
//                db.insertCategory(itemName, p_Price, s_Price)      //data store in sqlite database
//                categoryList.add(CategoryModelClass( itemName, p_Price, s_Price))
                Toast.makeText(context, "your data save", Toast.LENGTH_SHORT).show()


             //   updatefunction()   //define function


//                var manger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//                categoryBinding.rcvCategory.layoutManager = manger
//                categoryBinding.rcvCategory.adapter = adapter
//
////                var list = db.displayCategory()    //data display
//                adapter.update(categoryList)  //list pass in adapter class in function
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

}