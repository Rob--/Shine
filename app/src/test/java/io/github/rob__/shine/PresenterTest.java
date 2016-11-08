package io.github.rob__.shine;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import org.junit.Test;
import org.junit.Assert;

import io.github.rob__.shine.Model.MyLocation;
import io.github.rob__.shine.Service.MyService;

public class PresenterTest {

    Context c = new Activity();

    @Test
    public void testShowLocation(){
        final MyLocation loc = new MyLocation(c);

        MyView view = new MyView() {
            @Override
            public void showLocation(Location location){
                loc.setLocation(location);
            }
        };

        final MyService service = new MyService() {
            @Override
            public MyLocation getLocation(Context context) {
                return loc;
            }
        };

        MyPresenter p = new MyPresenter(view, service, null);
        p.getLocation();

        Assert.assertNotNull(loc);
        Log.d("Lat", String.valueOf(loc.getLocation().getLatitude()));
    }

}
