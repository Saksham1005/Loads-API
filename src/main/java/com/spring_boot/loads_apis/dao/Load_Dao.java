package com.spring_boot.loads_apis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring_boot.loads_apis.entity.Load;

class UserMapper implements RowMapper {

	@Override
	public Load mapRow(ResultSet rs, int rowNum) throws SQLException {
		Load load=new Load();
		load.setLoadingPoint(rs.getString("loadingpoint"));
		load.setProductType(rs.getString("producttype"));
		load.setTruckType(rs.getString("trucktype"));
		load.setUnloadingPoint(rs.getString("unloadingpoint"));
		load.setNoOfTrucks(rs.getString("nooftrucks"));
		load.setWeight(rs.getString("weight"));
		load.setComment(rs.getString("comment"));
		load.setShipperId(rs.getString("shipperid"));
		load.setDate(rs.getDate("date"));
		
		return load;
	}

}


@Repository
public class Load_Dao{
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	public void createTable() {
		var query1="CREATE TABLE LOADS("
				+ "loadId BIGSERIAL NOT NULL PRIMARY KEY,"
				+ "loadingPoint VARCHAR(50) NOT NULL,"
				+ "unloadingPoint VARCHAR(50) NOT NULL,"
				+ "productType VARCHAR(50) NOT NULL,"
				+ "truckType VARCHAR(50) NOT NULL,"
				+ "noOfTrucks VARCHAR(50) NOT NULL,"
				+ "weight VARCHAR(50) NOT NULL,"
				+ "comment VARCHAR(50),"
				+ "shipperId VARCHAR(100),"
				+ "date DATE )";
		var query2="CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\"";
		
		this.jdbcTemplate.update(query1);
		this.jdbcTemplate.update(query2);
	}
	
	public String addLoad(Load load) {
		System.out.println(load.getDate());
		try {
		var query="INSERT INTO loads(loadingpoint,unloadingpoint,"
				+ "producttype,trucktype,nooftrucks,"
				+ "weight,comment,shipperid,"
				+ "date) VALUES(?,?,?,?,?,?,?,?,?)";
				
		
		this.jdbcTemplate.update(query,load.getLoadingPoint(),load.getUnloadingPoint(),load.getProductType(),
				load.getTruckType(),load.getNoOfTrucks(),load.getWeight(),load.getComment(),load.getShipperId(),
				load.getDate());
		return "Loads details added successfully";
		}
		catch(Exception e) {
			return "Proper details of the load were not given";
		}
	}
	
	public List<?> getLoads(String shipperId){
//		System.out.println(shipperId);
		try {
			
		String query="Select * from loads where shipperid="+shipperId;
		List<?> result=jdbcTemplate.query(query, new UserMapper());
		System.out.println(result+" observed");
		
		return result;
		}
		catch(Exception e) {
			List<String>l;
			l=null;
			return l;
		}
	}
	
	public List<?> getLoad(String loadId) {
		String query="Select * from loads where loadid="+loadId;
		List<?> result=jdbcTemplate.query(query, new UserMapper());
		return result;
	}
	
	public void updateLoad(String loadId,Load load) {
		String query="update loads set loadingpoint=?,unloadingpoint=?,producttype=?,"
				+ "trucktype=?,nooftrucks=?,weight=?,comment=?,date=? where loadid=?";
		
	
		this.jdbcTemplate.update(query,load.getLoadingPoint(),load.getUnloadingPoint(),
				load.getProductType(),load.getTruckType(),load.getNoOfTrucks(),
				load.getWeight(),load.getComment(),load.getDate(),Long.parseLong(loadId));
	}
	
	public void deleteLoad(String loadId) {
		String query="Delete from loads where loadid=?";
		this.jdbcTemplate.update(query,Long.parseLong(loadId));
	}
}
