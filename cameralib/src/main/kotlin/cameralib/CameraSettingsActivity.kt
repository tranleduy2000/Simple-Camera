package cameralib

import android.annotation.SuppressLint
import android.os.Bundle
import cameralib.databinding.CamlibActivitySettingsBinding
import cameralib.dialogs.RadioGroupDialog
import cameralib.extensions.config
import cameralib.models.CaptureMode
import cameralib.models.RadioItem
import viewBinding

class CameraSettingsActivity : BaseSimpleActivity() {

    private val binding by viewBinding(CamlibActivitySettingsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setSupportActionBar(binding.settingsToolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupSound()
        setupVolumeButtonsAsShutter()
        setupFlipPhotos()
        setupSavePhotoMetadata()
        setupPhotoQuality()
        setupCaptureMode()
    }

    private fun setupSound() = binding.apply {
        settingsSound.isChecked = config.isSoundEnabled
        settingsSound.setOnCheckedChangeListener { _, isChecked ->
            config.isSoundEnabled = isChecked
        }
    }

    private fun setupVolumeButtonsAsShutter() = binding.apply {
        settingsVolumeButtonsAsShutter.isChecked = config.volumeButtonsAsShutter
        settingsVolumeButtonsAsShutterHolder.setOnClickListener {
            settingsVolumeButtonsAsShutter.toggle()
            config.volumeButtonsAsShutter = settingsVolumeButtonsAsShutter.isChecked
        }
    }

    private fun setupFlipPhotos() = binding.apply {
        settingsFlipPhotos.isChecked = config.flipPhotos
        settingsFlipPhotosHolder.setOnClickListener {
            settingsFlipPhotos.toggle()
            config.flipPhotos = settingsFlipPhotos.isChecked
        }
    }

    private fun setupSavePhotoMetadata() = binding.apply {
        settingsSavePhotoMetadata.isChecked = config.savePhotoMetadata
        settingsSavePhotoMetadataHolder.setOnClickListener {
            settingsSavePhotoMetadata.toggle()
            config.savePhotoMetadata = settingsSavePhotoMetadata.isChecked
        }
    }

    private fun setupPhotoQuality() {
        updatePhotoQuality(config.photoQuality)
        binding.settingsPhotoQualityHolder.setOnClickListener {
            val items = arrayListOf(
                RadioItem(100, "100%"),
                RadioItem(95, "95%"),
                RadioItem(90, "90%"),
                RadioItem(85, "85%"),
                RadioItem(80, "80%"),
                RadioItem(75, "75%"),
                RadioItem(70, "70%"),
                RadioItem(65, "65%"),
                RadioItem(60, "60%"),
                RadioItem(55, "55%"),
                RadioItem(50, "50%")
            )

            RadioGroupDialog(this@CameraSettingsActivity, items, config.photoQuality) {
                config.photoQuality = it as Int
                updatePhotoQuality(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updatePhotoQuality(quality: Int) {
        binding.settingsPhotoQuality.text = "$quality%"
    }

    private fun setupCaptureMode() {
        updateCaptureMode(config.captureMode)
        binding.settingsCaptureModeHolder.setOnClickListener {
            val items = CaptureMode.entries.toTypedArray().mapIndexed { index, captureMode ->
                RadioItem(index, getString(captureMode.stringResId), captureMode)
            }

            RadioGroupDialog(this@CameraSettingsActivity, ArrayList(items), config.captureMode.ordinal) {
                config.captureMode = it as CaptureMode
                updateCaptureMode(it)
            }
        }
    }

    private fun updateCaptureMode(captureMode: CaptureMode) {
        binding.settingsCaptureMode.text = getString(captureMode.stringResId)
    }
}
