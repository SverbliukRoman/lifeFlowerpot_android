package com.sverbusoft.lifeflowerpot.ui.activity.signUp

import android.os.Bundle
import androidx.databinding.DataBindingUtil
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
        supportActionBar!!.hide()
        binding.executePendingBindings();
    }
}
