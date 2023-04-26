package com.example.retrofitapi.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofitapi.*
import com.example.retrofitapi.APIClient
import com.example.retrofitapi.adapter.ProductsAdapterClass
import com.example.retrofitapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var apiInterfaceM: APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        intiView()
    }


    private fun intiView() {

        mainBinding.imgSearch.setOnClickListener {
            var i = Intent(this, SearchActivity::class.java)
            startActivity(i)
        }
        val connectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            Toast.makeText(this@MainActivity, "Connection is available", Toast.LENGTH_LONG).show()

            apiInterfaceM = APIClient.getClient().create(APIInterface::class.java)
            apiInterfaceM.getAllProducts().enqueue(object : Callback<AllProductsResponse<Any?>> {
                override fun onResponse(
                    call: Call<AllProductsResponse<Any?>>,
                    response: Response<AllProductsResponse<Any?>>
                ) {
                    var productList = response.body()?.products
                    var adapterClass = ProductsAdapterClass(this@MainActivity, productList)
                    {
                        var i = Intent(this@MainActivity, ItemDisplayActivity::class.java)
                        i.putExtra("id", it.id)
                        Log.e("main_Id", "item_Id: " + it.id)
                        startActivity(i)
                    }
                    val manager = GridLayoutManager(this@MainActivity, 2)
                    mainBinding.rcvProduct.layoutManager = manager
                    mainBinding.rcvProduct.adapter = adapterClass
                }

                override fun onFailure(call: Call<AllProductsResponse<Any?>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        } else {
            Toast.makeText(
                this@MainActivity,
                "Please Check Your Internet Connection  ",
                Toast.LENGTH_LONG
            ).show()
        }



        setSupportActionBar(mainBinding.toolbar)  //toolbar set


        supportActionBar?.setDisplayShowTitleEnabled(false)
        mainBinding.toolbar.overflowIcon?.setTint(Color.BLACK)  //option manu 3 dot's color change
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //create option menu
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }
//
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {

                val i = Intent(this, ProfileActivity::class.java)
                startActivity(i)

                Toast.makeText(this, "Profile ", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.logout -> {
                Toast.makeText(this, "Logout ", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}