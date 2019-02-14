package instruction.waterworld.com.instruction;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class InstItemActivity extends Activity {

    private ViewPager       viewPager;

    private View            view;
    private LayoutInflater  layoutInflater;
    private List<View>      viewList;
    private static final String TAG = "Instruction.InstItemActivity";

    private TypedArray array;
    private int page_numbers[];
    private String page_title[];
    private String page_description[];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Log.d(TAG, "onCreate: ");
        int postion =   getIntent().getIntExtra(Utils.INSTRUCTION_ITEMID, 0);
        String titleName =   getIntent().getStringExtra(Utils.INSTRUCTION_ITEMNAME);
        Log.d(TAG, " postion: "+postion+" "+titleName);

        setContentView(R.layout.instruction_item_layout);
        setCustomActionBar( titleName);
        page_numbers =getResources().getIntArray( R.array.instruction_page_num );


        int pages = page_numbers[postion];
        Log.d(TAG, "pages: "+pages);
        viewPager = findViewById(R.id.instruction_pagers);
        layoutInflater = getLayoutInflater();
        viewList = new ArrayList<View>();

        switch ( postion ){
            case 0:
                array       = getResources().obtainTypedArray( R.array.pages_1_icons );
                page_title = getResources().getStringArray(R.array.pages_1_labels );
                page_description = getResources().getStringArray( R.array.pages_1_desc );
                break;
            case 1:

                array       = getResources().obtainTypedArray( R.array.pages_2_icons );
                page_title = getResources().getStringArray(R.array.pages_2_labels );
                page_description = getResources().getStringArray( R.array.pages_2_desc );
                break;
            case 2:
                array       = getResources().obtainTypedArray( R.array.pages_3_icons );
                page_title = getResources().getStringArray(R.array.pages_3_labels );
                page_description = getResources().getStringArray( R.array.pages_3_desc );
                break;

            case 3:
                array       = getResources().obtainTypedArray( R.array.pages_4_icons );
                page_title = getResources().getStringArray(R.array.pages_4_labels );
                page_description = getResources().getStringArray( R.array.pages_4_desc );
                break;
            case 4:
                array       = getResources().obtainTypedArray( R.array.pages_5_icons );
                page_title = getResources().getStringArray(R.array.pages_5_labels );
                page_description = getResources().getStringArray( R.array.pages_5_desc );
                break;
            case 5:
                array       = getResources().obtainTypedArray( R.array.pages_6_icons );
                page_title = getResources().getStringArray(R.array.pages_6_labels );
                page_description = getResources().getStringArray( R.array.pages_6_desc );
                break;
            case 6:
                array       = getResources().obtainTypedArray( R.array.pages_7_icons );
                page_title = getResources().getStringArray(R.array.pages_7_labels );
                page_description = getResources().getStringArray( R.array.pages_7_desc );
                break;
            case 7:
                array       = getResources().obtainTypedArray( R.array.pages_8_icons );
                page_title = getResources().getStringArray(R.array.pages_8_labels );
                page_description = getResources().getStringArray( R.array.pages_8_desc );
                break;

        }
        for( int i =0; i< pages ; i++) {


            view = layoutInflater.inflate(R.layout.page_layout, null);
            ImageView  imageView = view.findViewById( R.id.page_map );
            TextView textTitle = view.findViewById( R.id.page_title );
            TextView textDescription = view.findViewById( R.id.page_description );
            TextView page_number    = view.findViewById(R.id.page_number );

            imageView.setImageResource( array.getResourceId(i, 0));
            textTitle.setText( page_title[i]);
            textDescription.setText( page_description[i]);
            page_number.setText( (i+1) +"/"+pages);

            viewList.add(view);
        }
        PagerAdapter  pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                Log.d(TAG, "getCount: ");
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

                return view== o;
            }

            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                Log.d(TAG, "destroyItem: ");
                container.removeView(viewList.get(position));
            }

            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                Log.d(TAG, "instantiateItem: ");
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };
        viewPager.setAdapter( pagerAdapter );

    };



    private void setCustomActionBar(String name) {
        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        View mActionBarView = LayoutInflater.from(this).inflate(R.layout.action_bar_layout, null);

        ActionBar actionBar = getActionBar();
        if(actionBar != null ) {
            actionBar.setCustomView(mActionBarView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);

            TextView mTextView = mActionBarView.findViewById(R.id.action_bar_title);
            Toolbar parent =(Toolbar) mActionBarView.getParent();
            parent.setContentInsetsAbsolute(0,0);
            mTextView.setText( name );

            ImageView imageView = mActionBarView.findViewById(R.id.action_bar_indiactor );
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }
}
