package so.xiaolu.xiaolu.mainframe;

import so.xiaolu.xiaolu.R;
import so.xiaolu.xiaolu.mainsetting.MainUrl;
import so.xiaolu.xiaolu.browse.BrowseCommodityActivity;
import so.xiaolu.xiaolu.jsonbean.IndexBean;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.google.gson.Gson;


import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

public class TodayFragment extends Fragment {
    final OkHttpClient client = new OkHttpClient();
    JSONArray jsonArray;
    MyHandle myHandler;
    View view;
    Context context;
    String TAG = "huangyan";

    public TodayFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        view = inflater.inflate(R.layout.today_main_fragment, container, false);
        myHandler = new MyHandle();
        Today_MyThread th = new Today_MyThread();
        th.start();
        return view;
    }

    /*
    * 今日特卖
    * */
    public class Today_MyThread extends Thread {
        @Override
        public void run() {
            try {
                MainUrl url = new MainUrl();
                Request request = new Request.Builder().url(url.getTODAY_URL()).build();
                Response response;
                String showInfoStr = "";
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        showInfoStr = response.body().string();
                    } else {
                        Log.d(TAG, response.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                parseIndexFromJson(showInfoStr);


                /* 测试开始*/
//                IndexBean person = new IndexBean();
//                IndexBean.female p = new IndexBean.female();
//                p.id = "25397";
//                p.url = "http://m.xiaolu.so/rest/v1/products/25397";
//                p.name = "韩版假两件碎花连衣裙/黑色碎花";
//                p.outer_id = "819254350011";
//                List list = new ArrayList();
//                list.add(p);
//                person.female_list =list;
                /* 测试结束*/


            } catch (Exception e) {
                Log.e("TAG", "error");
                e.printStackTrace();
            }
        }

    }

    public void parseIndexFromJson(String jsonData) {
        try {
            /*使用案例*/
//            Gson gson = new Gson();
//            java.lang.reflect.Type type = new TypeToken<JsonBean>() {}.getType();
//            JsonBean jsonBean = gson.fromJson(jsonData, type);
//            List<JsonBean.B> list = jsonBean.b;
//            Log.v(TAG, list.toString());
//            Log.v(TAG, list.get(0).b1.toString());
            /*使用案例*/
            Log.v(TAG, jsonData);
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<IndexBean>() {
            }.getType();
            IndexBean indexBean = gson.fromJson(jsonData, type);
            showMsg(indexBean);
            List<IndexBean.product> female_list = indexBean.female_list;
            List<IndexBean.product> child_list = indexBean.child_list;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMsg(IndexBean indexBean) {
        Message msg = Message.obtain();
        msg.obj = indexBean;
        msg.setTarget(myHandler);//把message内容放入，handle
        msg.sendToTarget();
    }

    class User {
        private JSONArray json;
        private Bitmap[] map = new Bitmap[jsonArray.length()];

    }

    class MyHandle extends Handler {

        @Override
        public void handleMessage(Message msg) {

            IndexBean indexBean = (IndexBean) msg.obj;
            List<IndexBean.product> female_list = indexBean.female_list;
            List<IndexBean.product> child_list = indexBean.child_list;
            String s = String.valueOf(female_list.size());

            Log.v(TAG, String.valueOf(female_list.size()));

//            for (int i = 0; i < female_list.size(); i++) {
//                Log.v(TAG, String.valueOf(female_list.get(i).name));
//
//            }
//            User user1=(User) msg.obj;
//            JSONArray json1=user1.json;
//            Bitmap[] mapArray=new Bitmap[json1.length()];
//
//            mapArray = user1.map;


            /***************************************/

            GridView nvzhuang_gridview = (GridView) view.findViewById(R.id.nvzhuang_gridview);
            GridView child_gridview = (GridView) view.findViewById(R.id.child_gridview);
//            ArrayList<HashMap<String, Object>> libr_gridItem = new ArrayList<HashMap<String, Object>>();
//            for (int i = 0; i < female_list.size(); i++) {
//
//                HashMap<String, Object> map = new HashMap<String, Object>();
//
//                String name = null;
//                name = female_list.get(i).name;
//                map.put("ItemImage", "https://mmbiz.qlogo.cn/mmbiz/yMhOQPTKhLuEZRxcZp90Kxq7ycrNc81wcAZ004oNFQMBIGVGL75awvTxVh7qGaEE8BoSWP3FSbeeQPMYrS4RBA/0?wx_fmt=png");
//                map.put("ItemText", female_list.get(i).name);
//
//                Log.d("FavoriteImage", "https://mmbiz.qlogo.cn/mmbiz/yMhOQPTKhLuEZRxcZp90Kxq7ycrNc81wcAZ004oNFQMBIGVGL75awvTxVh7qGaEE8BoSWP3FSbeeQPMYrS4RBA/0?wx_fmt=png");
//                Log.d("FavoriteText", female_list.get(i).name);
//                libr_gridItem.add(map);
//
//            }
            FraMyAdapter nvadapter = new FraMyAdapter(context, female_list);
            FraMyAdapter childadapter = new FraMyAdapter(context, child_list);

            nvzhuang_gridview.setAdapter(nvadapter);
            child_gridview.setAdapter(childadapter);


            nvzhuang_gridview.setOnItemClickListener(new OnItemClickListener() {
                Bundle bundle = null;
                JSONObject json;
                String loc_name;
                String loc_author;
                String loc_price;
                String loc_id;

                @Override
                public void onItemClick(AdapterView<?> arg0,
                                        View view, int position, long id) {


                    try {
                        json = jsonArray.getJSONObject(position);
                        loc_name = json.getString("name");
                        loc_author = json.getString("author");
                        loc_price = json.getString("price");
                        loc_id = json.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    bundle = new Bundle();
                    bundle.putString("loc_name", loc_name);
                    bundle.putString("loc_author", loc_author);
                    bundle.putString("loc_price", loc_price);
                    bundle.putString("loc_id", loc_id);
                    Intent intent = new Intent(getActivity(), BrowseCommodityActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


        }


    }

    /**************
     * set BaseAdapter
     ********************/

    public class FraMyAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<IndexBean.product> dataSource;
        private ImageLoader mImageLoader;
        List<HashMap<String, String>> data;

        public FraMyAdapter(Context c, List<IndexBean.product> productList) {
            this.inflater = LayoutInflater.from(c);
            this.dataSource = productList;
            RequestQueue mQueue = Volley.newRequestQueue(context);   //创建一个RequestQueue对象
            mImageLoader = new ImageLoader(mQueue, new BitmapCache());  //创建一个ImageLoader对象
            this.data = new ArrayList<HashMap<String, String>>();
            String productName = null;
            String agentPrice = null;
            String head_img = null;
            for (int i = 0; i < dataSource.size(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                productName = dataSource.get(i).name;
                head_img = dataSource.get(i).head_img;
                String[] temp = head_img.split("http://image.xiaolu.so/");
                if (temp.length > 1){
                    try {
                        head_img = "http://image.xiaolu.so/" + URLEncoder.encode(temp[1],"utf-8");
                    }catch (UnsupportedEncodingException e){
                        e.printStackTrace();
                    }
                }
                agentPrice = "价格:￥" + dataSource.get(i).agent_price;
                map.put("Name", productName);
                map.put("agentPrice", agentPrice);
                map.put("headImg", head_img);
                data.add(map);
            }
        }

        @Override
        public int getCount() {
            return dataSource.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.today_main_fragment_gridview, null);
                holder = new ViewHolder();
                holder.head_img = (ImageView) convertView.findViewById(R.id.product_gridview_ItemImage);
                holder.product_name = (TextView) convertView.findViewById(R.id.product_gridview_ItemText);
                holder.agent_price = (TextView) convertView.findViewById(R.id.product_gridview_price);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.product_name.setText(data.get(position).get("Name").toString());
            holder.agent_price.setText(data.get(position).get("agentPrice").toString());
            String url = "http://image.xiaolu.so/MG-1447492271324-%E9%9F%A9%E7%89%88%E5%81%87%E4%B8%A4%E4%BB%B6%E7%A2%8E%E8%8A%B1%E8%BF%9E%E8%A1%A3%E8%A3%9902.png"; //服务器端尽量不要用中文

            ImageListener listener = ImageLoader.getImageListener(holder.head_img, android.R.drawable.picture_frame, android.R.drawable.picture_frame);//获取一个ImageListener对象
            //mImageLoader.get(data.get(position).get("headImg").toString(), listener);//调用ImageLoader的get()方法加载网络上的图片
            mImageLoader.get(url, listener);
            return convertView;
        }

    }

    public class ViewHolder {
        public ImageView head_img;
        public TextView product_name;
        public TextView agent_price;
        public TextView std_sale_price;
    }
    /*
    * 缓存*/
    public class BitmapCache implements ImageCache {
        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }

    }


    public String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}