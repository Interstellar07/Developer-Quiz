package com.google.suryansh7202.developerquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ResultActivity : AppCompatActivity() {
    private var sendCurrentPosition:Int = 0
    private var mSelected_Quiz: String? =null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val iv_info = findViewById<ImageView>(R.id.iv_info)
        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvScore = findViewById<TextView>(R.id.tv_score)
        val btnFinish = findViewById<Button>(R.id.btn_finish)
        val share = findViewById<ImageView>(R.id.share)
        val wish = findViewById<TextView>(R.id.wish_congratulation)
        val resultAnaysis = findViewById<Button>(R.id.btn_result_analysis)
        val questionSelectedOptions = intent.getSerializableExtra("QuestionsExtra") as ArrayList<*>?
        sendCurrentPosition = intent.getIntExtra(Constants.Send_Current_Position,0)
        mSelected_Quiz = intent.getStringExtra(Constants.SELECTED_QUIZ)




        tvName.text = intent.getStringExtra(Constants.USER_NAME)

        val CorrectAns = intent.getIntExtra(Constants.CORRECT_ANSWER,0)
        val WrongAns = intent.getIntExtra(Constants.WRONG_ANSWER,0)
        val NotSelected = intent.getIntExtra(Constants.NOT_SELECTED,0)
        val TotalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)


        if(CorrectAns<=32){
            wish.text = "Better Luck Next Time"

        }else if(CorrectAns in 33..70){
            wish.text = "Keep it up"

        }


        tvScore.text = "Your Score is ${CorrectAns} out of ${TotalQuestion}."

        btnFinish.setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
        }


        iv_info.setOnClickListener{

            val url = "https://github.com/Suryansh1720001/Developer-Quiz/blob/main/README.md"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)


        }

        share.setOnClickListener{

            val sendIntent = Intent()
            sendIntent.type = "text/plain"
            sendIntent.action = Intent.ACTION_SEND
//          "Download this App - \n *My Score is ${CorrectAns} out of ${TotalQuestion}.* \n\nTry this Quiz App and learn in better way\nUsing this link \uD83D\uDC47\nhttps://github.com/Suryansh1720001/Developer Quiz-Application"
            val body ="\uD835\uDC03\uD835\uDC28\uD835\uDC30\uD835\uDC27\uD835\uDC25\uD835\uDC28\uD835\uDC1A\uD835\uDC1D \uD835\uDC2D\uD835\uDC21\uD835\uDC22\uD835\uDC2C \uD835\uDC1A\uD835\uDC29\uD835\uDC29 -\n" +
                    "\n" +
                    "\uD835\uDC0C\uD835\uDC32 \uD835\uDC12\uD835\uDC1C\uD835\uDC28\uD835\uDC2B\uD835\uDC1E ${CorrectAns} \uD835\uDC28\uD835\uDC2E\uD835\uDC2D \uD835\uDC28\uD835\uDC1F ${TotalQuestion}\n" +
                    "\n" +
                    "\uD835\uDC13\uD835\uDC1A\uD835\uDC24\uD835\uDC1E \uD835\uDC29\uD835\uDC1A\uD835\uDC2B\uD835\uDC2D \uD835\uDC22\uD835\uDC27 \uD835\uDC2D\uD835\uDC21\uD835\uDC22\uD835\uDC2C \uD835\uDC03\uD835\uDC1E\uD835\uDC2F\uD835\uDC1E\uD835\uDC25\uD835\uDC28\uD835\uDC29\uD835\uDC1E\uD835\uDC2B \uD835\uDC10\uD835\uDC2E\uD835\uDC22\uD835\uDC33 \uD835\uDC1A\uD835\uDC27\uD835\uDC1D \uD835\uDC1E\uD835\uDC27\uD835\uDC21\uD835\uDC1A\uD835\uDC27\uD835\uDC1C\uD835\uDC1E \uD835\uDC32\uD835\uDC28\uD835\uDC2E\uD835\uDC2B \uD835\uDC24\uD835\uDC27\uD835\uDC28\uD835\uDC30\uD835\uDC25\uD835\uDC1E\uD835\uDC1D\uD835\uDC20\uD835\uDC1E.\n" +
                    "\uD835\uDC14\uD835\uDC2C\uD835\uDC22\uD835\uDC27\uD835\uDC20 \uD835\uDC2D\uD835\uDC21\uD835\uDC22\uD835\uDC2C \uD835\uDC25\uD835\uDC22\uD835\uDC27\uD835\uDC24 \uD83D\uDC47\uD83C\uDFFB\n" +
                    "https://github.com/Suryansh1720001/Developer-Quiz/blob/main/README.md"
            sendIntent.putExtra(Intent.EXTRA_TEXT,body)
            Intent.createChooser(sendIntent, "Share using")
            startActivity(sendIntent)
        }


        resultAnaysis.setOnClickListener{
            val intent = Intent(this,PieChart::class.java)
            intent.putParcelableArrayListExtra(
                "QuestionsExtra",
                questionSelectedOptions as ArrayList<out Parcelable?>?
            )
            intent.putExtra(Constants.Send_Current_Position,sendCurrentPosition)
            intent.putExtra(Constants.CORRECT_ANSWER,CorrectAns)
            intent.putExtra(Constants.WRONG_ANSWER,WrongAns)
            intent.putExtra(Constants.NOT_SELECTED,NotSelected)
            intent.putExtra(Constants.SELECTED_QUIZ, mSelected_Quiz)
            intent.putExtra(Constants.TOTAL_QUESTIONS,TotalQuestion)


            startActivity(intent)

        }

    }
}
