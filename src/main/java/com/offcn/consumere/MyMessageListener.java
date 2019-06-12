package com.offcn.consumere;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener{

	@Override
	public void onMessage(Message msg) {
	  TextMessage message =	(TextMessage)msg;
	  try {
		System.out.println(message.getText());
	} catch (JMSException e) {
		e.printStackTrace();
	}
	}

}
