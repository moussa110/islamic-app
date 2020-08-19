package mahmoud.moussa.myquranapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mahmoud.moussa.myquranapplication.R

class SuraRecycleAdapter:RecyclerView.Adapter<SuraRecycleAdapter.ViewHolder>() {
    var data:List<String>?=null
    var onItemClick: OnItemClickListner?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:View=LayoutInflater.from(parent.context).inflate(R.layout.item_sura_name,parent,false);
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
        var name=data?.get(position);
        holder.name.text=name

        holder.itemView.setOnClickListener {
            onItemClick?.onClick(position,name)
        }
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        var name:TextView=itemView.findViewById(R.id.sura_name);
    }

    interface OnItemClickListner{
        fun onClick(pos:Int,name:String?)
    }
}