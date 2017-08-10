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
import com.bdreport.mq.data.DataModelBa;
import com.bdreport.mq.data.DataModelBx;
import com.bdreport.mq.data.JpaRealDataRepository;

@Component
@PropertySource(value= "classpath:/properties/local/application.properties")
public class JmsConsumerBa {

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

    private static Logger logger = Logger.getLogger(JmsConsumerBa.class.getName());

	@Autowired
	JpaRealDataRepository dataRepository;

    @JmsListener(destination = "${bdreport.queueBa.name}")
	public void receiveQueue(String text) {
		logger.debug("Message in Queue: " + text);

		DataModelBa dataModel = (DataModelBa) JSON.parseObject(text, DataModelBa.class);
		//dataRepository.append(dataModel);
		
		dataRepository.appendList(dataModel);
	}

}
