package mahmoud.moussa.myquranapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import mahmoud.moussa.myquranapplication.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            var intent=Intent(this,
                HomeActivity::class.java)
            startActivity(intent)
        },2000)
    }
}
