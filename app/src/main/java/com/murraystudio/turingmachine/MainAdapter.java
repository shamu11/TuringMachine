/**
 * Author: Shamus Murray
 *
 * This class handles displaying all the information for the recycleview list
 */

package com.murraystudio.turingmachine;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemHolder> {
        private List<String> itemsName;
        private List<String> itemsName2;
        private OnItemClickListener onItemClickListener;
        private LayoutInflater layoutInflater;

        public MainAdapter(Context context){
            layoutInflater = LayoutInflater.from(context);
            itemsName = new ArrayList<String>();
            itemsName2 = new ArrayList<String>();
        }

    @Override
    public MainAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.card_item, parent, false);
        return new ItemHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ItemHolder holder, int position) {
        Log.i("itemName2 length: ", Integer.toString(itemsName2.size()));
        holder.setItemName(itemsName2.get(position));

    }

    @Override
    public int getItemCount() {
        return itemsName2.size();
    }

    public int getItemNameCount() {
        return itemsName.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public OnItemClickListener getOnItemClickListener(){
        return onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(ItemHolder item, int position);
    }

    public void add(int location, String iName){
        itemsName.add(location, iName);
    }

    public void makeVisible(int location){
        itemsName2.add(location, itemsName.get(getItemNameCount() - location - 1));
        notifyItemInserted(location);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private MainAdapter parent;
        TextView textItemName;

        public ItemHolder(View itemView, MainAdapter parent) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.parent = parent;
            textItemName = (TextView) itemView.findViewById(R.id.turing_text);
        }

        public void setItemName(CharSequence name){
            textItemName.setText(name);
        }

        @Override
        public void onClick(View v) {
            final OnItemClickListener listener = parent.getOnItemClickListener();
            if(listener != null){
                listener.onItemClick(this, getPosition());
            }
        }
    }
}
