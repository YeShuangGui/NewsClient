package ysg.gdcp.cn.newsclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ysg.gdcp.cn.newsclient.OnClickEvent;
import ysg.gdcp.cn.newsclient.R;
import ysg.gdcp.cn.newsclient.WebActivity;
import ysg.gdcp.cn.newsclient.adapter.NewsAdapter;
import ysg.gdcp.cn.newsclient.domain.NewsData;

/**
 * Created by Administrator on 2017/6/22 15:50.
 *
 * @author ysg
 */

public class NewFragment extends Fragment implements OnClickEvent,SwipeRefreshLayout.OnRefreshListener{
    private static final int MSG_GET_NEWS = 1001;
    private int mTYPE;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mNewsAdapter;
    private List<NewsData.ResultBean.DataBean> mList = new ArrayList<>();
    private final String URL1 = "http://v.juhe.cn/toutiao/index?type=top&key=d34c49d35647f184e7c780ec73486b77";
    private final String URL2 = "http://v.juhe.cn/toutiao/index?type=shehui&key=d34c49d35647f184e7c780ec73486b77";
    private final String URL3 = "http://v.juhe.cn/toutiao/index?type=guonei&key=d34c49d35647f184e7c780ec73486b77";
    private final String URL4 = "http://v.juhe.cn/toutiao/index?type=guoji&key=d34c49d35647f184e7c780ec73486b77";
    private final String URL5 = "http://v.juhe.cn/toutiao/index?type=yule&key=d34c49d35647f184e7c780ec73486b77";
    private final String URL6 = "http://v.juhe.cn/toutiao/index?type=tiyu&key=d34c49d35647f184e7c780ec73486b77";
    private final String URL7 = "http://v.juhe.cn/toutiao/index?type=junshi&key=d34c49d35647f184e7c780ec73486b77";
    private final String URL8 = "http://v.juhe.cn/toutiao/index?type=keji&key=d34c49d35647f184e7c780ec73486b77";
    private final String URL9 = "http://v.juhe.cn/toutiao/index?type=caijing&key=d34c49d35647f184e7c780ec73486b77";
    private final String URL10 = "http://v.juhe.cn/toutiao/index?type=shishang&key=d34c49d35647f184e7c780ec73486b77";
    private Handler mHandler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("AAA", "onCreateView");
        View view =  inflater.inflate(R.layout.fragment_news,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //设置下拉圆圈上的颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
        //设置手指在下拉多少距离会触发下拉更新
        mSwipeRefreshLayout.setDistanceToTriggerSync(400);
        mSwipeRefreshLayout.setProgressBackgroundColor(R.color.colorPrimary);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mNewsAdapter = new NewsAdapter(mList,this);
        mRecyclerView.setAdapter(mNewsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTYPE = bundle.getInt("NEWSTYPE");
        initHandler();
        getNewsFromJuhe();

    }

    private void getNewsFromJuhe() {
        OkHttpClient client = new OkHttpClient();
        Request request;
        switch (mTYPE){
            case 1:
                request = new Request.Builder().url(URL1).build();
                break;
            case 2:
                request = new Request.Builder().url(URL2).build();
                break;
            case 3:
                request = new Request.Builder().url(URL3).build();
                break;
            case 4:
                request = new Request.Builder().url(URL4).build();
                break;
            case 5:
                request = new Request.Builder().url(URL5).build();
                break;
            case 6:
                request = new Request.Builder().url(URL6).build();
                break;
            case 7:
                request = new Request.Builder().url(URL7).build();
                break;
            case 8:
                request = new Request.Builder().url(URL8).build();
                break;
            case 9:
                request = new Request.Builder().url(URL9).build();
                break;
            case 10:
                request = new Request.Builder().url(URL10).build();
                break;
            default:
                request = new Request.Builder().url(URL1).build();
        }
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("AA","FAILED");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                NewsData newsData = gson.fromJson(response.body().string(), NewsData.class);
                mList = newsData.getResult().getData();
                mHandler.sendEmptyMessage(MSG_GET_NEWS);
            }
        });
    }

    private void initHandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == MSG_GET_NEWS){
                    mNewsAdapter.changData(mList);
                    return true;
                }
                return false;
            }
        });
    }


    @Override
    public void goToDetail(String url) {
        Intent i = new Intent(NewFragment.this.getContext(),WebActivity.class);
        i.putExtra("url",url);
        startActivity(i);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*mNewsAdapter.changData(mList);*/
                // 停止刷新
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000); // 1秒后发送消息，停止刷新

    }
}

