package io.github.rob__.shine.Service;

import android.content.Context;

import io.github.rob__.shine.Model.MyLocation;

public class ConcreteService implements MyService {

    private MyLocation location;

    /**
     * Constructor for the service. Sets up MyLocation which will initialise location retrieval.
     * @param context - Activity context.
     */
    public ConcreteService(Context context){
        location = new MyLocation(context);
    }

    /**
     * Simply returns the last known location.
     * @param context - Activity context
     * @return android.location - last known location, may be null.
     */
    @Override
    public MyLocation getLocation(Context context){
        return location;
    }

}
