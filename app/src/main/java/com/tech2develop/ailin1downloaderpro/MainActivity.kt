package com.tech2develop.ailin1downloaderpro

import android.app.AlertDialog
import android.app.Dialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    lateinit var builder : AlertDialog.Builder
    lateinit var mDialogView : View
    val CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun btnFromUrl(view: android.view.View) {
       mDialogView = LayoutInflater.from(this).inflate(R.layout.url_entry_layout,null)

        builder = AlertDialog.Builder(this).setView(mDialogView)
        builder.show()
    }

    fun btnDismisDialog(view: android.view.View) {
    }

    fun onDownload(view: android.view.View) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==  PackageManager.PERMISSION_DENIED){
                requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE),CODE)
            }else{
               startDownloading()
            }
        }else{
            startDownloading()
        }
    }

    private fun startDownloading() {
        var etUrl = mDialogView.findViewById<EditText>(R.id.etUrl)
        var url = etUrl.text.toString()
        if (url.isEmpty()){
            Toast.makeText(this,"Url is empty",Toast.LENGTH_SHORT).show()
        }else{

            var request = DownloadManager.Request(Uri.parse(url)).setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            var manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
            Toast.makeText(this,"Downloading...",Toast.LENGTH_SHORT).show()

        }
    }

    fun btnAllDownloads(view: android.view.View) {
        startActivity(Intent(this,Downloads::class.java))
    }
}