package com.jhonny.pu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener,
        View.OnTouchListener {

    lateinit var gDetector: GestureDetector
    var flag:Boolean = true
    var PictureNo: Int = 0
    var TotalPictures: Int = 6

    fun ShowPicture(){
        /*when (PictureNo){
            0 -> img.setImageResource(R.drawable.pu0)
            1 -> img.setImageResource(R.drawable.pu1)
            2 -> img.setImageResource(R.drawable.pu2)
            3 -> img.setImageResource(R.drawable.pu3)
        }*/
        var res:Int = getResources().getIdentifier("pu" + (PictureNo),
                "drawable", getPackageName())
        img.setImageResource(res)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gDetector = GestureDetector(this, this)
        img.setOnTouchListener(this)
        var res:Int = -1
        var countDrawables:Int = -1
        while (res != 0) {
            countDrawables++;
            res = getResources().getIdentifier("pu" + (countDrawables),
                    "drawable", getPackageName());
        }
        txv.text = PictureNo.toString()
        TotalPictures = countDrawables
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        PictureNo = 0
        txv.text = PictureNo.toString()
        ShowPicture()
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        flag=true
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        if(flag) {
            if (e1.getY() < e2.getY()) {
                PictureNo++
                if (PictureNo == TotalPictures) {
                    PictureNo = 0
                }
            } else {
                PictureNo--;
                if (PictureNo < 0) {
                    PictureNo = TotalPictures - 1
                }
            }
            ShowPicture()
            txv.text = PictureNo.toString()
            }
        flag = false
        return true

    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean {
        PictureNo = TotalPictures -1
        ShowPicture()
        txv.text = PictureNo.toString()
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }
}