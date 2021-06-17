package com.rsschool.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private lateinit var questions: MutableList<Question>
    private var questionNumber = 0
    private var score = 0
    private var wrQuest = false
    private lateinit var answers:IntArray
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = QuizFragmentArgs.fromBundle(requireArguments())
        score = args.score
        questionNumber = args.questionNumber
        answers = args.answerNumber
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        fillViewQuestion(questionNumber)
        disableButtons()
        pushedRadioButtons(answers)

            binding.apply {
                radioGroup.setOnCheckedChangeListener { group, checkedId ->
                    if (checkedId == R.id.option_two) {
                        answers.set(questionNumber,2)
                        if (optionTwo.text == getQuestionsForQuiz()[questionNumber].writeAnswer){
                            wrQuest=true
                        }
                    } else if (checkedId == R.id.option_one) {
                    answers.set(questionNumber,1)
                    if (optionOne.text == getQuestionsForQuiz()[questionNumber].writeAnswer) {
                        wrQuest = true
                    }
                    }else if(checkedId==R.id.option_three){
                        answers.set(questionNumber,3)
                        if (optionThree.text== getQuestionsForQuiz()[questionNumber].writeAnswer){
                            wrQuest=true
                        }
                    }else if(checkedId==R.id.option_four){
                        answers.set(questionNumber,4)
                        if (optionFour.text== getQuestionsForQuiz()[questionNumber].writeAnswer){
                            wrQuest=true
                        }
                    }else if (checkedId==R.id.option_five) {
                        answers.set(questionNumber, 5)
                        if (optionFive.text == getQuestionsForQuiz()[questionNumber].writeAnswer) {
                            wrQuest = true
                        }
                    }
                }
                    //кнопка далее
                nextButton.setOnClickListener {
                    questionNumber++
                    if(questionNumber<= getQuestionsForQuiz().size-1) {
                        if(wrQuest){
                            score++
                        }
                        view?.findNavController()?.navigate(
                            QuizFragmentDirections.actionQuizFragmentSelf(score, questionNumber,answers))
                    }else{
                        view?.findNavController()?.navigate(
                            QuizFragmentDirections.actionQuizFragmentToFinishGameFragment(score,questionNumber,answers))
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

    private fun pushedRadioButtons(answers: IntArray) {
      when(answers[questionNumber]){
          1->binding.optionOne.isChecked = true
          2->binding.optionTwo.isChecked = true
          3->binding.optionThree.isChecked = true
          4->binding.optionFour.isChecked = true
          5->binding.optionFive.isChecked = true
          else-> Toast.makeText(context, "$score", Toast.LENGTH_SHORT).show()
      }
    }

    private fun previousQuestion() {
        if (questionNumber != 0) {
            questionNumber--
            view?.findNavController()?.navigate(QuizFragmentDirections.actionQuizFragmentSelf(score, questionNumber,answers))
        }
    }

    private fun disableButtons(){
         if (questionNumber==0) {
             binding.previousButton.isEnabled = false
            // binding.toolbar.collapseIco
         }else if (questionNumber==4){
             binding.nextButton.text = "SHARE"
         }
    }



   fun fillViewQuestion(questionNumber:Int):Int{

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