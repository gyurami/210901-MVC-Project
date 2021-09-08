package com.sist.dao;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.ZipcodeVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.*;
public class MemberDAO {
	private Connection conn;
    private PreparedStatement ps;
    private static MemberDAO dao;
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
	
    //싱글턴 패턴
    public static MemberDAO newInstance() {
    	if(dao==null)
    		dao=new MemberDAO();
    	return dao;
    }
    
    
    //4-1. 아이디 중복체크
    public int memberidCheck(String id) {
    	int count=0;
    	try {
    		getConnection();
    		String sql="SELECT COUNT(*) FROM project_member "
    				+ "WHERE id=?";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, id);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		count=rs.getInt(1);
    		rs.close();
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
    	return count;
    }
    
    //4-2-1. 우편번호 검색
    public List<ZipcodeVO> postfind(String dong) {
    	List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
    	try {
    		getConnection();
	    	String sql="SELECT zipcode,sido,gugun,dong,NVL(bunji,' ') "
	    			+ "FROM zipcode "
	    			+ "WHERE dong LIKE '%'||?||'%'";
	    	ps=conn.prepareStatement(sql);
	    	ps.setString(1, dong);
	    	ResultSet rs=ps.executeQuery();
	    	while(rs.next()) {
	    		ZipcodeVO vo=new ZipcodeVO();
	    		vo.setZipcode(rs.getString(1));
	    		vo.setSido(rs.getString(2));
	    		vo.setGugun(rs.getString(3));
	    		vo.setDong(rs.getString(4));
	    		vo.setBunji(rs.getString(5));
	    		list.add(vo);
	    	}rs.close();
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
     	return list;
    }
    
    //4-2-2. 우편번호 검색 결과
    public int postfindCount(String dong) {
    	int count=0;
    	try {
    		getConnection();
	    	String sql="SELECT COUNT(*) FROM zipcode "
	    			+ "WHERE dong LIKE '%'||?||'%'";
	    	ps=conn.prepareStatement(sql);
	    	ps.setString(1, dong);
	    	ResultSet rs=ps.executeQuery();
	    	rs.next();
	    	count=rs.getInt(1);
	    	rs.close();
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
     	return count;
    }	
	
    
    //실제 회원가입
    //로그인
    //회원수정
    //회원탈퇴
    //아이디찾기, 비밀번호찾기
}
