package com.piofal.rozkladmzkstarogard.rozstg.ui;

/**
 * Created by Piotr on 2014-05-30.
 */
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
import com.piofal.rozkladmzkstarogard.rozstg.R;
import com.piofal.rozkladmzkstarogard.rozstg.database.RozkladmzkDatabase;
import com.piofal.rozkladmzkstarogard.rozstg.utils.SimpleCursorLoader;

public class BuslinesFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>, TextWatcher, AdapterView.OnItemClickListener
{
    private static final String STATE_LOCATION_ID = "location-id";
    private static final String STATE_TITLE = "title";
    private SimpleCursorAdapter mAdapter;
    private SQLiteDatabase mDatabase;
    private ListView mListView;
    private long mLocationId = -1L;
    private String mSelection = "";
    private String[] mSelectionArgs = null;
    private String mTitle;
    private TextView mTitleView;

    public BuslinesFragment()
    {
    }

    public BuslinesFragment(long paramLong, String paramString)
    {
        this.mLocationId = paramLong;
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
        TextView localTextView = this.mTitleView;
        if (this.mTitle == null);
        for (String str = "Linie"; ; str = this.mTitle)
        {
            localTextView.setText(str);
            this.mAdapter = new SimpleCursorAdapter(getActivity(), 2130903045, null, new String[0], null, 2)
            {
                public void bindView(View paramAnonymousView, Context paramAnonymousContext, Cursor paramAnonymousCursor)
                {
                    ((TextView)paramAnonymousView.findViewById(R.id.item_text)).setText(paramAnonymousCursor.getString(paramAnonymousCursor.getColumnIndex("line")) + "/" + paramAnonymousCursor.getString(paramAnonymousCursor.getColumnIndex("destination")));
                }
            };
            this.mListView.setAdapter(this.mAdapter);
            Bundle localBundle = new Bundle();
            localBundle.putLong("location-id", this.mLocationId);
            getLoaderManager().initLoader(0, localBundle, this);
            return;
        }
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        if (paramBundle != null)
        {
            this.mLocationId = paramBundle.getLong("location-id", -1L);
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
                long l = paramBundle.getLong("location-id");
                if (l == -1L)
                    return BuslinesFragment.this.mDatabase.query("buslines", null, BuslinesFragment.this.mSelection, BuslinesFragment.this.mSelectionArgs, null, null, null, null);
                if (BuslinesFragment.this.mSelection.equals(""));
                for (String str = ""; ; str = " AND " + BuslinesFragment.this.mSelection)
                    return BuslinesFragment.this.mDatabase.rawQuery("select busstops._id, buslines.line, buslines.destination from buslines join busstops on buslines._id=busstops.busline_id where busstops.location_id=" + l + str, BuslinesFragment.this.mSelectionArgs);
            }
        };
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        View localView = paramLayoutInflater.inflate(R.layout.list_with_search, null);
        this.mTitleView = ((TextView)localView.findViewById(R.id.list_title));
        this.mListView = ((ListView)localView.findViewById(R.id.list));
        this.mListView.setOnItemClickListener(this);
        ((EditText)localView.findViewById(R.id.search_box)).addTextChangedListener(this);
        return localView;
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
        HomeActivity localHomeActivity = (HomeActivity)getActivity();
        String str = ((TextView)paramView.findViewById(R.id.item_text)).getText().toString();
        if (this.mLocationId == -1L)
        {
            localHomeActivity.addFragment(new BusstopsFragment(paramLong, str));
            return;
        }
        localHomeActivity.addFragment(new DeparturesFragment(paramLong, str + " - " + this.mTitle));
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
        paramBundle.putLong("location-id", this.mLocationId);
        paramBundle.putString("title", this.mTitle);
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
        if (paramCharSequence.equals(""))
            this.mSelection = "";
        String str;
        for (this.mSelectionArgs = null; ; this.mSelectionArgs = new String[] { str, str })
        {
            if (this.mDatabase != null)
            {
                Bundle localBundle = new Bundle();
                localBundle.putLong("location-id", this.mLocationId);
                getLoaderManager().restartLoader(0, localBundle, this);
            }
            return;
         /*   StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("(UPPER(");
            localStringBuilder.append("destination");
            localStringBuilder.append(") GLOB ? OR UPPER(");
            localStringBuilder.append("line");
            localStringBuilder.append(") GLOB ?)");
            this.mSelection = localStringBuilder.toString();
            str = "*" + paramCharSequence.toString().toUpperCase() + "*";
       */
			}
    }
}

