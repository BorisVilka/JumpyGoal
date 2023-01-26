package com.jumpy.goal.sport

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.lang.Exception
import java.util.*
import kotlin.math.abs

class GameView(ctx: Context, att: AttributeSet): SurfaceView(ctx,att) {

    private var paused = true
    private var paintT: Paint = Paint().apply {
        textSize = 80f
        color = Color.BLACK
    }
    private var paintB: Paint = Paint(Paint.DITHER_FLAG)
    public var score = 0
    private val random = Random()
    private var ball: Bitmap = BitmapFactory.decodeResource(ctx.resources,R.mipmap.ball_foreground)
    private var goal = BitmapFactory.decodeResource(ctx.resources,R.mipmap.goal_foreground)
    private var y = 0
    private var dy = 0
    private val offset = 20
    private var millis = 0
    private var listener: EndListener? = null
    private var gx = 0
    private var gy = 0
    private val isSound = ctx.getSharedPreferences("prefs",Context.MODE_PRIVATE).getBoolean("sound",false)
    private val isMusic = ctx.getSharedPreferences("prefs",Context.MODE_PRIVATE).getBoolean("music",false)
    private val bg = MediaPlayer.create(ctx,R.raw.back).apply {
        setOnCompletionListener {
            start()
        }
    }
    private val sound = MediaPlayer.create(ctx,R.raw.sound)

    init {
        ball = Bitmap.createScaledBitmap(ball,ball.width/2,ball.height/2,true)
        holder.addCallback(object : SurfaceHolder.Callback{
            override fun surfaceCreated(holder: SurfaceHolder) {

            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                val canvas = holder.lockCanvas()
                if(canvas!=null) {
                    y = canvas.height/2
                    gx = canvas.width
                    gy = random.nextInt(300)+canvas.height/2
                    draw(canvas)
                    holder.unlockCanvasAndPost(canvas)
                }
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                bg.stop()
                bg.release()
            }

        })
    }
    fun start() {
        if(isMusic) bg.start()
        paused = false
        val updateThread = Thread {
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    if (!paused) {
                        update.run()
                        millis ++
                    }
                }
            }, 0, 16)
        }

        updateThread.start()
    }
    fun togglePause() {
        paused = !paused
        if(isMusic) {
            if(paused) bg.pause()
            else bg.start()
        }
    }
    fun isPaused(): Boolean {
        return paused
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action) {
            MotionEvent.ACTION_UP -> {

            }
            MotionEvent.ACTION_DOWN -> {
               if(millis>=offset) dy -= 20
            }
        }
        postInvalidate()
        return true
    }

    val update = Runnable{
        var isEnd = false
        try {
            val canvas = holder.lockCanvas()
            if(millis>=offset && millis % 2 == 0) dy+=2
            y+=dy
            if(gx<0) {
                gx = canvas.width
                gy = random.nextInt(300)+canvas.height/2
            }
            gx-=4
            if(abs(gx-x)<=goal.width/4) {
                if(abs(gy-y)>goal.height/2+ball.height/2) isEnd = true
                if(abs(gy-y)<=goal.height/2) {
                    score++
                    if(isSound) sound.start()
                    gx = canvas.width
                    gy = random.nextInt(300)+canvas.height/2
                }
            } else if(x-gx>=goal.width/2) {
                isEnd = true
            }
            canvas.drawColor(ctx.resources.getColor(R.color.bg,ctx.theme))
            canvas.drawText("Score: $score",50f,100f,paintT)
            canvas.drawBitmap(ball,70f,y.toFloat(),paintB)
            canvas.drawBitmap(goal,gx.toFloat(),gy.toFloat(),paintB)
            if(!isEnd) isEnd = (y<=0 || y>=canvas.height)
            holder.unlockCanvasAndPost(canvas)
            if(isEnd) {
                if(listener!=null) listener!!.end()
                togglePause()

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setEndListener(list: EndListener) {
        this.listener = list
    }

    companion object {
        interface EndListener {
            fun end();
        }
    }
}