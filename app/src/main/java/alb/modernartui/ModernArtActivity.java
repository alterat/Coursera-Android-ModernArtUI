package alb.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;


public class ModernArtActivity extends AppCompatActivity {

    static String TAG = "ModernArtUI";
    int prog = 0;
    int c1, c2, c3, c4;
    DialogFragment mDialog;
    static String URL = "http://www.moma.org/collection/artists/4057";
    static Uri webpage = Uri.parse(URL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final LinearLayout ll1 = (LinearLayout) findViewById(R.id.pane1);
        final LinearLayout ll2 = (LinearLayout) findViewById(R.id.pane2);
        final LinearLayout ll3 = (LinearLayout) findViewById(R.id.pane3);
        final LinearLayout ll4 = (LinearLayout) findViewById(R.id.pane4);

        // Choose the colour for the panes, then update the LinearLayout backgrounds
        c1= Color.rgb(10, 0, 155);
        updateColour(ll1, c1);
        c2 = Color.rgb(0, 155, 43);
        updateColour(ll2, c2);
        c3= Color.rgb(49, 130, 0);
        updateColour(ll3, c3);
        c4 = Color.rgb(0, 0, 65);
        updateColour(ll4, c4);

        // Setup a SeekBar listener and change the colour of the panes
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress;
                c1= Color.rgb(10, (int) (255*(progress/100.0)), 155);
                updateColour(ll1, c1);
                c2 = Color.rgb((int) (255*(progress/100.0)), 155, 43);
                updateColour(ll2, c2);
                c3= Color.rgb(49, 130, (int) (255*(progress/100.0)));
                updateColour(ll3, c3);
                c4 = Color.rgb((int) (255*(progress/100.0)), (int) (100*(progress/100.0)), 65);
                updateColour(ll4, c4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    // Create Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Process clicks on Options Menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // When user clicks the item, open the dialog fragment
            case R.id.info:
                showDialogFragment();
                return true;
            default:
                return false;
        }
    }

    // HELPER method
    public void showDialogFragment() {
        mDialog = InfoDialogFragment.newInstance();
        mDialog.show(getFragmentManager(), "Info");
    }


    // Class that creates the InfoDialogFragment
    public static class InfoDialogFragment extends DialogFragment {

        public static InfoDialogFragment newInstance() {
            return new InfoDialogFragment();
        }

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            // Create a view with features given in the dialog_layout.xml file
            View viewDialog = getActivity().getLayoutInflater().inflate(R.layout.dialog_layout, null);

            // Setup the NotNow Button
            Button buttonNotNow = (Button) viewDialog.findViewById(R.id.notnow);
            buttonNotNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "Selected NO");
                    dismiss();
                }
            });

            // Setup the link button: attach an intent to it
            Button buttonMOMA = (Button) viewDialog.findViewById(R.id.moma);
            buttonMOMA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openURL = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(openURL);
                    dismiss();
                }
            });

            builder.setView(viewDialog).setCancelable(false);

            return builder.create();

        }
    }

// OLD VERSION WITHOUT STYLE
//    public void showDialogFragment() {
//        mDialog = InfoDialogFragment.newInstance();
//        mDialog.show(getFragmentManager(), "Info");
//    }
//
//
//    // Class that creates the InfoDialogFragment
//    public static class InfoDialogFragment extends DialogFragment {
//
//        public static InfoDialogFragment newInstance() {
//            return new InfoDialogFragment();
//        }
//
//        // Build AlertDialog using AlertDialog.Builder
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            return new AlertDialog.Builder(getActivity())
//                    .setMessage("Inspired by the works of artists such as Piet " +
//                            "Mondrien and Ben Nicholson. \n \n " +
//                            "Click here to know more")
//
//                    // User cannot dismiss dialog by hitting back button
//                    .setCancelable(false)
//
//
//                    // Set up MOMA Button
//                    .setPositiveButton("Visit MOMA",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(
//                                        final DialogInterface dialog, int id) {
//                                    Log.i(TAG, "Selected YES");
//                                    Intent openURL = new Intent(Intent.ACTION_VIEW, webpage);
//                                    startActivity(openURL);
//
//                                }
//                            })
//
//                    // Set up Not Now Button
//                    .setNegativeButton("Not Now",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog,
//                                                    int id) {
//                                    Log.i(TAG, "Selected NO");
//                                }
//                            })
//
//                    .create();
//        }
//    }

    // HELPER method: update the background colour of the linear layout
    public void updateColour(LinearLayout ll, int col) {
        ll.setBackgroundColor(col);

    }


}
