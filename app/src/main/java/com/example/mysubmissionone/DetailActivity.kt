package com.example.mysubmissionone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.mysubmissionone.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var food: Food? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        food = IntentCompat.getParcelableExtra(intent, EXTRA_FOOD, Food::class.java)

        with(binding) {
            food?.let {
                sivFood.setImageResource(it.image)
                tvName.text = it.name
                tvLocation.text = getString(R.string.location, it.location)
                tvDescription.text = getString(R.string.description, it.description)

                actionShare.setOnClickListener {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "${food?.name}\n${food?.location}\n${food?.description}"
                        )
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
            }
        }
    }

    companion object {
        const val EXTRA_FOOD = "extra_food"
    }
}
