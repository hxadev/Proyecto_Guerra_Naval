package com.example.alfon.guerranaval

import android.content.Context
import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.ImageFormat
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.support.annotation.ColorRes
import android.support.annotation.IntegerRes
import android.view.Window
import android.webkit.DownloadListener
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_jugar.*

class JugarActivity : AppCompatActivity() {

    var barcosMios=0
    var barcosEnemigos=0
    var aciertosMaquina=0

    lateinit var ImagenesEnemigo:IntArray
    lateinit var ImagenesMio:IntArray

    lateinit var tableroMio:ArrayList<Button>
    private lateinit var tableroEnemigo:ArrayList<Button>

    private lateinit var mp: MediaPlayer
    private lateinit var vibratorServ: Vibrator

    val BARCO_DERRIBADO="0"
    val BARCO_POSICIONADO="1"
    val TIRO_FALLIDO="2"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_jugar)
        try{
            llenarArregloEnemigo()
            llenarArregloMio()
            posicionarBarcosEnemigos(tableroEnemigo)
            posicionarBarcosMios(tableroMio)
            jugar()

            btnVolver.setOnClickListener {
                salir()
            }
        }catch (e:Exception){

        }



    }

    private fun salir(){
        this.finish()
        closeContextMenu()
    }

    private fun llenarArregloEnemigo(){
        tableroEnemigo=ArrayList<Button>(24)
        try{
            tableroEnemigo.add(0,e00)
            tableroEnemigo.add(0,e01)
            tableroEnemigo.add(0,e02)
            tableroEnemigo.add(0,e03)
            tableroEnemigo.add(0,e04)
            tableroEnemigo.add(0,e10)
            tableroEnemigo.add(0,e11)
            tableroEnemigo.add(0,e12)
            tableroEnemigo.add(0,e13)
            tableroEnemigo.add(0,e14)
            tableroEnemigo.add(0,e20)
            tableroEnemigo.add(0,e21)
            tableroEnemigo.add(0,e22)
            tableroEnemigo.add(0,e23)
            tableroEnemigo.add(0,e24)
            tableroEnemigo.add(0,e30)
            tableroEnemigo.add(0,e31)
            tableroEnemigo.add(0,e32)
            tableroEnemigo.add(0,e33)
            tableroEnemigo.add(0,e34)
            tableroEnemigo.add(0,e40)
            tableroEnemigo.add(0,e41)
            tableroEnemigo.add(0,e42)
            tableroEnemigo.add(0,e43)
            tableroEnemigo.add(0,e44)

            for (i:Int in 0 until tableroEnemigo.size){
                    tableroEnemigo[i].setText("")
            }

        }catch(e:Exception){
            System.out.println(e.printStackTrace())
        }
    }

    private fun llenarArregloMio(){
        tableroMio=ArrayList<Button>(16)
        try{
            tableroMio.add(0,m00)
            tableroMio.add(0,m01)
            tableroMio.add(0,m02)
            tableroMio.add(0,m03)
            tableroMio.add(0,m04)
            tableroMio.add(0,m10)
            tableroMio.add(0,m11)
            tableroMio.add(0,m12)
            tableroMio.add(0,m13)
            tableroMio.add(0,m14)
            tableroMio.add(0,m20)
            tableroMio.add(0,m21)
            tableroMio.add(0,m22)
            tableroMio.add(0,m23)
            tableroMio.add(0,m24)
            tableroMio.add(0,m30)
            tableroMio.add(0,m31)
            tableroMio.add(0,m32)
            tableroMio.add(0,m33)
            tableroMio.add(0,m34)
            tableroMio.add(0,m40)
            tableroMio.add(0,m41)
            tableroMio.add(0,m42)
            tableroMio.add(0,m43)
            tableroMio.add(0,m44)


                for (j:Int in 0 until tableroMio.size){
                    tableroMio[j].setText("")
                }


        }catch(e:Exception){
            System.out.println(e.printStackTrace())
        }
    }


    fun posicionarBarcosEnemigos(tableroE:ArrayList<Button>){
        for(i:Int in 0 until tableroE.size){
            tableroE[((Math.random()*i+1).toInt())].setText(BARCO_POSICIONADO)
            tableroE[i].textSize=0F
        }

        for(i:Int in 0 until tableroE.size){
            tableroE[i].setBackgroundResource(R.drawable.mar5050dp)
            if(tableroE[i].text==BARCO_POSICIONADO){
                barcosEnemigos++
            }
        }



        //Toast.makeText(this,"Enemigos: ${barcosEnemigos}",Toast.LENGTH_LONG).show()
    }


    fun finalizarJuego(){
        for(i:Int in 0 until tableroEnemigo.size){
            tableroEnemigo[i].isEnabled=false
            tableroMio[i].isEnabled=false

        }
    }

    fun posicionarBarcosMios(tableroM:ArrayList<Button>){
        for(i:Int in 0 until tableroM.size){
            tableroM[i].setBackgroundResource(R.drawable.mar5050dp)
        }

        for(i:Int in 0 until tableroM.size){
            //tableroM[ ((Math.random()*i+1).toInt()) ].setText("1")
            tableroM[ ((Math.random()*i+1).toInt()) ].setText(BARCO_POSICIONADO)
            tableroM[i].textSize=12F
        }
        for(i:Int in 0 until tableroM.size){
            if(tableroM[i].text==BARCO_POSICIONADO){
                tableroM[i].setBackgroundResource(R.drawable.barcogame)
                barcosMios++
            }
        }

    }



    fun ataqueEnemigo() {
        val tamTabM = tableroMio.size
        var posicionTirar = Math.random() * tamTabM
        //var aux: Int = 0

        //tableroMio[(Math.random()*tamTabM).toInt()].setBackgroundColor(Color.RED)


        do {

            if (tableroMio[posicionTirar.toInt()].text == BARCO_POSICIONADO && tableroMio[posicionTirar.toInt()].text != BARCO_DERRIBADO) {
                tableroMio[posicionTirar.toInt()].text = BARCO_DERRIBADO
                tableroMio[posicionTirar.toInt()].isEnabled = false
                tableroMio[posicionTirar.toInt()].setBackgroundResource(R.drawable.barcoexplotado5050dp)
                aciertosMaquina++
            }
            if (tableroMio[posicionTirar.toInt()].text != BARCO_POSICIONADO && tableroMio[posicionTirar.toInt()].text != BARCO_DERRIBADO) {
                tableroMio[posicionTirar.toInt()].text = TIRO_FALLIDO
                tableroMio[posicionTirar.toInt()].setBackgroundResource(R.drawable.exposion5050dp)
            }

        } while (tableroMio[posicionTirar.toInt()].text == BARCO_POSICIONADO && tableroMio[posicionTirar.toInt()].text != BARCO_DERRIBADO)

        //Toast.makeText(this, "${aciertosMaquina}", Toast.LENGTH_SHORT).show()

        if (aciertosMaquina >= barcosMios) {
            Toast.makeText(this, "¡Ha ganado la maquina!", Toast.LENGTH_LONG).show()
        }
        /*do{
            if(tableroMio[posicionTirar.toInt()].text!="1" && tableroMio[posicionTirar.toInt()].text!=explotado) {

                tableroMio[posicionTirar.toInt()].setBackgroundColor(Color.YELLOW)
            }else if(tableroMio[posicionTirar.toInt()].text=="1")
            {
                tableroMio[posicionTirar.toInt()].text = explotado
                tableroMio[posicionTirar.toInt()].isEnabled = false
                tableroMio[posicionTirar.toInt()].setBackgroundResource(R.drawable.barcoexplotado5050dp)
                aciertosMaquina++
            }


            Toast.makeText(this, "${aciertosMaquina}", Toast.LENGTH_SHORT).show()

        }while(tableroMio[posicionTirar.toInt()].text=="1" && aciertosMaquina<barcosMios)
        */


        /*if(tableroMio[(posicionTirar).toInt()].text=="1") {
            aciertosMaquina++
            tableroMio[posicionTirar.toInt()].text="0"
            tableroMio[posicionTirar.toInt()].isEnabled=false
            tableroMio[posicionTirar.toInt()].setBackgroundColor(RED)


        }else{
            tableroMio[posicionTirar.toInt()].setBackgroundColor(Color.BLUE)
        }

        Toast.makeText(this, "${aciertosMaquina}", Toast.LENGTH_SHORT).show()
        if(aciertosMaquina==barcosMios)
        {
            Toast.makeText(this,"¡Ha ganado la maquina!",Toast.LENGTH_LONG).show()
        }*/


        /*else
            {
                tableroMio[posicionTirar.toInt()].setBackgroundColor(Color.MAGENTA)
            }*/
    }


     fun jugar()
    {
        mp=MediaPlayer.create(this,R.raw.clickboton)
        vibratorServ=this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        //var turnoMaquina:Boolean=false
        var aciertosJugador=0
        var fallosJugador=0


        for (i:Int in 0 until tableroEnemigo.size){
            tableroEnemigo[i].setOnClickListener {

                if(tableroEnemigo[i].text.equals(BARCO_POSICIONADO)){
                    aciertosJugador++

                    //Toast.makeText(this,"Derribado",Toast.LENGTH_SHORT).show()
                    tableroEnemigo[i].setText(BARCO_DERRIBADO)
                    textAciertos.text=aciertosJugador.toString()
                    //tableroEnemigo[i].setBackgroundColor(RED)
                    tableroEnemigo[i].setBackgroundResource(R.drawable.barcoexplotado5050dp)
                    tableroEnemigo[i].isEnabled=false
                    vibratorServ.vibrate(80)
                    mp.start()
                    ataqueEnemigo()
                }else{
                    fallosJugador++
                    tableroEnemigo[i].setText(TIRO_FALLIDO)
                    textFallos.text=(fallosJugador).toString()
                    //tableroEnemigo[i].setBackgroundColor(Color.YELLOW)
                    tableroEnemigo[i].setBackgroundResource(R.drawable.exposion5050dp)
                    vibratorServ.vibrate(80)
                    mp.start()
                    ataqueEnemigo()

                }

                if(aciertosJugador==barcosEnemigos){
                    Toast.makeText(this,"¡Has ganado!",Toast.LENGTH_LONG).show()
                    finalizarJuego()
                }


            }
        }
    }

}
