package blueprint.libraries.architecture_components.functional

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint abstract class BaseActivity: AppCompatActivity() {

    @Inject lateinit var aString: String

}
