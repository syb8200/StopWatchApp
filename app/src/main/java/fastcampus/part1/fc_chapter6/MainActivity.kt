package fastcampus.part1.fc_chapter6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import fastcampus.part1.fc_chapter6.databinding.ActivityMainBinding
import fastcampus.part1.fc_chapter6.databinding.DialogCountdownSettingBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var countdownSecond = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.startButton.setOnClickListener {
            start()
            binding.startButton.isVisible = false
            binding.stopButton.isVisible = false
            binding.pauseButton.isVisible = true
            binding.lapButton.isVisible = true
        }

        binding.stopButton.setOnClickListener {
            // 정지 버튼 클릭 -> 다이얼로그 -> 정지
            showAlertDialog()
        }

        binding.pauseButton.setOnClickListener {
            pause()
            binding.startButton.isVisible = true
            binding.stopButton.isVisible = true
            binding.pauseButton.isVisible = false
            binding.lapButton.isVisible = false
        }

        binding.lapButton.setOnClickListener {
            lap()
        }

        binding.countdownTextView.setOnClickListener {
            showCountdownSettingDialog()
        }

    }

    private fun start() {

    }

    private fun stop() {
        binding.startButton.isVisible = true
        binding.stopButton.isVisible = true
        binding.pauseButton.isVisible = false
        binding.lapButton.isVisible = false
    }

    private fun pause() {

    }

    private fun lap() {

    }

    private fun showCountdownSettingDialog() {
        AlertDialog.Builder(this).apply {
            // NumberPicker
            val dialogBinding = DialogCountdownSettingBinding.inflate(layoutInflater)
            setView(dialogBinding.root)

            with(dialogBinding.countdownSecondPicker) {
                maxValue = 20
                minValue = 0
                value = countdownSecond
            }

            setTitle("카운트다운 설정")
            setPositiveButton("확인") { _, _ ->
                countdownSecond = dialogBinding.countdownSecondPicker.value
                // String format 사용 -> 01초, 02초 ... 형식 맞춰주기 위해서
                // %02d% : 00 형식의 숫자가 나타나게 된다.
                binding.countdownTextView.text = String.format("%02d", countdownSecond)
            }
            setNegativeButton("취소", null)
        }.show()
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("종료하시겠습니까?")
            setPositiveButton("네") { _, _ ->
                stop()
            }
            setNegativeButton("아니오", null)
        }.show()
    }
}