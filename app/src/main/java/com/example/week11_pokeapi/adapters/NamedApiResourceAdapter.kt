package com.example.week11_pokeapi.adapters

import com.example.week11_pokeapi.model.NamedApiResource
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class NamedApiResourceAdapter : JsonDeserializer<NamedApiResource> {

    data class Json(val name: String, val url: String)

    override fun deserialize(
        element: JsonElement,
        type: Type,
        context: JsonDeserializationContext
    ): NamedApiResource {
        val temp = context.deserialize<Json>(element, TypeToken.get(Json::class.java).type)
        return NamedApiResource(temp.name, temp.url)
    }
}