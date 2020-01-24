package com.example.core.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.NavigationCommand
import com.example.core.extension.injectViewModel
import com.example.core.presentation.view_model.BaseViewModel
import javax.inject.Inject

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = injectViewModel(viewModelFactory)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[getViewModelClass()]
        viewModel.getNavigationCommands()
            .observe(
                viewLifecycleOwner,
                Observer { command ->
                    when (command) {
                        is NavigationCommand.To -> findNavController().navigate(command.directions)
                        is NavigationCommand.Back -> TODO()
                        is NavigationCommand.BackTo -> TODO()
                        is NavigationCommand.ToRoot -> TODO()
                    }
                }
            )
    }

    abstract fun getViewModelClass(): Class<T>
}