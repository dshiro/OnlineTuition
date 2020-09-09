package com.example.onlinetuition

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_bank_in.*
import java.io.IOException

/*class BankIn : AppCompatActivity(){*/
    class BankIn : AppCompatActivity(), View.OnClickListener {

    private var filePath: Uri? = null

    private val PICK_IMAGE_REQUEST = 1234

    internal var storage:FirebaseStorage?=null
    internal var storageReference:StorageReference?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_in)

        //Init Firebase
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        // setup button
        imageButton.setOnClickListener(this)
        btnUpload.setOnClickListener(this)

       /* //button click
        imageButton.setOnClickListener{
            //check runtime permission
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system os is < marshmallow
                pickImageFromGallery();
            }
        }*/
    }




 /*   override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }*/


/*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            image_view.setImageURI(data?.data)
        }
    }*/


/////////////////////////////////////////////////////////////////////////////second function//////////////////////
    override fun onClick(p0: View) {
       if(p0 === imageButton)
           showFileChoose()
        else if (p0 === btnUpload)
           uploadFile()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST &&
                resultCode == Activity.RESULT_OK &&
                data != null && data.data != null)
        {
            if(data?.data!= null){
              val selectImage: Uri? = data.data
                Log.i("selectImage", "onActivityResult: file path : " + selectImage);
            }

            filePath = data.data;
            try{
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filePath)
                image_view!!.setImageBitmap(bitmap)
            }catch (e:IOException)
            {
                e.printStackTrace()
            }
        }
    }

    private fun uploadFile() {
        if(filePath != null)
        {
/*            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading..")
            progressDialog.show()*/

            val imageRef = storageReference!!.child(("images/testing2"))

            Log.i("selectImage", "imageRef" + imageRef.toString());
            imageRef.putFile(filePath!!)

                .addOnSuccessListener {
       /*             progressDialog.dismiss()*/
                    Toast.makeText(applicationContext,"File Uploaded", Toast.LENGTH_SHORT).show()
                }
                 .addOnFailureListener {


                     /*  progressDialog.dismiss()*/
                     Log.i("selectImage", "Upload Failed");

                 }
/*             .addOnProgressListener {taskSnapshot->
             val progress = 100.0 * taskSnapshot.bytesTransferred/taskSnapShot.totalByteCount
            progressDialog.setMessage("Uploaded"+progress.toInt() + "%...")
         }*/
        }
    }

    private fun showFileChoose() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST)

    }
}
