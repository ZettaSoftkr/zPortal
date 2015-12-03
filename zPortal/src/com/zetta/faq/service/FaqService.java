package com.zetta.faq.service;

import java.util.List;

import com.zetta.faq.model.Faq;
import com.zetta.notice.model.Notice;



public interface FaqService {	
	
	
	   public Faq getById(int bi_faq_sn) ;

	   public Faq save(Faq faq) ;
	    
	   public Faq update(Faq faq);

	   public int remove(int bi_faq_sn);

	   public Faq findList(int bi_faq_sn);
	    
	   public Faq findWithView(int bi_faq_sn);
	   
	   public List<Faq> getFaqList(int searchTitle, String searchContent);
	    
	   public List<Faq> findAllList(int currentPage, int countPerPage,int searchTitle, String searchContent);

	   public int getTotalCount(int searchTitle, String searchContent);	   
	   
	   public Faq getPriView(int bi_faq_sn);
	    
	   public Faq getNextView(int bi_faq_sn);
	}
