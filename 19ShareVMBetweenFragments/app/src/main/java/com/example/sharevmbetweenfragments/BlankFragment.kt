package com.example.sharevmbetweenfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class BlankFragment : Fragment() {
    private lateinit var root: View
    private lateinit var viewModel: MyViewModel
    private lateinit var seekBar: SeekBar
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_blank, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seekBar = root.findViewById(R.id.seekBar)
        textView = root.findViewById(R.id.textView)

        // 两个 Fragment 共享 需要注意！
        viewModel = ViewModelProvider(requireActivity())
            .get(MyViewModel::class.java)

        viewModel.score.observe(viewLifecycleOwner) {
            seekBar.setProgress(it, true)
            textView.text = seekBar.progress.toString()
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.changeScore(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

}