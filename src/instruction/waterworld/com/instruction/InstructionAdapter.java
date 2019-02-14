package instruction.waterworld.com.instruction;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InstructionAdapter extends BaseAdapter {

    private final  String  TAG = "instruction.InstructionAdapter";
    private LayoutInflater mInflater;
    private List<Bean> mDatas=null;
    private TypedArray array;
    private String[] titles;
    private String[] description;
    private String[] num;

    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public InstructionAdapter(Context context) {

        mInflater = LayoutInflater.from(context);

        if( mDatas == null ){
            mDatas = new ArrayList<>();

            array       = context.getResources().obtainTypedArray( R.array.instruction_icons );
            titles      = context.getResources().getStringArray( R.array.instruction_labels );
            description = context.getResources().getStringArray( R.array.instruction_desc );
            num         = context.getResources().getStringArray( R.array.instruction_num );

            for(int i= 0; i< array.length(); i++  ){
                int icons = array.getResourceId( i, 0);
                Bean bean = new Bean( icons, titles[i], description[i],num[i] );
                Log.d(TAG, "InstructionAdapter: "+ icons+" "+titles[i]+" "+description[i]+" "+num[i]);
                mDatas.add( bean );
            }
        }
    }

    //返回数据集的长度
    @Override
    public int getCount() {
        if( mDatas != null) {
            return mDatas.size();
        }else{
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //这个方法才是重点，我们要为它编写一个ViewHolder
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_layout, parent, false); //加载布局
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.item_image);
            holder.titleTv = (TextView) convertView.findViewById(R.id.item_title);
            holder.descTv = (TextView) convertView.findViewById(R.id.item_desc);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.item_indicate);

            convertView.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }

        Bean bean = mDatas.get(position);
        holder.image.setImageResource( bean.getImgID( ));
        holder.titleTv.setText(bean.getTitle());
        holder.descTv.setText(bean.getDesc());
        holder.phoneTv.setText(bean.getPhone());

        return convertView;
    }

    public String  getItemTitle( int position ){
        String title = "";
        if( titles.length != 0 ){
            title= titles[position];
        }
        return title;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        ImageView image;
        TextView titleTv;
        TextView descTv;
        TextView phoneTv;
    }




}
