package ysg.gdcp.cn.newsclient.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ysg.gdcp.cn.newsclient.OnClickEvent;
import ysg.gdcp.cn.newsclient.R;
import ysg.gdcp.cn.newsclient.domain.NewsData;

/**
 * Created by Administrator on 2017/6/22 15:45.
 *
 * @author ysg
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsData.ResultBean.DataBean> mNewsList;
    private OnClickEvent mOnClickEvent;
    public NewsAdapter(List<NewsData.ResultBean.DataBean> newsList,OnClickEvent onClickEvent){
        this.mNewsList = newsList;
        this.mOnClickEvent = onClickEvent;
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        final NewsViewHolder holder = new NewsViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                NewsData.ResultBean.DataBean news = mNewsList.get(position);
                String url = news.getUrl();
                mOnClickEvent.goToDetail(url);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsData.ResultBean.DataBean news = mNewsList.get(position);
        holder.mTextView.setText(news.getTitle());
        Glide.with(holder.mImageView.getContext())
                .load(news.getThumbnail_pic_s())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        public NewsViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_img);
            mTextView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
    public void changData(List<NewsData.ResultBean.DataBean> newsList){
        this.mNewsList = newsList;
        notifyDataSetChanged();

    }

}

