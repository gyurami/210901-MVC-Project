package com.sist.dao;

import java.sql.*;
import javax.sql.*;
import java.util.*;
import javax.naming.*;
import com.sist.vo.*;
public class FoodDAO {
	//1. 연결, 등록, 반환
		private Connection conn;
	    private PreparedStatement ps;
	    private static FoodDAO dao;
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
		
	 // 2.싱글턴 패턴 
		  public static FoodDAO newInstance()
		  {
			  if(dao==null) // 미생성시에는 
				  dao=new FoodDAO();
			  return dao; // 이미 만들어진 dao객체를 사용한다 
				  
		  }
	    
	    
	    //3-1. 아이디 중복체크
	    public List<CategoryVO> foodCategoryData()
		  {
			  System.out.println("DAO연결");
			  List<CategoryVO> list=new ArrayList<CategoryVO>();
			  try
			  {
				  getConnection();
				  String sql="SELECT cno,title,subject,poster "
						    +"FROM project_food_category";
				  ps=conn.prepareStatement(sql);
				  ResultSet rs=ps.executeQuery();
				  while(rs.next())
				  {
					  CategoryVO vo=new CategoryVO();
					  vo.setCno(rs.getInt(1));
					  vo.setTitle(rs.getString(2));
					  vo.setSubject(rs.getString(3));
					  vo.setPoster(rs.getString(4));
					  list.add(vo);
				  }
				  rs.close();
			  }catch(Exception ex)
			  {
				  ex.printStackTrace();
			  }
			  finally
			  {
				  disConnection();
			  }
			  return list;
		  }
		  // 카테고리별 목록 읽기
		  public List<FoodVO> foodCategoryListData(int cno)
		  {
			  List<FoodVO> list=new ArrayList<FoodVO>();
			  try
			  {
				  getConnection();
				  String sql="SELECT no,poster,name,tel,address,type,score "
						    +"FROM project_food_house "
						    +"WHERE cno=?";
				  ps=conn.prepareStatement(sql);
				  ps.setInt(1, cno);
				  ResultSet rs=ps.executeQuery();
				  while(rs.next())
				  {
					  FoodVO vo=new FoodVO();
					  vo.setNo(rs.getInt(1));
					  String poster=rs.getString(2);// 이미지 5개를 묶어서 저장 (1개만 가지고 온다)
					  poster=poster.substring(0,poster.indexOf("^"));
					  poster=poster.replace("#","&");
					  vo.setPoster(poster);
					  vo.setName(rs.getString(3));
					  vo.setTel(rs.getString(4));
					  String addr=rs.getString(5);
					  addr=addr.substring(0,addr.lastIndexOf("지"));// 길 /지번
					  vo.setAddress(addr);
					  vo.setType(rs.getString(6));
					  vo.setScore(rs.getDouble(7));
					  list.add(vo);
				  }
				  
				  rs.close();
			  }catch(Exception ex)
			  {
				  ex.printStackTrace();
			  }
			  finally
			  {
				  disConnection();
			  }
			  return list;
		  }
		  // 카테고리제목 읽기 
		  public CategoryVO foodCategoryInfoData(int cno)
		  {
			  CategoryVO vo=new CategoryVO();
			  try
			  {
				  getConnection();
				  String sql="SELECT title,subject FROM project_food_category "
						    +"WHERE cno=?";
				  ps=conn.prepareStatement(sql);
				  ps.setInt(1, cno);
				  ResultSet rs=ps.executeQuery();
				  rs.next();
				  vo.setTitle(rs.getString(1));
				  vo.setSubject(rs.getString(2));
				  rs.close();
			  }catch(Exception ex)
			  {
				  ex.printStackTrace();
			  }
			  finally
			  {
				  disConnection();
			  }
			  return vo;
		  }
		  
		  public FoodVO foodCookieInfoData(int no)
		  {
			  FoodVO vo=new FoodVO();
			  try
			  {
				  getConnection();
				  String sql="SELECT no,name,poster "
						    +"FROM project_food_house "
						    +"WHERE no=?";
				  ps=conn.prepareStatement(sql);
				  ps.setInt(1, no);
				  ResultSet rs=ps.executeQuery();
				  rs.next();
				  vo.setNo(rs.getInt(1));
				  vo.setName(rs.getString(2));
				  String poster=rs.getString(3);
				  poster=poster.substring(0,poster.indexOf("^"));
				  poster=poster.replace("#", "&");
				  vo.setPoster(poster);
				  rs.close();
			  }catch(Exception ex) 
			  {
				  ex.printStackTrace();
			  }
			  finally
			  {
				  disConnection();
			  }
			  return vo;
		  }
}
