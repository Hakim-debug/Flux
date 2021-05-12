package com.example.flux

import android.app.Activity
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONStringer
import java.nio.charset.Charset

internal class FileParser {
    fun parseFile(activity: Activity,raw:Int): JSONArray {

       activity.resources.openRawResource(raw).use{ output->
           var storeDB = JSONArray(String(output.readBytes(), Charset.defaultCharset()))

           for (items in arrayOf(storeDB)){
               storeDB = items;
               if (storeDB.length() == items.length()){

                      return  storeDB



               }
           }


       }

       return  JSONArray()
    }
}