package yukams.app.background_locator_2.provider

import android.location.Location
import android.os.Build
import com.google.android.gms.location.LocationResult
import yukams.app.background_locator_2.Keys
import java.util.HashMap

class LocationParserUtil {
    companion object {
        fun getLocationMapFromLocation(location: Location): HashMap<Any, Any> {
            var speedAccuracy = 0f
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                speedAccuracy = location.speedAccuracyMetersPerSecond
            }
            var isMocked = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                isMocked = location.isFromMockProvider
            }

            return hashMapOf(
                Keys.ARG_IS_MOCKED as Any to isMocked,
                Keys.ARG_LATITUDE as Any to location.latitude,
                Keys.ARG_LONGITUDE as Any to location.longitude,
                Keys.ARG_ACCURACY as Any to location.accuracy,
                Keys.ARG_ALTITUDE as Any to location.altitude,
                Keys.ARG_SPEED as Any to location.speed,
                Keys.ARG_SPEED_ACCURACY as Any to speedAccuracy,
                Keys.ARG_HEADING as Any to location.bearing,
                Keys.ARG_TIME as Any to location.time.toDouble(),
                Keys.ARG_PROVIDER as Any to (location.provider ?: "unknown"),

//                Keys.ARG_PROVIDER as Any to location.provider,
            )

//            return hashMapOf(
//                    Keys.ARG_IS_MOCKED to isMocked,
//                    Keys.ARG_LATITUDE to location.latitude,
//                    Keys.ARG_LONGITUDE to location.longitude,
//                    Keys.ARG_ACCURACY to location.accuracy,
//                    Keys.ARG_ALTITUDE to location.altitude,
//                    Keys.ARG_SPEED to location.speed,
//                    Keys.ARG_SPEED_ACCURACY to speedAccuracy,
//                    Keys.ARG_HEADING to location.bearing,
//                    Keys.ARG_TIME to location.time.toDouble(),
//                    Keys.ARG_PROVIDER to location.provider,
//            )
        }

        fun getLocationMapFromLocation(location: LocationResult?): HashMap<Any, Any>? {
            val firstLocation = location?.lastLocation ?: return null

            var speedAccuracy = 0f
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                speedAccuracy = firstLocation.speedAccuracyMetersPerSecond
            }
            var isMocked = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                isMocked = firstLocation.isFromMockProvider
            }

            return hashMapOf(
                    Keys.ARG_IS_MOCKED to isMocked,
                    Keys.ARG_LATITUDE to firstLocation.latitude,
                    Keys.ARG_LONGITUDE to firstLocation.longitude,
                    Keys.ARG_ACCURACY to firstLocation.accuracy,
                    Keys.ARG_ALTITUDE to firstLocation.altitude,
                    Keys.ARG_SPEED to firstLocation.speed,
                    Keys.ARG_SPEED_ACCURACY to speedAccuracy,
                    Keys.ARG_HEADING to firstLocation.bearing,
                    Keys.ARG_TIME to firstLocation.time.toDouble())
        }
    }
}