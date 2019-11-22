package com.example.projectpath;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import static com.example.projectpath.R.layout.activity_food1;


public class ImageFood1 extends PagerAdapter {
    private Context mContext;
    private int[] mImageIds = new int[]{R.drawable.food0101, R.drawable.food0102, R.drawable.food0103, R.drawable.food0104};
    private LayoutInflater inflater1;

    ImageFood1(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);

        inflater1 = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View page = inflater1.inflate(R.layout.activity_food1, null);

        page.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                switch (position){
                    case 0:Toast.makeText(mContext,"Select 1",Toast.LENGTH_SHORT).show();break;
                    case 1:Toast.makeText(mContext,"Select 2",Toast.LENGTH_SHORT).show();break;
                    case 2:Toast.makeText(mContext,"Select 3",Toast.LENGTH_SHORT).show();break;
                    case 3:Toast.makeText(mContext,"Select 4",Toast.LENGTH_SHORT).show();break;
                }
                if(position==0){Toast.makeText(mContext,"Select 1",Toast.LENGTH_SHORT).show();}
            }
        });

        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
