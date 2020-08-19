package mahmoud.moussa.myquranapplication.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_radio.*
import kotlinx.android.synthetic.main.item_radio.*
import mahmoud.moussa.myquranapplication.R
import mahmoud.moussa.myquranapplication.adapter.RadioAdapter
import mahmoud.moussa.myquranapplication.apiManager.ApiManager
import mahmoud.moussa.myquranapplication.apiManager.model.RadioResponse
import mahmoud.moussa.myquranapplication.apiManager.model.RadiosChannel
import mahmoud.moussa.myquranapplication.services.RadioServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class RadioFragment : Fragment() {

    var adapter=RadioAdapter();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radio_rv.adapter = adapter;
        getChannelFromApi();

            adapter.onPlayClick = object : RadioAdapter.SetOnItemClickListner {
                override fun onClick(channel: RadiosChannel?) {
                    if (mBound)
                        mService.startRadioPlayer(channel?.radioUrl,channel?.name)

                    radio_name_tv.text=channel?.name+"            "+channel?.name+"            ";
                    radio_name_tv.isSelected=true;
                    }
                }
            adapter.onStopClick = object : RadioAdapter.SetOnItemClickListner {
            override fun onClick(channel: RadiosChannel?) {
                if (mBound)
                    mService.stopRadioPlayer()

                radio_name_tv.text=""
            }
        }
    }


    private lateinit var mService: RadioServices
    private var mBound: Boolean = false

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as RadioServices.RadioBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

        override fun onStart() {
            super.onStart()
            startService();
            bindService();
        }

    private fun startService() {
        val intent=Intent(activity,RadioServices::class.java);
        activity?.startService(intent)
    }

    override fun onStop() {
        super.onStop()
        activity?.unbindService(connection)
        mBound=false;
    }

    private fun bindService() {
        val intent=Intent(activity,RadioServices::class.java);
        activity?.bindService(intent,connection,Context.BIND_AUTO_CREATE);
    }

    private fun getChannelFromApi() {
        ApiManager.
        getWebServices().
        getRadioChannel()
            .enqueue(object :Callback<RadioResponse>{
                override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
                    Toast.makeText(activity,t.message?:"error",Toast.LENGTH_LONG);
                }

                override fun onResponse(
                    call: Call<RadioResponse>,
                    response: Response<RadioResponse>
                ) {
                    radio_progress.visibility=View.GONE
                    val data=response.body()?.radios;
                    adapter.changeData(data)
                }

            })
    }

}
