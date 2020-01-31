package com.patinala.oxxodemo.viewmodels

import com.patinala.oxxodemo.models.Character
import com.patinala.oxxodemo.models.ComicList
import com.patinala.oxxodemo.models.Image

class CharacterViewModel {

    var name: String = ""
    var image: Image? = null
    var description: String = ""
    var comics: ComicList? = null

    fun setModel(character: Character) {
        this.name = character.name
        this.image = character.thumbnail
        this.description = character.description
        this.comics = character.comics
    }

}