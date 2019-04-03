package cn.arsenals.seventimer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import cn.arsenals.seventimer.utils.LogUtil
import cn.arsenals.seventimer.utils.PermissionUtil
import com.baidu.mapapi.map.BaiduMap
import kotlinx.android.synthetic.main.activity_map.*
import com.baidu.mapapi.map.MapPoi
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.map.MyLocationData
import com.baidu.location.BDLocation
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer


class MapActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MapActivity"
    }

    private lateinit var mLocationClient : LocationClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SDKInitializer.initialize(applicationContext);
        SDKInitializer.setCoordType(CoordType.BD09LL);

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

        bmapView.map.isMyLocationEnabled = true;

        mLocationClient = LocationClient(this)
        val option = LocationClientOption()
        option.isOpenGps = true
        option.setCoorType("bd09ll")
        option.setScanSpan(1000)
        mLocationClient.locOption = option
        val myLocationListener = MyLocationListener()
        mLocationClient.registerLocationListener(myLocationListener)
        mLocationClient.start()
    }

    override fun onResume() {
        super.onResume()

        val lackedPermissions = PermissionUtil.checkPermission(this, PermissionUtil.PERMISSIONS)
        if (lackedPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, lackedPermissions, 0)
        }

        bmapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        bmapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient.stop();
        bmapView.map.isMyLocationEnabled = false;
        bmapView.onDestroy();
    }

    fun showSnackBar(coord: LatLng) {
        Snackbar.make(this.bmapView, "goto 7timer.info", Snackbar.LENGTH_LONG).setAction("GO") {
            val url = "http://www.7timer.info/index.php?product=civil&lon=" + coord.longitude + "&lat=" + coord.latitude + "&lang=zh-CN&tzshift=0"
            startActivity(Intent(this, WebActivity::class.java).putExtra("url", url))
        }.show()
    }

    inner class MyLocationListener : BDAbstractLocationListener() {
        override fun onReceiveLocation(location: BDLocation?) {
            if (location == null || bmapView == null) {
                return
            }
            val locData = MyLocationData.Builder()
                    .accuracy(location.radius)
                    .direction(location.direction).latitude(location.latitude)
                    .longitude(location.longitude).build()
            bmapView.map.setMyLocationData(locData)
        }
    }
}
