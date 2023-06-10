package com.quynhlm.dev.assignmentactivity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhongBan_Adapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Phong_Ban_Model> list;

    ArrayList<Phong_Ban_Model> listOld;

    public PhongBan_Adapter(Context context, ArrayList<Phong_Ban_Model> list, ArrayList<Phong_Ban_Model> listOld) {
        this.context = context;
        this.list = list;
        this.listOld = listOld;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Phong_Ban_Model> getList() {
        return list;
    }

    public void setList(ArrayList<Phong_Ban_Model> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_item_phong_ban, parent, false);

        ImageView imglogo = convertView.findViewById(R.id.imglogo_phongban);
        TextView txtname = convertView.findViewById(R.id.txtName_phongban);

        imglogo.setImageResource(list.get(position).getImg());
        txtname.setText(list.get(position).getPhongban());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String pb = constraint.toString();
                if(pb.isEmpty()){
                    list = listOld;
                }else{
                    ArrayList<Phong_Ban_Model> listS = new ArrayList<>();
                    for (Phong_Ban_Model p: list) {
                        if(p.getPhongban().toLowerCase().contains(pb.toLowerCase())){
                            listS.add(p);
                        }
                    }
                    list = listS;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Phong_Ban_Model>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
