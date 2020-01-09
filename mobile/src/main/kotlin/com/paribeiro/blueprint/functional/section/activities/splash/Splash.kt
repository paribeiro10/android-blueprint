package com.paribeiro.blueprint.functional.section.activities.splash

import com.paribeiro.blueprint.R
import com.paribeiro.blueprint.functional.section.base.BaseActivity
import com.paribeiro.blueprint.databinding.ActivitySplashBinding

class Splash: BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun layoutToInflate() = R.layout.activity_splash

    override fun getViewModelClass() = SplashViewModel::class.java

    override fun doOnCreated() { /* TODO */ }

}
