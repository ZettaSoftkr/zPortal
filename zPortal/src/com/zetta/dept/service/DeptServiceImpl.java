package com.zetta.dept.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.common.DateTimeUtil;
import com.zetta.dept.dao.DeptDAO;
import com.zetta.dept.model.Dept;
import com.zetta.qlikview.utils.QVXtoCSVconversion;



@Service
@Transactional
public class DeptServiceImpl implements DeptService  {
	
	public Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private DeptDAO deptDao;	

	
	@Override
	public Dept getById(String bi_dept_id){
		
		
		return deptDao.getById(bi_dept_id);
		
	}

	@Override
	public Dept save(Dept dept) {
		
		dept.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		dept.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		Dept result = deptDao.insert(dept);
		//파일 갯수 위한 메소드 
		
		return result;
		
	}
	
	
	

	@Override
	public Dept update(Dept dept) {
		
		
		Dept editDept =   deptDao.getById(dept.getBi_dept_id());
		dept.setBi_perm_yn("Y");
		dept.setBi_sort_sn("0");
		dept.setBi_reg_dt(editDept.getBi_reg_dt());
		dept.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		Dept result = deptDao.update(dept);
		
		return result;
		
	}
	
	@Override
	public int remove(String bi_dept_id) {
		
	
		return deptDao.delete(bi_dept_id);		
	}
	
	

	@Override
	public Dept findList(String bi_dept_id) {
		
		Dept dept = deptDao.findList(bi_dept_id);
		
		return dept;
		
	}

	@Override
	public Dept findWithView(String bi_dept_id) {
		
	
		return findList(bi_dept_id);
		
	}

	

	@Override
	public List findAllList(int startRow, int endRow, int searchTitle, String searchContent) {
		
		return deptDao.findAllList(startRow, endRow, searchTitle, searchContent);
	}
	


	@Override
	public int getTotalCount(int searchTitle, String searchContent) {
		return deptDao.getTotalNo(searchTitle, searchContent);
	}
	
	@Override
	public List<Dept> getDeptList() {
		
		return deptDao.getDeptList();
	}
	
	
	public Dept getDeptCode(String bi_deptnm){
		
		
		Dept result = deptDao.getDeptCode(bi_deptnm);
		//파일 갯수 위한 메소드 
		
		return result;
		
	
	}
	
	
	public int deleteAll(){
		
		return deptDao.deleteAll();
	}
	
	
	@Value("#{qvconf['qlikview.QVDOUT']}")
	private String outputPath;
	
	public String addDeptList(String qvdName) throws IOException{
		
		
		int delNum = deptDao.deleteDept("Y");
		if(delNum == 1){
			int key1 = 0;
			int key2 = 0;
			int key3 = 0;
			int key4 = 0;
			int key5 = 0;
			List<Dept> list = new ArrayList<Dept>();
			List optionValue = new QVXtoCSVconversion().getValues(qvdName, outputPath); 
			int optionSize = optionValue.size() / 2;
			for (int i = 0; i < optionSize; i++) {
	
				key1 = i * 2;
				key2 = (i * 2) + 1;
				
				logger.info("optionValue.get(1):: " + optionValue.get(key1));
				logger.info("optionValue.get(2):: " + optionValue.get(key2));
			
							
				Dept dept = new Dept();		
				
				
				dept.setBi_dept_id(String.valueOf(optionValue.get(key1)));
				dept.setBi_deptnm(String.valueOf(optionValue.get(key2)));
				dept.setBi_perm_yn("Y");
				dept.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				dept.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
	
				list.add(dept);
	
				
			}
			
		   int saveNum = 	deptDao.batchInsert(list);
		   
		   if(saveNum == 1){
			   
			   return "sucess";
			   
		   }else{
			   
			   return "fail";
			  
		   }
		   
		}else{
			
			return "fail";
		}
		
		
	}
	
	public int deleteDept(String bi_perm_yn){
		
		return deptDao.deleteDept(bi_perm_yn);
	}

}
