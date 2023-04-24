package com.example.myapplication


//Caleb Millard
//613362
//Title: Sudoku Game in Kotlin
//lab 5 CMPT 360
//Dr.Rick Sutcliffe
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
   public inner class Container(var value: Int, THIS: Context?) {
       var permanent = false
       var button: Button


       // initialize the class Container which creates the array of enterable fields and then checks them
       init {
           permanent = if (value != 0) true else false
           button = Button(THIS)
           if (permanent) button.text = value.toString()
           button.setOnClickListener(View.OnClickListener {
               if (permanent) return@OnClickListener
               value++
               if (value > 9) value = 1
               button.text = value.toString()
               if (checker()) {
                   Text!!.text = "Correct"
               } else {
                   Text!!.text = "There is a repeated Digit"
               }
           })
       }
   }
//this function will iterate through each of the fields and make sure each of them follows the rules of a correct soduku
   fun correctchecker(i1: Int, j1: Int, i2: Int, j2: Int): Boolean {
       val seen = BooleanArray(10)
       for (i in 0..8) seen[i] = false
       for (i in i1 until i2) {
           for (j in j1 until j2) {
               val value = table[i][j]!!.value
               if (value != 0) {
                   if (seen[value]) return false
                   seen[value] = true
               }
           }
       }
       return true
   }
//this will call the other checker and it will call each of the rows and areas to check
   fun checker(): Boolean {
   //checks horizontal lines
       for (i in 0..8) if (!correctchecker(i, 0, i + 1, 9)) return false
   //checks vertical lines   
       for (j in 0..8) if (!correctchecker(0, j, 9, j + 1)) return false
   //checks boxes   
       for (i in 0..2) for (j in 0..2) if (!correctchecker(
               3 * i,
               3 * j,
               3 * i + 3,
               3 * j + 3
           )
       ) return false
   //only returns true if they are all correct
       return true
   }
//if all the areas are correct will return true
   fun finished(): Boolean {
       for (i in 0..8) for (j in 0..8) if (table[i][j]!!.value == 0) return false
       return true
   }
//initializing varaibles outside the start function that will be used in the class
   lateinit var table: Array<Array<Container?>>
   //all variables that are instantiated must have some value or be stated as null when declaring them.
   var input: String? = null
   var layout: TableLayout? = null
   var Llayout: LinearLayout? = null
   var Text: TextView? = null
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       //could be just pasted in one string but for my visual put it in like this
       input = "3 - 6 8 9 1 5 7 2 " +
               "2 - 1 7 3 5 6 8 4 " +
               "5 7 8 2 6 4 3 9 1 " +
               "8 5 9 4 - 3 1 2 6 " +
               "4 6 3 9 1 2 8 5 7 " +
               "7 1 2 6 5 8 4 3 9 " +
               "1 3 7 5 4 9 2 6 8 " +
               "9 2 4 3 8 6 7 1 5 " +
               "6 8 5 1 2 7 9 4 3 "
       //will be split based on the delimiter " " and will create this an array from the string input
       val split = input!!.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
           .toTypedArray()
       //this is the array that is being filled
       table = Array(9) {
           arrayOfNulls(
               9
           )
       }
       //forces the array to be displayed and shown as a 9 x 9 grid
       layout = TableLayout(this)
       for (i in 0..8) {
           val row = TableRow(this)
           var j = 0
           while (i < 9) {
               val s = split[i * 9 + j]
               val c = s[0]
               table[i][j] = Container(s[if (0 == '-'.code) 0 else c.code - '0'.code].code, this)
               row.addView(table[i][j]!!.button)
               j++
           }
           layout!!.addView(row)
       }
       //this was the most difficult part of this lab since it is dealing with the layout on screen.
       // this ended up being the most effective way of doing all of this without major issues.
       layout!!.isShrinkAllColumns = true
       Text = TextView(this)
       Llayout = LinearLayout(this)
       Llayout!!.addView(Text)
       Llayout!!.addView(layout)
       Llayout!!.orientation = LinearLayout.VERTICAL
       setContentView(layout)
   }
}


