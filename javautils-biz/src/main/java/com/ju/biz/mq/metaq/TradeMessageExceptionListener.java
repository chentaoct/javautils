package com.ju.biz.mq.metaq;

import com.ju.biz.mq.metaq.messages.Trade;
import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;


/**
 * Process trade messages listener.
 * 
 * @author dennis
 * 
 */
public class TradeMessageExceptionListener extends DefaultMessageListener<Trade> {

    @Override
    public void onReceiveMessages(MetaqMessage<Trade> msg) {
        Trade trade = msg.getBody();
        System.out.println("receive trade message:" + trade);
        throw new RuntimeException("12312");
    }

}
