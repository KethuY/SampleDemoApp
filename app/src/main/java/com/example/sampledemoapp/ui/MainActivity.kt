package com.example.sampledemoapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampledemoapp.R
import com.example.sampledemoapp.databinding.ActivityMainBinding
import com.example.sampledemoapp.repository.DemoNutResult
import com.example.sampledemoapp.ui.helper.getMockDemoNutsData
import com.example.sampledemoapp.viewmodel.DemoNutViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mViewModel: DemoNutViewModel by viewModel()
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        window.statusBarColor = ContextCompat.getColor(this ,R.color.colorPrimarySecondary)
        initObservers()
    }

    private fun initObservers() {
        mBinding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.recyclerView.setHasFixedSize(true)

        mViewModel.demoNutUIModel.observe(this) { model ->
            if (model.isNotEmpty()) {
                mBinding.recyclerView.adapter = DemoNutsAdapter(this,model) {
                    Toast.makeText(this, "${it.name} selected", Toast.LENGTH_LONG).show()
                }
            }
        }

        mViewModel.getDemoNuts().observe(this) {
            it?.let {
                when (it) {
                    is DemoNutResult.Loading -> {
                        showLoader()
                    }

                    is DemoNutResult.Success -> {
                        mViewModel.updateDemoNutUiModel(it.data)
                       hideLoader()
                    }

                    else -> {
                        mViewModel.updateDemoNutUiModel(getMockDemoNutsData())
                        hideLoader()
                    }
                }
            }
        }
    }

    private fun hideLoader() {
        mBinding.progressBar.visibility = View.GONE
    }

    private fun showLoader() {
        mBinding.progressBar.visibility = View.VISIBLE
    }
}