package com.piofal.rozkladmzkstarogard.rozstg.ui;

/**
 * Created by Piotr on 2014-05-30.
 */

import android.view.View;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.LoaderManager;
        import android.support.v4.app.LoaderManager.LoaderCallbacks;
        import android.support.v4.content.Loader;
        import android.support.v4.widget.SimpleCursorAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ListView;
        import android.widget.TextView;

import com.piofal.rozkladmzkstarogard.rozstg.database.RozkladmzkDatabase;
import com.piofal.rozkladmzkstarogard.rozstg.utils.SimpleCursorLoader;

public class FavouritesFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener
{
    private SimpleCursorAdapter mAdapter;
    private SQLiteDatabase mDatabase;
    private ListView mListView;

    public void onActivityCreated(Bundle paramBundle)
    {
        super.onActivityCreated(paramBundle);
        this.mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item, null, new String[] { "name" }, new int[] { 2131099663 }, 2);
        this.mListView.setAdapter(this.mAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    public Loader<Cursor> onCreateLoader(int paramInt, Bundle paramBundle)
    {
        if (this.mDatabase == null)
            this.mDatabase = RozkladmzkDatabase.getDatabase(getActivity());
        return new SimpleCursorLoader(getActivity())
        {
            public Cursor loadInBackground()
            {
                return FavouritesFragment.this.mDatabase.rawQuery("select busstop_id _id, name from favourites", null);
            }
        };
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        View localView = paramLayoutInflater.inflate(R.layout.list, null);
        this.mListView = ((ListView)localView.findViewById(R.id.list));
        this.mListView.setOnItemClickListener(this);
        return localView;
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
        String str = ((TextView)paramView.findViewById(R.id.item_text)).getText().toString();
        ((HomeActivity)getActivity()).addFragment(new DeparturesFragment(paramLong, str));
    }

    public void onLoadFinished(Loader<Cursor> paramLoader, Cursor paramCursor)
    {
        this.mAdapter.swapCursor(paramCursor);
    }

    public void onLoaderReset(Loader<Cursor> paramLoader)
    {
        this.mAdapter.swapCursor(null);
    }

    public void onPause()
    {
        super.onPause();
        if (this.mDatabase != null)
        {
            this.mDatabase.close();
            this.mDatabase = null;
        }
    }

    public void onResume()
    {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }
}

