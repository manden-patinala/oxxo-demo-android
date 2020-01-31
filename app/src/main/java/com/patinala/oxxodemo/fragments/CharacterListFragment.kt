package com.patinala.oxxodemo.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.patinala.oxxodemo.R
import com.patinala.oxxodemo.activities.MainActivity
import com.patinala.oxxodemo.adapters.CharacterListAdapter
import com.patinala.oxxodemo.controllers.CharacterController
import com.patinala.oxxodemo.models.Character
import com.patinala.oxxodemo.viewmodels.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.view_custom_toolbar.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * A simple [Fragment] subclass.
 */
class CharacterListFragment : Fragment() {

    var mainActivity: MainActivity? = null
    var characterController: CharacterController? = null

    var adapter: CharacterListAdapter? = null
    var characters = arrayListOf<Character>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        title.text = context!!.getString(R.string.title_character_list)

        adapter = CharacterListAdapter(context!!.applicationContext, characters) { character ->
            val fragment = CharacterSummaryFragment()
            val characterViewModel = CharacterViewModel()
            characterViewModel.setModel(character)

            fragment.characterViewModel = characterViewModel
            mainActivity!!.addFragment(fragment)
        }

        character_list_recycler_view.layoutManager = LinearLayoutManager(context)
        character_list_recycler_view.adapter = adapter

        doAsync {
            characterController = CharacterController.getInstance()
            characterController!!.getAllCharacters({ result ->
                characters.addAll(result)

                uiThread {
                    adapter!!.notifyDataSetChanged()
                }

            }, { error, message ->

            })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

}
