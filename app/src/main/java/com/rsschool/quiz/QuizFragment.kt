package com.rsschool.quiz

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private var questionNumber = 0
    private lateinit var answers:IntArray
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = QuizFragmentArgs.fromBundle(requireArguments())
        questionNumber = args.questionNumber
        setTheme()
        answers = args.answerNumber
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        fillViewQuestion(questionNumber)
        disableButtons()
        pushedRadioButtons(answers)

        //Системная кнопка назад
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
           previousQuestion()
        }

            binding.apply {
                radioGroup.setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {
                        R.id.option_two -> {
                            answers[questionNumber] = 2
                            nextButton.isEnabled = true
                        }
                        R.id.option_one -> {
                            answers[questionNumber] = 1
                            nextButton.isEnabled = true
                        }
                        R.id.option_three -> {
                            answers[questionNumber] = 3
                            nextButton.isEnabled = true
                        }
                        R.id.option_four -> {
                            answers[questionNumber] = 4
                            nextButton.isEnabled = true
                        }
                        R.id.option_five -> {
                            answers[questionNumber] = 5
                            nextButton.isEnabled = true
                        }
                    }
                }
                //кнопка далее
                nextButton.setOnClickListener {
                    questionNumber++
                    if(questionNumber<= getQuestionsForQuiz().size-1) {
                        view?.findNavController()?.navigate(
                            QuizFragmentDirections.actionQuizFragmentSelf(questionNumber,answers))
                    }else{
                        view?.findNavController()?.navigate(
                            QuizFragmentDirections.actionQuizFragmentToFinishGameFragment(questionNumber,answers))
                    }
                }

                //предыдущий вопрос
                previousButton.setOnClickListener{
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
      }
    }

    private fun previousQuestion() {
        if (questionNumber != 0) {
            questionNumber--
            view?.findNavController()?.navigate(QuizFragmentDirections.actionQuizFragmentSelf(questionNumber,answers))
        }
    }

    private fun disableButtons(){
        when(questionNumber) {
            0 -> {
                    binding.previousButton.isEnabled = false
                    binding.toolbar.navigationIcon = null
                    binding.nextButton.isEnabled = answers[questionNumber]>0}
            1-> binding.nextButton.isEnabled = answers[questionNumber]>0
            2-> binding.nextButton.isEnabled = answers[questionNumber]>0
            3-> binding.nextButton.isEnabled = answers[questionNumber]>0
            4->{ binding.nextButton.text = "SUBMIT"
                binding.nextButton.isEnabled=false
                binding.nextButton.isEnabled = answers[questionNumber]>0}
            else-> binding.nextButton.isEnabled = answers[questionNumber]>0
            }
    }

   private fun fillViewQuestion(questionNumber:Int):Int{

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

    private fun setTheme() {
        val theme: Int
        val color: Int
        when (questionNumber) {
            0 -> {
                theme = R.style.Theme_Quiz_First
                color = R.color.first_question_status_bar}
            1 ->{
                theme = R.style.Theme_Quiz_Second
                color = R.color.second_question_status_bar}
            2 -> {
                theme = R.style.Theme_Quiz_Third
                color = R.color.third_question_status_bar}
            3 -> {
                theme = R.style.Theme_Quiz_Fourth
                color = R.color.fourth_question_status_bar}
            else -> {
                theme = R.style.Theme_Quiz_Fifth
                color = R.color.fifth_question_status_bar}
        }
        requireContext().setTheme(theme)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), color)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}