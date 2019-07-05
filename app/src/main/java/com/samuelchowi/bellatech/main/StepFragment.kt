package com.samuelchowi.bellatech.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.samuelchowi.bellatech.R
import com.samuelchowi.bellatech.main.model.StepModel
import kotlinx.android.synthetic.main.fragment_stepper_page.*

class StepFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_stepper_page, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val step: StepModel? = arguments?.getParcelable(STEP_ARGUMENT)
        tvwTitle.text = step?.title
        tvwBody.text = step?.body

        Log.d(TAG, "object used: $step")
    }

    companion object {
        private const val STEP_ARGUMENT: String = "argument_context"
        val TAG: String = StepFragment::class.java.canonicalName.orEmpty()

        fun instance(step: StepModel): StepFragment =
                StepFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(STEP_ARGUMENT, step)
                    }
                }
    }
}