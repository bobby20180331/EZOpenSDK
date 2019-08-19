package com.videogo.scanvideo;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.LruCache;

import ezviz.ezopensdk.R;

public class MyVideoThumbLoader {
    // 创建cache
    private LruCache<String, Bitmap> lruCache;
    private Context mContext;

    public MyVideoThumbLoader(Context context) {
        mContext = context;
        int maxMemory = (int) Runtime.getRuntime().maxMemory();// 获取最大的运行内存
        int maxSize = maxMemory / 4;
        // 拿到缓存的内存大小
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 这个方法会在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };
    }
    public void addVideoThumbToCache(String path, Bitmap bitmap) {
        if (getVideoThumbToCache(path) == null) {
            // 当前地址没有缓存时，就添加

            lruCache.put(path, bitmap);
        }
    }
    public Bitmap getVideoThumbToCache(String path) {

        return lruCache.get(path);

    }
    public void showThumbByAsynctack(String path, MyImageView imgview,int width,int height) {

        if (getVideoThumbToCache(path) == null) {
            // 异步加载
            new MyBobAsynctack(imgview, path,width,height).execute(path);
        } else {
            imgview.setImageBitmap(getVideoThumbToCache(path));
        }

    }

    class MyBobAsynctack extends AsyncTask<String, Void, Bitmap> {
        private MyImageView imgView;
        private String path;
        private int width;
        private int height;

        public MyBobAsynctack(MyImageView imageView, String path,int width,int height) {
            this.imgView = imageView;
            this.path = path;
            this.width = width;
            this.height = height;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap2 = null;
            try {
                ThumbnailUtils tu = new ThumbnailUtils();
                Bitmap bitmap = tu.createVideoThumbnail(params[0], MediaStore.Video.Thumbnails.MINI_KIND);

                if (bitmap == null) {
                    //将drawable转bitmap
                    bitmap = android.graphics.BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
                }
                // //直接对Bitmap进行缩略操作，最后一个参数定义为OPTIONS_RECYCLE_INPUT ，来回收资源
                bitmap2 = tu.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                // 加入缓存中
                if (getVideoThumbToCache(params[0]) == null) {
                    addVideoThumbToCache(path, bitmap2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap2;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //if (imgView.getTag().equals(path)) {// 通过 Tag可以绑定 图片地址和
                // imageView，这是解决Listview加载图片错位的解决办法之一
                imgView.setImageBitmap(bitmap);
          //  }
        }
    }
}
