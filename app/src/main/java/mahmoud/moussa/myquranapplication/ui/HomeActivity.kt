package mahmoud.moussa.myquranapplication.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.*
import mahmoud.moussa.myquranapplication.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.
            beginTransaction().
            replace(R.id.nav_container,QuranFragment())
            .commit();

        nav_view.setOnNavigationItemSelectedListener {
            var id=it.itemId;
            var fragment:Fragment=
                when(id){
                    R.id.navigation_quran -> QuranFragment()
                    R.id.navigation_tasbeh -> TasbihFragment()
                    R.id.navigation_radio -> RadioFragment()
                    else -> QuranFragment();
                }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_container,fragment)
                .addToBackStack("")
                .commit();
            return@setOnNavigationItemSelectedListener true;
        }

//        navView.setOnNavigationItemSelectedListener {
//            val id=it.itemId;
//            when(id){
//                R.id.navigation_quran ->showQuranFragment();
//                R.id.navigation_tasbeh ->showTasbihFragment();
//            }
//            return@setOnNavigationItemSelectedListener true;
//        }
//
//        navView.selectedItemId=
//            R.id.navigation_quran;


    }
//
//    private fun showTasbihFragment() {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(
//                R.id.nav_container,
//                TasbihFragment()
//            )
//            .addToBackStack("")
//            .commit()
//    }
//
//    private fun showQuranFragment() {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(
//                R.id.nav_container,
//                QuranFragment()
//            )
//            .addToBackStack("")
//            .commit()
//    }
}
