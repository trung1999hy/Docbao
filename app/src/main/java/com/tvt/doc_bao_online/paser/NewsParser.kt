package com.tvt.doc_bao_online.paser

import com.tvt.doc_bao_online.model.News
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.HttpURLConnection
import java.net.URL

object NewsParser {
    suspend fun parserNews(link:String):List<News>{
        val listnew= arrayListOf<News>()
        val url=URL(link)
        val connection=url.openConnection()as HttpURLConnection
        val inputStream = connection.inputStream
        val xmlPullParserFactory= XmlPullParserFactory.newInstance()
        val xmlPullParser=xmlPullParserFactory.newPullParser()
        xmlPullParser.setInput(inputStream,"utf-8")
        var type = xmlPullParser.eventType
        var newss=News()
        var text=""
        while (type!=XmlPullParser.END_DOCUMENT){
            when(type){
                XmlPullParser.START_TAG->{
                    val tag=xmlPullParser.name
                    if (tag=="item"){
                        newss=News()
                    }else if (tag== "img"){
                        val img =xmlPullParser.getAttributeValue(null,"src")
                        newss.image=img
                    }
                }
                XmlPullParser.TEXT->{
                    text=xmlPullParser.text
                }
                XmlPullParser.END_TAG->{
                    val tag =xmlPullParser.name
                    when (tag) {
                        "title"->{
                            newss.title=text
                        }
                        "description"->{
                            newss.description=text
                        }

                        "link" -> {
                            newss.link=text
                        }
                        "item" -> {
                            listnew.add(newss)
                        }
                        "date" -> {
                           newss.datet=text
                        }
                    }
                }
            }
          type=xmlPullParser.next()
        }
        return listnew
    }
}