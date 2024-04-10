package br.com.snowpet.core

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.snowpet.R
import br.com.snowpet.databinding.MessageDialogBinding

class MessageDialog(
    private val title: String,
    private val description: String? = "",
    private val btnPrimary: String,
    private val btnSecondary: String? = "",
    private val primaryOnClickListener: MessageDialog.() -> Unit,
    private val secondaryOnClickLister: MessageDialog.() -> Unit? = {},
    private val canceledOnTouchOutside: Boolean = false,
) : DialogFragment() {
    private lateinit var binding: MessageDialogBinding

    override fun onStart() {
        super.onStart()
        resizeAndAdjustDialog()
    }

    private fun resizeAndAdjustDialog() {
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.message_dialog, container)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = MessageDialogBinding.bind(view)

        adjustButtonsLayout()
        cancelTOuchOutside()
        setupUI()
        setupListeners()
        alterButtonsAndIcons()

        binding.idTextviewDialogSubtitle.visibility = if (description.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    private fun alterButtonsAndIcons() {
//        binding.buttonPrimaryComponent.setCustomStyle(
//            DsButtonType.Primary,
//            ContextCompat.getColor(requireContext(), statusMessageDialog.color),
//        )
//        binding.buttonPrimaryExtended.setCustomStyle(
//            DsButtonType.Primary,
//            ContextCompat.getColor(requireContext(), statusMessageDialog.color),
//        )

//        val iconColor = ContextCompat.getColor(requireContext(), statusMessageDialog.color)
//        val iconDrawable = ContextCompat.getDrawable(requireContext(), statusMessageDialog.icon)
//        val tintedIcon = DrawableCompat.wrap(iconDrawable!!)
//        DrawableCompat.setTint(tintedIcon, iconColor)
//        binding.icon.setImageDrawable(tintedIcon)

//        val outerCircleColor = ContextCompat.getColor(requireContext(), statusMessageDialog.secondaryColor)
//        val outerCircleDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.circle_default)
//        val tintedOuterCircle = DrawableCompat.wrap(outerCircleDrawable!!)
//        DrawableCompat.setTint(tintedOuterCircle, outerCircleColor)
    }

    private fun adjustButtonsLayout() {
        if (btnSecondary.isNullOrEmpty()) {
            binding.buttonSecondary.visibility = View.GONE
            binding.buttonPrimary.visibility = View.GONE
            binding.buttonPrimaryExtended.visibility = View.VISIBLE
        }
    }

    private fun cancelTOuchOutside() {
        if (canceledOnTouchOutside) {
            dialog?.setOnKeyListener { view1: DialogInterface?, i: Int, keyEvent: KeyEvent ->
                when (keyEvent.keyCode) {
                    KeyEvent.KEYCODE_BACK -> return@setOnKeyListener true
                    else -> return@setOnKeyListener false
                }
            }
            dialog!!.setCanceledOnTouchOutside(!canceledOnTouchOutside)
        }
    }

    private fun setupUI() {
        binding.idTextviewDialogTitle.text = title
        binding.idTextviewDialogSubtitle.text = description
//        binding.buttonPrimaryComponent.set(btnPrimary)
//        binding.buttonPrimaryExtended.setButtonText(btnPrimary)
//        binding.buttonSecondaryComponent.setButtonText(btnSecondary)
    }

    private fun setupListeners() {
        binding.buttonPrimary.setOnClickListener { primaryOnClickListener() }
        binding.buttonPrimaryExtended.setOnClickListener { primaryOnClickListener() }
        binding.buttonSecondary.setOnClickListener { secondaryOnClickLister() }
        binding.closeDialogButton.setOnClickListener { dialog?.dismiss() }
    }
}
