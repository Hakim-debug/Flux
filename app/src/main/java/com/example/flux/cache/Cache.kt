package com.example.flux.cache

import android.os.CountDownTimer
import kotlin.concurrent.timer

interface ICache{
    var size:Int
    public fun setCache(key: Any,value:Any)
    public fun getCache(key: Any):Any?
    public fun removeCache(key: Any):Any?
    public fun clear()

}
class Cache:ICache {




    private  var hashCache=  HashMap<Any,Any>()
    override var size: Int = 0
        get() = hashCache.size
    override fun setCache(key: Any, value: Any) {
        hashCache[key] = value
    }

    override fun getCache(key: Any)=hashCache[key]




    override fun removeCache(key: Any)=hashCache.remove(key).hashCode()


    override fun clear() {
    var   timer= object : CountDownTimer(20000,65344*10){
        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {
            hashCache.clear()

        }

        }
     timer.start()
    }


}