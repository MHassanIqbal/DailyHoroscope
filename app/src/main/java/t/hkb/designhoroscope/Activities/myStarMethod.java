/*
    TODO:Custom myStars class created by HAMZA KHALID BHUTTA
    TODO:Dated:Friday,2nd November,2018
    TODO:To Find out Sun Stars by entring date of birth
*/

package t.hkb.designhoroscope.Activities;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class myStarMethod {
    public static void showDate(int month, int day, String[] values,String[] dateRangesActionBar, Context firstActivity, Class secondActivity, String key, String key2) {
        String star = "", dateRangesAction = "";
        month = month + 1;
        if (((month == 12) && (day >= 22 && day <= 31)) || ((month == 1) && (day >= 1 && day <= 19))) {
            star = values[9];
            dateRangesAction = dateRangesActionBar[9];
        } else if (((month == 1) && (day >= 20 && day <= 31)) || ((month == 2) && (day >= 1 && day <= 18))) {
            star = values[10];
            dateRangesAction = dateRangesActionBar[10];
        } else if (((month == 2) && (day >= 19 && day <= 28)) || ((month == 3) && (day >= 1 && day <= 20))) {
            star = values[11];
            dateRangesAction = dateRangesActionBar[11];
        } else if (((month == 3) && (day >= 21 && day <= 31)) || ((month == 4) && (day >= 1 && day <= 19))) {
            star = values[0];
            dateRangesAction = dateRangesActionBar[0];
        } else if (((month == 4) && (day >= 20 && day <= 30)) || ((month == 5) && (day >= 1 && day <= 20))) {
            star = values[1];
            dateRangesAction = dateRangesActionBar[1];
        } else if (((month == 5) && (day >= 20 && day <= 31)) || ((month == 6) && (day >= 1 && day <= 20))) {
            star = values[2];
            dateRangesAction = dateRangesActionBar[2];
        } else if (((month == 6) && (day >= 21 && day <= 30)) || ((month == 7) && (day >= 1 && day <= 22))) {
            star = values[3];
            dateRangesAction = dateRangesActionBar[3];
        } else if (((month == 7) && (day >= 23 && day <= 31)) || ((month == 8) && (day >= 1 && day <= 22))) {
            star = values[4];
            dateRangesAction = dateRangesActionBar[4];
        } else if (((month == 8) && (day >= 23 && day <= 31)) || ((month == 9) && (day >= 1 && day <= 22))) {
            star = values[5];
            dateRangesAction = dateRangesActionBar[5];
        } else if (((month == 9) && (day >= 23 && day <= 30)) || ((month == 10) && (day >= 1 && day <= 22))){
            star = values[6];
            dateRangesAction = dateRangesActionBar[6];
        } else if (((month == 10) && (day >= 23 && day <= 31)) || ((month == 11) && (day >= 1 && day <= 22))){
            star = values[7];
            dateRangesAction = dateRangesActionBar[7];
        } else if(((month == 11) && (day >= 22 && day <= 30)) || ((month == 12) && (day >= 1 && day <= 21))) {
            star = values[8];
            dateRangesAction = dateRangesActionBar[8];
        }

        Intent intent = new Intent(firstActivity,secondActivity);
        intent.putExtra(key,star);
        intent.putExtra(key2,dateRangesAction);
        firstActivity.startActivity(intent); // start Intent
//        Toast.makeText(firstActivity,star , Toast.LENGTH_SHORT).show();
    }
}
