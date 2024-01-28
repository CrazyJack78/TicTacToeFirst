package com.example.tictactoe

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class MainActivity : AppCompatActivity() {
    private val fields = arrayOfNulls<Field>(9) // erstellen eis Array um die einzelnen Felder darin abzuspeichern und mit dem Array wieder aufrufen zu können

    // da man dieses besser abzählen kann al seinzelne Objektnamen
    private var ende = 'n'
    var beginnerText: TextView? = null
    private var controlVariableOfWinnerStatus = 0.toChar()

    // gamerSwitcher
    protected fun playerSwitch() {
        if (playerYellow == true) {
            playerYellow = false
            playerRed = true
            //Toast.makeText(this, "Jetzt ist Player Red an der Reihe", Toast.LENGTH_SHORT).show();
        } else {
            playerYellow = true
            playerRed = false
            //Toast.makeText(this, "Jetzt ist Player Yellow an der Reihe", Toast.LENGTH_SHORT).show();
        }
    }

    // Sichtbarkeit des ResetButon
    fun resetButtonView() {
        val buttonView = findViewById<Button>(R.id.reset)
        buttonView.visibility = View.VISIBLE
        beginnerText = findViewById(R.id.beginnerInfo)
        beginnerText!!.visibility = View.INVISIBLE
    }

    // Überprüfen des Gewinners
    protected fun win(w: Char) {                    // Gewinnbenachrichtigung und Aktionen
        if (w == 'y') {
            val winText = findViewById<TextView>(R.id.goal)
            winText.text = "Gelb hat gewonnen"
            //Toast.makeText(this, "Yellow wins", Toast.LENGTH_SHORT).show();
            winImageRollingStars('y')
            ende = 'e'
        } else if (w == 'r') {
            val winText = findViewById<TextView>(R.id.goal)
            winText.text = "Rot hat gewonnen"
            //Toast.makeText(this, "Red wins", Toast.LENGTH_SHORT).show();
            winImageRollingStars('r')
            ende = 'e'
        } else if (w == 'x') {
            Toast.makeText(this, "Dieser Platz ist schon besetzt", Toast.LENGTH_LONG).show()
        } else if (w == 'e') {
            Toast.makeText(this, "Es gibt bereits einen Gewinner", Toast.LENGTH_LONG).show()
        } else if (w == 'u') {
            val winText = findViewById<TextView>(R.id.goal)
            winText.text = "Unentschieden"
            winImageRollingStars('u')
            Toast.makeText(this, "Das Spiel ist Unentschieden", Toast.LENGTH_LONG).show()
        }
    }

    //Animation bei Gewinn
    protected fun winImageRollingStars(player: Char) {
        val winnerImageL = findViewById<ImageView>(R.id.winnerImageL)
        val winnerImageR = findViewById<ImageView>(R.id.winnerImageR)
        if (player == 'y') {
            winnerImageL.setImageResource(R.drawable.yellowcoin)
            winnerImageR.setImageResource(R.drawable.yellowcoin)
        } else if (player == 'r') {
            winnerImageL.setImageResource(R.drawable.redcoin)
            winnerImageR.setImageResource(R.drawable.redcoin)
        } else if (player == 'u') {
            winnerImageL.setImageResource(R.drawable.redcoin)
            winnerImageR.setImageResource(R.drawable.yellowcoin)
        }
        winnerImageL.alpha = 1f
        winnerImageL.animate().rotation(5000f).duration = 2000
        winnerImageR.alpha = 1f
        winnerImageR.animate().rotation(-5000f).duration = 2000
    }

    //Initialisierung bei Start
    fun build() {

        // Initialisieren und speichern der Imagefelder/Objekte Pic im Field
        var fieldZaehler = 0
        var v: Int
        var h: Int
        var field: ImageView
        v = 0
        while (v < 3) {
            h = 0
            while (h < 3) {
                val V = Integer.toString(v)
                val H = Integer.toString(h) // umwandeln der Zähler Koordinaten v und h in einen String zur Übername in die ID des ImageViews
                var idName = "" // erstellen der StringVariablen mit Ausgangswert um += schreiben zu können
                idName = "fieldCoin$V$H" // zusammenfügen und abspeichern der Id
                Log.i("fieldName", idName) //  überprüüfen der richtigen zusammensetzung des ID-Namen
                val resID = resources.getIdentifier(idName, "id", packageName) // abrufen der Ressource-Id (die lange die hinter dem namen steht) mithilfe der Image-ID
                // man kann das auch für andere Objekte Button ect verwenden
                Log.i("fieldId", Integer.toString(resID))
                field = findViewById<View>(resID) as ImageView // einspeichern des ImageViews in das ImageView field zur Übergabe an die Erstellung des Objektes der Klasse pic Field
                Log.i("resID", resID.toString())
                fields[fieldZaehler] = Field(field, v, h) // erstellen von einem Objekt in dem field gespeichert wird
                // einspeichern der erstelten Objekte in einem Array (fields[]) zur möglichen koordinierten Weiterverwendung
                fieldZaehler++ // setzt den Zähler für das Array eines höher nach dem Abspeichern des alten Feldes
                h++
            }
            v++
        }
        neustart()


        /*
        //ImageView field;
        //String testfield = "fieldCoinA1";
        //int resID = getResources().getIdentifier(testfield,"id",getPackageName());
        //Log.i("resId", Integer.toString(resID));
        //field = ((ImageView) findViewById(resID));

        //field = findViewById(R.id.fieldCoin00);     // im field wird das ImageView gespeichert
        pica1 = new Field(field, 0, 0);        // erstellen von einem Objekt in dem field gespeichert wird
        fields[0] = pica1;                         // Objekte deshalb weil dadurch objektbezogene Daten und Methoden^in der klasse generiert werden können


        field = findViewById(R.id.fieldCoin01);
        pica2 = new Field(field, 0, 1);
        fields[1] = pica2;      // einspeichern der erstelten Objekte in einem Array (fields[]) zur möglichen koordinierten Weiterverwendung

        field = findViewById(R.id.fieldCoin02);
        fields[2] = new Field(field, 0, 2); // hier können sie durch die Tag's der Images wieder aufgerufen werden und man spart sich jeden einzelnen Button/Image zu schreiben
         = pica3;

        field = findViewById(R.id.fieldCoin10);
        picb1 = new Field(field, 1, 0);
        fields[3] = picb1;

        field = findViewById(R.id.fieldCoin11);
        picb2 = new Field(field, 1, 1);
        fields[4] = picb2;

        field = findViewById(R.id.fieldCoin12);
        picb3 = new Field(field, 1, 2);
        fields[5] = picb3;

        field = findViewById(R.id.fieldCoin20);
        picc1 = new Field(field, 2, 0);
        fields[6] = picc1;

        field = findViewById(R.id.fieldCoin21);
        picc2 = new Field(field, 2, 1);
        fields[7] = picc2;

        field = findViewById(R.id.fieldCoin22);
        picc3 = new Field(field, 2, 2);
        fields[8] = picc3;
        */
    }

    // Betätigung der einzelnen Button/Felder
    fun fieldCoin(v: View) {  // v ist eine Variable von View, oft heißt sie view wegen des sprechenden schreibens
        val counter = v as ImageView // abfangen des onClick und Tag vom Image
        val tempOfTagFromImageView = counter.tag.toString().toInt() // umwandeln des "Tag" des in einen int-Wert da man den Tag nicht direkt in einen Integer umwandel kann
        // muss daraus zuerst ein String gemacht werden mit counter.get().toString() (counter ist die Variable des ImageView
        controlVariableOfWinnerStatus = fields[tempOfTagFromImageView]!!.coinSet(ende) // setzen der Farbe im übergebenen Feld mit übergabe von 'ende' falls ein Spieler gewonnen hat,
        // im tempOfTagFromImageView steht die jewelige Tagzahl des entsprechenden Feldes um mit ihr das passende Array[] aus Fields[] wieder aufzurufen
        // und ihr die entsprechenden Werte zu übergeben
        // w ist der Rückgabewert aus der Funktion coinSet aus Fields das wiederum den Wert aus der Gewinnüberprüfung hat
        if (controlVariableOfWinnerStatus == 'n') {
            win(controlVariableOfWinnerStatus)
        } else {
            object : CountDownTimer(1001, 1000) {
                // ein CountdownTimer hat ein vordefiniertes Ende
                override fun onTick(millisUntilFinished: Long) {
                    Log.i("Timer Interval", controlVariableOfWinnerStatus.toString()) // der Interval wird direkt bei Start ausgefürht
                }

                override fun onFinish() {
                    Log.i("Timer Finish", controlVariableOfWinnerStatus.toString()) // Finish wird nur zum Ende ausgefürht
                    // Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_SHORT).show();
                    // !! Hier reicht das einfache "this" nicht und muss entweder get getApplicationContext() oder ZB MainActivity.this heißen
                    win(controlVariableOfWinnerStatus) // Hier musste ich den Char 'w' Global setzen damit er vom Timer weitergegeben werden kann
                }
            }.start() // Start nicht vergessen sonst ist nix mit Timer :D
        }
        // Rückgabewert w aus der Klasse Field von der Überprüfung ob ein Spieler eine Reihe hat
        resetButtonView() // Anzeige des Neustart-Button wenn der erste Spieler das Spiel begonnen hat
    }

    // Neustartmethoden
    fun reset(view: View?) { // View ist dafür da um von den Button für die onClick-Methode bereitgestellt zu werden
        fieldClear()
        val winText = findViewById<TextView>(R.id.goal)
        winText.text = null // leeren des Ihnhaltes Textviews
        playerYellow = true // Zurücksetzen
        playerRed = false
        val buttonView = findViewById<Button>(R.id.reset) // sichtbarkeit des Neustart Button
        //buttonView.animate().alpha(0).setDuration(10); // unsichtbarmachen des Neustart-Button
        buttonView.visibility = View.INVISIBLE
        val beginnerText = findViewById<TextView>(R.id.beginnerInfo)
        beginnerText.visibility = View.VISIBLE
        neustart()
        ende = 'n' // rücksetzen im 'ende' weil das Spiel im Falle eine Gewinners ein e gesetzt in ende hat
    }

    private fun neustart() {
        for (v in 0..2) {
            for (h in 0..2) {
                yOrR[v][h] = 'n'
            }
        }
        val winnerImageLempty = findViewById<ImageView>(R.id.winnerImageL)
        val winnerImageRempty = findViewById<ImageView>(R.id.winnerImageR)
        winnerImageLempty.setImageDrawable(null)
        winnerImageLempty.animate().rotation(1f).duration = 1
        winnerImageRempty.setImageDrawable(null)
        winnerImageRempty.animate().rotation(1f).duration = 1
    }

    fun fieldClear() {                       // Leeren der Felder und Reset der Animationen durch überschreiben mit anderen Animationen
        var emptyField = findViewById<ImageView>(R.id.fieldCoin00)
        emptyField.setImageResource(0)
        emptyField.animate().rotation(1f).duration = 1
        emptyField = findViewById(R.id.fieldCoin01)
        emptyField.setImageResource(0)
        emptyField.animate().rotation(1f).duration = 1
        emptyField = findViewById(R.id.fieldCoin02)
        emptyField.setImageResource(0)
        emptyField.animate().rotation(1f).duration = 1
        emptyField = findViewById(R.id.fieldCoin10)
        emptyField.setImageResource(0)
        emptyField.animate().rotation(1f).duration = 1
        emptyField = findViewById(R.id.fieldCoin11)
        emptyField.setImageResource(0)
        emptyField.animate().rotation(1f).duration = 1
        emptyField = findViewById(R.id.fieldCoin12)
        emptyField.setImageResource(0)
        emptyField.animate().rotation(1f).duration = 1
        emptyField = findViewById(R.id.fieldCoin20)
        emptyField.setImageResource(0)
        emptyField.animate().rotation(1f).duration = 1
        emptyField = findViewById(R.id.fieldCoin21)
        emptyField.setImageResource(0)
        emptyField.animate().rotation(1f).duration = 1
        emptyField = findViewById(R.id.fieldCoin22)
        emptyField.setImageResource(0)
        emptyField.animate().rotation(1f).duration = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Starteigenschaften der Spieler
        playerYellow = true
        playerRed = false
        build()
    }

    companion object {
        @JvmField
        var playerYellow = false
        @JvmField
        var playerRed = false
        @JvmField
        var yOrR = Array(3) { CharArray(3) }
    }
}