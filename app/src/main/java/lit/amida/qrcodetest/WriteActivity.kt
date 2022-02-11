package lit.amida.qrcodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import lit.amida.qrcodetest.databinding.ActivityWriteBinding
import java.util.*

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        // デフォルトでランダムなUUIDをEditTextに挿入
        binding.editText.setText(UUID.randomUUID().toString())

        // データをバーコードに変更するためのインスタンス
        val barcodeEncoder = BarcodeEncoder()

        binding.buttonCreate.setOnClickListener {

            try{
                // ImageViewにBitmap形式の画像を設定
                binding.imageQr.setImageBitmap(
                    // Bitmap形式でQRコードを生成
                    barcodeEncoder.encodeBitmap(binding.editText.text.toString(), BarcodeFormat.QR_CODE, 400, 400)
                )
            } catch (e:Exception){
                // 生成に失敗したらToastで通知
                // ex) データが何も無い，エンコード可能なデータ量を超えた，etc...
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

    }
}