package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import com.rsschool.quiz.databinding.FragmentFinishGameBinding
import java.lang.StringBuilder

class FinishGameFragment : Fragment() {
    private var _binding: FragmentFinishGameBinding? = null
    private val binding get() = _binding!!
    private var score = 0
    private lateinit var answers:IntArray
    private lateinit var questions: MutableList<Question>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinishGameBinding.inflate(inflater, container, false)
        val args = QuizFragmentArgs.fromBundle(requireArguments())
        score = args.score
        answers = args.answerNumber

        //Системная кнопка назад
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            view?.findNavController()
                ?.navigate(FinishGameFragmentDirections.actionFinishGameFragmentToStartFragment())
        }
        binding.apply {
            // share button
            shareBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                    .putExtra(Intent.EXTRA_SUBJECT,"Quiz results")
                    .putExtra(Intent.EXTRA_TEXT, "createMessage")
                startActivity(intent)
            }
            //exit button
            exitBtn.setOnClickListener {
                ActivityCompat.finishAffinity(requireActivity())
            }

            //start new game btn
            startNewGameBtn.setOnClickListener {
                view?.findNavController()?.navigate(FinishGameFragmentDirections.actionFinishGameFragmentToStartFragment())
            }

            //результат
            resultTextView.text =  getScore(answers).toString()
                //createMessage(score,answers)
             //   "Ваш рейтинг: $score из ${answers.size}"

        }
        return binding.root
    }

    private fun createMessage(score:Int, answers:IntArray): String {
        var count = 0
        val stringBuilder = StringBuilder("")
        return stringBuilder.apply {
            append("Ваш результат: ${score} из ${answers.size} \n\n")
            getQuestionsForQuiz().forEach {
                append("На вопрос ${it.question}",
                    "Вы ответили: ${it.answers[answers[count]-1]} \n\n")
                count++
            }
        }.toString()


    }

    private fun getScore(answers: IntArray):Int{
        var score = 0
        var count = 0
        getQuestionsForQuiz().forEach {
            if (it.writeAnswer==it.answers[answers[count]-1])
                score++
                count++
        }
        return score
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}