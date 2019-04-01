package cn.arsenals.seventimer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import cn.arsenals.seventimer.utils.LogUtil
import com.baidu.mapapi.map.BaiduMap
import kotlinx.android.synthetic.main.activity_map.*
import com.baidu.mapapi.map.MapPoi
import com.baidu.mapapi.model.LatLng

class MapActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MapActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        bmapView.map.setOnMapClickListener(object : BaiduMap.OnMapClickListener {
            override fun onMapPoiClick(mapPoi: MapPoi?): Boolean {
                LogUtil.i(TAG, "onMapPoiClick, LatLng : ${mapPoi?.position}")
                if (mapPoi != null) {
                    showSnackBar(mapPoi.position)
                } else {
                    LogUtil.w(TAG, "Null MapPoi generated")
                }
                return false
            }

            override fun onMapClick(coord: LatLng?) {
                LogUtil.i(TAG, "onMapClick, LatLng : $coord")
                if (coord != null) {
                    showSnackBar(coord)
                } else {
                    LogUtil.w(TAG, "Null LatLng generated")
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        bmapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        bmapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        bmapView.onDestroy()
    }

    fun showSnackBar(coord: LatLng) {
        Snackbar.make(this.bmapView, "goto 7timer.info", Snackbar.LENGTH_LONG).setAction("GO") {
            val url = "http://www.7timer.info/index.php?product=civil&lon=" + coord.longitude + "&lat=" + coord.latitude + "&lang=zh-CN&tzshift=0"
            startActivity(Intent(this, WebActivity::class.java).putExtra("url", url))
        }.show()
    }
}
