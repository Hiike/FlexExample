package com.flextest.flexexample;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_STRING = 0;
    private static final int TYPE_ADD_STRING = 1;

    private final Context mContext;
    private List<String> mStringList;
    private List<Integer> mList;

    CustomAdapter(Context context) {
        mContext = context;
        mStringList = new ArrayList<>();
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_STRING) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
            return new StringViewHolder(view);
        } else if (viewType == TYPE_ADD_STRING) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_button, parent, false);
            return new AddStringViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mList.get(position)) {
            case TYPE_STRING:
                for (String string : mStringList) {
                    TextView stringTv = new TextView(mContext);
                    stringTv.setBackground(ContextCompat.getDrawable(mContext, R.drawable.rounded_background));
                    stringTv.setTextColor(ContextCompat.getColor(mContext, android.R.color.white));
                    stringTv.setText(string);
                    stringTv.setMaxLines(1);
                    stringTv.setPadding(mContext.getResources().getDimensionPixelOffset(R.dimen.half_padding),
                            mContext.getResources().getDimensionPixelOffset(R.dimen.half_padding),
                            mContext.getResources().getDimensionPixelOffset(R.dimen.half_padding),
                            mContext.getResources().getDimensionPixelOffset(R.dimen.half_padding));
                    ((StringViewHolder) holder).mStringCell.addView(stringTv);
                }
                ((StringViewHolder) holder).mEditButton.setVisibility(View.VISIBLE);
                break;
            case TYPE_ADD_STRING:
                ((AddStringViewHolder) holder).mAction.setText("Add a text");
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position);
    }

    class StringViewHolder extends RecyclerView.ViewHolder {
        FlexboxLayout mStringCell;

        Button mEditButton;

        StringViewHolder(View itemView) {
            super(itemView);
            mStringCell = itemView.findViewById(R.id.string_cell);
            mEditButton = itemView.findViewById(R.id.edit_button);
        }
    }

    class AddStringViewHolder extends RecyclerView.ViewHolder {
        Button mAction;

        AddStringViewHolder(View itemView) {
            super(itemView);
            mAction = itemView.findViewById(R.id.add_button);
        }
    }

    void update() {
        mStringList.add("Lavuje laj ro.");
        mStringList.add("Kewriw ven hasdovfit.");
        mStringList.add("Forekag mabsu de.");
        mStringList.add("Lavuje laj ro.");
        mStringList.add("Kewriw ven hasdovfit.");
        mStringList.add("Forekag mabsu de.");
        mStringList.add("Lavuje laj ro.");
        mStringList.add("Kewriw ven hasdovfit.");
        mStringList.add("Forekag mabsu de.");
        mStringList.add("Lavuje laj ro.");
        mStringList.add("Kewriw ven hasdovfit.");
        mStringList.add("Forekag mabsu de.");

        if (mStringList.size() > 0) {
            mList.add(TYPE_STRING);
        }
        mList.add(TYPE_ADD_STRING);
        notifyDataSetChanged();
    }
}