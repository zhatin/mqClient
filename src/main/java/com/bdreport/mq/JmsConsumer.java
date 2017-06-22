package com.bdreport.mq;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bdreport.mq.data.JmsRealDataModel;
import com.bdreport.mq.data.JpaRealDataRepository;

@Component
public class JmsConsumer {

	private static Logger logger = Logger.getLogger(JmsConsumer.class.getName());

	@Autowired
	JpaRealDataRepository dataRepository;

	@JmsListener(destination = "bdreport.queue")
	public void receiveQueue(String text) {
		logger.debug("Message in Queue: " + text);

		JmsRealDataModel dataModel = (JmsRealDataModel) JSON.parseObject(text, JmsRealDataModel.class);
		//dataRepository.append(dataModel);
		
		dataRepository.appendList(dataModel);
	}

}
