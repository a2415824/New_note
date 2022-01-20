package com.example.new_note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),NoteClickDeleteInterface,NoteClickedInterface {

    lateinit var noteRV:RecyclerView
    lateinit var FADbut:FloatingActionButton
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRV = findViewById(R.id.RVnotes)
        FADbut = findViewById(R.id.FADbu)
        noteRV.layoutManager = LinearLayoutManager(this)

        val noteRVadapter = NoteRVadapter(this,this,this)
        noteRV.adapter = noteRVadapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this,{
            list->list?.let {
                noteRVadapter.updateList(it)
        }
        })
        FADbut.setOnClickListener{
            val intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }



    override fun onDeleteIconClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle} Deleted",Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
        this.finish()
    }
}