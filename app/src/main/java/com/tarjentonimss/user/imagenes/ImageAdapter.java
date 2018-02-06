package com.tarjentonimss.user.imagenes;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
@SuppressWarnings("deprecation")
public class ImageAdapter extends BaseAdapter {

    int mGalleryItemBackground;
    private Context mContext;

    public Integer medidor = MainActivity.densidadpantalla;
    // references to our images
    private Integer[] mImageIds = {
            R.drawable.cowboyshombre,
            R.drawable.cowboysmujer,
            R.drawable.billshombre,
            R.drawable.billsmujer,
            R.drawable.cuarenhombre,
            R.drawable.cuarentmujer,
            R.drawable.denverhombre,
            R.drawable.denvermujer,
            R.drawable.dolphinshombtre,
            R.drawable.dophinsmujer,
            R.drawable.eagleshombre,
            R.drawable.eaglesmujer,
            R.drawable.greenhombre,
            R.drawable.greenmujer,
            R.drawable.lionshombre,
            R.drawable.lionsmujer,
            R.drawable.panterashombre,
            R.drawable.panterasmujer,
            R.drawable.patriotshombre,
            R.drawable.patriotsmujuer,
            R.drawable.pitburghombre,
            R.drawable.patriotsmujuer,
            R.drawable.raidershombre,
            R.drawable.raidersmujer,
            R.drawable.santoshombre,
            R.drawable.santosmujer


    };

    public ImageAdapter(Context c) {
        mContext = c;
        TypedArray attr = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
        mGalleryItemBackground = attr.getResourceId(
                R.styleable.HelloGallery_android_galleryItemBackground, 0);
        attr.recycle();
    }

    public int getCount() {

        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }



    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mImageIds[position]);

        if (medidor > 239) {
            imageView.setLayoutParams(new Gallery.LayoutParams(280, 240));}
        if (medidor < 160) {
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 180));}
        if (medidor < 140) {
            imageView.setLayoutParams(new Gallery.LayoutParams(120, 110));}



        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundResource(mGalleryItemBackground);
        return imageView;




    }
}
