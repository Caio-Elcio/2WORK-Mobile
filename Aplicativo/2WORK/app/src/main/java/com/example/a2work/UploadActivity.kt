package com.example.a2work

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.a2work.profile.SlideActivity
import kotlinx.android.synthetic.main.activity_upload.*
import java.io.ByteArrayOutputStream

class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        initPermissions()
        openGalleryForImage.setOnClickListener {
            openGalleryForImage()
        }

        bottom_navigation_upload.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_home -> {
                    startActivity(Intent(this, FeedActivity::class.java))
                }
                R.id.navi_upload -> {
                }
                R.id.navi_projeto -> {
                    startActivity(Intent(this, ProjetosActivity::class.java))
                }
                R.id.navi_conta -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
                R.id.navi_planos -> {
                    startActivity(Intent(this, SlideActivity::class.java))
                }
            }
            true
        }
    }

    private var check = false
    var stringImage: String? = null

    private val startImageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val uriImage = result.data?.data
            if (uriImage != null) {
                stringImage = encode(uriImage)
            }

            stringImage?.let { decode(it) }
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startImageResult.launch(intent)
    }

    private fun initPermissions(){
        if(!getPermission()) setPermission()
        else check = true
    }

    private fun getPermission(): Boolean =
        (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)

    private fun setPermission(){
        val permissionsList = listOf<String>(
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(this, permissionsList.toTypedArray(), PERMISSION_CODE)
    }

    private fun errorPermission(){
        Toast.makeText(this,"Sem permissão", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    errorPermission()
                } else {
                    check = true
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    companion object {
        private const val PERMISSION_CODE = 1
    }


    fun encode(imageUri: Uri): String {
        val input = contentResolver.openInputStream(imageUri)
        val image = BitmapFactory.decodeStream(input , null, null)
        val baos = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes: ByteArray = baos.toByteArray()

        val imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)

        return imageString
    }

    fun decode(imageString: String) {
        val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    fun upload(view: View) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("2WORK")
        builder.setMessage("Projeto publicado com sucesso!")
        builder.setPositiveButton("OK", { dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }
}