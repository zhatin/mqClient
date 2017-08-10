package com.bdreport.mq;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bdreport.mq.data.JmsRealDataModel;
import com.bdreport.mq.data.JpaRealDataRepository;

@Component
@PropertySource(value= "classpath:/properties/local/application.properties")
public class JmsConsumer {

    @Configuration
    @Profile("production")
    @PropertySource("classpath:/properties/production/application.properties")
    static class Production
    { }

    @Configuration
    @Profile("local")
    @PropertySource({"classpath:/properties/local/application.properties"})
    static class Local
    { }

    private static Logger logger = Logger.getLogger(JmsConsumer.class.getName());

	@Autowired
	JpaRealDataRepository dataRepository;

    @JmsListener(destination = "${bdreport.queue.name}")
	public void receiveQueue(String text) {
		logger.debug("Message in Queue: " + text);

		JmsRealDataModel dataModel = (JmsRealDataModel) JSON.parseObject(text, JmsRealDataModel.class);
		//dataRepository.append(dataModel);
		
		dataRepository.appendList(dataModel);
	}

}
