package com.bdreport.mq.jdbc;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "report_bx_data")
public class DataRecordBx {

	@Id
	@GenericGenerator(name = "id_generator", strategy = "identity")
	// @GenericGenerator(name = "id_generator", strategy = "sequence",parameters
	// = {@Parameter(name = "sequence",value="seq_id_seq")})
	@GeneratedValue(generator = "id_generator")
	private long seq_id;

	@Column(name = "insert_time", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date insert_time;

	@Column(name = "gateway_no")
	private int gateway_no;
	
	@Column(name = "terminator_no")
	private int terminator_no;

	@Column(name = "protocol_code")
	private String protocol_code;

	@Column(name = "data_date_time")
	private String data_date_time;

	@Column(name = "sensor_no")
	private int sensor_no;

	@Column(name = "sensor_data")
	private float sensor_data;

	@Column(name = "remark")
	private String remark;

	public long getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(long seq_id) {
		this.seq_id = seq_id;
	}

	public Date getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}

	public int getGateway_no() {
		return gateway_no;
	}

	public void setGateway_no(int gateway_no) {
		this.gateway_no = gateway_no;
	}

	public int getTerminator_no() {
		return terminator_no;
	}

	public void setTerminator_no(int terminator_no) {
		this.terminator_no = terminator_no;
	}

	public String getProtocol_code() {
		return protocol_code;
	}

	public void setProtocol_code(String protocol_code) {
		this.protocol_code = protocol_code;
	}

	public String getData_date_time() {
		return data_date_time;
	}

	public void setData_date_time(String data_date_time) {
		this.data_date_time = data_date_time;
	}

	public int getSensor_no() {
		return sensor_no;
	}

	public void setSensor_no(int sensor_no) {
		this.sensor_no = sensor_no;
	}

	public float getSensor_data() {
		return sensor_data;
	}

	public void setSensor_data(float sensor_data) {
		this.sensor_data = sensor_data;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
