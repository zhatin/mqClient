package com.bdreport.mq.data;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bdreport.mq.jdbc.DataRecordBx;

@Repository
public class JpaRealDataRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private static Logger logger = Logger.getLogger(JpaRealDataRepository.class);

	@Transactional(readOnly = false)
	public void append(DataModelBx dataModel) {
		String sql = "";

		sql = sql
				+ "insert into data_from_mq (gateway_no,data_type,data_date_time,remote_ip,remote_port,data_length,data_list) values (";
		sql = sql + Integer.valueOf(dataModel.getGatewayNo()).toString() + ", ";
		sql = sql + "'" + dataModel.getFuncCode() + "',";
		sql = sql + "'" + dataModel.getDataTime() + "',";
		sql = sql + "'" + dataModel.getIpAddr() + "',";
		sql = sql + Integer.valueOf(dataModel.getInetPort()).toString() + ", ";
		sql = sql + Integer.valueOf(dataModel.getDataLength()).toString() + ", ";
		sql = sql + "'" + dataModel.getDataList().toString() + "'";
		sql = sql + " );";

		logger.debug("SQL: " + sql);

		this.entityManager.createNativeQuery(sql).executeUpdate();
	}

	@Transactional(readOnly = false)
	public void appendList(DataModelBx dataModel) {

		int gateway_no = dataModel.getGatewayNo();
		String protocol_code = dataModel.getFuncCode();
		String data_date_time = dataModel.getDataTime();
		// int dl = dataModel.getDataLength();
		Map<Integer, List<Float>> dat = dataModel.getDataList();
		Iterator<Map.Entry<Integer, List<Float>>> entries = dat.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<Integer, List<Float>> entry = entries.next();
			int terminator_no = entry.getKey().intValue();
			List<Float> datList = entry.getValue();
			for (int i = 0; i < datList.size(); i++) {
				DataRecordBx dc = new DataRecordBx();
				dc.setGateway_no(gateway_no);
				dc.setProtocol_code(protocol_code);
				dc.setData_date_time(data_date_time);
				dc.setTerminator_no(terminator_no);
				dc.setSensor_no(i + 1);
				dc.setSensor_data(datList.get(i).floatValue());

				this.entityManager.persist(dc);
			}
		}

	}

	@Transactional(readOnly = false)
	public void appendList(DataModelBa dataModel) {

		int gateway_no = dataModel.getGatewayNo();
		String protocol_code = dataModel.getFuncCode();
		String data_date_time = dataModel.getDataTime();
		// int dl = dataModel.getDataLength();
		Map<Integer, Map<Integer, Float>> dat = dataModel.getDataList();
		Iterator<Map.Entry<Integer, Map<Integer, Float>>> entries = dat.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<Integer, Map<Integer, Float>> entry = entries.next();
			int terminator_no = entry.getKey().intValue();
			Map<Integer, Float> datList = entry.getValue();
			Iterator<Map.Entry<Integer,Float>> ents= datList.entrySet().iterator();
			while (ents.hasNext()) {
				Map.Entry<Integer,Float> ent = ents.next();
				DataRecordBx dc = new DataRecordBx();
				dc.setGateway_no(gateway_no);
				dc.setProtocol_code(protocol_code);
				dc.setData_date_time(data_date_time);
				dc.setTerminator_no(terminator_no);
				dc.setSensor_no(ent.getKey().intValue());
				dc.setSensor_data(ent.getValue().floatValue());

				this.entityManager.persist(dc);
			}
		}

	}
}
