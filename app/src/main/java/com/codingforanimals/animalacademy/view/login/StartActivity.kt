package com.codingforanimals.animalacademy.view.login

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat
import androidx.lifecycle.lifecycleScope
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.login.LoginContract
import com.codingforanimals.animalacademy.presenter.login.StartPresenter
import com.codingforanimals.animalacademy.helpers.utils.Utils
import com.codingforanimals.animalacademy.model.data.dataholders.UserDataHolder
import kotlinx.android.synthetic.main.activity_create_user.logo
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.coroutines.launch
import android.util.Pair as UtilPair

class StartActivity : AppCompatActivity(), LoginContract.View.Login {

    private val presenter = StartPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        TextViewCompat.setAutoSizeTextTypeWithDefaults(textView3, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)

        logo.setOnTouchListener(Utils.getResizerOnTouchListener(logo))
        logo.setOnClickListener { }


        fb_login_btn.setOnTouchListener(Utils.getResizerOnTouchListener(fb_login_btn))
        fb_login_btn.setOnClickListener { presenter.createFacebookLoginIntent() }

        google_login_btn.setOnTouchListener(Utils.getResizerOnTouchListener(google_login_btn))
        google_login_btn.setOnClickListener { }

        create_btn.setOnClickListener { presenter.goToCreateUserActivity() }

        login_btn.setOnTouchListener(Utils.getResizerOnTouchListener(login_btn))
        login_btn.setOnClickListener {
            lifecycleScope.launch {
                presenter.signInIntent(
                    email_txt.text.toString().trim(),
                    password_txt.text.toString().trim()
                )
                UserDataHolder.getUserData()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode)
    }

    override fun onBackPressed() {
        this.moveTaskToBack(true)
    }

    override fun onCreateUserClickBuildAnimationOptions(): Bundle {
        val p1 = UtilPair.create(create_btn as View, "image_slides_down")
        val p2 = UtilPair.create(logo as View, "logo")
        return ActivityOptions.makeSceneTransitionAnimation(this, p1, p2).toBundle()
    }

    override fun showProgress() {
        start_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        start_progress.visibility = View.INVISIBLE
    }

}
