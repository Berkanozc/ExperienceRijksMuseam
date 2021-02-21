package com.example.exprijksmuseam.model

data class Artist(
    override val name: String,
    override val imageUrl: String,
    override val description: String,
    override val moreLink: String
) : ItemInterface