package com.example.mtalh.hijinnks.MapDirectionTimeDistance;

import java.util.List;

/**
 * Created by CP on 12/28/2017.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
