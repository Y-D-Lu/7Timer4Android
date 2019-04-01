package cn.arsenals.seventimer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SDKInitializer.initialize(applicationContext);
        SDKInitializer.setCoordType(CoordType.BD09LL);

        main_btn1.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }
    }
}
