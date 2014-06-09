package com.piofal.rozkladmzkstarogard.rozstg.ui;

/**
 * Created by Piotr on 2014-05-30.
 */

import android.widget.Toast;

        import android.app.AlertDialog;
        import android.app.AlertDialog.Builder;
        import android.content.DialogInterface;
        import android.content.DialogInterface.OnClickListener;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.view.View;
        import android.widget.Toast;

import com.piofal.rozkladmzkstarogard.rozstg.R;

public class HomeActivity extends FragmentActivity
{
    /* private void showUpdateDialog()
     {
     AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
     localBuilder.setMessage("Wymagane jest pobranie bazy rozkładów z internetu. Operacja ta jest jednorazowa. Czy pobrać rozkłady teraz?").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
     {
     public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
     {
     HomeActivity.this.startService(new Intent(HomeActivity.this, SyncService.class));
     }
     }).setNegativeButton("Anuluj", new DialogInterface.OnClickListener()
     {
     public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
     {
     paramAnonymousDialogInterface.cancel();
     }
     });
     localBuilder.create().show();
     }
*/
    public void addFragment(Fragment paramFragment)
    {
//      switch (getSharedPreferences("piofal.rozkladmzk.preferences.RozkladmzkPreferences", 0).getInt("db-status", 0))
//    {
//        case 1:
//        default:
                FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
                localFragmentTransaction.addToBackStack(null);
                localFragmentTransaction.replace(R.id.main_content, paramFragment);
                localFragmentTransaction.setTransition(4097);
                localFragmentTransaction.commit();
                return;
//           case 0:
//		 showUpdateDialog();
//             return;
//         case 2:
// }
//        Toast.makeText(this, "Proszę poczekać na zakończenie pobierania rozkładów.", 0).show();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.main);
        {
            setFragment(new MainMenuFragment());
//     if (getSharedPreferences("piofal.rozkladmzk.preferences.RozkladmzkPreferences", 0).getInt("db-status", 0) == 0)
//       showUpdateDialog();
        }
        findViewById(R.id.main_bottombar).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://sidzanuff.platinum.edu.pl/piofal/"));
                HomeActivity.this.startActivity(localIntent);
            }

        });
    }

    public void setFragment(Fragment paramFragment)
    {
        FragmentManager localFragmentManager = getSupportFragmentManager();
        for (int i = 0; i < localFragmentManager.getBackStackEntryCount(); i++)
            localFragmentManager.popBackStack();
        FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
        localFragmentTransaction.replace(R.id.main_content, paramFragment);
        localFragmentTransaction.commitAllowingStateLoss();
    }
}

