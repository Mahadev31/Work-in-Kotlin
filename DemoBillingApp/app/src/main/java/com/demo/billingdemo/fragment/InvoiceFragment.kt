package com.demo.billingdemo.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.billingdemo.*
import com.demo.billingdemo.adapter.CustomerSuggestionAdapter
import com.demo.billingdemo.adapter.InvoiceAdapter
import com.demo.billingdemo.adapter.ItemSuggestionAdapter
import com.demo.billingdemo.databinding.DialogCategoryAddBinding
import com.demo.billingdemo.databinding.DialogCustomerAddBinding
import com.demo.billingdemo.databinding.FragmentInvoiceBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InvoiceFragment : Fragment() {

    lateinit var invoiceBinding: FragmentInvoiceBinding

    lateinit var db: SqliteDatabaseHelper

    var invoiceList = ArrayList<AddItemModelClass>()

    lateinit var adapter: InvoiceAdapter

    var date: String? = null
    var selectedShopName: String? = null

    var itemName: String? = null
    var qty: String? = null
    var price: String? = null
    var total: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        invoiceBinding = FragmentInvoiceBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        db = SqliteDatabaseHelper(requireContext())
        adapter = InvoiceAdapter()
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        invoiceBinding.rcvBill.layoutManager = manager
        invoiceBinding.rcvBill.adapter = adapter


        //static date Format
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDateFormat: String = simpleDateFormat.format(Date())
        date = invoiceBinding.txtDate.setText(currentDateFormat).toString()

        customer()
        billItem()
        printInvoice()
        return invoiceBinding.root
    }


    private fun customer() {
        invoiceBinding.rcvItemSuggestion.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        invoiceBinding.rcvItemSuggestion.setHasFixedSize(true)
        invoiceBinding.edtCName.doOnTextChanged { text, start, before, count ->
            if (start < count || start > count) {

                invoiceBinding.rcvItemSuggestion.visibility = View.VISIBLE
                invoiceBinding.txtAddNewItem.visibility = View.VISIBLE
                invoiceBinding.txtAddNewItem.setOnClickListener {
                    val shopName = invoiceBinding.edtCName.text.toString()
                    addNewCustomerFun(shopName)

                }
                db.displayCustomerData().forEach {
                    if (it.shopName.startsWith(text.toString())) {
                        val suggestionList = ArrayList<CustomerModelClass>()
                        Log.e("suggestionItem: ", text.toString())
                        suggestionList.add(it)
                        invoiceBinding.rcvItemSuggestion.adapter =
                            CustomerSuggestionAdapter(
                                requireContext(),
                                suggestionList
                            ) { customerName ->

                                selectedShopName =
                                    invoiceBinding.edtCName.setText(customerName).toString()


                            }
                    }
                }
            }
        }
    }

    private fun addNewCustomerFun(shopName: String) {
        val dialog = Dialog(requireContext())   //dialog set
        val dialogBinding = DialogCustomerAddBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)  //dialog in xml file set

        dialogBinding.edtShopName.setText(shopName)

        dialogBinding.btnSet.setOnClickListener {
            val id = 0
            val newShopName = dialogBinding.edtShopName.text.toString()   //variable define
            val newCustomerName = dialogBinding.edtCustomerName.text.toString()   //variable define
            val newMobileNumber = dialogBinding.edtMobileNumber.text.toString()   //variable define

            db.insertCustomerData(
                newShopName,
                newCustomerName,
                newMobileNumber
            )      //data store in sqlite database
            CustomerFragment.customerList.add(
                CustomerModelClass(
                    id,
                    newShopName,
                    newCustomerName,
                    newMobileNumber
                )
            )
            Toast.makeText(context, "your data save", Toast.LENGTH_SHORT).show()

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

    private fun billItem() {


        invoiceBinding.rcvItemSuggestion.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        invoiceBinding.rcvItemSuggestion.setHasFixedSize(true)

        invoiceBinding.edtItemAdd.doOnTextChanged { text, start, before, count ->

            if (start < count || start > count) {

                invoiceBinding.rcvItemSuggestion.visibility = View.VISIBLE
                invoiceBinding.txtAddNewItem.visibility = View.VISIBLE
                invoiceBinding.txtAddNewItem.setOnClickListener {
                    addNewItem()
                }
            }

            CategoryFragment.categoryList.forEach {
                if (it.itemName.startsWith(text.toString())) {
                    val suggestionList = ArrayList<CategoryModelClass>()
                    Log.e("TAG", "suggestionList: ${text.toString()}")
                    suggestionList.add(it)
                    var adapterSuggestion = ItemSuggestionAdapter(
                        requireContext()

                    ) { itemSug, salePrice ->
                        invoiceBinding.edtItemAdd.setText(itemSug)
                        invoiceBinding.edtPriceAdd.setText(salePrice)


                        invoiceBinding.edtQtyAdd.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(
                                s: CharSequence?,
                                start: Int,
                                count: Int,
                                after: Int
                            ) {

                            }

                            override fun onTextChanged(
                                s: CharSequence?,
                                start: Int,
                                before: Int,
                                count: Int
                            ) {

                            }

                            override fun afterTextChanged(s: Editable?) {
                                val price =
                                    if (invoiceBinding.edtPriceAdd.text.toString().isNotEmpty()) {
                                        invoiceBinding.edtPriceAdd.text.toString().toInt()
                                    } else {
                                        salePrice.toInt()
                                    }
                                val qty =
                                    if (invoiceBinding.edtQtyAdd.text.toString().isNotEmpty()) {
                                        invoiceBinding.edtQtyAdd.text.toString().toInt()
                                    } else {
                                        "0".toInt()
                                    }
                                val value = price * qty
                                invoiceBinding.edtTotalAdd.setText(value.toString())
                            }
                        })


                        invoiceBinding.edtPriceAdd.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(
                                s: CharSequence?,
                                start: Int,
                                count: Int,
                                after: Int
                            ) {

                            }

                            override fun onTextChanged(
                                s: CharSequence?,
                                start: Int,
                                before: Int,
                                count: Int
                            ) {

                            }

                            override fun afterTextChanged(s: Editable?) {
                                val price =
                                    if (invoiceBinding.edtPriceAdd.text.toString().isNotEmpty()) {
                                        invoiceBinding.edtPriceAdd.text.toString().toInt()
                                    } else {
                                        salePrice.toInt()
                                    }
                                val qty =
                                    if (invoiceBinding.edtQtyAdd.text.toString().isNotEmpty()) {
                                        invoiceBinding.edtQtyAdd.text.toString().toInt()
                                    } else {
                                        "0".toInt()
                                    }
                                val value = price * qty
                                invoiceBinding.edtTotalAdd.setText(value.toString())
                            }
                        })

                    }

                    invoiceBinding.rcvItemSuggestion.adapter = adapterSuggestion

                    adapterSuggestion.updateList(suggestionList)
                }
            }
        }

        invoiceBinding.btnAddItem.setOnClickListener {

            invoiceBinding.txtAddNewItem.visibility = View.GONE
            invoiceBinding.rcvItemSuggestion.visibility = View.GONE

            var id = 0
             itemName = invoiceBinding.edtItemAdd.text.toString()
             qty = invoiceBinding.edtQtyAdd.text.toString()
             price = invoiceBinding.edtPriceAdd.text.toString()
             total = invoiceBinding.edtTotalAdd.text.toString()

//            db.insertInvoiceItemFun(itemName!!, qty!!, price!!, total!!)
            db.insertInvoiceData(date, selectedShopName, itemName!!, qty!!, price!!, total!!)
            invoiceList.add(AddItemModelClass(id, itemName!!, qty!!, price!!, total!!))

            adapter.updateList(invoiceList)


            val total1 = if (invoiceBinding.txtTotal.text.toString().isNotEmpty()) {
                invoiceBinding.txtTotal.text.toString().toInt()
            } else {
                "0".toInt()
            }
            var total2 = if (invoiceBinding.edtTotalAdd.text.toString().isNotEmpty()) {
                invoiceBinding.edtTotalAdd.text.toString().toInt()
            } else {
                "0".toInt()
            }
            val totalMain = total1 + total2
            invoiceBinding.txtTotal.text = totalMain.toString()


            invoiceBinding.edtItemAdd.setText("")
            invoiceBinding.edtQtyAdd.setText("")
            invoiceBinding.edtPriceAdd.setText("")
            invoiceBinding.edtTotalAdd.setText("")

        }

    }

    private fun addNewItem() {
        val dialog = Dialog(requireContext())   //dialog set
        val dialogBinding = DialogCategoryAddBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)  //dialog in xml file set

        dialogBinding.btnSet.setOnClickListener {

            var itemName = dialogBinding.edtItem.text.toString()   //variable define
            var costPrice = dialogBinding.edtPPrice.text.toString()   //variable define
            var salePrice = dialogBinding.edtSPrice.text.toString()   //variable define

            db.insertCategory(itemName, costPrice, salePrice)      //data store in sqlite database

            Toast.makeText(context, "your data save", Toast.LENGTH_SHORT).show()

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

    private fun printInvoice() {
        invoiceBinding.imgDone.setOnClickListener {
//            db.insertInvoiceData(date, selectedShopName)

            val fragmentManager: FragmentManager?= fragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(com.demo.billingdemo.R.id.frameView, DisplayInvoiceFragment())
            fragmentTransaction.commit()
        }

    }

}