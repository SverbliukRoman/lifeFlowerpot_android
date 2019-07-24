package com.sverbusoft.lifeflowerpot.ui.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar = ProgressDialog(this)
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
    }

    protected fun showProgressBar(value: Boolean){
        if(value)
        progressBar.show()
        else
            progressBar.hide()
    }

    protected fun showLongToast(text: String){
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}