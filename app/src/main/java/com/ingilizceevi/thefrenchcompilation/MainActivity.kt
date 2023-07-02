package com.ingilizceevi.thefrenchcompilation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.forEach
import androidx.fragment.app.FragmentContainerView
import com.ingilizceevi.conceptbuilder.ConceptLoader
import com.ingilizceevi.frenchconnection.SoundPlayer

class MainActivity : AppCompatActivity() {
    val c = ConceptLoader("chapter03")
    lateinit var b1 : Button
    lateinit var sound:  SoundPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        b1 = findViewById<Button>(R.id.button1)
        val b2 = findViewById<Button>(R.id.button2)
        val b3 = findViewById<Button>(R.id.button3)
        b1.setOnClickListener(myOnClickListener1)
        b2.setOnClickListener(myOnClickListener2)
        b3.setOnClickListener(myOnClickListener3)
        sound = SoundPlayer(applicationContext)
    }
    val myOnClickListener1 = View.OnClickListener {
        val x = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as GamePanel
        x.addRowToScoll(20)
        b1.setOnClickListener(myOnClickListener4)
    }


    val myOnClickListener2 = View.OnClickListener {
        c.loadDrawables()
        c.loadAudio()
        val x = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as GamePanel
        val v = c.theImages
        v.forEach{entry ->
            x.addImageToScrollView(entry.value, entry.key)
        }

    }
    val myOnClickListener3 = View.OnClickListener {
        val image = it as ImageView
        val key = image.tag.toString()
        val u = c.theSounds[key]
        if (u != null) {
            sound.startSound(u)
        }
    }
    val myOnClickListener4 = View.OnClickListener {
        setListenerOnImage()
    }


    fun setListenerOnImage(){
        val gp = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as GamePanel
        val scroll = gp.scroller1
        scroll.forEach {
            val row = it as LinearLayout
            row.forEach {
                val image = it as ImageView
                image.setOnClickListener(myOnClickListener3)
            }
        }
    }
}