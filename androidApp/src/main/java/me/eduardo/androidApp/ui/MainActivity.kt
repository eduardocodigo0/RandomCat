package me.eduardo.androidApp.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.eduardo.androidApp.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
            .create(MainViewModel::class.java)
        lifecycle.addObserver(vm)

        lifecycleScope.launch {
            vm.baseVM.catIMG.collect { url ->
                if (url.isNotEmpty() && url.isNotBlank()) {
                    Picasso.get().load(url).into(binding.ivCat)
                    binding.pbLoading.visibility = View.GONE
                    binding.ivCat.visibility = View.VISIBLE
                    binding.btShare.visibility = View.VISIBLE
                    binding.btGetCat.isActivated = true
                }
            }
        }

        lifecycleScope.launch {
            vm.baseVM.error.collect { error ->
                if (error) {
                    Toast.makeText(applicationContext, "We had an error! :(", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.btGetCat.setOnClickListener {
            binding.pbLoading.visibility = View.VISIBLE
            binding.ivCat.visibility = View.GONE
            binding.btShare.visibility = View.GONE
            binding.btGetCat.isActivated = false
            vm.getCat()
        }

        binding.btShare.setOnClickListener {

            val image: Bitmap? = Bitmap.createBitmap(binding.ivCat.drawable.toBitmap())
            image?.let { img ->

                val share = Intent(Intent.ACTION_SEND)
                share.type = "image/jpeg"
                share.putExtra(Intent.EXTRA_STREAM, getImageURI(this, img))
                startActivity(Intent.createChooser(share, "Share Image"))

            }
        }
    }


    private fun getImageURI(inContext: Context, inImage: Bitmap): Uri? {

        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Cat", null)
        return Uri.parse(path)

    }

    override fun onDestroy() {

        _binding = null
        super.onDestroy()
    }

}
