package br.com.snowpet.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.snowpet.R
import br.com.snowpet.core.MessageDialog
import br.com.snowpet.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this
        viewModel.setTeste(7)

        setUpObservers()

        MessageDialog(
            title = "teste",
            btnPrimary = "OK",
            primaryOnClickListener = { dialog?.dismiss() },
        ).show(supportFragmentManager, "TAG")
    }

    private fun setUpObservers() {
        viewModel.teste.observe(this) {
            binding?.numero?.text = viewModel.teste.value.toString()
        }
    }
}
