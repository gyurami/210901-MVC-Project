package com.sist.dao;

import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import com.sist.vo.FreeboardVO;
import com.sist.vo.ReplyVO;
public class FreeboardDAO {
	//1. 연결, 등록, 반환
		private Connection conn;
	    private PreparedStatement ps;
	    private static FreeboardDAO dao;
	    public void getConnection()
	    {
	    	// DBCP 소스코딩 (톰캣이 Connection객체를 만든다 => 만들어진 주소만 사용)
	    	try
	    	{
	    		Context init=new InitialContext();
	    		Context c=(Context)init.lookup("java://comp//env");
	    		DataSource ds=(DataSource)c.lookup("jdbc/oracle");
	    		conn=ds.getConnection();
	    	}catch(Exception ex)
	    	{
	    		ex.printStackTrace();
	    	}
	    }
	    public void disConnection()
	    {
	    	try
	    	{
	    		if(ps!=null) ps.close();
	    		if(conn!=null) conn.close();
	    	}catch(Exception ex) {}
	    	
	    }
		
	    //2. 싱글턴 패턴
	    public static FreeboardDAO newInstance() {
	    	if(dao==null)
	    		dao=new FreeboardDAO();
	    	return dao;
	    }
	    
	    
	    //3. 기능
	    //3-1. 게시글 목록
	    public List<FreeboardVO> freeboardListData(int page){
	    	List<FreeboardVO> list=new ArrayList<FreeboardVO>();
	    	try {
	    		getConnection();
	    		String sql="SELECT no,subject,name,regdate,hit,num"
	    				+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num"
	    				+ "FROM (SELECT no,subject,name,regdate,hit "
	    				+ "FROM project_freeboard ORDER BY no DESC)) "
	    				+ "WHERE no BETWEEN ? AND ?";
	    		ps=conn.prepareStatement(sql);
	    		int rowSize=10;
	    		int start=(rowSize*page)-(rowSize-1);
	    		int end=(rowSize*page);
	    		ps.setInt(1, start);
	    		ps.setInt(2, end);
	    		
	    		ResultSet rs=ps.executeQuery();
	    		while(rs.next()) {
	    			FreeboardVO vo=new FreeboardVO();
	    			vo.setNo(rs.getInt(1));
	    			vo.setSubject(rs.getString(2));
	    			vo.setName(rs.getString(3));
	    			vo.setRegdate(rs.getDate(4));
	    			vo.setHit(rs.getInt(5));
	    			list.add(vo);
	    		}rs.close();
	    	}catch (Exception e) {
				e.printStackTrace();
			}finally {
				disConnection();
			}
	    	return list;
	    }
	    
	    //3-1-1. 총페이지
	    public int freeboardTotalPage() {
	    	int total=0;
	    	try {
	    		getConnection();
	    		String sql="SELECT CEIL(COUNT(*)/10.0) FROM project_freeboard";
	    		ps=conn.prepareStatement(sql);
	    		ResultSet rs=ps.executeQuery();
	    		rs.next();
	    		total=rs.getInt(1);
	    		rs.close();
	    	}catch (Exception e) {
	    		e.printStackTrace();
			}finally {
				disConnection();
			}return total;
	    }
	    
	    //3-2. 게시글 추가
	    public FreeboardVO freeboardInsertData(FreeboardVO vo) {
	    	FreeboardVO vo=new FreeboardVO();
	    	try {
	    		getConnection();
	    		//no,name,subject,content,pwd,regdate,hit
	    		String sql="INSERT INTO project_freeboard VALUES(no,name,subject,content,pwd) "
	    				+ "VALUES(pf_no_seq.nextval,?,?,?,?)";
	    		ps=conn.prepareStatement(sql);
	    	}catch (Exception e) {
				e.printStackTrace();
			}finally {
				disConnection();
			}
	    	return vo;
	    }
	    
	    //3-3. 게시글 상세보기
	    public FreeboardVO freeboardDetailData(int no) {
	    	FreeboardVO vo=new FreeboardVO();
	    	try {
	    		getConnection();
	    		//no,name,subject,content,pwd,regdate,hit
	    		//3-3-1. 조회수 증가
	    		String sql="UPDATE project_freeboard SET "
	    				+ "hit=hit+1 "
	    				+ "WHERE no=?";
	    		ps=conn.prepareStatement(sql);
	    		ps.setInt(1, no);
	    		ps.executeUpdate();
	    		
	    		//3-3-2. 게시물 상세보기
	    		sql="SELECT no,name,subject,content,regdate,hit "
    				+ "FROM project_freeboard "
    				+ "WHERE no=?";
	    		ps=conn.prepareStatement(sql);
	    		ps.setInt(1, no);
	    		ResultSet rs=ps.executeQuery();
	    		rs.next();
	    		vo.setNo(rs.getInt(1));
	    		vo.setName(rs.getString(2));
	    		vo.setSubject(rs.getString(3));
	    		vo.setContent(rs.getString(4));
	    		vo.setRegdate(rs.getDate(5));
	    		vo.setHit(rs.getInt(6));
	    		rs.close();
	    	}catch (Exception e) {
				e.printStackTrace();
			}finally {
				disConnection();
			}
	    	return vo;
	    }
	    
	    //3-4. 게시글 수정
	    public FreeboardVO freeboardUpdateData(int no) {
	    	FreeboardVO vo=new FreeboardVO();
	    	try {
	    		getConnection();
	    		//no,name,subject,content,pwd,regdate,hit
	    		//3-4-1. 게시물 상세보기
	    		String sql="SELECT no,name,subject,content "
    				+ "FROM project_freeboard "
    				+ "WHERE no=?";
	    		ps=conn.prepareStatement(sql);
	    		ps.setInt(1, no);
	    		ResultSet rs=ps.executeQuery();
	    		rs.next();
	    		vo.setNo(rs.getInt(1));
	    		vo.setName(rs.getString(2));
	    		vo.setSubject(rs.getString(3));
	    		vo.setContent(rs.getString(4));
	    		rs.close();
	    	}catch (Exception e) {
				e.printStackTrace();
			}finally {
				disConnection();
			}
	    	return vo;
	    }
	    
	    
	    //3.4-2 실제 수정(비밀번호 체크 => true: 수정, false: 비밀번호 재입력)
	    public boolean freeboardUpdate(FreeboardVO vo) { // 모델에서 vo값 받아옴
	    	boolean bCheck=false;
	    	try {
	    		getConnection();
	    		String sql="SELECT pwd FROM project_freeboard "
	    				+ "WHERE no=?";
	    		ps=conn.prepareStatement(sql);
	    		ps.setInt(1, vo.getNo());
	    		ResultSet rs=ps.executeQuery();
	    		rs.next();
	    		String db_pwd=rs.getString(1);
	    		rs.close();
	    		if(db_pwd.equals(vo.getPwd())) {
	    			bCheck=true;
	    			//실제 수정
	    			sql="UPDATE project_freeboard SET "
	    				+ "name=?, subject=?, content=? "
	    				+ "WHERE no=?";
	    			ps=conn.prepareStatement(sql);
	    			ps.setString(1, vo.getName());
	    			ps.setString(2, vo.getSubject());
	    			ps.setString(3, vo.getContent());
	    			ps.setInt(4, vo.getNo());
	    			ps.executeUpdate();
	    		}else {
	    			bCheck=false;
	    		}
	    	}catch (Exception e) {
				e.printStackTrace();
			}finally {
				disConnection();
			}
	    	return bCheck;
	    }
	    
	    //3-5. 게시글 삭제
	    public boolean freeboardDeleteData(int no, String pwd) {
	    	boolean bCheck=false;
	    	try {
	    		getConnection();
	    		String sql="SELECT pwd FROM project_freeboard "
	    				+ "WHERE no=?";
	    		ps=conn.prepareStatement(sql);
	    		ps.setInt(1, no);
	    		ResultSet rs=ps.executeQuery();
	    		rs.next();
	    		String db_pwd=rs.getString(1);
	    		rs.close();
	    		if(pwd.equals(db_pwd)) {
	    			bCheck=true;
	    			sql="DELETE FROM project_reply "
	    				+ "WHERE bno=?";
	    			ps=conn.prepareStatement(sql);
	    			ps.setInt(1, no);
	    			ps.executeUpdate(); //참조테이블의 데이터 먼저 삭제하기(FK 쓰고있는 애)
	    			
	    			sql="DELETE FROM project freeboard "
	    				+ "WHERE no=?";
	    			ps=conn.prepareStatement(sql);
	    			ps.setInt(1, no);
	    			ps.executeUpdate();
	    			conn.commit();
	    			}
	    		else {
	    			bCheck=false;
	    		}
	    		
	    	}catch (Exception e) {
				e.printStackTrace();
			}finally {
				disConnection();
			}
	    	return bCheck;
	    }
//=================================================================================
	    //1. 댓글목록
	    public List<ReplyVO> replyListData(int bno, int type){
	    	List<ReplyVO> list=new ArrayList<ReplyVO>();
	    	try {
	    		getConnection();
	    		String sql="SELECT no,bno,id,msg,TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') "
	    				+ "FROM project_reply "
	    				+ "WHERE bno=? AND type=?";
	    		ps=conn.prepareStatement(sql);
	    		ps.setInt(1, bno); //게시글 번호
	    		ps.setInt(2, type); //타입(1:게시판,2:맛집,3:영화,4:관광)
	    		ResultSet rs=ps.executeQuery();
	    		while(rs.next()) {
	    			
	    			
	    		}
	    	}catch (Exception e) {
				e.printStackTrace();
			}finally {
				disConnection();
			}
	    	return list;
	    }
	
}
