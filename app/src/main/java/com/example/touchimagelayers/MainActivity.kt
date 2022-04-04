package com.example.touchimagelayers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ortiz.touchview.TouchImageView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val touchImage1 = findViewById<TouchImageView>(R.id.touch_image_1)
        val touchImage2 = findViewById<TouchImageView>(R.id.touch_image_2)
        val touchImageOverlay = findViewById<TouchImageView>(R.id.touch_image_overlay)

        touchImageOverlay.setOnTouchImageViewListener {
            touchImage1.setZoom(touchImageOverlay)
            touchImage2.setZoom(touchImageOverlay)
        }

        // Works
        //  val img = getBitmap(this, R.drawable.ic_baseline_aspect_ratio_24)
        //  touchImage2.setImageBitmap(img)

        /** HERE IS THE PROBLEM **/
        // Does not work (after zooming in, dinosaur head is unreachable)
        val img = getBitmap(this, R.drawable.corgosaurus)
        touchImage2.setImageBitmap(img)

        // Also does not work (after zooming in, dinosaur head is unreachable)
//        touchImage2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.corgosaurus))

    }


    fun getBitmap(context: Context, drawableRes: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, drawableRes) ?: return null
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }
}