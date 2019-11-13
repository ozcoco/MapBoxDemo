package me.oz.demo.mapbox.objbox;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.ListFragment;

import me.oz.demo.mapbox.R;

public class ObjectBoxActivity extends AppCompatActivity {

    ListFragment mListFragment;

    OnItemClickListener mOnItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_object_box);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mOnItemClickListener = new OnSimpleItemClickListener();

        mListFragment = new ObjectBoxActivityFragment(mOnItemClickListener);

        final String[] items = {"Basic"};

        mListFragment.setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items));

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mListFragment).commit();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mListFragment = null;

        mOnItemClickListener = null;
    }
}


class OnSimpleItemClickListener implements OnItemClickListener {

    @Override
    public void onItemClick(View view, int position, long id) {

    }
}


interface OnItemClickListener {

    void onItemClick(View view, int position, long id);

}