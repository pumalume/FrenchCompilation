package com.ingilizceevi.thefrenchcompilation

import android.graphics.drawable.Drawable

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.forEach
import com.ingilizceevi.conceptbuilder.ConceptLoader
import com.ingilizceevi.frenchconnection.SoundPlayer

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Scroller : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var main : View
    lateinit var scroller : LinearLayout
    private var scrollSize:Int = 0
    private var rowNum:Int = 0
    private var colNum:Int = 0
    private lateinit var concepts : ConceptLoader
    lateinit var sound: SoundPlayer
    lateinit var button: ImageButton
    //private val gameBrain: GameBrain by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        if(param1!=null)concepts = ConceptLoader(param1!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        main = inflater.inflate(R.layout.fragment_scroller, container, false)
        scroller = main.findViewById(R.id.scroll_view)
        button = main.findViewById(R.id.cancelButton)
        sound = SoundPlayer(requireContext())
        concepts.loadDrawables()
        concepts.loadAudio()
        return main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addRowToScroll()
        imagesAreAdded()
    }

    override fun onResume() {
        super.onResume()
        setListenerOnImage()
    }

    fun imagesAreAdded() {
        concepts.theImages.forEach { entry ->
            addImageToScrollView(entry.value, entry.key)
        }
    }

    fun imageSoundIsPlayed(image:ImageView){
        val key = image.tag.toString()
        val u = concepts.theSounds[key]
        if (u != null) { sound.startSound(u) }
    }

    fun handleOnScrollElement(index:Int): ImageView {
        val imageview = scroller.getChildAt(index) as ImageView
        return imageview
    }

    fun addRowToScroll(){
        var numberOfRows = concepts.theImages.size
        if (numberOfRows%2==1)numberOfRows++
        numberOfRows=numberOfRows/2
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            200
        )
        for(i in 0 until numberOfRows) {
            val row = LinearLayout(requireContext())
            row.layoutParams=lp
            row.orientation = LinearLayout.HORIZONTAL
            //if(i%2==0) row.setBackgroundColor(resources.getColor(R.color.purple_700))
            scroller.addView(row)
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
        tempImage.setImageDrawable(concept)
        val row = scroller.getChildAt(rowNum) as LinearLayout
        row.addView(tempImage)
        colNum++
        if(colNum==2){
            rowNum++
            colNum=0
        }
        scrollSize++
    }

    fun setListenerOnImage() {
        scroller.forEach {
            val row = it as LinearLayout
            row.forEach {
                val image = it as ImageView
                image.setOnClickListener(myOnClickListener)
            }
        }
    }
    val myOnClickListener = View.OnClickListener {
        val image = it as ImageView
        imageSoundIsPlayed(image)
    }

    fun emptyTheScroll(){
        scroller.removeAllViews()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Scroller().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}