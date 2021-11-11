package com.example.notebook


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.notebook.databinding.ActivityMainBinding
import com.example.notebook.main.NoteFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val fragment = NoteFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()


    }


    fun changeFragment(item: Fragment,
                       bundles: HashMap<String, String>?){
        bundles?.let {
            val bndl = Bundle()
            for(z in bundles){
                bndl.putString(z.key, z.value)
            }
            item.arguments = bndl
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, item)
            .addToBackStack("item")
            .commit()
    }

}