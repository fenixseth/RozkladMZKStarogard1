package com.piofal.rozkladmzkstarogard.rozstg.utils;

/**
 * Created by Piotr on 2014-05-30.
 */

import android.database.Cursor;

        import android.content.Context;
        import android.database.Cursor;
        import android.support.v4.content.AsyncTaskLoader;

public abstract class SimpleCursorLoader extends AsyncTaskLoader<Cursor>
{
    private Cursor mCursor;

    public SimpleCursorLoader(Context paramContext)
    {
        super(paramContext);
    }

    public void deliverResult(Cursor paramCursor)
    {
        if (isReset())
            if (paramCursor != null)
                paramCursor.close();
        Cursor localCursor;
        do
        {
            return;
            localCursor = this.mCursor;
            this.mCursor = paramCursor;
            if (isStarted())
                super.deliverResult(paramCursor);
        }
        while ((localCursor == null) || (localCursor == paramCursor) || (localCursor.isClosed()));
        localCursor.close();
    }

    public abstract Cursor loadInBackground();

    public void onCanceled(Cursor paramCursor)
    {
        if ((paramCursor != null) && (!paramCursor.isClosed()))
            paramCursor.close();
    }

    protected void onReset()
    {
        super.onReset();
        onStopLoading();
        if ((this.mCursor != null) && (!this.mCursor.isClosed()))
            this.mCursor.close();
        this.mCursor = null;
    }

    protected void onStartLoading()
    {
        if (this.mCursor != null)
            deliverResult(this.mCursor);
        if ((takeContentChanged()) || (this.mCursor == null))
            forceLoad();
    }

    protected void onStopLoading()
    {
        cancelLoad();
    }
}

