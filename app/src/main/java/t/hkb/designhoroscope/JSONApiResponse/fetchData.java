package t.hkb.designhoroscope.JSONApiResponse;

import android.os.AsyncTask;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import t.hkb.designhoroscope.JSONApiResponse.utils.JSONProperties;
import t.hkb.designhoroscope.R;

public class fetchData extends AsyncTask<Void,Void,Void> {

    private final String star;
    private final String time;
    private StringBuffer dataStreamBuffer;
    private String dataStream;
    private ImageView imageFirst, imageSecond;
    private TextView discriptionView, compatibilityView, colorView, moodView, luckyNumView, luckyTimeView;
    private ProgressBar progressBar;

    //Constructor
    public fetchData(String star, String time, TextView discriptionView, TextView compatibilityView, TextView colorView, TextView moodView, TextView luckyNumView, TextView luckyTimeView, ImageView imageFirst,ImageView imageSecond, ProgressBar progressBar) {
        this.star = star;
        this.time = time;
        this.discriptionView = discriptionView;
        this.compatibilityView = compatibilityView;
        this.colorView = colorView;
        this.moodView = moodView;
        this.luckyNumView = luckyNumView;
        this.luckyTimeView = luckyTimeView;
        this.progressBar = progressBar;
        this.imageFirst = imageFirst;
        this.imageSecond = imageSecond;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //second step
        try {
            //second step
            URL url = new URL("https://aztro.sameerkumar.website/?sign=" + star + "&day=" + time + "");
//            URL url = new URL("http://d2weczhvl823v0.herokuapp.com/horoscope/week/libra");
//            URL url = new URL("https://horoscope-free-api.herokuapp.com/?time="+time+"&sign="+star+"");


            //third step
            HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("POST");
            //fourth step
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            dataStreamBuffer = new StringBuffer();
            while (line != null) {
                line = bufferedReader.readLine();
                dataStreamBuffer.append(line);
            }
            dataStream = dataStreamBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
            JSONObject jsonObj = new JSONObject(dataStream);
            JSONProperties p = new JSONProperties(jsonObj.getString("date_range"), jsonObj.getString("current_date"), jsonObj.getString("description"), jsonObj.getString("compatibility"), jsonObj.getString("mood"), jsonObj.getString("color"), jsonObj.getString("lucky_number"), jsonObj.getString("lucky_time"));
            discriptionView.setText(Html.fromHtml("Description : " + p.getDescription()));
            compatibilityView.setText(Html.fromHtml("Compatibility : " + p.getCompatibility()));
//            andText.setText(Html.fromHtml(" and "));
            setIcons(star, imageFirst);
            setIcons(p.getCompatibility(), imageSecond);
            colorView.setText(Html.fromHtml("Color : " + p.getColor()));
            moodView.setText(Html.fromHtml("Mood : " + p.getMood()));
            luckyNumView.setText(Html.fromHtml("Lucky number : " + p.getLucky_number()));
            luckyTimeView.setText(Html.fromHtml("Lucky Time : " + p.getLucky_time()));

            progressBar.setVisibility(View.GONE);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setIcons(String compatibility, ImageView image) {
        if (compatibility.equals("Aries")) {
            image.setImageResource(R.drawable.ic_aries);
        } else if (compatibility.equals("Aquarius")) {
            image.setImageResource(R.drawable.ic_aquarius);
        } else if (compatibility.equals("Cancer")) {
            image.setImageResource(R.drawable.ic_cancer);
        } else if (compatibility.equals("Capricorn") || compatibility.equals("Capricornius")) {
            image.setImageResource(R.drawable.ic_capricornius);
        } else if (compatibility.equals("Gemini")) {
            image.setImageResource(R.drawable.ic_gemini);
        } else if (compatibility.equals("Leo")) {
            image.setImageResource(R.drawable.ic_leo);
        } else if (compatibility.equals("Libra")) {
            image.setImageResource(R.drawable.ic_libra);
        } else if (compatibility.equals("Pisces")) {
            image.setImageResource(R.drawable.ic_pisces);
        } else if (compatibility.equals("Virgo")) {
            image.setImageResource(R.drawable.ic_virgo);
        } else if (compatibility.equals("Taurus")){
            image.setImageResource(R.drawable.ic_taurus);
        }else if(compatibility.equals("Scorpio")||compatibility.equals("Scorpius")){
            image.setImageResource(R.drawable.ic_scorpius);
        }else if(compatibility.equals("Sagittarius")){
            image.setImageResource(R.drawable.ic_sagittarius_symbol);
        }else{

//            image.setImageResource(R.drawable.ic_aries);
        }
    }

}
