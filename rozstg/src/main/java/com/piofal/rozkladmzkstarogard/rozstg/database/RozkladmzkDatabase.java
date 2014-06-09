package com.piofal.rozkladmzkstarogard.rozstg.database;

/**
 * Created by Piotr on 2014-05-30.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import java.io.File;

public class RozkladmzkDatabase
{
    public static final String DB_NAME = "mzkbb.db";

    public static SQLiteDatabase getDatabase(Context paramContext)
    {
        return SQLiteDatabase.openOrCreateDatabase(new File(paramContext.getFilesDir(), "mzkbb.db"), null);
    }

    public static abstract interface BuslinesColumns extends BaseColumns
    {
        public static final String DESTINATION = "destination";
        public static final String LINE = "line";
    }

    public static abstract interface BusstopsColumns extends BaseColumns
    {
        public static final String BUSLINE_ID = "busline_id";
        public static final String LOCATION_ID = "location_id";
    }

    public static abstract interface DeparturesColumns extends BaseColumns
    {
        public static final String BUSSTOP_ID = "busstop_id";
        public static final String DAY = "day";
        public static final String HOUR = "hour";
        public static final String MINUTE = "minute";
        public static final String SYMBOLS = "symbols";
    }

    public static abstract interface LocationsColumns extends BaseColumns
    {
        public static final String LOCATION = "location";
    }

    public static abstract interface Tables
    {
        public static final String BUSLINES = "buslines";
        public static final String BUSSTOPS = "busstops";
        public static final String DEPARTURES = "departures";
        public static final String LOCATIONS = "locations";
    }
}

