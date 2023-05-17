package com.example.mychartapp.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mychartapp.databinding.ActivityCreateGroupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateGroupActivity : AppCompatActivity() {

    lateinit var createGroupBinding: ActivityCreateGroupBinding

    lateinit var mDbRef: DatabaseReference
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createGroupBinding = ActivityCreateGroupBinding.inflate(layoutInflater)
        setContentView(createGroupBinding.root)
        mAuth = FirebaseAuth.getInstance()

        initView()
    }

    private fun initView() {

        createGroupBinding.btnCreateGroup.setOnClickListener {

            var progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Creating Group")
            var groupTitle = createGroupBinding.edtGroupTitle.text.toString()

            if (groupTitle.isEmpty()) {
                Toast.makeText(this, "Please Enter Group Title", Toast.LENGTH_SHORT).show()
            }

            progressDialog.show()

            var timestamp = "" + System.currentTimeMillis()

            var hashmap = HashMap<String, String>()
            hashmap.put("groupId", timestamp)
            hashmap.put("groupTitle", groupTitle)
            hashmap.put("timeTamp", timestamp)
            hashmap.put("createdBy", mAuth.currentUser?.uid!!)

            //create group
            mDbRef = FirebaseDatabase.getInstance().getReference("Groups")
            mDbRef.child(timestamp).setValue(hashmap)
                .addOnSuccessListener {
                    var addMemberHashmap=HashMap<String,String>()
                    addMemberHashmap.put("uid",mAuth.currentUser?.uid!!)
                    addMemberHashmap.put("role","creator")
                    addMemberHashmap.put("timeTamp",timestamp)

                    mDbRef.child(timestamp).child("Participants").child(mAuth.uid!!)
                        .setValue(addMemberHashmap).addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(this, "Group created ", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            progressDialog.dismiss()
                            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        }


                }
                .addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }
}