package mahmoud.moussa.myquranapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mahmoud.moussa.myquranapplication.R

class SuraContentRecAdapter:RecyclerView.Adapter<SuraContentRecAdapter.ViewHolder>() {
    var data:List<String>?=null
    var onItemClick: OnItemClickListner?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sura_content,parent,false);
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data?.size ?:0;
    }

    fun changeData(item:List<String>){
        this.data=item;
        notifyDataSetChanged();
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var content=data?.get(position);
        holder.content.text=content+" {${position+1}}"

        holder.itemView.setOnClickListener {
            onItemClick?.onClick(position,content)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var content: TextView =itemView.findViewById(R.id.sura_content_tv);
    }

    interface OnItemClickListner{
        fun onClick(pos:Int,name:String?)
    }
}