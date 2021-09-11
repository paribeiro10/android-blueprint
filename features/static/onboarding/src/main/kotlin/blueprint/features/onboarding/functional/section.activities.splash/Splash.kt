package blueprint.features.onboarding.functional.section.activities.splash

import android.os.Bundle
import android.widget.Toast
import blueprint.features.onboarding.R
import blueprint.libraries.architecture_components.functional.BaseActivity
import blueprint.libraries.architecture_components.functional.TestObject
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint class Splash: BaseActivity() {

    @Inject lateinit var anObject: TestObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this@Splash, "The string is $aString", Toast.LENGTH_SHORT).show()
        Toast.makeText(this@Splash, "The objects value is ${anObject.cenas}", Toast.LENGTH_SHORT).show()
    }

}
