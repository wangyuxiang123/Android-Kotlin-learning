package com.example.secondactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

val OBJECT_KEY = "key"
val NAME_KEY = "name"
val AGE_KEY = "age"
val REQUEST_CODE = 100
val MESSAGE_KEY = "message"

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val sendMessage: Button = findViewById(R.id.button2)
        textView = findViewById(R.id.textView3)
        val sendOtherMessage: Button = findViewById(R.id.button4)

        button.setOnClickListener() {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        sendMessage.setOnClickListener() {
            val intent = Intent(this, SecondActivity::class.java)
            val bundle = Bundle()
            putMessageToBundle(bundle)
            putObjectToBundle(bundle)

            intent.putExtras(bundle)
            startActivity(intent)
        }


        // 注册启动器回调
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            // 检查结果码
            if (it.resultCode == RESULT_OK) {
                textView.text = it.data?.getStringExtra(MESSAGE_KEY) ?: "无"
            } else {
                textView.text = "用户撤销了操作"
            }
        }

        sendOtherMessage.setOnClickListener() {
            val intent = Intent(this, ReceiveFromActivity::class.java)
            
            launcher.launch(intent)
        }

    }


    // 两种方式
    // 1保存对象
    fun putObjectToBundle(bundle: Bundle) {
        var user = User("李四", 45)
        bundle.putParcelable(OBJECT_KEY, user)
    }

    // 2
    fun putMessageToBundle(bundle: Bundle) {
        bundle.putString(NAME_KEY, "张三")
        bundle.putInt(AGE_KEY, 22)
    }


}