package com.example.jsouphelpdemo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class JianshuAdapter extends BaseQuickAdapter<JianshuBean, BaseViewHolder> {

    private Context context;

    public JianshuAdapter(Context context) {
        super(R.layout.item_home);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, JianshuBean bean) {
        helper.setText(R.id.tv_author, bean.getAuthorName())
                .setText(R.id.tv_title, bean.getTitle())
                .setText(R.id.tv_content, bean.getContent())
                .setText(R.id.tv_collectTag, bean.getCollectionTag())
                .setText(R.id.tv_talk, bean.getTalkNum())
                .setText(R.id.tv_like, bean.getLikeNum())
                .addOnClickListener(R.id.iv_primary)
                .addOnClickListener(R.id.tv_content)
                .addOnClickListener(R.id.tv_title)
                .addOnClickListener(R.id.tv_collectTag)
                .addOnClickListener(R.id.tv_author);
        Glide.with(context).load(bean.getPrimaryImg()).into((ImageView) helper.getView(R.id.iv_primary));
    }
}
