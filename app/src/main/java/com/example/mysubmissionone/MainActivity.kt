package com.example.mysubmissionone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mysubmissionone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listFoods = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listFoods.addAll(getListFoods())
        binding.rvFoods.setHasFixedSize(true)
        binding.rvFoods.adapter = FoodAdapter(listFoods)

        binding.topAppBar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_about) {
                val aboutActivity = Intent(this, AboutActivity::class.java)
                startActivity(aboutActivity)
                true
            } else {
                false
            }
        }
    }

    private fun getListFoods(): ArrayList<Food> {
        with(resources) {
            val image = obtainTypedArray(R.array.food_image)
            val name = getStringArray(R.array.food_name)
            val location = getStringArray(R.array.food_location)
            val description = getStringArray(R.array.food_description)
            val listFoods = ArrayList<Food>()

            for (i in name.indices) {
                val food = Food(image.getResourceId(i, -1), name[i], location[i], description[i])
                listFoods.add(food)
            }

            image.recycle()
            return listFoods
        }
    }
}
