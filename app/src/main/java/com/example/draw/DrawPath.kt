package com.example.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View
import kotlin.math.abs

class DrawPath @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr)
{
    private  var  paint: Paint?=null
    private  var path: Path?=null
    private  var  mPath: Path?=null
    private  var  pathlist= ArrayList<PainPath>()
    private  var  undonePathlist=ArrayList<PainPath>()
    private var  mY:Float?=null
    private  var  mX:Float?=null
    private  var  touchTolerance:Float= 4f




    init {
        paint = Paint()
       // path =Path()
        paint!!.color=Color.BLUE
        paint!!.strokeWidth=10f
        paint!!.style=Paint.Style.STROKE
        paint!!.isAntiAlias=true
    }

    override fun onDraw(canvas: Canvas?) {
        if (pathlist.size>0){
            for (path in pathlist){
                canvas!!.drawPath(path.path,paint!!)
            }
        }


    }



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val xPos:Float=event!!.x
        val yPos:Float=event.y

        when (event.action){
            MotionEvent.ACTION_DOWN->{
                touchStart(xPos,yPos)
                invalidate()
              //  path!!.moveTo(xPos,yPos)

            }
            MotionEvent.ACTION_MOVE->{
                touchMove(xPos,yPos)
              //  path!!.lineTo(xPos,yPos)

            }
            MotionEvent.ACTION_UP->{
                touchUp()
                invalidate()

            }
            else->{

            }

        }
        invalidate()
        return  true
    }
    private fun touchUp() {
        mPath!!.lineTo(mX!!,mY!!)


    }

    private fun touchMove(xPos: Float, yPos: Float) {
        val  dx:Float= abs(xPos-mX!!)
        val  dy:Float= abs(yPos-mY!!)
        if (dx >= touchTolerance|| dy >=touchTolerance){
            mPath!!.quadTo(mX!!,mY!!,(xPos+mX!!)/2,(yPos+ mY!!)/2)
            mX=xPos
            mY=yPos
        }

    }
    private fun touchStart(xPos: Float, yPos: Float) {
        mPath= Path()
        val  painPath=PainPath(mPath!!)
        pathlist.add(painPath)
        mPath!!.reset()
        mPath!!.moveTo(xPos,yPos)
        mY=yPos
        mX=xPos
    }
    fun setUndo(){
        val size: Int  = pathlist.size
        if (size>0){
            undonePathlist.add(pathlist[size-1])
            pathlist.removeAt(size-1)
            invalidate()
        }

    }
    fun setRedo(){
        val  size: Int =undonePathlist.size
        if (size>0){
            pathlist.add(undonePathlist[size-1])
            undonePathlist.removeAt(size-1)
            invalidate()

        }

    }



}