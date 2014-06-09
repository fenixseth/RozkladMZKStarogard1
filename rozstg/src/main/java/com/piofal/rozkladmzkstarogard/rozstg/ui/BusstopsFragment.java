package com.piofal.rozkladmzkstarogard.rozstg.ui;

/**
 * Created by Piotr on 2014-05-30.
 */

import com.piofal.rozkladmzkstarogard.rozstg.database.RozkladmzkDatabase;
import com.piofal.rozkladmzkstarogard.rozstg.utils.SimpleCursorLoader;

import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.LoaderManager;
        import android.support.v4.app.LoaderManager.LoaderCallbacks;
        import android.support.v4.content.Loader;
        import android.support.v4.widget.SimpleCursorAdapter;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;

public class BusstopsFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>, TextWatcher, AdapterView.OnItemClickListener
{
    private static final String STATE_BUSLINE_ID = "busline-id";
    private static final String STATE_TITLE = "title";
    private SimpleCursorAdapter mAdapter;
    private long mBuslineId = -1L;
    private SQLiteDatabase mDatabase;
    private ListView mListView;
    private String mSelection = "";
    private String[] mSelectionArgs = null;
    private String mTitle = "Przystanki";
    private TextView mTitleView;

    public BusstopsFragment()
    {
    }

    public BusstopsFragment(long paramLong, String paramString)
    {
        this.mBuslineId = paramLong;
        this.mTitle = paramString;
    }

    public void afterTextChanged(Editable paramEditable)
    {
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }

    public void onActivityCreated(Bundle paramBundle)
    {
        super.onActivityCreated(paramBundle);
        this.mTitleView.setText(this.mTitle);
        this.mAdapter = new SimpleCursorAdapter(getActivity(), 2130903045, null, new String[] { "location" }, new int[] { 2131099663 }, 2);
        this.mListView.setAdapter(this.mAdapter);
        Bundle localBundle = new Bundle();
        localBundle.putLong("busline-id", this.mBuslineId);
        getLoaderManager().initLoader(0, localBundle, this);
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        if (paramBundle != null)
        {
            this.mBuslineId = paramBundle.getLong("busline-id", -1L);
            this.mTitle = paramBundle.getString("title");
        }
    }

    public Loader<Cursor> onCreateLoader(int paramInt, final Bundle paramBundle)
    {
        if (this.mDatabase == null)
            this.mDatabase = RozkladmzkDatabase.getDatabase(getActivity());
        return new SimpleCursorLoader(getActivity())
        {
            public Cursor loadInBackground()
            {
                if (paramBundle.getLong("busline-id") == -1L)
                    return BusstopsFragment.this.mDatabase.query("locations", new String[] { "_id", "location" }, BusstopsFragment.this.mSelection, BusstopsFragment.this.mSelectionArgs, null, null, "location");
                if (BusstopsFragment.this.mSelection.equals(""));
                for (String str = ""; ; str = " AND " + BusstopsFragment.this.mSelection)
                    return BusstopsFragment.this.mDatabase.rawQuery("select busstops._id, locations.location from busstops join locations on busstops.location_id = locations._id and busstops.busline_id=" + BusstopsFragment.this.mBuslineId + str, BusstopsFragment.this.mSelectionArgs);
            }
        };
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        View localView = paramLayoutInflater.inflate(R.layout.list_with_search, null);
        this.mTitleView = ((TextView)localView.findViewById(R.id.list_title));
        this.mListView = ((ListView)localView.findViewById(R.id.list));
        ((EditText)localView.findViewById(R.id.search_box)).addTextChangedListener(this);
        this.mListView.setOnItemClickListener(this);
        return localView;
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
        HomeActivity localHomeActivity = (HomeActivity)getActivity();
        String str = ((TextView)paramView.findViewById(R.id.item_text)).getText().toString();
        if (this.mBuslineId == -1L)
        {
            localHomeActivity.addFragment(new BuslinesFragment(paramLong, str));
            return;
        }
        localHomeActivity.addFragment(new DeparturesFragment(paramLong, this.mTitle + " - " + str));
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
        if (this.mDatabase == null)
            this.mDatabase = RozkladmzkDatabase.getDatabase(getActivity());
    }

    public void onSaveInstanceState(Bundle paramBundle)
    {
        super.onSaveInstanceState(paramBundle);
        paramBundle.putLong("busline-id", this.mBuslineId);
        paramBundle.putString("title", this.mTitle);
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
        if (paramCharSequence.equals(""))
            this.mSelection = "";
        String[] arrayOfString;
        for (this.mSelectionArgs = null; ; this.mSelectionArgs = arrayOfString)
        {
            if (this.mDatabase != null)
            {
                Bundle localBundle = new Bundle();
                localBundle.putLong("busline-id", this.mBuslineId);
                getLoaderManager().restartLoader(0, localBundle, this);
            }
            return;
            /*StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("UPPER(");
            localStringBuilder.append("location");
            localStringBuilder.append(") GLOB ?");
            this.mSelection = localStringBuilder.toString();
            arrayOfString = new String[1];
            arrayOfString[0] = ("*" + paramCharSequence.toString().toUpperCase() + "*");
			*/
        }
    }
}

