package com.example.feature_movies.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feature_movies.R

class MoviesStubFragment : Fragment() {

    companion object {

        const val TAG = "MoviesStubFragment"

        fun newInstance(): MoviesStubFragment {
            return MoviesStubFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_movies_stub, container, false)
    }
}