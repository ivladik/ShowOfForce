package com.example.feature_movies.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.presentation.ui.fragment.BaseFragment
import com.example.feature_movies.R
import com.example.feature_movies.di.MoviesSubcomponent
import com.example.feature_movies.presentation.view_model.MoviesStubViewModel
import java.lang.IllegalStateException

class MoviesStubFragment : BaseFragment<MoviesStubViewModel>() {

    companion object {

        const val TAG = "MoviesStubFragment"

        fun newInstance(): MoviesStubFragment {
            return MoviesStubFragment()
        }
    }

    override fun getViewModelClass() = MoviesStubViewModel::class.java

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MoviesSubcomponent.instance
            ?.injectMoviesStubFragment(this)
            ?: throw IllegalStateException("ShowToursSubcomponent cannot be null")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_movies_stub, container, false)
    }
}