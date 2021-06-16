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
    private lateinit var questions: MutableList<Question>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinishGameBinding.inflate(inflater, container, false)

//        val args = FragmentFinishGameBinding.fromBundle(requireArguments())
//        questions = args.questions.toMutableList()

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
        }
        return binding.root
    }

//    private fun createMessage(): String {
//        var count = 1
//        val stringBuilder = StringBuilder("")
//        return stringBuilder.apply {
//            append("Your result: ${rightAnswers()} из ${questions.size} \n\n")
//            for (question in questions) {
//                append(
//                    "${count++}) ${question.question}\n" +
//                            "Your answer: ${question.answers?.get(question.answer)} \n\n"
//                )
//            }
//        }.toString()
//
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}