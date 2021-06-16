package com.rsschool.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.rsschool.quiz.databinding.FragmentStartBinding

//
class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private val questions = getQuestionsForQuiz().toTypedArray()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        //Системная кнопка назад
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            ActivityCompat.finishAffinity(requireActivity())
        }


        binding.startGameBtn.setOnClickListener() {
            view?.findNavController()
                ?.navigate(StartFragmentDirections.actionStartFragmentToQuizFragment(0, 0))
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}