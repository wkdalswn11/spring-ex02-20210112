package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long rno);
	
	public int delete(Long rno);
	
	public int update(ReplyVO vo);
	
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri, // 파라미터가 두개이기때문에 명확하게 표시할 필요가있음
			@Param("bno") Long bno);	// 그래서 @Param으로 어노테이션을 사용했음.
}
