package com.example.tictactoe

import android.os.Handler
import android.util.Log
import android.widget.ImageView

class Field     //Konszruktoren
(var field: ImageView, var v: Int, var h: Int) : MainActivity() {
    // Setzen der einzelnen Coins Rot oder Gelb (wie X und O)
    // und Rückgabe an die Main die Farbve des Gewinners
    fun coinSet(end: Char): Char {
        Log.i("CoinIn", "Test")
        return if (end == 'e') {
            'e'
        } else if (yOrR[v][h] == 'n') {
            if (playerRed == true) {
                field.setImageResource(R.drawable.redcoin)
                field.x = -1000f
                field.y = -1000f // verstzen des Image auf Position x / y damit es von da zurückkommen kann
                field.scaleX = 0.01f
                field.scaleY = 0.01f
                field.animate().translationX(0f).translationY(0f).alpha(1f).duration = 1000 // verschiedene Animationen
                val handler = Handler()
                handler.postDelayed({ field.animate().scaleX(0.8f).scaleY(0.8f).rotation(1000f).duration = 1000 }, 500)
                yOrR[v][h] = 'r'
                Log.i("Player", "Red") // Log zur überprüfung
            } else if (playerYellow == true) {
                field.setImageResource(R.drawable.yellowcoin)
                field.x = 1000f
                field.y = 1000f
                field.scaleX = 0.01f
                field.scaleY = 0.01f
                field.animate().translationX(0f).translationY(0f).alpha(1f).duration = 1000
                val handler = Handler()
                handler.postDelayed({ field.animate().scaleX(0.8f).scaleY(0.8f).rotation(-900f).duration = 1000 }, 500)
                yOrR[v][h] = 'y'
                Log.i("Player", "Yellow") // Log zur überprüfung
            }
            playerSwitch() // wechsel der Spielerfarbe nach setzen eines Coins
            theWinnerControl() //Rückgabe der Spielerfarbe des Gewinners
        } else {
            'x'
        }
    }

    // Überprüfung ob ein Spieler gewonnen hat + Rückgabewert welcher Spieler gewonnen hat
    fun theWinnerControl(): Char {         // für das nächste Mal, wenn man den TextView oder ImmageView in der Main erstellt und hierhin mit übergibt könnte es funktionieren


        //vertikal
        return if (yOrR[0][0] == yOrR[0][1] && yOrR[0][1] == yOrR[0][2] && yOrR[0][0] != 'n' && yOrR[0][1] != 'n' && yOrR[0][2] != 'n') {
            yOrR[0][0]
        } else if (yOrR[1][0] == yOrR[1][1] && yOrR[1][1] == yOrR[1][2] && yOrR[1][0] != 'n' && yOrR[1][1] != 'n' && yOrR[1][2] != 'n') {
            yOrR[1][0]
        } else if (yOrR[2][0] == yOrR[2][1] && yOrR[2][1] == yOrR[2][2] && yOrR[2][0] != 'n' && yOrR[2][1] != 'n' && yOrR[2][2] != 'n') {
            yOrR[2][0]
        } else if (yOrR[0][0] == yOrR[1][0] && yOrR[1][0] == yOrR[2][0] && yOrR[0][0] != 'n' && yOrR[1][0] != 'n' && yOrR[2][0] != 'n') {
            yOrR[0][0]
        } else if (yOrR[0][1] == yOrR[1][1] && yOrR[1][1] == yOrR[2][1] && yOrR[0][1] != 'n' && yOrR[1][1] != 'n' && yOrR[2][1] != 'n') {
            yOrR[0][1]
        } else if (yOrR[0][2] == yOrR[1][2] && yOrR[1][2] == yOrR[2][2] && yOrR[0][2] != 'n' && yOrR[1][2] != 'n' && yOrR[2][2] != 'n') {
            yOrR[0][2]
        } else if (yOrR[0][0] == yOrR[1][1] && yOrR[1][1] == yOrR[2][2] && yOrR[0][0] != 'n' && yOrR[1][1] != 'n' && yOrR[2][2] != 'n') {
            yOrR[0][0]
        } else if (yOrR[0][2] == yOrR[1][1] && yOrR[1][1] == yOrR[2][0] && yOrR[0][2] != 'n' && yOrR[1][1] != 'n' && yOrR[1][0] != 'n') {
            yOrR[0][2]
        } else if (yOrR[0][0] != 'n' && yOrR[0][1] != 'n' && yOrR[0][2] != 'n' && yOrR[1][0] != 'n' && yOrR[1][1] != 'n' && yOrR[1][2] != 'n' && yOrR[2][0] != 'n' && yOrR[2][1] != 'n' && yOrR[2][1] != 'n') {
            'u'
        } else {
            'n'
        }
    }

    companion object {
        const val temp = 'n'
    }
}