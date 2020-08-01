package com.wanshare.wscomponent.pulltorefresh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SmartRefreshLayout refreshLayout;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = findViewById(R.id.smartRefresh);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RvAdapter());

        //下拉刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                    }
                }, 1000);
            }
        });

        //下拉刷新和上拉加载监听
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //完成加载更多
                        refreshLayout.finishLoadMore();
                    }
                }, 1000);
            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //完成刷新
                        refreshLayout.finishRefresh();
                    }
                }, 1000);
            }
        });

        //设置下拉刷新和加载更多为可用
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(true);
    }

    class RvAdapter extends RecyclerView.Adapter<RvAdapter.Vh> {
        private List<String> list = new ArrayList<>();

        public RvAdapter() {
            for(int i = 1; i <= 20; i++) {
                list.add(String.format("%d%d%d", i, i, i));
            }
        }

        @NonNull
        @Override
        public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Vh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull Vh holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Vh extends RecyclerView.ViewHolder {
            private TextView tv;

            public Vh(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }

            public void bind(int position) {
                tv.setText(list.get(position));
            }
        }
    }
}
