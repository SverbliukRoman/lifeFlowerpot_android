package com.sverbusoft.lifeflowerpot.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sverbusoft.lifeflowerpot.R
import com.sverbusoft.lifeflowerpot.databinding.ActivityLoginBinding
import com.sverbusoft.lifeflowerpot.ui.activity.BaseActivity
import com.sverbusoft.lifeflowerpot.ui.activity.signUp.SignUpActivity

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding;

    val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.viewModel = viewModel;
        binding.executePendingBindings();
        supportActionBar!!.hide()

        viewModel.showProgressBar.observe(this, Observer { t ->
            showProgressBar(t)
        })

        viewModel.showToast.observe(this, Observer {
            showLongToast(it) }
        )

        viewModel.startActivity.observe(this, Observer {
            startActivity(it.first, it.second)
        })

        binding.btnGoToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
