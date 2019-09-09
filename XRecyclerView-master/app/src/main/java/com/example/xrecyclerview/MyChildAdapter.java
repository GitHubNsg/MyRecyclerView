package com.example.xrecyclerview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yanxuwen.DensityUtil;
import com.yanxuwen.MyRecyclerview.MyBaseAdapter;
import com.yanxuwen.swipelibrary.SwipeLayout;

import java.util.List;

/**
 * 作者：严旭文 on 2016/5/11 14:36
 * 邮箱：420255048@qq.com
 */
public class MyChildAdapter extends MyBaseAdapter {
    private List<String> mDataSet;
    private Context mContext;
    boolean isStaggered = false;

    public MyChildAdapter(Context context, List<String> dataSet) {
        super(context, dataSet);
        this.mDataSet = dataSet;
        this.mContext = context;
    }

    public MyChildAdapter(Context context, List<String> dataSet, boolean isStaggered) {
        super(context, dataSet);
        this.mDataSet = dataSet;
        this.mContext = context;
        this.isStaggered = isStaggered;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        addSwipe(R.layout.swipe_default2, SwipeLayout.ShowMode.LayDown, SwipeLayout.DragEdge.Right, true);
        addExpand(R.layout.expand_default);
        return new ViewHolder(setLayout(R.layout.item, parent));
    }

    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        final ViewHolder mViewHolder = (ViewHolder) holder;
        int adpterposition = holder.getCurPosition();
        mViewHolder.text.setText(mDataSet.get(position));
        android.widget.LinearLayout.LayoutParams lp = new android.widget.LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        if (isStaggered && position % 2 == 0) {
            lp.height = DensityUtil.dip2px(mContext, 80);
            lp.width = DensityUtil.dip2px(mContext, 50);
            lp.leftMargin = DensityUtil.dip2px(mContext, 5);
            lp.rightMargin = DensityUtil.dip2px(mContext, 5);
            lp.topMargin = DensityUtil.dip2px(mContext, 5);
            lp.bottomMargin = DensityUtil.dip2px(mContext, 5);


        } else {
            lp.height = DensityUtil.dip2px(mContext, 50);
            lp.width = DensityUtil.dip2px(mContext, 50);
            lp.leftMargin = DensityUtil.dip2px(mContext, 5);
            lp.rightMargin = DensityUtil.dip2px(mContext, 5);
            lp.topMargin = DensityUtil.dip2px(mContext, 5);
            lp.bottomMargin = DensityUtil.dip2px(mContext, 5);
        }

        mViewHolder.v_expand.setLayoutParams(lp);
        super.onBindViewHolder(holder, position);

    }

    class ViewHolder extends BaseViewHolder implements View.OnClickListener {
        private ViewHolder mViewHolder;
        TextView text;
        View v_expand;
        View swipe_delete;
        LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            v_expand = itemView.findViewById(R.id.v_expand);
            swipe_delete = itemView.findViewById(R.id.swipe_delete);
            layout = itemView.findViewById(R.id.layout);

            mViewHolder = this;
            //设置自动展开按钮，
//            setExpandView(v_expand);
            swipe_delete.setOnClickListener(this);
//            layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext,"点击",Toast.LENGTH_SHORT).show();
//
//                }
//            });
            layout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Toast.makeText(mContext, "点击", Toast.LENGTH_SHORT).show();

                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.swipe_delete:
                    remove(mViewHolder.getCurPosition());
                    break;

            }
        }
    }

    public void add(String text, int position) {
        mDataSet.add(position, text);
        super.add(position);
    }
}
