package uz.texnopos.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.texnopos.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        viewBinding.btnSave.setOnClickListener {
            val etName = viewBinding.etName.text.toString()
            val etAge = viewBinding.etAge.text.toString().toInt()
            val isAdult = viewBinding.checkbox.isChecked

            editor.apply {
                putString("name", etName)
                putInt("age", etAge)
                putBoolean("isAdult", isAdult)
                apply()
            }
        }
        viewBinding.btnLoad.setOnClickListener {
            val name = sharedPref.getString("name", null)
            val age = sharedPref.getInt("age", 0)
            val isAdult = sharedPref.getBoolean("isAdult", false)

            viewBinding.etName.setText(name)
            viewBinding.etAge.setText(age.toString())
            viewBinding.checkbox.isChecked = isAdult
        }
    }
}