package com.piofal.rozkladmzkstarogard.rozstg.utils;

/**
 * Created by Piotr on 2014-05-30.
 */

import java.io.IOException;

        import android.content.Context;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager;
        import android.content.pm.PackageManager.NameNotFoundException;
        import java.io.IOException;
        import java.net.URL;
        import java.net.URLConnection;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.util.EntityUtils;

public class Utils
{
    public static String downloadText(String paramString)
            throws IOException
    {
        return EntityUtils.toString(new DefaultHttpClient().execute(new HttpPost(paramString)).getEntity(), "UTF-8");
    }

    public static long getRemoteFileSize(String paramString)
            throws IOException
    {
        return getRemoteFileSize(new URL(paramString));
    }

    public static long getRemoteFileSize(URL paramURL)
            throws IOException
    {
        URLConnection localURLConnection = paramURL.openConnection();
        localURLConnection.connect();
        return localURLConnection.getContentLength();
    }

    public static String getVersionName(Context paramContext)
            throws PackageManager.NameNotFoundException
    {
        return paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
    }
}

