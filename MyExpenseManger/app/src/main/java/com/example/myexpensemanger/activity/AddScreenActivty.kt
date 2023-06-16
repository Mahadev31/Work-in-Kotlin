package com.example.myexpensemanger.activity

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myexpensemanger.adapter.DialogCategoryAdapter
import com.example.myexpensemanger.adapter.DialogPaymentAdapter
import com.example.myexpensemanger.databinding.ActivityAddScreenBinding
import com.example.myexpensemanger.databinding.DialogCategoryBinding
import com.example.myexpensemanger.databinding.DialogPaymentBinding
import com.example.myexpensemanger.sqlite.SqLiteHelperData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AddScreenActivty : AppCompatActivity() {


    lateinit var addScreenBinding: ActivityAddScreenBinding   // activity binding

    lateinit var dbS: SqLiteHelperData   // data base class define

    lateinit var selectedCategory: String    //  category variable define
    lateinit var selectedMode: String     //  mode variable define
    lateinit var selectedDateValue: String   //  date variable  define
    lateinit var page: String     //  page variable  define

    var id_number: Int = 0     //  id variable  define
    var flag = 0    //  flag variable  define

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addScreenBinding = ActivityAddScreenBinding.inflate(layoutInflater)
        setContentView(addScreenBinding.root)  //set xml file

        var name: String? = intent.getStringExtra("title")  // title set in this variable
        addScreenBinding.txtTitle.text = name  // variable set  in text view

        dbS = SqLiteHelperData(this)   // set context class in sqlite

        if (intent != null && intent.hasExtra("updateRecord")) {  // data update key access this class

            flag = 1
            var newAmt: String? = intent.getStringExtra("amount")   // key set in variable
            var newNote: String? = intent.getStringExtra("note")   // key set  variable
            var newDone: String? = intent.getStringExtra("iconName")   // key set  variable
            id_number = intent.getIntExtra("id_no", 0)   // key set  variable

            Log.e("TAG", "RS_ID: " + id_number)
            Log.e("TAG", "RS_Amount: " + newAmt)

            addScreenBinding.edtAmount.setText(newAmt)  //variable set in text view
            addScreenBinding.edtNote.setText(newNote)  //variable set in text view
            addScreenBinding.imgDone.text = newDone  //variable set in text view

        }

        intitView()  //create function


    }


    private fun intitView() {  //set function

        categoryDialogData()   //create function
        dataAndTime()    //create function
        paymentMode()    //create function
        page = intent.getStringExtra("Page").toString()  // key set  variable

        when (page) {  // variable set in when statement

            "income" -> {   //click income open income page
                addScreenBinding.rbIncome.isChecked = true  // set radio button income true

            }

            "expense" -> {   //click expense open income page
                addScreenBinding.rbExpense.isChecked = true  // set radio button expense true
            }

        }
        addScreenBinding.imgBack.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }



        addScreenBinding.imgDone.setOnClickListener {
            var amount = addScreenBinding.edtAmount.text.toString()
            var note = addScreenBinding.edtNote.text.toString()

            if (amount.isEmpty()) {
                Toast.makeText(this, "Please Enter Amount", Toast.LENGTH_SHORT).show()
            } else if (amount.length <= 1 || amount.length >= 10) {
                Toast.makeText(this, "Please Enter valid Amount", Toast.LENGTH_SHORT).show()
            } else if (note.isEmpty()) {
                Toast.makeText(this, "Please Enter Note", Toast.LENGTH_SHORT).show()
            }  else {

                if (addScreenBinding.rgGroup.checkedRadioButtonId == -1) {

                } else {
                    val selectId: Int = addScreenBinding.rgGroup.checkedRadioButtonId
                    var selectedRadioButton: RadioButton = findViewById(selectId)
                    var text = selectedRadioButton.text.toString()

                    page = if (text == "Income") {
                        "Income"
                    } else {
                        "expense"
                    }

                }
                Log.e("TAG", "select button: " + addScreenBinding.imgDone.text.toString())
                if (flag == 1) {

                    dbS.updateRecord(amount,selectedCategory,selectedDateValue,selectedMode, note,page,id_number )


                } else {
                    dbS.insertTransact(
                        amount,
                        selectedCategory,
                        selectedDateValue,
                        selectedMode,
                        note,
                        page
                    )
                }
                Log.e("TAG", "Raj_ID: $id_number")
                Log.e("TAG", "Raj_AMOUNT: $amount")
                Log.e("TAG", "Raj_category: $selectedCategory")
                Log.e("TAG", "Raj_date: $selectedDateValue")
                Log.e("TAG", "Raj_mode: $selectedMode")
                Log.e("TAG", "Raj_NOTE: $note")
                Log.e("TAG", "Raj_page: $page")


                var intent = Intent(this, TransactionActivity::class.java)
                finish()
                startActivity(intent)

                Toast.makeText(this, "Your Data Save", Toast.LENGTH_SHORT).show()
            }

        }

    }


    private fun categoryDialogData() {

        addScreenBinding.cdCategory.setOnClickListener {


            val dialog = Dialog(this)
            val dialogBinding = DialogCategoryBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)

            val list = dbS.displayCategory()

            var adapter = DialogCategoryAdapter(list, { categoryName ->
                Log.e("TAG", "categoryDialogData: " + categoryName)
                selectedCategory = categoryName
                addScreenBinding.txtCategorySelected.text = selectedCategory
            })
            val manger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            dialogBinding.recycleDialog.layoutManager = manger
            dialogBinding.recycleDialog.adapter = adapter


            dialogBinding.btnSet.setOnClickListener {
                Toast.makeText(this, "Your data is sava", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialogBinding.btnCancel.setOnClickListener {

                Toast.makeText(this, "Your data is not Save", Toast.LENGTH_SHORT).show()
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

    private fun dataAndTime() {

        //static date Format
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDateFormat: String = simpleDateFormat.format(Date())
        addScreenBinding.txtDate.text = currentDateFormat

        selectedDateValue = currentDateFormat

        //Dynamic date Format
        var cal: Calendar = Calendar.getInstance()
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                addScreenBinding.txtDate.text = sdf.format(cal.getTime())
            }
        }
        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        addScreenBinding.txtDate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@AddScreenActivty,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
        selectedDateValue = currentDateFormat

//static time Format
        val simpleTimeFormat = SimpleDateFormat("HH:mm ")
        val currentTime: String = simpleTimeFormat.format(Date())
        addScreenBinding.txtTime.text = currentTime
        //Dynamic time Format
        val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                    val formattedTime: String = when {
                        hourOfDay == 0 -> {
                            if (minute < 10) {
                                "${hourOfDay + 12}:0${minute} am"
                            } else {
                                "${hourOfDay + 12}:${minute} am"
                            }
                        }
                        hourOfDay > 12 -> {
                            if (minute < 10) {
                                "${hourOfDay - 12}:0${minute} pm"
                            } else {
                                "${hourOfDay - 12}:${minute} pm"
                            }
                        }
                        hourOfDay == 12 -> {
                            if (minute < 10) {
                                "${hourOfDay}:0${minute} pm"
                            } else {
                                "${hourOfDay}:${minute} pm"
                            }
                        }
                        else -> {
                            if (minute < 10) {
                                "${hourOfDay}:${minute} am"
                            } else {
                                "${hourOfDay}:${minute} am"
                            }
                        }
                    }

                    addScreenBinding.txtTime.text = formattedTime
                }
            }

        addScreenBinding.txtTime.setOnClickListener {
            val timePicker = TimePickerDialog(this, timePickerDialogListener, 12, 10, false)
            timePicker.show()
        }
    }

    private fun paymentMode() {

        var paymentList = ArrayList<String>()

        paymentList.add("Cash")
        paymentList.add("Credit Card")
        paymentList.add("Debit Card")
        paymentList.add("Net Banking")
        paymentList.add("Cheque")

        addScreenBinding.cdMode.setOnClickListener {

            val dialog = Dialog(this)

            val dialogPayBinding = DialogPaymentBinding.inflate(layoutInflater)
            dialog.setContentView(dialogPayBinding.root)


            var adapter1 = DialogPaymentAdapter(paymentList, { modeName ->
                selectedMode = modeName
                addScreenBinding.txtModeSelected.text = modeName
            })
            val manger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            dialogPayBinding.recycleDialog.layoutManager = manger
            dialogPayBinding.recycleDialog.adapter = adapter1


            dialogPayBinding.btnSet.setOnClickListener {

                Toast.makeText(this, "Your data is sava", Toast.LENGTH_SHORT).show()



                dialog.dismiss()
            }
            dialogPayBinding.btnCancel.setOnClickListener {

                Toast.makeText(this, "Your data is not Save", Toast.LENGTH_SHORT).show()
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


