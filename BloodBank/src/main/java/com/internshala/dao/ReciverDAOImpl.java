package com.internshala.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.internshala.bo.AvailableBloodSampleBO;
import com.internshala.bo.BloodRequestBO;
import com.internshala.bo.ReciverBO;
import com.internshala.entity.ReciverRegistration;


@Repository
public class ReciverDAOImpl implements ReciverDAO {
	private static final String INSERT_RECIVER="INSERT INTO RECIVERREGISTRATION(RECVNAME,RECVCITY,RECVADDRS,RECVMOBNO,BLOODGRPNAME,RECVPASS,RECVCPASS,RECVEMAIL) VALUES(?,?,?,?,?,?,?,?)";
	private static final String FETCH_RECV_BYEMAIL="SELECT RECVID,RECVNAME,RECVCITY,RECVADDRS,RECVMOBNO,BLOODGRPNAME,RECVPASS,RECVCPASS,RECVEMAIL FROM RECIVERREGISTRATION WHERE RECVEMAIL=?";
	private static final String Get_ALL_BLODD_SAMPLES="SELECT BLOODGRPNAME,HOSPREGID,QTY FROM AVAILABLEBLOODSAMPLE ORDER BY QTY";
	private static final String INSERT_BLOOD_REQ="INSERT INTO BLOODREQUEST(HOSPREGID,RECVEMAIL,QTY,BLOODGRPNAME) VALUES(?,?,?,?)";
	private static final String UPDATE_BLOOD_QTY="UPDATE AVAILABLEBLOODSAMPLE SET QTY=QTY-? WHERE HOSPREGID=? AND BLOODGRPNAME=?";
	private static final String DELETE_BLOOD_ROW="DELETE FROM AVAILABLEBLOODSAMPLE WHERE HOSPREGID=? AND BLOODGRPNAME=?";
	private static final String CHECK_EMAIL="SELECCT RECVID FROM RECIVERREGISTRATION WHERE RECVEMAIL=?";
	
	@Autowired
	private JdbcTemplate jt;
	


	@Override
	public int insertReciver(ReciverBO bo) {
		int count=0;   
		count=jt.update(INSERT_RECIVER, bo.getRecvName(),bo.getRecvCity(),bo.getRecvAddrs(),bo.getRecvMobNO(),bo.getBloodGrpName(),bo.getRecvPass(),bo.getRecvCPass(),bo.getRecvEmail());
		return count;
	}
	
	@Override
	public ReciverBO getReciverDetails(String recvEmail) {
		return jt.queryForObject(FETCH_RECV_BYEMAIL, new BeanPropertyRowMapper<ReciverBO>(ReciverBO.class),recvEmail);
	}
	
	@Override
	public List<AvailableBloodSampleBO> getAllBloodSamples() {
		List<AvailableBloodSampleBO> bo=null;
		bo=jt.query(Get_ALL_BLODD_SAMPLES,new ListBloodSamplesRowMapper()); 
		return bo;
	}
	private class ListBloodSamplesRowMapper implements ResultSetExtractor<List<AvailableBloodSampleBO>>{
		@Override
		public List<AvailableBloodSampleBO> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<AvailableBloodSampleBO> listBo=null;
			listBo=new ArrayList<AvailableBloodSampleBO>();
			
			while(rs.next()) {
				AvailableBloodSampleBO bo=new AvailableBloodSampleBO();
				bo.setBloodGrpName(rs.getString(1));
				bo.setHospRegId(rs.getString(2));
				bo.setQty(rs.getInt(3));
				listBo.add(bo);
			}
			return listBo;
		}	
	}
	
	@Override
	public int insertBloodReq(BloodRequestBO bo,boolean flag) {
		int count=0;
		count= jt.update(INSERT_BLOOD_REQ,bo.getHospRegId(),bo.getRecvEmail(),bo.getQty(),bo.getBloodGrpName() );
		if(flag==false)
			jt.update(UPDATE_BLOOD_QTY, bo.getQty(),bo.getHospRegId(),bo.getBloodGrpName());
		else
			jt.update(DELETE_BLOOD_ROW, bo.getHospRegId(),bo.getBloodGrpName());
		return count;
	}
	
	@Override
	public boolean existsByEmail(String email, Class<ReciverRegistration> class1) {
		try {
			ReciverBO bo=jt.queryForObject(FETCH_RECV_BYEMAIL, new BeanPropertyRowMapper<ReciverBO>(ReciverBO.class),email);
			return true;
		}
		catch(Exception e){
			return false;
		}
		
		

	}

}
