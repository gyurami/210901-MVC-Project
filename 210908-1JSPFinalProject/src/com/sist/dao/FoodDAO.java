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
	    
	    
	    //
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
		  
		  // 맛집 상세보기
		  public FoodVO foodDetailData(int no) {
			FoodVO vo=new FoodVO();
			try {
				getConnection();
				String sql="SELECT no,poster,name,score,address,tel,type,price,parking,time,menu,good,soso,bad,cno "
						+ "FROM project_food_house "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ResultSet rs=ps.executeQuery();
				rs.next();
				vo.setNo(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setScore(rs.getDouble(4));
				vo.setAddress(rs.getString(5));
				vo.setTel(rs.getString(6));
				vo.setType(rs.getString(7));
				vo.setPrice(rs.getString(8));
				vo.setParking(rs.getString(9));
				vo.setTime(rs.getString(10));
				vo.setMenu(rs.getString(11));
				vo.setGood(rs.getInt(12));
				vo.setSoso(rs.getInt(13));
				vo.setBad(rs.getInt(14));
				vo.setCno(rs.getInt(15));
				rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				disConnection();
			}
			  return vo;
		  }
		  
		  
		  // 여행 명소
		  public List<SeoulLocationVO> foodSeoulLocationData(String addr) {
				List<SeoulLocationVO> list=new ArrayList<SeoulLocationVO>();
				try {
					getConnection();
					String sql="SELECT no,title,poster,rownum"
							+ "FROM (SELECT no,title,poster "
							+ "FROM seoul_location WHERE address LIKE '%'||?||'%' ORDER BY no ASC) "
							+ "WHERE rownum<=5";
					ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						SeoulLocationVO vo=new SeoulLocationVO();
						vo.setNo(rs.getInt(1));
						vo.setTitle(rs.getString(2));
						vo.setPoster(rs.getString(3));
						list.add(vo);
					}rs.close();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					disConnection();
				}
				  return list;
			  }
		  
		  // 여행 호텔
		  public List<SeoulHotelVO> foodSeoulHotelData(String addr) {
				List<SeoulHotelVO> list=new ArrayList<SeoulHotelVO>();
				try {
					getConnection();
					String sql="SELECT no,name,poster,rownum"
							+ "FROM (SELECT no,name,poster "
							+ "FROM seoul_hotel WHERE address LIKE '%'||?||'%' ORDER BY no ASC) "
							+ "WHERE rownum<=5";
					ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						SeoulHotelVO vo=new SeoulHotelVO();
						vo.setNo(rs.getInt(1));
						vo.setName(rs.getString(2));
						vo.setPoster(rs.getString(3));
						list.add(vo);
					}rs.close();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					disConnection();
				}
				  return list;
			  }
		  
		  // 여행 자연
		  public List<SeoulNatureVO> foodSeoulNatureData(String addr) {
				List<SeoulNatureVO> list=new ArrayList<SeoulNatureVO>();
				try {
					getConnection();
					String sql="SELECT no,title,poster,rownum"
							+ "FROM (SELECT no,title,poster "
							+ "FROM seoul_nature WHERE address LIKE '%'||?||'%' ORDER BY no ASC) "
							+ "WHERE rownum<=5";
					ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						SeoulNatureVO vo=new SeoulNatureVO();
						vo.setNo(rs.getInt(1));
						vo.setTitle(rs.getString(2));
						vo.setPoster(rs.getString(3));
						list.add(vo);
					}rs.close();  
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					disConnection();
				}
				  return list;
			  }
		  
}
