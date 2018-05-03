package io.roll.dundjinntome.start;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import io.roll.dundjinntome.start.CharacterFragment;

import io.roll.dundjinntome.data.CharContent;
import io.roll.dundjinntome.start.CharacterFragment.OnListFragmentInteractionListener;
import io.roll.dundjinntome.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CharContent.CharObj} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCharacterRecyclerViewAdapter extends RecyclerView.Adapter<MyCharacterRecyclerViewAdapter.ViewHolder> {

    //private final List<DummyItem> mValues;
    private final List<CharContent.CharObj> myItems;
    private final OnListFragmentInteractionListener mListener;

    public MyCharacterRecyclerViewAdapter(List<CharContent.CharObj> charObjs, OnListFragmentInteractionListener listener) {
        myItems = charObjs;
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == myItems.size()) ? R.layout.fragment_button : R.layout.fragment_character;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        if(viewType == R.layout.fragment_character){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_character, parent, false);
        }

        else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_button, parent, false);
        }

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(position == myItems.size()) {
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show();
                    Fragment prev = CharacterFragment.getFragment();
                    if (prev != null) {
                        DialogFragment df = (DialogFragment) prev;
                        df.dismiss();
                    }
                }
            });
        }
        else {
            final String name = myItems.get(position).name;
            holder.mIdView.setText(myItems.get(position).id);
            holder.title.setText(name + " - " + myItems.get(position).charClass);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(holder.mItem);
                    }
                }
            });
        }
    }

/*    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = myItems.get(position);
        holder.mIdView.setText(myItems.get(position).id);
        holder.mContentView.setText(myItems.get(position).name + " " + myItems.get(position).charClass);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }*/

    @Override
    public int getItemCount() {
        return myItems.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public Button button;
        public final View mView;
        public final TextView mIdView;
        public CharContent.CharObj mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            title  = (TextView) view.findViewById(R.id.content);
            button = (Button) view.findViewById(R.id.loadBack);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
