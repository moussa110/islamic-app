package mahmoud.moussa.myquranapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mahmoud.moussa.myquranapplication.R
import mahmoud.moussa.myquranapplication.apiManager.model.RadiosChannel


class RadioAdapter:RecyclerView.Adapter<RadioAdapter.ViewHolder>() {
    var data:List<RadiosChannel?>?=null
    var onPlayClick:SetOnItemClickListner?=null;
    var onStopClick:SetOnItemClickListner?=null;
    private var isHide=true

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var channelName:TextView=itemView.findViewById(R.id.item_radio_channel_tv);
        var radioPlay:Button=itemView.findViewById(R.id.item_radio_play);
        var radioStop:Button=itemView.findViewById(R.id.item_radio_stop);
        var radioProgres:ProgressBar=itemView.findViewById(R.id.item_progress);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.item_radio,parent,false);
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
       return data?.size?:0;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: RadiosChannel? =data?.get(position);
        holder.channelName.setText(item?.name);
        holder.channelName.isSelected=true;

        if (!isHide)
                holder.radioProgres.visibility=View.VISIBLE
        else
                holder.radioProgres.visibility=View.INVISIBLE

        if (onPlayClick!=null){
            holder.radioPlay.setOnClickListener {
                onPlayClick?.onClick(item)
            }
        }

        if (onStopClick!=null){
            holder.radioStop.setOnClickListener{
                onStopClick?.onClick(item)
            }
        }
    }

    fun hideProgressBar() { // here set as true
        isHide = true
        notifyDataSetChanged()
    }

    fun showProgressBar() { // here set as true
        isHide = false
        notifyDataSetChanged()
    }

    fun changeData(data:List<RadiosChannel?>?){
        this.data=data;
        notifyDataSetChanged();
    }

    public interface SetOnItemClickListner{
        fun onClick(channel: RadiosChannel?);
    }
}