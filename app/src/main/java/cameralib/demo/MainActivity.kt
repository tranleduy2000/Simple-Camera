package cameralib.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cameralib.CameraLibConfiguration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        startActivity(
            CameraLibConfiguration(outputFolder = filesDir)
                .toIntent(this)
        )
    }
}
