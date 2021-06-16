package com.rsschool.quiz

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.findNavController
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private lateinit var questions: MutableList<Question>
    private var questionNumber = 0
    private var score = 0
    private var wrQuest = false
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = QuizFragmentArgs.fromBundle(requireArguments())
        score = args.score
        questionNumber = args.questionNumber
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
         fillViewQuestion(questionNumber)
        disableButtons()

            binding.apply {
                radioGroup.setOnCheckedChangeListener { group, checkedId ->
                    if (checkedId == R.id.option_two && optionTwo.text == getQuestionsForQuiz()[questionNumber].writeAnswer) {
                        wrQuest=true
                        Toast.makeText(context, "$score", Toast.LENGTH_SHORT).show()
                    } else if (checkedId == R.id.option_one && optionOne.text == getQuestionsForQuiz()[questionNumber].writeAnswer) {
                        wrQuest=true
                        Toast.makeText(context, "$score", Toast.LENGTH_SHORT).show()
                    } else if (checkedId == R.id.option_three && optionThree.text == getQuestionsForQuiz()[questionNumber].writeAnswer) {
                        wrQuest=true
                        Toast.makeText(context, "$score", Toast.LENGTH_SHORT).show()
                    } else if (checkedId == R.id.option_four && optionFour.text == getQuestionsForQuiz()[questionNumber].writeAnswer) {
                        wrQuest=true
                        Toast.makeText(context, "$score", Toast.LENGTH_SHORT).show()
                    }
                }

                nextButton.setOnClickListener {
                    Toast.makeText(context, "$questionNumber", Toast.LENGTH_SHORT).show()
                    questionNumber++
                    Toast.makeText(context, "$questionNumber", Toast.LENGTH_SHORT).show()
                    if(questionNumber<= getQuestionsForQuiz().size-1) {
                        if(wrQuest){
                            score++
                            Toast.makeText(context, "$score", Toast.LENGTH_SHORT).show()
                        }
                        view?.findNavController()?.navigate(
                            QuizFragmentDirections.actionQuizFragmentSelf(score, questionNumber))
                    }else{
                        view?.findNavController()?.navigate(
                            QuizFragmentDirections.actionQuizFragmentToFinishGameFragment())
                    }
                }

                //предыдущий вопрос
                previousButton.setOnClickListener {
                    previousQuestion()
                }
                    // предыдущий вопрос на тулбаре
                toolbar.setNavigationOnClickListener {
                    previousQuestion()
                }
                }
        return binding.root
    }

    private fun previousQuestion() {
        if (questionNumber != 0) {
            questionNumber--
            view?.findNavController()?.navigate(QuizFragmentDirections.actionQuizFragmentSelf(score, questionNumber))
        }
    }

    private fun disableButtons(){
         if (questionNumber==0) {
             binding.previousButton.isEnabled = false
         }else if (questionNumber==4){
             binding.nextButton.text = "SHARE"
         }
    }



   fun fillViewQuestion(questionNumber:Int):Int{
      // val questionNumber = questionNumber
       var score = score
       val thisQuestion = getQuestionsForQuiz()[questionNumber]
           binding.apply {
               radioGroup.clearCheck()
               toolbar.title = "Question ${questionNumber + 1}"

               question.text = thisQuestion.question
               optionOne.text = thisQuestion.answers[0]
               optionTwo.text = thisQuestion.answers[1]
               optionThree.text = thisQuestion.answers[2]
               optionFour.text = thisQuestion.answers[3]
               optionFive.text = thisQuestion.answers[4]
               }
       return questionNumber
   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}