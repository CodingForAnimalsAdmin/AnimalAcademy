package com.codingforanimals.animalacademy.contract.login

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation

interface LoginContract {

    interface View {

        interface First {
            fun getLogo(): android.view.View
            fun getLogoIn(): android.view.View
            fun hideRlUnsigned()
            fun animateRlUnsigned(animation: Animation)
        }
        interface Login {
            fun onCreateUserClickBuildAnimationOptions(): Bundle
            fun showProgress()
            fun hideProgress()
        }
        interface CreateUser {
            fun showProgressbar()
            fun hideProgressbar()
        }
        interface Welcome {
            fun bindTexts(title: String, subtitle: String)
            fun onBackPressed()
        }
    }

    interface Actions {


        interface First {
            fun onInitAnimate()
        }
        interface Login {
            fun createFacebookLoginIntent()
            fun onActivityResult(requestCode: Int, resultCode: Int)
            suspend fun signInIntent(email: String, password: String)
            fun goToCreateUserActivity()
        }
        interface CreateUser {
            fun createUserIntent(name: String, email: String, password: String, confPassword: String)
        }
        interface Welcome {
            suspend fun buildAndBindTexts()
            fun shouldExit(intent: Intent?)
            fun startMainActivity()
        }
    }
}