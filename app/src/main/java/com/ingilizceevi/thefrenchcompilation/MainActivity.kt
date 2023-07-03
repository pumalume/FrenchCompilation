package com.ingilizceevi.thefrenchcompilation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.forEach
import androidx.fragment.app.FragmentContainerView
import com.ingilizceevi.chapterchoice.ChapterSelection
import com.ingilizceevi.conceptbuilder.ConceptLoader
import com.ingilizceevi.frenchconnection.SoundPlayer

class MainActivity : AppCompatActivity() {

    var initiated = false
    lateinit var panel:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startChapterSelection()
    }

    fun startChapterSelection(){
        val intent = Intent(this, ChapterSelection::class.java )
        startActivityForResult(intent, 0)
    }
    fun startPictureVocabulary(chapter:String){
        val gameIntent = Intent(this, PictureVocabulary::class.java)
        gameIntent.putExtra("chapterName", chapter)
        startActivityForResult(gameIntent, 1)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                val result = data?.getStringExtra("chapterName")
                startPictureVocabulary(result!!)
            }
        }
        if (requestCode == 1) {
            val result = data?.getStringExtra("chapterName")
            startChapterSelection()
        }
    }

}