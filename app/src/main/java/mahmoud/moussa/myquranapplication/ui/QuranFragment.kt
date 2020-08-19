package mahmoud.moussa.myquranapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_quran.*
import mahmoud.moussa.myquranapplication.Constants
import mahmoud.moussa.myquranapplication.DataHolder
import mahmoud.moussa.myquranapplication.R
import mahmoud.moussa.myquranapplication.adapter.SuraRecycleAdapter

/**
 * A simple [Fragment] subclass.
 */
class QuranFragment : Fragment() {

    var adapter: SuraRecycleAdapter =
        SuraRecycleAdapter();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapter.changeData(DataHolder.ArSuras)
        rv_main.adapter=adapter
        adapter.onItemClick=object :
            SuraRecycleAdapter.OnItemClickListner {
            override fun onClick(pos: Int, name: String?) {
                var intent= Intent(activity,QuranActivity::class.java)
                intent.putExtra(Constants.MAIN_EXTRA_FILE_NAME_KEY,"${pos+1}.txt");
                intent.putExtra(Constants.MAIN_EXTRA_SURA_NAME_KEY,name);
                startActivity(intent)
            }
        }


    }

}
