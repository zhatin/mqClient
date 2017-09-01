CREATE TABLE `report_bx_data` (
  `seq_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_date_time` varchar(255) DEFAULT NULL,
  `protocol_code` varchar(255) DEFAULT NULL,
  `gateway_no` int(11) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT NULL,
  `sensor_data` float DEFAULT NULL,
  `sensor_no` int(11) DEFAULT NULL,
  `terminator_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`seq_id`),
  KEY `index2` (`data_date_time`),
  KEY `index3` (`protocol_code`),
  KEY `index4` (`gateway_no`),
  KEY `index5` (`sensor_data`),
  KEY `index6` (`sensor_no`),
  KEY `index7` (`terminator_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;