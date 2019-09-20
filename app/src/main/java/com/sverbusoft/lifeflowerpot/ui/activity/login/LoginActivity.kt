package com.sverbusoft.lifeflowerpot.ui.activity.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sverbusoft.lifeflowerpot.R
import com.sverbusoft.lifeflowerpot.databinding.ActivityLoginBinding
import com.sverbusoft.lifeflowerpot.ui.activity.BaseActivity
import com.sverbusoft.lifeflowerpot.ui.activity.main.MainActivity
import com.sverbusoft.lifeflowerpot.ui.activity.signUp.SignUpActivity
import io.reactivex.disposables.Disposable

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding;
    lateinit var disposableEmail: Disposable

    val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initObserver();
        viewModel.checkUserLogin()
        super.onCreate(savedInstanceState)
        viewModel.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.viewModel = viewModel;
        binding.executePendingBindings();

        initClickListener();
        initRxBinding();

        supportActionBar?.hide();
    }

    private fun initObserver(){
        viewModel.isLoading.observe(this, Observer { t ->
            showProgressBar(t)
        })

        viewModel.successLogin.observe(this, Observer {
            showLongToast("Login Success")
            startActivity(MainActivity::class.java, null)
        })

        viewModel.errorLogin.observe(this, Observer {
            showLongToast(it)
        })

    }

    private fun initClickListener(){
        binding.btnGoToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRxBinding(){
        disposableEmail = RxTextView.textChanges(binding.etEmailSignIn).subscribe {
            viewModel.saveEmail(it.toString())
        };
    }

    override fun onDestroy() {
        disposableEmail.dispose()
        super.onDestroy()
    }
}
