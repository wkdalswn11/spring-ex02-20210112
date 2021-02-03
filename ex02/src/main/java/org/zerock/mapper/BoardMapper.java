package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
		
		public int getTotalCount(Criteria cri);
		// SELECT count(*) FROM tb1_board
		
		public List<BoardVO> getList();
		
		public List<BoardVO> getListWithPaging(Criteria cri); // amount > 한페이지에 몇개를 가져야하는가
		
		public void insert(BoardVO board);
//		{
//			String sql = "INSERT INTO tb1_board "
//					+ "(bno, title, content, writer, regdate, updatedate) "
//					+ "VALUES (seq_board.nextval, ?, ?, ?, SYSDATE, SYSDATE) ";
//			PreparedStatement pstmt = con.prepareStatement(sql);
//			
//			pstmt.setString(1, board.getTitle());
//			pstmt.setString(2, board.getContent());
//			pstmt.setString(3, board.getWriter());
//			
//			pstmt.updateQuery();
//			close();
//		}
		
		public void insertSelectKey(BoardVO board);
			// 1. seq_board의 nextval을 먼저 조회(select)
			// 2. 조회된 nextval을 insert에서 사용
		
		public BoardVO read(Long bno);
			
		public int delete(Long bno);
		
		public int update(BoardVO board);
											// 여기서 amount는 실제 갯수가아닌 하나를 더하면 +1 , 하나를지우면 -1 을 하는 것
		public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
		
		
}
