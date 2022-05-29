package com.example.a2work

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a2work.adapters.ProjetoAdapter
import com.example.a2work.data.profile.models.Projeto
import com.example.a2work.databinding.ActivityUploadBinding
import com.example.a2work.profile.SlideActivity
import com.example.a2work.rest.Rest
import com.example.a2work.services.ProjectService
import kotlinx.android.synthetic.main.activity_upload.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class UploadActivity : AppCompatActivity() {

    private val retrofitProjeto = Rest.getInstance().create(ProjectService::class.java)

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

        addProject.setOnClickListener {
            uploadProject()
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


    private fun uploadProject(){

        val preferencesId: SharedPreferences = getSharedPreferences("id_user", Context.MODE_PRIVATE)
        val getIdActiveUser = preferencesId.getString("id_user", "")

        val newProject = Projeto(
            idProjeto = null,
            tituloProjeto = etTitleProject.text.toString(),
            descricaoProjeto = etDescriptionTitle.text.toString(),
            imagemProjeto = stringImage!!,
            nomeUsuario = null,
            primeiraLetraNome = null,
            dataHoraProjeto = null,
            totalVisualizacoesProjeto = null,
            totalCurtidasProjeto = null,
//            primeiraPergunta = false,
//            segundaPergunta = false,
//            terceiraPergunta = false,
            fkUsuario = getIdActiveUser?.toInt()
        )

        if (getIdActiveUser != null) {
            retrofitProjeto.uploadProject(getIdActiveUser, newProject).enqueue(
                object : Callback<Void>{
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            val builder = AlertDialog.Builder(baseContext)
                            builder.setTitle("2WORK")
                            builder.setMessage("Projeto publicado com sucesso!")
                            builder.setPositiveButton("OK") { _: DialogInterface, _: Int ->
                                finish()
                            }
                            builder.show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(
                            baseContext,
                            "Não deu pra cadastrar um projeto",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            )
        }
    }

}