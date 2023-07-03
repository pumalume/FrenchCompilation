package com.ingilizceevi.thefrenchcompilation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PictureVocabulary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_vocabulary)
        val intent: Intent = intent
        val bundle: Bundle? = intent.getExtras()
        val result = bundle?.getString("chapterName")
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainView, Scroller.newInstance(result!!, ""))
            .commit()
    }

    override fun onResume() {
        super.onResume()
        val scroll = supportFragmentManager.findFragmentById(R.id.mainView) as Scroller
        val cancel = scroll.button
        cancel.setOnClickListener({
            supportFragmentManager.beginTransaction()
                .remove(scroll)
                .commit()
            setResult(1)
            finish()
        })

    }
}