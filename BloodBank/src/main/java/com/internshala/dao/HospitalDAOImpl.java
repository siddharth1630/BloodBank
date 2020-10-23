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
import com.internshala.bo.HospitalBO;
import com.internshala.bo.ReciverBO;
import com.internshala.entity.AvailableBloodSample;
import com.internshala.entity.BloodRequest;
import com.internshala.entity.HospitalRegistration;

@Repository
public class HospitalDAOImpl implements HospitalDAO {
	private static final String INSERT_HOSPITAL="INSERT INTO HOSPITALREGISTRATION(HOSPNAME,HOSPCITY,HOSPADDRS,HOSPMOBNO,HOSPREGID,HOSPPASS,HOSPCPASS) VALUES(?,?,?,?,?,?,?)";
	private static final String FETCH_HOSP_BYREGID="SELECT HOSPID,HOSPNAME,HOSPCITY,HOSPADDRS,HOSPMOBNO,HOSPREGID,HOSPPASS,HOSPCPASS FROM HOSPITALREGISTRATION WHERE HOSPREGID=?";
	private static final String INSERT_BLOOD="INSERT INTO AVAILABLEBLOODSAMPLE(HOSPREGID,BLOODGRPNAME,QTY) VALUES(?,?,?)";
	private static final String UPDATE_BLOOD="UPDATE AVAILABLEBLOODSAMPLE SET QTY=QTY+? WHERE HOSPREGID=? AND BLOODGRPNAME=?";
	private static final String Get_ALL_BLODD_REQ="SELECT HOSPREGID,QTY,BLOODGRPNAME,RECVEMAIL FROM BLOODREQUEST WHERE HOSPREGID=? ORDER BY QTY";
	private static final String CHECK_REGID="SELECT HOSPID FROM HOSPITALREGISTRATION WHERE HOSPREGID=?";
	private static final String FETCH_ALL_HOSP="HOSPID,HOSPNAME,HOSPCITY,HOSPADDRS,HOSPMOBNO,HOSPREGID FROM HOSPITALREGISTRATION";
	@Autowired
	private JdbcTemplate jt;

	@Override
	public int insertHospital(HospitalBO bo) {
		return jt.update(INSERT_HOSPITAL, bo.getHospName(),bo.getHospCity(),bo.getHospAddrs(),bo.getHospMobNO(),bo.getHospRegId(),bo.getHospPass(),bo.getHospCPass());
	}
	
	@Override
	public HospitalBO gethospitalDetails(String hospRegId) {
		return jt.queryForObject(FETCH_HOSP_BYREGID, new BeanPropertyRowMapper<HospitalBO>(HospitalBO.class),hospRegId);
	}
	
	@Override
	public int insertBlood(AvailableBloodSampleBO bo) {
		return jt.update(INSERT_BLOOD,bo.getHospRegId(), bo.getBloodGrpName(),bo.getQty());
	}
	
	@Override
	public int updateBlood(AvailableBloodSampleBO bo) {
		return jt.update(UPDATE_BLOOD, bo.getQty(),bo.getHospRegId(),bo.getBloodGrpName());
	}
	
	@Override
	public List<BloodRequestBO> getListOfBloodReq(String hospRegId) {
		List<BloodRequestBO> bo=null;
		bo=jt.query(Get_ALL_BLODD_REQ,new ListBloodReqRowMapper(),hospRegId); 
		return bo;
	}
	private class ListBloodReqRowMapper implements ResultSetExtractor<List<BloodRequestBO>>{
		@Override
		public List<BloodRequestBO> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<BloodRequestBO> listBo=null;
			listBo=new ArrayList<BloodRequestBO>();
			
			while(rs.next()) {
				BloodRequestBO bo=new BloodRequestBO();
				bo.setHospRegId(rs.getString(1));
				bo.setQty(rs.getInt(2));
				bo.setBloodGrpName(rs.getString(3));
				bo.setRecvEmail(rs.getString(4));
				listBo.add(bo);
			}
			return listBo;
		}	
	}
	
	@Override
	public boolean existsByRegId(String regId, Class<HospitalRegistration> class1) {
		try {
			HospitalBO bo=jt.queryForObject(CHECK_REGID, new BeanPropertyRowMapper<HospitalBO>(HospitalBO.class),regId);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	//  currently not in use
	@Override
	public List<HospitalBO> fetchAllHospitals() {
		List<HospitalBO> bo=null;
		bo=jt.query(FETCH_ALL_HOSP,new ListHosptalRowMapper()); 
		return bo;
	}
	private class ListHosptalRowMapper implements ResultSetExtractor<List<HospitalBO>>{
		@Override
		public List<HospitalBO> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<HospitalBO> listBo=null;
			listBo=new ArrayList<HospitalBO>();
			
			while(rs.next()) {
				HospitalBO bo=new HospitalBO();
				bo.setHospId(rs.getInt(1));
				bo.setHospName(rs.getString(2));
				bo.setHospCity(rs.getString(3));
				bo.setHospAddrs(rs.getString(4));
				bo.setHospMobNO(rs.getLong(5));
				bo.setHospRegId(rs.getString(2));		
				
				listBo.add(bo);
			}
			return listBo;
		}	
	}
}
