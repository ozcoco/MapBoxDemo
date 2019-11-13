package me.oz.demo.mapbox.objbox;

import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class ObjectBoxActivityFragment extends ListFragment {

    private OnItemClickListener mOnItemClickListener;

    public ObjectBoxActivityFragment(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(v, position, id);

    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {

        if (itemClickListener != null)
            mOnItemClickListener = itemClickListener;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mOnItemClickListener = null;

    }
}
