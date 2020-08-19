package mahmoud.moussa.myquranapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_tasbih.*
import mahmoud.moussa.myquranapplication.R

/**
 * A simple [Fragment] subclass.
 */
class TasbihFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasbih, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var counter:Int=0;
        var totalCounter:Int=0;

        refresh_iv.setOnClickListener(View.OnClickListener {
            counter=0;
            totalCounter=0;
            sebha_counter.text="0"
            sebha_counter_total.text="0"
        })

        val adapter=
            ArrayAdapter(activity,android.R.layout.simple_spinner_dropdown_item,resources.getStringArray(
                R.array.Tasabeh
            ));
        spinner_sebha.adapter=adapter
        spinner_sebha.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sebha_counter.text="0"
                counter=0;
            }

        }
        sebha.setOnClickListener {
            counter++;
            totalCounter++;
            sebha_counter.text="$counter"
            sebha_counter_total.text="$totalCounter"
        }


    }

}
