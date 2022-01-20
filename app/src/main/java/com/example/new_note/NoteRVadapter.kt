package com.example.new_note

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVadapter(val context: Context,
                    val noteClickedInterface: NoteClickedInterface,
                    val noteClickDeleteInterface: NoteClickDeleteInterface ):RecyclerView.Adapter<NoteRVadapter.ViewHolder>(){

                     private val allNotes = ArrayList<Note>()
                        inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                            val noteTV = itemView.findViewById<TextView>(R.id.TVnoteTitle)
                            val timeTV = itemView.findViewById<TextView>(R.id.TVtime)
                            val deleteTV = itemView.findViewById<ImageView>(R.id.Deletebt)
                        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.timeTV.setText("Last Updated: "+allNotes.get(position).timestamp)

        holder.deleteTV.setOnClickListener{
            noteClickDeleteInterface.onDeleteIconClicked(allNotes.get(position))
        }
        holder.itemView.setOnClickListener{
            noteClickedInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}
interface NoteClickDeleteInterface{
    fun onDeleteIconClicked(note: Note)
}
interface NoteClickedInterface{
    fun onNoteClick(note: Note)
}