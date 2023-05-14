package com.example.firbaselogin.activity

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.firbaselogin.StudentModelClass
import com.example.firbaselogin.databinding.ActivityHomeScreenBinding
import com.example.firbaselogin.databinding.SelecteImageDialogBinding
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*
import kotlin.collections.ArrayList

class HomeScreenActivity : AppCompatActivity() {

    lateinit var homeScreenBinding: ActivityHomeScreenBinding

    lateinit var firebaseDatabase: FirebaseDatabase

    var PICK_IMAGE_REQUEST = 100
    var CAMERA_REQUEST = 200

    lateinit var storageReference: StorageReference

    lateinit var filePath: Uri
    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    var studentList: ArrayList<StudentModelClass> = ArrayList()

     var image:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeScreenBinding=ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(homeScreenBinding.root)

        initView()

        signOut()
    }

    private fun signOut() {
        // Initialize firebase auth
//        firebaseAuth = FirebaseAuth.getInstance()
        // Firebase sign out
//        firebaseAuth.signOut()

        // Google sign out
        homeScreenBinding.btnGoogleLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut();
            LoginManager.getInstance().logOut();
            googleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
//
//        mainBinding.btnGoogleLogout.setOnClickListener { view ->
//            // Sign out from google
//            googleSignInClient.signOut().addOnCompleteListener { task ->
//                // Check condition
//                if (task.isSuccessful) {
//                    // When task is successful sign out from firebase
//                    firebaseAuth.signOut()
//                    // Display Toast
//                    Toast.makeText(
//                        applicationContext,
//                        "Logout successful",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    // Finish activity
//                    finish()
//                }
//            }
//        }
    }

    private fun initView() {
        var sharedPreferences = getSharedPreferences("MySharePref", MODE_PRIVATE)

        firebaseDatabase = FirebaseDatabase.getInstance()
        // get the Firebase  storage reference

        storageReference = FirebaseStorage.getInstance().reference

        homeScreenBinding.btnUploadImage.setOnClickListener {

            uploadImage()
        }
        homeScreenBinding.btnInsertRecord.setOnClickListener {

            var name = homeScreenBinding.edtName.text.toString()
            var email = homeScreenBinding.edtEmail.text.toString()
            var mobile = homeScreenBinding.edtMobile.text.toString()
            var address = homeScreenBinding.edtAddress.text.toString()
            var key = firebaseDatabase.reference.child("StudentTb").push().key ?: ""
            var data = StudentModelClass(key, name, email, mobile, address,image)
            Log.e("TAG", "initView:Model "+image )

            if (name.isEmpty()) {
                Toast.makeText(this, "please Enter Name", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, "please Enter Email", Toast.LENGTH_SHORT).show()
            } else if (mobile.isEmpty()) {
                Toast.makeText(this, "please Enter Mobile", Toast.LENGTH_SHORT).show()
            } else if (address.isEmpty()) {
                Toast.makeText(this, "please Enter Address", Toast.LENGTH_SHORT).show()
            } else {
                firebaseDatabase.reference.child("StudentTb").child(key).setValue(data)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener {
                        Log.e("TAG", "initView: " + it.message)
                    }
                homeScreenBinding.edtName.setText("").toString()
                homeScreenBinding.edtEmail.setText("").toString()
                homeScreenBinding.edtMobile.setText("").toString()
                homeScreenBinding.edtAddress.setText("").toString()

            }

        }
        homeScreenBinding.btnDisplayRecord.setOnClickListener {

            var i = Intent(this, DataDisplayActivity::class.java)
            startActivity(i)
        }
        homeScreenBinding.btnSelectImage.setOnClickListener {

            selectImage()
        }

    }


    private fun selectImage() {
        // Defining Implicit Intent to mobile gallery
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Image from here..."),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            filePath = data?.data!!
            homeScreenBinding.imgShow.setImageURI(filePath)
        }

    }


    // UploadImage method
    private fun uploadImage() {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            // Defining the child of storageReference
            val ref = storageReference
                .child(
                    "images/"
                            + UUID.randomUUID().toString()
                )


            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath).addOnCompleteListener{

//                it.result.uploadSessionUri

                ref.downloadUrl.addOnSuccessListener {

                    image=it.toString()
                    Log.e("TAG", "uploadImage: "+image)
                }
            }
                .addOnSuccessListener { // Image uploaded successfully
                    // Dismiss dialog
                    progressDialog.dismiss()
                    Toast.makeText(this, "Image Uploaded!!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e -> // Error, Image not uploaded
                    progressDialog.dismiss()
                    Toast.makeText(this, "Failed " + e.message, Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnProgressListener { taskSnapshot ->

                    // Progress Listener for loading
                    // percentage on the dialog box
                    val progress =
                        (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount)
                    progressDialog.setMessage(
                        "Uploaded " + progress.toInt() + "%"
                    )
                }
        }
    }

}