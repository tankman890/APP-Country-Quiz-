package com.example.quizzappat2

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_que.*

class QuizQueActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionNumber: Int = 0
    private var score = 0
    private var user_name: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_que)

        mQuestionsList = Constants.getQuestions()
        Log.i("Questions Size", "${mQuestionsList!!.size}")

        user_name = intent.getStringExtra("user").toString()

        setQuestion()

        tv_optionOne.setOnClickListener(this)
        tv_optionTwo.setOnClickListener(this)
        tv_optionThree.setOnClickListener(this)
        tv_optionFour.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion() {
        //mCurrentPosition = 1
        val question: Question? = mQuestionsList!![mCurrentPosition - 1]

        defaultOptionView()

        if(mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = "FINISH"
        }else {
            btn_submit.text = "Submit"
        }

        progress_bar.progress = mCurrentPosition
        tv_progress.text = "${mCurrentPosition}/${progress_bar.max}"

        tv_que.text = question!!.question
        image.setImageResource(question.image)

        tv_optionOne.text = question.optionOne
        tv_optionTwo.text = question.optionTwo
        tv_optionThree.text = question.optionThree
        tv_optionFour.text = question.optionFour
    }

    private fun defaultOptionView() {
        val option = ArrayList<TextView>()
        option.add(tv_optionOne)
        option.add(tv_optionTwo)
        option.add(tv_optionThree)
        option.add(tv_optionFour)

        for(opt in option) {
            opt.setTextColor(Color.parseColor("#768089"))
            opt.typeface = Typeface.DEFAULT
            opt.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_optionOne -> {
                selectedOption(tv_optionOne, 1)
            }
            R.id.tv_optionTwo -> {
                selectedOption(tv_optionTwo, 2)
            }
            R.id.tv_optionThree -> {
                selectedOption(tv_optionThree, 3)
            }
            R.id.tv_optionFour -> {
                selectedOption(tv_optionFour, 4)
            }
            R.id.btn_submit -> {
                if(mSelectedOptionNumber == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        } else -> {
                            var intent = Intent(this, ResultActivity::class.java)

                            intent.putExtra("score", score)
                            intent.putExtra("user", user_name)

                            startActivity(intent)
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    if(question!!.correctAnswer != mSelectedOptionNumber) {
                        answerView(mSelectedOptionNumber, R.drawable.incorrect_option_border_bg)
                    } else {
                        score++
                    }

                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size) {
                        btn_submit.text = "FINISH"
                    }else {
                        btn_submit.text = "Go To Next Question"
                    }

                    mSelectedOptionNumber = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                tv_optionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tv_optionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tv_optionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                tv_optionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    private fun selectedOption(tv: TextView, optionNumber: Int) {
        defaultOptionView()
        mSelectedOptionNumber = optionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
}