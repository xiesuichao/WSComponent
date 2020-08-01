package com.wanshare.wscomponent.websocket;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wanshare.wscomponent.websocket.bean.DepthEntity;
import com.wanshare.wscomponent.websocket.bean.KlineEntity;
import com.wanshare.wscomponent.websocket.bean.OverviewEntity;
import com.wanshare.wscomponent.websocket.bean.SummaryEntity;
import com.wanshare.wscomponent.websocket.bean.TradeHistoryEntity;

import java.util.List;

public class TestActivity1 extends AbsWebSocketActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        TextView textView = findViewById(R.id.tv_test_info);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendText("42[\"home:join\"]");
            }
        });
    }

    @Override
    public void onServiceBindSuccess() {
        super.onServiceBindSuccess();
//        sendText("k-line/21345/1d");
//        sendText("depth/21345");
//        sendText("overview/21345");
//        sendText("partition-summary/21345");
        sendText("trade-history/21345");
    }

    @Override
    public void onMessageResponse(Response message) {

//        KlineEntity  klineEntity= (KlineEntity)message.getResponseEntity();
//        String period = klineEntity.getPeriod();

//        DepthEntity depthEntity = (DepthEntity)message.getResponseEntity();
//        String list = depthEntity.getAsks().toString();

//        OverviewEntity overviewEntity = (OverviewEntity)message.getResponseEntity();
//        String name = overviewEntity.getBuyerCoinName();

//        List<SummaryEntity> summaryEntity = (List<SummaryEntity>)message.getResponseEntity();
//        String name = summaryEntity.get(0).getBuyerCoinName();

        List<TradeHistoryEntity> tradeHistoryEntity = (List<TradeHistoryEntity>)message.getResponseEntity();
        String time = tradeHistoryEntity.get(0).getCreatedAt();

}

    @Override
    public void onSendMessageError(ErrorResponse error) {

    }
}
