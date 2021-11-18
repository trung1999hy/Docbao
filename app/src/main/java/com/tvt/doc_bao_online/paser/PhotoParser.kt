package com.tvt.doc_bao_online.paser

import android.util.JsonReader
import com.tvt.doc_bao_online.model.Photo
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

object PhotoParser {
     suspend fun parser(link : String):List<Photo> {
        val photos = arrayListOf<Photo>()
         val url = URL(link)
         val connection=url.openConnection()
         val inputStream= connection.getInputStream()
         val br = BufferedReader(InputStreamReader(inputStream))
        val data = StringBuffer()
         var line = br.readLine()
         while (line !=null){
             data.append(line)
             line=br.readLine()
         }
         val temPhotos= JSONArray(data.toString())
         for (i in 0 until temPhotos.length()){
             val temPhoto = temPhotos[i]as JSONObject
             val album = temPhoto.getInt("album")
             val id = temPhoto.getInt("id")
             val thumbnailUrl=temPhoto.getString("thumbnailUrl")
             val tile = temPhoto.getString("tile")
             val purl = temPhoto.getString("url")
             photos.add(Photo(album,id,thumbnailUrl,tile,purl))
         }
         return photos
    }
}