package com.ingilizceevi.thefrenchcompilation

import androidx.lifecycle.ViewModel

class GameBrain: ViewModel() {
    val Clicked: MutableMap<String, Int> = mutableMapOf()

    fun initializeMap(concepts:MutableList<String>){
        concepts.forEach { element->Clicked[element]=0 }

    }
    fun imageHasBeenClicked(imageTag:String){
        var t = Clicked[imageTag]
        t = t!! + 1
        Clicked[imageTag]= t
    }
}
