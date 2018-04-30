package com.example.gabi.busk_13_09; /**
 * Created by gabi on 28/10/2016.
 */
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
public class Clusterr implements ClusterItem {



    /**
     * Created by Dimitar Danailov on 6/3/15.
     * email: dimityr.danailov@gmail.com
     *
     * Documentation: https://developers.google.com/maps/documentation/android/utility/marker-clustering
     */

        private final LatLng mPosition;

        public Clusterr(double latitude, double longitude) {
            mPosition = new LatLng(latitude, longitude);
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

}
