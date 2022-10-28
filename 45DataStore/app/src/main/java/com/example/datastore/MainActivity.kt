package com.example.datastore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val ITEM_KEY = intPreferencesKey("my_int_number")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding) {
            btnLoad.setOnClickListener() {
                MainScope().launch {
                    val num = dataStore.data.map {
                        it[ITEM_KEY]
                    }.firstOrNull()
                    textView.text = num?.toString() ?: "获取数据失败"
                }
            }
            btnSave.setOnClickListener() {
                val ranValue = Random.nextInt(1, 100)
                CoroutineScope(Dispatchers.IO).launch {
                    dataStore.edit {
                        it[ITEM_KEY] = ranValue
                    }
                }
                textView.text = "将 $ranValue 写入"
            }

        }
    }
}