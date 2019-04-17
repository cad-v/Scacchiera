package com.example.game

import android.content.Context
import android.graphics.*
import android.provider.CalendarContract
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import java.util.*
import java.nio.file.Files.size



class ScacchieraView:View {
    //Questi due costruttori ci servono per forza copia incollati
    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    //Devo sempre fare onDraw per far si che mi disegni qualcosa sulla view, super già sta scritto

    var verti = 0
    var oriz = 0
    var dimensione = 5
    var canvas: Canvas? = null
    var schermo = Rect()
    var controllo = 0
    var xi = 10
    var yi = 10
    var matricecolori = Array(dimensione, {IntArray(dimensione)})
    var controllo2 = 1
    var lato=0
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var schermo = Rect()
        canvas?.getClipBounds(schermo) //Metto in schermo gli estremi massimi del rettangolo
        var oriz = (schermo.right - schermo.left) / dimensione
        var verti = (schermo.bottom - schermo.top) / dimensione
         lato=verti
        var num = 0
        println("Hai cliccato")
        for (i in 0..(dimensione-1)) {
            for (j in 0..(dimensione-1)) {
                var casella = Rect(
                    schermo.left + j * oriz,
                    schermo.top + i * verti,
                    schermo.left + (j + 1) * oriz,
                    schermo.top + (i + 1) * verti
                )
                var paint = Paint()   //paint mi serve per colorare gli oggetti
                if (controllo == 0) {
                    if ((j + num) % 2 == 0) {
                      paint.color = Color.YELLOW
                        canvas?.drawRect(casella, paint)  //il punto interrogativo mi serve per dire:
                        //SOLO SE ESISTE COLORA QUELL'OGGETTO
                        matricecolori[i][j] = Color.YELLOW
                    }
                    else {

                        paint.color = Color.BLACK
                        canvas?.drawRect(casella, paint)  //il punto interrogativo mi serve per dire:
                        //SOLO SE ESISTE COLORA QUELL'OGGETTO
                        matricecolori[i][j]= Color.BLACK
                    }
                    }

                    if (j == xi) {
                        if (matricecolori[i][j] == Color.YELLOW) {
                            paint.color = Color.BLACK
                            canvas?.drawRect(casella, paint)
                            matricecolori[i][j] = Color.BLACK
                            controllo2 = 1


                        } else {
                            paint.color = Color.YELLOW
                            canvas?.drawRect(casella, paint)
                            matricecolori[i][j] = Color.YELLOW
                            controllo2 = 1

                        }
                    }

                    if (i == yi) {
                        if (matricecolori[i][j]== Color.YELLOW) {
                            paint.color = Color.BLACK
                            canvas?.drawRect(casella, paint)
                            matricecolori[i][j] = Color.BLACK
                            controllo2 = 1
                        } else {
                            paint.color = Color.YELLOW
                            canvas?.drawRect(casella, paint)
                            matricecolori[i][j] = Color.YELLOW
                            controllo2 = 1

                        }
                    }
                    if (controllo2 == 0) {
                       paint.color = matricecolori[i][j]
                        canvas?.drawRect(casella, paint)
                    }
                    controllo2 = 0
            }
            num++
        }
        println("Lato Quadrato" + verti)

        println("Colonna Cliccata Dopo" + xi)
        println("Riga Cliccata Dopo" + yi)
        println("Controllo" + controllo)
        controllo = 0
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            if (event.action != null) {

                val x: Float = event.getX()
                val y: Float = event.getY()
                xi = (x / lato).toInt()   //colonna
                yi = (y / lato).toInt() //riga
                println("Lato Quadrato" + lato)
                println("Colonna Cliccata" + xi)
                println("Riga Cliccata" + yi)
                controllo = 1

                invalidate()

            }
        }
        //return super.onTouchEvent(event)
        return true
    }
}
