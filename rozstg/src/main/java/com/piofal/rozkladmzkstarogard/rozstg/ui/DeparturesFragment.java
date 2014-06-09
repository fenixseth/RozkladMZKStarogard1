package com.piofal.rozkladmzkstarogard.rozstg.ui;

/**
 * Created by Piotr on 2014-05-30.
 */

import com.piofal.rozkladmzkstarogard.rozstg.R;
import com.piofal.rozkladmzkstarogard.rozstg.database.RozkladmzkDatabase;
import com.piofal.rozkladmzkstarogard.rozstg.utils.SimpleCursorLoader;

import android.content.Context;
        import android.content.res.Resources;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.LoaderManager;
        import android.support.v4.app.LoaderManager.LoaderCallbacks;
        import android.support.v4.content.Loader;
        import android.support.v4.widget.SimpleCursorAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.GridView;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.ToggleButton;
        import java.util.Calendar;
import android.graphics.drawable.*;

public class DeparturesFragment extends Fragment
        implements View.OnClickListener, AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor>
{
    private static final String STATE_BUSSTOP_ID = "busstop-id";
    private static final String STATE_DAY = "day";
    private static final String STATE_FAVOURITE = "favourite";
    private static final String STATE_TITLE = "title";
    private SimpleCursorAdapter mAdapter;
    private long mBusstopId = -1L;
    private TextView mComments;
    private View mCommentsBar;
    private SQLiteDatabase mDatabase;
    private int mDay = 0;
    private ImageButton mFavButton;
    private boolean mFavourite = false;
    private GridView mListView;
    private ToggleButton mNiedziele;
    private ToggleButton mRobocze;
    private ToggleButton mSoboty;
    private String mTitle = "";
    private TextView mTitleView;

    public DeparturesFragment()
    {
    }

    public DeparturesFragment(long paramLong, String paramString)
    {
        this.mBusstopId = paramLong;
        this.mTitle = paramString;
    }

    private void setButtons()
    {
        this.mRobocze.setChecked(false);
        this.mNiedziele.setChecked(false);
        this.mSoboty.setChecked(false);
        switch (this.mDay)
        {
            default:
                return;
            case 0:
                this.mRobocze.setChecked(true);
                return;
            case 1:
                this.mSoboty.setChecked(true);
                return;
            case 2:
        }
        this.mNiedziele.setChecked(true);
    }

    public void onActivityCreated(Bundle paramBundle)
    {
        super.onActivityCreated(paramBundle);
        setButtons();
        this.mTitleView.setText(this.mTitle);
        ImageButton localImageButton = this.mFavButton;
        if (this.mFavourite);
        for (int i = R.drawable.s1; ; i = R.drawable.s2)
        {
            localImageButton.setImageResource(i);
            final Calendar localCalendar = Calendar.getInstance();
            this.mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.grid_item, null, new String[0], null, 2)
            {
                public void bindView(View paramAnonymousView, Context paramAnonymousContext, Cursor paramAnonymousCursor)
                {
                    int i = paramAnonymousCursor.getInt(paramAnonymousCursor.getColumnIndex("hour"));
                    int j = paramAnonymousCursor.getInt(paramAnonymousCursor.getColumnIndex("minute"));
                    String str = paramAnonymousCursor.getString(paramAnonymousCursor.getColumnIndex("symbols"));
                    int k = localCalendar.get(12);
                    int m = localCalendar.get(11);
                    int n;
                    TextView localTextView;
                    if (i < m)
                    {
                        n = 1;
                        TextView = (TextView)paramAnonymousView.findViewById(R.id.item_text);
                        Object[] arrayOfObject = new Object[3];
                        arrayOfObject[0] = Integer.valueOf(i);
                        arrayOfObject[1] = Integer.valueOf(j);
                        arrayOfObject[2] = str;
                        localTextView.setText(String.format("%02d:%02d%s", arrayOfObject));
                        if (n == 0)
                            break label186;
                    }
                    label186: for (int i1 = DeparturesFragment.this.getResources().getColor(R.color.dark_red); ; i1 = -16777216)
                    {
                        TextView.setTextColor(i1);
                        return;
                       /* if ((i == m) && (j <= k))
                       // {
                            //n = 1;
                          //  break;
                        //}
                        n = 0;
						*/
                      //  break;
                    }
                }
            };
            this.mListView.setAdapter(this.mAdapter);
            Bundle localBundle = new Bundle();
            localBundle.putLong("busstop-id", this.mBusstopId);
            localBundle.putInt("day", this.mDay);
            getLoaderManager().initLoader(2, localBundle, this);
            getLoaderManager().initLoader(1, localBundle, this);
            getLoaderManager().initLoader(0, localBundle, this);
            return;
        }
    }

    public void onClick(View paramView)
    {
        if (paramView.getId() == R.id.departures_fav)
        {
            FragmentActivity localFragmentActivity;
            if (!this.mFavourite)
            {
                SQLiteDatabase localSQLiteDatabase = this.mDatabase;
                Object[] arrayOfObject = new Object[2];
                arrayOfObject[0] = Long.valueOf(this.mBusstopId);
                arrayOfObject[1] = this.mTitle;
                localSQLiteDatabase.execSQL("insert into favourites (busstop_id, name) values (?, ?)", arrayOfObject);
                localFragmentActivity = getActivity();
                if (this.mFavourite)
                    break label149;
            }
            label149: for (String str = "Dodano do ulubionych"; ; str = "Usunięto z ulubionych")
            {
                Toast.makeText(FragmentActivity, str, 1).show();
                Bundle localBundle2 = new Bundle();
                localBundle2.putLong("busstop-id", this.mBusstopId);
                getLoaderManager().restartLoader(2, localBundle2, this);
                return;
               // this.mDatabase.execSQL("delete from favourites where busstop_id=" + this.mBusstopId);
               // break;
            }
        }
        switch (paramView.getId())
        {
            default:
            case R.id.depratures_robocze:
            case R.id.departures_soboty:
            case R.id.departures_niedziele:
        }
        while (true)
        {
            setButtons();
            Bundle localBundle1 = new Bundle();
            localBundle1.putLong("busstop-id", this.mBusstopId);
            localBundle1.putInt("day", this.mDay);
            getLoaderManager().restartLoader(0, localBundle1, this);
            return;
            this.mDay = 0;
            continue;
            this.mDay = 1;
            continue;
            this.mDay = 2;
        }
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        if (paramBundle != null)
        {
            this.mDay = paramBundle.getInt("day", 0);
            this.mBusstopId = paramBundle.getLong("busstop-id", -1L);
            this.mTitle = paramBundle.getString("title");
            this.mFavourite = paramBundle.getBoolean("favourite", false);
        }
    }

    public Loader<Cursor> onCreateLoader(final int paramInt, final Bundle paramBundle)
    {
        if (this.mDatabase == null)
            this.mDatabase = RozkladmzkDatabase.getDatabase(getActivity());
        return new SimpleCursorLoader(getActivity())
        {
            public Cursor loadInBackground()
            {
                long l = paramBundle.getLong("busstop-id");
                int i = paramBundle.getInt("day");
                switch (paramInt)
                {
                    default:
                        return null;
                    case 0:
                        String str = "busstop_id=" + l + " AND " + "day" + "=" + i;
                        return DeparturesFragment.this.mDatabase.query("departures", null, str, null, null, null, null);
                    case 1:
                        return DeparturesFragment.this.mDatabase.rawQuery("select c.comment from comments c join comment_busstop_maps cb on c._id=cb.comment_id and cb.busstop_id=" + l, null);
                    case 2:
                }
                return DeparturesFragment.this.mDatabase.rawQuery("select _id from favourites where busstop_id=" + l, null);
            }
        };
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        View localView = paramLayoutInflater.inflate(R.layout.departures, null);
        this.mRobocze = ((ToggleButton)localView.findViewById(R.id.depratures_robocze));
        this.mSoboty = ((ToggleButton)localView.findViewById(R.id.departures_soboty));
        this.mNiedziele = ((ToggleButton)localView.findViewById(R.id.departures_niedziele));
        this.mRobocze.setOnClickListener(this);
        this.mSoboty.setOnClickListener(this);
        this.mNiedziele.setOnClickListener(this);
        this.mListView = ((GridView)localView.findViewById(R.id.list));
        this.mListView.setOnItemClickListener(this);
        this.mTitleView = ((TextView)localView.findViewById(R.id.departures_title));
        this.mFavButton = ((ImageButton)localView.findViewById(R.id.departures_fav));
        this.mFavButton.setOnClickListener(this);
        this.mComments = ((TextView)localView.findViewById(R.id.departures_comments));
        this.mCommentsBar = localView.findViewById(R.id.departures_bottom_bar);
        return localView;
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
    }

    public void onLoadFinished(Loader<Cursor> paramLoader, Cursor paramCursor)
    {
        switch (paramLoader.getId())
        {
            default:
                return;
            case 0:
                this.mAdapter.swapCursor(paramCursor);
                return;
            case 1:
                if (paramCursor.getCount() == 0)
                {
                    this.mCommentsBar.setVisibility(8);
                    return;
                }
                paramCursor.moveToFirst();
                StringBuilder localStringBuilder = new StringBuilder();
                while (!paramCursor.isAfterLast())
                {
                    localStringBuilder.append(paramCursor.getString(0));
                    paramCursor.moveToNext();
                    if (!paramCursor.isAfterLast())
                        localStringBuilder.append(", ");
                }
                this.mCommentsBar.setVisibility(0);
                this.mComments.setText(localStringBuilder.toString());
                return;
            case 2:
        }
        int i = paramCursor.getCount();
        boolean bool = false;
        if (i != 0)
            bool = true;
        this.mFavourite = bool;
        ImageButton localImageButton = this.mFavButton;
        if (this.mFavourite);
        for (int j = 2130837508; ; j = 2130837507)
        {
            localImageButton.setImageResource(j);
            return;
        }
    }

    public void onLoaderReset(Loader<Cursor> paramLoader)
    {
        switch (paramLoader.getId())
        {
            default:
                return;
            case 0:
        }
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
        paramBundle.putInt("day", this.mDay);
        paramBundle.putLong("busstop-id", this.mBusstopId);
        paramBundle.putString("title", this.mTitle);
        paramBundle.putBoolean("favourite", this.mFavourite);
    }
}

/* Location:           C:\Users\Piotr\Downloads\Compressed\mzk\eu.gingermobile\vortexinfinitum.rozkladmzk.piofald Name:     vortexinfinitum.rozkladmzk.ui.DeparturesFragment
 * JD-Core Version:    0.6.2
 */
