package com.demo.billingdemo.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.billingdemo.*
import com.demo.billingdemo.adapter.CustomerAdapterClass
import com.demo.billingdemo.databinding.DialogCustomerAddBinding
import com.demo.billingdemo.databinding.DialogCustomerUpdateBinding
import com.demo.billingdemo.databinding.FragmentCustomerBinding
import com.google.android.material.snackbar.Snackbar

class CustomerFragment : Fragment() {

    lateinit var customerBinding: FragmentCustomerBinding
    lateinit var adapter: CustomerAdapterClass

    lateinit var db: SqliteDatabaseHelper

    companion object {
        var customerList = ArrayList<CustomerModelClass>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customerBinding = FragmentCustomerBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        db = SqliteDatabaseHelper(requireContext())
        infoFunction()   //define function

        initView()


        return customerBinding.root
    }

    private fun initView() {
        deleteData()
        customerBinding.imgAdd.setOnClickListener {  //data add

            val dialog = Dialog(requireContext())   //dialog set
            val dialogBinding = DialogCustomerAddBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)  //dialog in xml file set

            dialogBinding.btnSet.setOnClickListener {
                var id = 0
                var companyName = dialogBinding.edtCompanyName.text.toString()   //variable define
                var customerName = dialogBinding.edtCustomerName.text.toString()   //variable define
                var mobileNumber = dialogBinding.edtMobileNumber.text.toString()   //variable define

                db.insertCustomerData(
                    companyName,
                    customerName,
                    mobileNumber
                )      //data store in sqlite database
                customerList.add(CustomerModelClass(id, companyName, customerName, mobileNumber))
                Toast.makeText(context, "your data save", Toast.LENGTH_SHORT).show()

                customerList = db.displayCustomerData()    //data display
                adapter.update(customerList)  //list pass in adapter class in function
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

    private fun infoFunction() {   //set function


        adapter = CustomerAdapterClass(requireContext()) { click ->

            val updateDialog = Dialog(requireContext())   //dialog define
            val dialogBinding = DialogCustomerUpdateBinding.inflate(layoutInflater)
            updateDialog.setContentView(dialogBinding.root)  //set xml file

            updateDialog.window?.setGravity(Gravity.BOTTOM)//dialog position set

            dialogBinding.txtCompanyDialog.text = click.companyName   //text view in set value
            dialogBinding.txtCustomerNameDialog.text = click.customerName  //text view in set value
            dialogBinding.txtMobileDialog.text = click.mobileNumber  //text view in set value

            dialogBinding.imgEdite.setOnClickListener {

                updateFunction(click.id, click.companyName, click.customerName, click.mobileNumber)

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
        customerBinding.rcvCustomer.layoutManager = manger
        customerBinding.rcvCustomer.adapter = adapter

        customerList = db.displayCustomerData()    //data display
        adapter.update(customerList)  //list pass in adapter class in function


    }

    private fun updateFunction(
        id: Int,
        companyName: String,
        customerName: String,
        mobileNumber: String
    ) {
        val dialog = Dialog(requireContext())   //dialog set
        val dialogBinding = DialogCustomerAddBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)  //dialog in xml file set

        dialogBinding.txtTitle.text = "Update"


        dialogBinding.edtCompanyName.setText(companyName) //variable define
        dialogBinding.edtCustomerName.setText(customerName)   //variable define
        dialogBinding.edtMobileNumber.setText(mobileNumber) //variable define

        Log.e("TAG", "updateFunction: $id $companyName $customerName")
        dialogBinding.btnSet.setOnClickListener {

            val newCompanyName =
                dialogBinding.edtCompanyName.text.toString() //variable define
            val newCustomerName =
                dialogBinding.edtCustomerName.text.toString()   //variable define
            val newMobileNumber =
                dialogBinding.edtMobileNumber.text.toString() //variable define
//
            db.updateCustomerData(
                newCompanyName,
                newCustomerName,
                newMobileNumber,
                id
            )      //data update in sqlite database

            Log.e("TAG", "updateData: $newCompanyName $newCustomerName $newMobileNumber")
            Toast.makeText(context, "your data is Update", Toast.LENGTH_SHORT).show()

            customerList = db.displayCustomerData()    //data display
            adapter.update(customerList)  //list pass in adapter class in function
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

    private fun deleteData() {
        val swipeToDeleteCallback: SwipeToDeleteCallback = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val item: CustomerModelClass = adapter.getData().get(position)
                adapter.removeItem(position)
                val snackbar = Snackbar
                    .make(
                        customerBinding.coordinatorLayout,
                        "Customer was removed from the list.",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction("UNDO") {
                    adapter.restoreItem(item, position)
                    customerBinding.rcvCustomer.scrollToPosition(position)
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }

        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(   customerBinding.rcvCustomer)

    }
}