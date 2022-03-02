package lk.isoft.studentlogger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    interface OnItemClickListener {
        fun OnItemClick(position: Int)
    }

    //var title = mutableListOf<String>("")
    var title = MainActivity.Companion.array
    var schl = MainActivity.Companion.arrayscl

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vx = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(vx)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = title[position]
        holder.school.text = schl[position]

    }

    override fun getItemCount(): Int {
        return title.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var name: TextView
        var school: TextView
        init {
            name = itemView.findViewById(R.id.textView8)
            school = itemView.findViewById(R.id.textView9)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            //
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.OnItemClick(position)
            }

        }

    }

}