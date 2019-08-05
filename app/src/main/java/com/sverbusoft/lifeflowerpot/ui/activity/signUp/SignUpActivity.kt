package com.sverbusoft.lifeflowerpot.ui.activity.signUp

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sverbusoft.lifeflowerpot.R
import com.sverbusoft.lifeflowerpot.databinding.ActivitySignUpBinding
import com.sverbusoft.lifeflowerpot.ui.activity.BaseActivity

class SignUpActivity : BaseActivity() {
    lateinit var binding: ActivitySignUpBinding

    val viewModel: SignUpViewModel by lazy {
        ViewModelProviders.of(this).get(SignUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.viewModel = viewModel
        binding.executePendingBindings();
        supportActionBar!!.hide()

        viewModel.showProgressBar.observe(this, Observer {
            showProgressBar(it)
        })

        viewModel.showToast.observe(this, Observer {
            showLongToast(it)
        })

        viewModel.startActivity.observe(this, Observer {
            startActivity(it.first, it.second)
            finish()
        })
    }
}
