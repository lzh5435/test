package com.offcn.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息的异步接收
 * @author luoyanpeng
 *
 */
public class MqCosumer {

	public static void main(String[] args) throws Exception {
		// jetty 默认启动mq的端口是8161   
		// mq的默认端口61616
		String url = "tcp://192.168.21.166:61616";
		// 创建连接
		ConnectionFactory  connectionFactory = new ActiveMQConnectionFactory(url);
		// 获取连接对象
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// 通过connection获取session对象  
		// 第一个参数  是否开启事务   ，  消息的模式
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 获取队列
		Queue queue = session.createQueue("javatest");
		// 创建消息消费者
		MessageConsumer consumer = session.createConsumer(queue);
		// 监听消息  异步（接收消息）
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message msg) {
				if(msg instanceof TextMessage){
					TextMessage message = (TextMessage)msg;
					try {
						System.out.println("接收的消息是"+message.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		// 等待键盘输入
		System.in.read();
		consumer.close();
		session.close();
		connection.close();
	}

}
