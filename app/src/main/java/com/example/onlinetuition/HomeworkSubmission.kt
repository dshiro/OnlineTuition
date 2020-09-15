package com.example.onlinetuition

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_bank_in.*
import kotlinx.android.synthetic.main.activity_homework_submission.*
import java.io.IOException
import java.lang.StringBuilder
import java.util.*

class HomeworkSubmission() : AppCompatActivity() , View.OnClickListener  {


    private var filePath: Uri? = null

    private val PICK_DOCX_REQUEST = 1234

    internal var storage:FirebaseStorage?=null
    internal var storageReference:StorageReference?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework_submission)

        //Init Firebase
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        // setup button
        btnAddAttachment.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_DOCX_REQUEST &&
            resultCode == Activity.RESULT_OK &&
            data != null && data.data != null)
        {
            if(data?.data!= null){
                val selectFile: Uri? = data.data
                Log.i("selectFile", "onActivityResult: file path : $selectFile");
            }

            filePath = data.data;

        }
    }

    override fun onClick(p0: View?) {
        if(p0 === btnAddAttachment)
            showFileChoose()
        else if (p0 === btnSubmit)
            uploadFile()
    }

    private fun uploadFile() {
        if(filePath != null)
        {
            val fileName = UUID.randomUUID().toString()
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading..")
            progressDialog.show()

            val fileRef = storageReference!!.child(("homeworks/$fileName"))

            Log.i("selectFile", "fileRef$fileRef");
            fileRef.putFile(filePath!!)

                .addOnSuccessListener {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext,"File Uploaded!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {


                    progressDialog.dismiss()
                    Log.i("selectFile", "Upload Failed");

                }

        }
    }


    private fun showFileChoose() {
        val intent = Intent()
        intent.type = "application/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select File"),PICK_DOCX_REQUEST)
    }
}

