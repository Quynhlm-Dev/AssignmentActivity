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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class NhanVien_Adapter extends BaseAdapter implements Filterable {

    private Context c;
    private ArrayList<Nhan_Vien_Model> list;

    private ArrayList<Nhan_Vien_Model> listOld;

    public NhanVien_Adapter(Context c, ArrayList<Nhan_Vien_Model> list, ArrayList<Nhan_Vien_Model> listOld) {
        this.c = c;
        this.list = list;
        this.listOld = listOld;
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Nhan_Vien_Model> getList() {
        return list;
    }

    public void setList(ArrayList<Nhan_Vien_Model> list) {
        this.list = list;
    }

    public ArrayList<Nhan_Vien_Model> getListOld() {
        return listOld;
    }

    public void setListOld(ArrayList<Nhan_Vien_Model> listOld) {
        this.listOld = listOld;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
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
        LayoutInflater Inflater = ((Activity) c).getLayoutInflater();
        convertView = Inflater.inflate(R.layout.list_item_layout, parent, false);

        ImageView imglogo = convertView.findViewById(R.id.imglogo);
        TextView txtID = convertView.findViewById(R.id.txtma);
        TextView txtName = convertView.findViewById(R.id.txtten);
        TextView txtPhongban = convertView.findViewById(R.id.txtphongban);
        TextView txtGioitinh = convertView.findViewById(R.id.txtGioiTinh);
        ImageView imgDelete = convertView.findViewById(R.id.imgDelete);
        ImageView imgUpdate = convertView.findViewById(R.id.imgsua);

        imglogo.setImageResource(list.get(position).getImglogo());
        txtID.setText(list.get(position).getId());
        txtName.setText(list.get(position).getName());
        txtPhongban.setText(list.get(position).getPhongBan());
        txtGioitinh.setText(list.get(position).getGioiTinh());



        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity_Nhan_Vien) c).Delete(position);
            }
        });
        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity_Nhan_Vien) c).Update(position);
            }
        });
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String nv = constraint.toString();
                if (nv.isEmpty()) {
                    list = listOld;
                } else {
                    ArrayList<Nhan_Vien_Model> listS = new ArrayList<>();
                    for (Nhan_Vien_Model n : listOld) {
                        if(n.getName().toLowerCase().contains(nv.toLowerCase())){
                            listS.add(n);
                        }
                    }
                    list = listS;
                }
                FilterResults filterResults =new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Nhan_Vien_Model>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
