package com.example.guess_the_word

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Arrays
import java.util.Collections
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

   // Declaring Array of string
    internal var Days= arrayOf(
        "Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"
    )

    //Declaring all other variables
    lateinit var day:String
    lateinit var random:Random

    //declaring variables for the view
    lateinit var txtRightAnswer:TextView
    lateinit var txtQuestionContainer:TextView
    lateinit var txtCorrectAnswer:TextView
    lateinit var etUsrInput:EditText
    lateinit var btShow:Button
    lateinit var btCheck:Button
    lateinit var btNext:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtRightAnswer=findViewById(R.id.txt_Right_Answer)
        txtCorrectAnswer=findViewById(R.id.txt_Correct_Answer)
        txtQuestionContainer=findViewById(R.id.text_Question_container)
        etUsrInput=findViewById(R.id.et_User_Input)
        btShow=findViewById(R.id.btShow)
        btCheck=findViewById(R.id.btCheck)
        btNext=findViewById(R.id.btNext)

        // initialize the random variable
        random = Random
        
        // actual logic
        day=Days[random.nextInt(Days.size)]
        txtQuestionContainer.text=mixWords(day)

        //set the listener
        btCheck.setOnClickListener {
            if(etUsrInput.text.toString().equals(day,ignoreCase = true)){
                val dialog = Dialog(this@MainActivity)
                dialog.setContentView(R.layout.correct_dialog)
                val bthide=dialog.findViewById<Button>(R.id.btConfirmDialog)
                dialog.show()

                bthide.setOnClickListener {
                    dialog.dismiss()

                    day=Days[random.nextInt(Days.size)]
                    txtQuestionContainer.text=mixWords(day)
                    etUsrInput.setText("")
                    txtRightAnswer.visibility=View.INVISIBLE
                    txtCorrectAnswer.visibility=View.INVISIBLE

                }
            }
            else{
                Toast.makeText(this@MainActivity,"you Failed :)",Toast.LENGTH_SHORT).show()
            }

        }

        //set the listener to the Next button
        btNext.setOnClickListener {

            day=Days[random.nextInt(Days.size)]
            txtQuestionContainer.text=mixWords(day)

            etUsrInput.setText("")
            txtRightAnswer.visibility=View.INVISIBLE
            txtCorrectAnswer.visibility=View.INVISIBLE
        }
        //set the listener to the show button
        btShow.setOnClickListener {
            txtCorrectAnswer.visibility= View.VISIBLE
            txtRightAnswer.visibility=View.VISIBLE

            //set the right answer to the txtRightanswer
            txtRightAnswer.text=day
        }
    }
    fun mixWords(word:String):String{
        val word= Arrays.asList<String>(*word.split("".toRegex()).dropLastWhile({it.isEmpty()}).toTypedArray())
        Collections.shuffle(word)
        var mixed = ""
        
        for(i in word){
            mixed +=i
        }
        return mixed
    }
}