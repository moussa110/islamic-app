package mahmoud.moussa.myquranapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quran.*
import mahmoud.moussa.myquranapplication.Constants
import mahmoud.moussa.myquranapplication.DataHolder
import mahmoud.moussa.myquranapplication.R
import mahmoud.moussa.myquranapplication.adapter.SuraContentRecAdapter
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class QuranActivity : AppCompatActivity() {
    val adapter = SuraContentRecAdapter();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran)

        val suraName = intent.getStringExtra(Constants.MAIN_EXTRA_SURA_NAME_KEY)
        val fileName = intent.getStringExtra(Constants.MAIN_EXTRA_FILE_NAME_KEY)
        quran_sura_name.setText(suraName)
        val suraContent = readSuraFile(fileName)
        adapter.changeData(suraContent)
        quran_rv.adapter = adapter

    }

    fun readSuraFile(fileName: String): List<String> {
        val suraContent = mutableListOf<String>()
        var reader: BufferedReader? = null;
        try {
            reader = BufferedReader(
                InputStreamReader(getAssets().open(fileName))
            );

            // do reading, usually loop until end of file reading
            var mLine: String? = null
            mLine = reader.readLine()
            while (mLine != null) {
                //proccess line
                suraContent.add(mLine)
                mLine = reader.readLine()
            }
        } catch (e: IOException) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (e: IOException) {
                    //log the exception
                }
            }
        }

        return suraContent
    }
}