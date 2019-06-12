package com.offcn.productor;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MsgProductor {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext-mq.xml");
		// 获取模板
		JmsTemplate template =  (JmsTemplate) app.getBean("jmsTemplate");
		// 获取消息队列
		ActiveMQTopic topic =  (ActiveMQTopic) app.getBean("topicDestination");
		// 发送消息
		
		template.send(topic, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = session.createTextMessage("lailelaodi");
				return msg;
			}
		});
	}
}
