package com.example.mychartapp.activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mychartapp.R
import com.example.mychartapp.adapter.MessageAdapterClass
import com.example.mychartapp.model.MessageModelClass
import com.example.mychartapp.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {

    lateinit var chatBinding: ActivityChatBinding
    lateinit var adapter: MessageAdapterClass
    lateinit var messageList: ArrayList<MessageModelClass>

    lateinit var mDbRef: DatabaseReference

    var receiverRoom: String? = null
    var senderRoom: String? = null


    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chatBinding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(chatBinding.root)

        initView()
    }

    private fun initView() {

chatBinding.imgBack.setOnClickListener{
    onBackPressed()
}
        var firstName = intent.getStringExtra("firstName")
        var lastName = intent.getStringExtra("lastName")
        var receiverUid = intent.getStringExtra("uid")

        mDbRef = FirebaseDatabase.getInstance().getReference()

        var senderUid = FirebaseAuth.getInstance().currentUser?.uid
        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid


        chatBinding.txtUserFirstName.text=firstName
        chatBinding.txtUserLastName.text=lastName

        messageList = ArrayList()
        adapter = MessageAdapterClass(this, messageList)

        chatBinding.chatRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        chatBinding.chatRecyclerView.adapter = adapter

        //logic for adding data to recyclerView
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()
                    for (postSnapshot in snapshot.children) {
                        val message = postSnapshot.getValue(MessageModelClass::class.java)
                        messageList.add(message!!)
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        //adding the message for database
        chatBinding.imgSend.setOnClickListener {
            var message = chatBinding.edtMessageBox.text.toString()
            var messageObject = MessageModelClass(message, senderUid)

            mDbRef.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                    notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    setuoNotfication(message,senderUid)
                }

            chatBinding.edtMessageBox.setText("")
        }
    }

    private fun setuoNotfication(message:String,senderUid:String?) {
        val intent = Intent(this, ChatActivity::class.java)

        // FLAG_UPDATE_CURRENT specifies that if a previous
        // PendingIntent already exists, then the current one
        // will update it with the latest intent
        // 0 is the request code, using it later with the
        // same method again will get back the same pending
        // intent for future reference
        // intent passed here is to our afterNotification class
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)

        // RemoteViews are used to use the content of
        // some different layout apart from the current activity layout
        val contentView = RemoteViews(packageName, R.layout.notification_item)

        // checking if android version is greater than oreo(API 26) or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
//                .setContent(contentView)
                .setContentText(senderUid)
                .setSubText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        } else {

            builder = Notification.Builder(this)
                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())
    }
}