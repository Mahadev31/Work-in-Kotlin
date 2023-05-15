package com.example.firbaselogin.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firbaselogin.databinding.ActivityMainBinding
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var firebaseAuth: FirebaseAuth

    lateinit var googleSignInClient: GoogleSignInClient

    lateinit var callbackManager: CallbackManager
    var Email="email"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {
//
        var sharedPreferences = getSharedPreferences("MySharePref", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isLogin", false) == true) {
            var intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
// Initialize Firebase Auth
        auth = Firebase.auth
        mainBinding.btnLogin.setOnClickListener {
            var email = mainBinding.edtEmail.text.toString()
            var password = mainBinding.edtPassword.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "email value is empty. please fill email ", Toast.LENGTH_SHORT)
                    .show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "password value is empty. please fill password ", Toast.LENGTH_SHORT)
                    .show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
                        var i = Intent(this, HomeScreenActivity::class.java)

                        var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                        myEdit.putBoolean("isLogin", true)
                        myEdit.putString("email", email)
//                        myEdit.putString("firstName", it.firstName)
//            myEdit.putString("lastName", lastName)
                        myEdit.commit()

                        startActivity(i)

                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
        mainBinding.txtCreateAccountPage.setOnClickListener {
            var i=Intent(this, CreateAccountActivity::class.java)
            startActivity(i)
        }

        // Initialize sign in options the client-id is copied form google-services.json file
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("167582794627-2372p5q5ih8ukjl0i6qnesin8i751j49.apps.googleusercontent.com")
            .requestEmail()
            .build()


        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        mainBinding.btnGoogleLogin.setOnClickListener { // Initialize sign in intent
            val intent: Intent = googleSignInClient.signInIntent
            // Start activity for result
            startActivityForResult(intent, 100)
        }

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        // Initialize firebase user
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        if (firebaseUser != null) {
            // When user already sign in redirect to profile activity
            startActivity(
                Intent(
                    this,
                    HomeScreenActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }


//facebook Login
        callbackManager = CallbackManager.Factory.create()
        mainBinding.btnLoginButton.setReadPermissions(Arrays.asList(Email))

        // Callback registration
        mainBinding.btnLoginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {

                }

                override fun onError(error: FacebookException) {

                }

                override fun onSuccess(result: LoginResult) {

                    var request = GraphRequest.newMeRequest(result.accessToken,
                        object : GraphRequest.GraphJSONObjectCallback {
                            override fun onCompleted(obj: JSONObject?, response: GraphResponse?) {

                                var email = obj?.getString("email")

                                Log.e("TAG", "onCompleted:"+email)

                            }
                        })

                    val parameters=Bundle()
                    parameters.putString("fields","id,name,email,gender,birthday")
                    request.parameters=parameters
                    request.executeAsync()



                    val credential: AuthCredential = FacebookAuthProvider.getCredential(result.accessToken.token)
                    Log.e("Token", "onSuccess: "+result.accessToken.token)
                    // Check credential
                    firebaseAuth.signInWithCredential(credential)
                        .addOnCompleteListener{
                            // Check condition
                            if (it.isSuccessful) {
                                // When task is successful redirect to profile activity
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        HomeScreenActivity::class.java
                                    ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                )
                                // Display Toast
                                Toast.makeText(this@MainActivity, "Firebase authentication successful", Toast.LENGTH_SHORT).show()
                            } else {
                                // When task is unsuccessful display Toast
                                Toast.makeText(this@MainActivity, "Authentication Failed :", Toast.LENGTH_SHORT).show()
                            }
                        }

                }

            })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check condition
        if (requestCode == 100) {
            // When request code is equal to 100 initialize task
            val signInAccountTask: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)

            // check condition
            if (signInAccountTask.isSuccessful) {
                // When google sign in successful initialize string
                val s = "Google sign in successful"
                // Display Toast
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
                // Initialize sign in account
                try {
                    // Initialize sign in account
                    val googleSignInAccount = signInAccountTask.getResult(ApiException::class.java)
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null initialize auth credential
                        val authCredential: AuthCredential = GoogleAuthProvider.getCredential(
                            googleSignInAccount.idToken, null
                        )
                        // Check credential
                        firebaseAuth.signInWithCredential(authCredential)
                            .addOnCompleteListener(this) { task ->
                                // Check condition
                                if (task.isSuccessful) {
                                    // When task is successful redirect to profile activity
                                    startActivity(
                                        Intent(
                                            this,
                                            HomeScreenActivity::class.java
                                        ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    )
                                    // Display Toast
                                    Toast.makeText(this, "Firebase authentication successful", Toast.LENGTH_SHORT).show()
                                } else {
                                    // When task is unsuccessful display Toast
                                    Toast.makeText(this, "Authentication Failed :", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                } catch (e: ApiException) {
                    e.printStackTrace()
                }
            }
        }
    }

}