package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.Sample1Mapper;
import org.zerock.mapper.Sample2Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxService {	// Tx = 트랜젝션
	
	@Setter (onMethod_ = @Autowired)
	private Sample1Mapper mapper1;
	@Setter (onMethod_ = @Autowired)
	private Sample2Mapper mapper2;
	
	@Transactional		// 트랜젝션 어노테이션을통해  아래에 insertCol1, insertCol2 가 모두 만족할때만 OK
	public void addData(String value) {
		mapper1.insertCol1(value); // 500bytes 50bytes보다 클경우 OK
		mapper2.insertCol2(value); // 50bytes	50bytes보다 클경우 fail
	}
	
}	
