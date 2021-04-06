package com.sitamrock11.pdfreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PdfListAdapter extends RecyclerView.Adapter<PdfListAdapter.PdfListViewHolder> {
    Context context;
    ArrayList<File> list=new ArrayList<>();
    public PdfListAdapter(Context context){
        this.context=context;
    }
    @Override
    public PdfListViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new PdfListViewHolder(view);
    }

    @Override
    public void onBindViewHolder( PdfListViewHolder holder, int position) {
       holder.pdfName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void updateList(ArrayList newlist){
        list.clear();
        list.addAll(newlist);
        notifyDataSetChanged();
    }

    public class PdfListViewHolder extends RecyclerView.ViewHolder {
        TextView pdfName;
        public PdfListViewHolder( View itemView) {
            super(itemView);
            pdfName=itemView.findViewById(R.id.txtPdfName);
        }
    }
}
