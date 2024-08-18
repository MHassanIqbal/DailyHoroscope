package t.hkb.designhoroscope.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import t.hkb.designhoroscope.R;
import t.hkb.designhoroscope.database.model.Table;


public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Table> recordsList;


    public recyclerAdapter(Context context, List<Table> records) {
        this.context = context;
        this.recordsList = records;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_for_rv, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView star;
        public TextView time;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
              star = view.findViewById(R.id.textHeading);
              time = view.findViewById(R.id.textDate);
              timestamp = view.findViewById(R.id.textDiscription);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Table table = recordsList.get(position);
        holder.star.setText(table.getStar());
        holder.time.setText(table.getTime());
        holder.timestamp.setText(formatDate(table.getTimestamp()));

//      Toast.makeText(context, table.getId()+":"+table.getStar(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }

//    // Clean all elements of the recycler
//    public void clear() {
//        items.clear();
//        notifyDataSetChanged();
//    }
//
//    // Add a list of items -- change to type used
//    public void addAll(List<Table> list) {
//        Table.addAll(list);
//        notifyDataSetChanged();
//    }
}