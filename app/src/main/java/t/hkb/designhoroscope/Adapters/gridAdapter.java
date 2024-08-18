package t.hkb.designhoroscope.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import t.hkb.designhoroscope.R;

public class gridAdapter extends BaseAdapter {
    private Context context;
    private int []logos;
    private int height;
    private String []values;
    private LayoutInflater layoutInflater;
    private View view;

    public gridAdapter(Context applicationContext, int[] logos, String[] values,int height) {
        this.context = applicationContext;
        this.logos = logos;
        this.values = values;
        this.height = height;
    }

    @Override
    public int getCount() {
        return values.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE); // inflate the layout
        if(convertView == null){

            view = new View(context);
            view = layoutInflater.inflate(R.layout.activity_gridview,null);
            view.setMinimumHeight(height/7);

            ImageView icon = (ImageView) view.findViewById(R.id.icon); // get the reference of ImageView
            TextView tView= (TextView)view.findViewById(R.id.textIcon);
            icon.setImageResource(logos[position]); // set logo images
            icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            tView.setText(values[position]);

        }
        return view;
    }
}
