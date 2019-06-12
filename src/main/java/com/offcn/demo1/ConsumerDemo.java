package com.offcn.demo1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerDemo {

	public static void main(String[] args) throws Exception {
		// jetty 默认启动mq的端口是8161   
				// mq的默认端口61616
				String url = "tcp://10.10.84.201:61616";
				// 创建连接
				ConnectionFactory  connectionFactory = new ActiveMQConnectionFactory(url);
				// 获取连接对象
				Connection connection = connectionFactory.createConnection();
				connection.start();
				// 通过connection获取session对象  
				// 第一个参数  是否开启事务   ，  消息的模式
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				// 获取队列
				Topic topic = session.createTopic("java1210");
				// 创建消息消费者
				MessageConsumer consumer = session.createConsumer(topic);
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
