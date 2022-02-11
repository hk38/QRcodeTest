package lit.amida.qrcodetest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import lit.amida.qrcodetest.databinding.ActivityReadBinding


class ReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.buttonReadQr.setOnClickListener {
            // バーコードリーダの設定を作成
            val options = ScanOptions()
            // 画面回転のオンオフ trueで回転しない，falseで回転する
            options.setOrientationLocked(true)
            // 読み込み画面で表示されるテキストを設定
            options.setPrompt("枠内にバーコードを収めてください")
            // バーコードリーダの立ち上げ
            barcodeLauncher.launch(options)
        }

    }

    // バーコードの読み込み後の処理．フィールドとして定義すること
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
        // 読み込んだ内容がnull，つまり何も読み込まなかったときの処理
        if (result.contents == null) Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        // 何かしらを読み込んだときの処理
        else binding.textRead.text = result.contents
    }
}