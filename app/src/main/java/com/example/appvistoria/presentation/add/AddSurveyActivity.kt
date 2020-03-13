package com.example.appvistoria.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appvistoria.R
import kotlinx.android.synthetic.main.activity_add_survey.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


private var imageview: ImageView? = null

private val GALLERY = 1
private val CAMERA = 2
val PERMISSION_CODE = 100


class AddSurveyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_survey)

        btn_salvar_vistoria.setOnClickListener {
            val listItems = arrayOf("Aprovada", "Pendente", "Reprovada")
            val mBuilder = AlertDialog.Builder(this@AddSurveyActivity)
            mBuilder.setTitle("Status da Vistoria")
            mBuilder.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                val txtView: String
                txtView = listItems[i]
                Toast.makeText(this, "$txtView", Toast.LENGTH_LONG).show()
                //dialogInterface.dismiss()
            }
            mBuilder.setPositiveButton("Ok") { dialog, which ->
                // Do something when click the positive button
                dialog.cancel()
                Toast.makeText(this, "Vistoria Salva com Sucesso!", Toast.LENGTH_LONG).show()
                finish()

                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)

            }
            // Set the neutral/cancel button click listener
            mBuilder.setNeutralButton("Cancelar") { dialog, which ->
                // Do something when click the neutral button
                dialog.cancel()
            }

            val mDialog = mBuilder.create()
            mDialog.show()

        }

        img_chassi_veiculo!!.setOnClickListener {
            imageview = img_chassi_veiculo
            checkPermission(Manifest.permission.CAMERA, PERMISSION_CODE)
        }
        img_motor_veiculo!!.setOnClickListener {
            imageview = img_motor_veiculo
            checkPermission(
                Manifest.permission.CAMERA, PERMISSION_CODE
            )
        }
        img_frente_veiculo!!.setOnClickListener {
            imageview = img_frente_veiculo
            checkPermission(
                Manifest.permission.CAMERA, PERMISSION_CODE
            )
        }
        img_traseira_veiculo!!.setOnClickListener {
            imageview = img_traseira_veiculo
            checkPermission(
                Manifest.permission.CAMERA, PERMISSION_CODE
            )
        }
        img_odometro_veiculo!!.setOnClickListener {
            imageview = img_odometro_veiculo
            checkPermission(
                Manifest.permission.CAMERA, PERMISSION_CODE
            )
        }

    }

    // Function to check and request permission.
    fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@AddSurveyActivity, permission)
            == PackageManager.PERMISSION_DENIED
        ) { // Requesting the permission
            ActivityCompat.requestPermissions(
                this@AddSurveyActivity, arrayOf(permission),
                requestCode
            )
        } else {
            showPictureDialog()

            Toast.makeText(
                this@AddSurveyActivity,
                "Permission already granted",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super
            .onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
            )
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                showPictureDialog()

//                Toast.makeText(
//                    this@AddSurveyActivity,
//                    "Camera Permission Granted",
//                    Toast.LENGTH_SHORT
//
//                )
//                    .show()
            } else {
//                Toast.makeText(
//                    this@AddSurveyActivity,
//                    "Camera Permission Denied",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()
            }
        }
    }

    private fun showPictureDialog() {
        val pictureDialog = androidx.appcompat.app.AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )

        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        /* if (resultCode == this.RESULT_CANCELED)
         {
         return
         }*/
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data!!.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val path = saveImage(bitmap)
                    Toast.makeText(this@AddSurveyActivity, "Image Saved!", Toast.LENGTH_SHORT)
                        .show()
                    imageview!!.scaleType = ImageView.ScaleType.CENTER_CROP
                    imageview!!.setImageBitmap(bitmap)

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@AddSurveyActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        } else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            imageview!!.scaleType = ImageView.ScaleType.CENTER_CROP
            imageview!!.setImageBitmap(thumbnail)
            saveImage(thumbnail)
            Toast.makeText(this@AddSurveyActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveImage(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY
        )
        // have the object build the directory structure, if needed.
        Log.d("fee", wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists()) {

            wallpaperDirectory.mkdirs()
        }

        try {
            Log.d("heel", wallpaperDirectory.toString())
            val f = File(
                wallpaperDirectory, ((Calendar.getInstance()
                    .getTimeInMillis()).toString() + ".jpg")
            )
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(
                this,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null
            )
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {
        private val IMAGE_DIRECTORY = "/demonuts"
    }


}
