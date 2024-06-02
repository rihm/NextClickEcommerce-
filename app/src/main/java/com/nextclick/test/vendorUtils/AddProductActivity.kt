package com.nextclick.test.vendorUtils

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nextclick.test.R
import com.nextclick.test.databinding.ActivityAddProductBinding
import com.nextclick.test.db.NextclickDatabase
import com.nextclick.test.db.Product
import com.nextclick.test.repository.ProductRpository
import com.nextclick.test.viewModel.ProductViewModel
import com.nextclick.test.viewModel.ProductViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException


class AddProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddProductBinding
    lateinit var productViewModel: ProductViewModel
    lateinit var image:ByteArray
    lateinit var selectedImageUri:Uri
    var bitmap: Bitmap? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_product)

        val dao = NextclickDatabase.getDatabase(applicationContext).productDao()
        val productRpository = ProductRpository(dao)

        productViewModel =
            ViewModelProvider(
                this,
                ProductViewModelFactory(productRpository)
            ).get(ProductViewModel::class.java)

        val product_name = binding.editProName.text
        val product_rate = binding.editProRate.text
        val product_desc = binding.editProDesciption.text

        image = byteArrayOf()

        binding.btnSave.setOnClickListener(View.OnClickListener {
            if(bitmap!=null){
                val product = Product(
                    0,
                    product_name.toString(),
                    product_rate.toString(),
                    product_desc.toString(),
                    convertBitmapToByteArray(bitmap!!)
                );
                productViewModel.insertProduct(product)
                Toast.makeText(
                    this@AddProductActivity,
                    "Product added ..." + image.toString(),
                    Toast.LENGTH_LONG
                ).show()

                finish()
            }else{
                Toast.makeText(
                    this@AddProductActivity,
                    "Select Image" + image.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }


        })



        binding.uploadImage.setOnClickListener(View.OnClickListener {
//            photoPickerLauncher.launch(
//                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//            )
            takeImage()

        })




    }

    fun takeImage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "select a picture"), 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK)
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data?.data!!)
            binding.imgProduct.setImageBitmap(bitmap)        }
    }


    fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        var baos: ByteArrayOutputStream? = null
        return try {
            baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            baos.toByteArray()
        } finally {
            if (baos != null) {
                try {
                    baos.close()
                } catch (e: IOException) {
                    Log.e(
                        AddProductActivity::class.java.getSimpleName(),
                        "ByteArrayOutputStream was not closed"
                    )
                }
            }
        }
    }


}

