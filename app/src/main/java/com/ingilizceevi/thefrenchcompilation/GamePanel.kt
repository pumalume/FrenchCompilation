package com.ingilizceevi.thefrenchcompilation

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.view.setMargins
import androidx.core.view.setPadding

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.marginTop
import androidx.core.view.size
import androidx.fragment.app.activityViewModels

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GamePanel : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var main : View
    lateinit var scroller1 : LinearLayout
    private lateinit var scroller2 : LinearLayout
    private var scrollSize:Int = 0
    private var rowNum:Int = 0
    private var colNum:Int = 0
    private val gameBrain: GameBrain by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        main = inflater.inflate(R.layout.fragment_game_panel, container, false)
        scroller1 = main.findViewById(R.id.scroll_view1)

        return main
    }

    fun viewTagIs(index:Int):Int{
        return scroller1.getChildAt(index).tag.toString().toInt()
    }

    fun handleOnScrollElement(index:Int): ImageView {
        val imageview = scroller1.getChildAt(index) as ImageView
        return imageview
    }
    fun sizeOfScroll():Int{return scrollSize}

    fun addRowOfImagesToScrollView(list : MutableList<Concept>){
        val size = list!!.size
        emptyTheScroll()
        for(i in 0 until size  ){
            //addImageToScrollView(list[i])
        }
    }

    fun addRowToScoll(numberOfRows:Int){
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            200
        )
        for(i in 0 until 30) {
            val row = LinearLayout(requireContext())
            row.layoutParams=lp
            row.orientation = LinearLayout.HORIZONTAL
            //if(i%2==0) row.setBackgroundColor(resources.getColor(R.color.purple_700))
            scroller1.addView(row)
        }
    }
    fun addImageToScrollView(concept: Drawable, s:String) {

        val fl = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.MATCH_PARENT,
            1f
            )
        fl.gravity = Gravity.CENTER
        val tempImage = ImageView(requireContext())
        tempImage.layoutParams = fl
        tempImage.tag = s
        tempImage.setPadding(10,10,10,10)
//        else tempImage.setPadding(20,0,0,0)

        tempImage.setImageDrawable(concept)
        val row = scroller1.getChildAt(rowNum) as LinearLayout
        row.addView(tempImage)
        colNum++
        if(colNum==2){
            rowNum++
            colNum=0
        }
        scrollSize++
    }
    fun resetTheSize(){
        //scroller1.
    }

    fun emptyTheScroll(){
        scroller1.removeAllViews()
    }
    //////////////////////
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GamePanel().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}