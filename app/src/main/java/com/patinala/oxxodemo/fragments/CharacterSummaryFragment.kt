package com.patinala.oxxodemo.fragments


import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.patinala.oxxodemo.R
import com.patinala.oxxodemo.activities.MainActivity
import com.patinala.oxxodemo.viewmodels.CharacterViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_character_summary.*
import kotlinx.android.synthetic.main.fragment_character_summary.view.*
import kotlinx.android.synthetic.main.view_custom_toolbar.*
import kotlinx.android.synthetic.main.view_item_character_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class CharacterSummaryFragment : Fragment() {

    var mainActivity: MainActivity? = null

    var characterViewModel: CharacterViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        title.text = characterViewModel!!.name
        back.visibility = View.VISIBLE

        back.setOnClickListener {
            fragmentManager!!.popBackStack()
        }

        Picasso.with(context)
            .load("${characterViewModel!!.image!!.path}/portrait_incredible.${characterViewModel!!.image!!.extension}")
            .into(view.character_summary_image)

        character_summary_decription.text = characterViewModel!!.description

        var index = 1
        characterViewModel!!.comics!!.items.forEach {
            val comicName = TextView(context)
            comicName.text = String.format("%d - %s", index, it.name)
            comicName.textSize = 16f
            comicName.setPadding(0, 6, 6, 6)
            character_summary_comics_container.addView(comicName)
            index++
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

}
