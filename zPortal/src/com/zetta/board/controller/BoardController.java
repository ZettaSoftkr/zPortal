package com.zetta.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.board.model.Board;
import com.zetta.board.service.BoardService;
import com.zetta.common.BoardUtil;
import com.zetta.common.DateTimeUtil;
import com.zetta.fileUpload.model.FileUpload;
import com.zetta.fileUpload.service.FileUploadService;

@Controller
public class BoardController {

	public Logger logger = Logger.getLogger(getClass());

	// private static final String DEFAULT_CURRENT_PAGE = "1";
	// private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	BoardService boardService;
	@Autowired
	FileUploadService fileUploadService;

	@RequestMapping(value = "/board/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			  @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			, @RequestParam(value = "keyVal", required = false, defaultValue = "") String keyVal
			, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
			, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
			, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
			, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
			, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		


		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "board/" + pageNm;

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_bbs_sn", keyVal);
		mav.addObject("keyVal", keyVal);
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);
		return mav;

	}
	@RequestMapping(value = "/admin/board/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView adminGotoPage(
			      @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
				, @RequestParam(value = "keyVal", required = false, defaultValue = "") String keyVal
				, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
				, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
				, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
				, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
				, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
				, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
				, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
				, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			    , HttpServletRequest request
			    , HttpServletResponse response) throws Exception {

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "admin/board/" + pageNm;

		}
		
		logger.info("keyVal::" + keyVal);

		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_bbs_sn", keyVal);
		mav.addObject("keyVal", keyVal);
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);
		return mav;

	}
	@RequestMapping(value = "/mypage/board/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoMyPage(  
			  @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
				, @RequestParam(value = "keyVal", required = false, defaultValue = "") String keyVal				
				, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
				, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
				, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
				, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
				, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
				, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
				, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
				, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId	
			    , HttpServletRequest request
			    , HttpServletResponse response) throws Exception {

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "mypage/board/" + pageNm;

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_bbs_sn", keyVal);
		mav.addObject("keyVal", keyVal);
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);
		return mav;

	}

	@RequestMapping(value = "/board/getListData.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView list(
			  @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage 
			, @RequestParam(value = "pageCount", required = false, defaultValue = "10") int countPerPage 
			, @RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "0") int bi_bbs_sn
			, @RequestParam(value = "sord", required = false, defaultValue = "") String sord
			, @RequestParam(value = "searchTitle", required = false, defaultValue = "0") int searchTitle
			, @RequestParam(value = "searchContent", required = false, defaultValue = "") String searchContent
			, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("boardList.do Start");

		int totalNo = boardService.getTotalCount(searchTitle, searchContent);

		int endPage = 0;
		int startRow = 0;
		int endRow = 0;

		if (totalNo > 0) {

			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo); // 마지막
																				// 페이지

			startRow = BoardUtil.startRow(currentPage, countPerPage); // 시작 row

			endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage); // 끝
																			// row

		}

		ModelAndView mav = new ModelAndView();

		// mav.addObject("pageDivideForm", pageDivideForm);
		mav.addObject("currentPage", currentPage);
		mav.addObject("totalPage", endPage);
		mav.addObject("totalNo", totalNo);

		mav.addObject("rows", boardService.findAllList(startRow, endRow, searchTitle, searchContent));
		logger.info("boardList:: ");
		logger.info("boardList.do end");
		mav.setViewName("jsonView");

		return mav;

	}

	@RequestMapping(value = "/mypage/board/getListData.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView mypageList(
			  @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage // 현재 페이지
			, @RequestParam(value = "pageCount", required = false, defaultValue = "10") int countPerPage // 페이지당데이터
			, @RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "0") int bi_bbs_sn
			, @RequestParam(value = "sord", required = false, defaultValue = "") String sord
			, @RequestParam(value = "searchTitle", required = false, defaultValue = "0") int searchTitle
			, @RequestParam(value = "searchContent", required = false, defaultValue = "") String searchContent
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("mypage.do Start");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int totalNo = boardService.getMyPageTotalNo(searchTitle, searchContent, auth.getName());
		int endPage = 0;
		int startRow = 0;
		int endRow = 0;

		if (totalNo > 0) {

			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo); // 마지막페이지
			startRow = BoardUtil.startRow(currentPage, countPerPage); // 시작 row
			endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage); // 끝 row
		}

		ModelAndView mav = new ModelAndView();

		// mav.addObject("pageDivideForm", pageDivideForm);
		mav.addObject("currentPage", currentPage);
		mav.addObject("totalPage", endPage);
		mav.addObject("totalNo", totalNo);

		mav.addObject("rows", boardService.findMypageList(startRow, endRow, searchTitle, searchContent, auth.getName()));
		logger.info("boardList:: ");
		logger.info("boardList.do end");
		mav.setViewName("jsonView");

		return mav;

	}

	@RequestMapping(value = "/mypage/board/getViewData.do")
	// , method = RequestMethod.POST
	public ModelAndView mypageView(@RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "") int bi_bbs_sn, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("bi_bbs_sn::" + bi_bbs_sn);

		Board boardPrev = boardService.getPreView(bi_bbs_sn);
		Board boardNext = boardService.getNextView(bi_bbs_sn);

		ModelAndView mav = new ModelAndView();

		if (boardPrev == null) {

			mav.addObject("prevTitle", "이전글");
			mav.addObject("prevDate", "");

		} else {

			String preTitle = "<a href=\"#\" onclick=\"getViewPage('bi_bbs_sn','" + boardPrev.getBi_bbs_sn() + "');\">" + boardPrev.getBi_bbs_sn() + "</a>";

			mav.addObject("prevTitle", preTitle);
			mav.addObject("prevDate", boardPrev.getParseModifyDate());

		}

		if (boardNext == null) {

			mav.addObject("nextTitle", "다음글");
			mav.addObject("nextDate", "");

		} else {

			String nextTitle = "<a href=\"#\" onclick=\"getViewPage('bi_bbs_sn','" + boardNext.getBi_bbs_sn() + "');\">" + boardNext.getBi_bbs_sn() + "</a>";
			mav.addObject("nextTitle", nextTitle);
			mav.addObject("nextDate", boardNext.getParseModifyDate());

		}

		mav.addObject("rows", boardService.findList(bi_bbs_sn));
		mav.setViewName("jsonView");

		return mav;
	}

	@RequestMapping(value = "/board/getViewData.do")
	// , method = RequestMethod.POST
	public ModelAndView view(@RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "") int bi_bbs_sn, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("bi_bbs_sn::" + bi_bbs_sn);

		Board boardPrev = boardService.getPreView(bi_bbs_sn);
		Board boardNext = boardService.getNextView(bi_bbs_sn);

		ModelAndView mav = new ModelAndView();

		if (boardPrev == null) {

			mav.addObject("prevTitle", "이전글");
			mav.addObject("prevDate", "");

		} else {

			String preTitle = "<a href=\"#\" onclick=\"getViewPage('bi_bbs_sn','" + boardPrev.getBi_bbs_sn() + "');\">" + boardPrev.getBi_titl() + "</a>";

			mav.addObject("prevTitle", preTitle);
			mav.addObject("prevDate", boardPrev.getParseModifyDate());

		}

		if (boardNext == null) {

			mav.addObject("nextTitle", "다음글");
			mav.addObject("nextDate", "");

		} else {

			String nextTitle = "<a href=\"#\" onclick=\"getViewPage('bi_bbs_sn','" + boardNext.getBi_bbs_sn() + "');\">" + boardNext.getBi_titl() + "</a>";
			mav.addObject("nextTitle", nextTitle);
			mav.addObject("nextDate", boardNext.getParseModifyDate());

		}

		mav.addObject("rows", boardService.findList(bi_bbs_sn));
		mav.setViewName("jsonView");

		return mav;
	}

	@RequestMapping(value = "/mypage/board/save.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView myPageSave(
			  @ModelAttribute("fileUpload") FileUpload fileUpload
			, @RequestParam("files") MultipartFile[] files
			, @ModelAttribute("board") Board board
			, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
			, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
			, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
			, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
			, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId	
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		board.setBi_unity_cust_id(auth.getName()); // 아이디 등록
		board.setBi_inqire_num(0);
		boardService.save(board);
		ModelAndView mav = new ModelAndView();

		Board getBoard = boardService.findListDesc();
		
	
		


		logger.info("파일갯수::" + files.length);

		for (int i = 0; i < files.length; i++) {

			if (files[i].getSize() > 0) {

				String fileRoot = request.getRealPath(boardFileRoot);
				fileUpload = fileUploadService.uploadMultipleFileHandler(fileUpload, fileRoot, files[i]);
				fileUpload.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_sn(getBoard.getBi_bbs_sn());
				fileUpload.setBi_svc_nm("board");
				fileUpload.setBi_file_num(i);
				fileUpload.setBi_atch_flpth_nm(boardFileRoot); // 파일경로
				
				logger.info("regdt: "+ DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				logger.info("updt: "+ DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				logger.info("sn: "+ getBoard.getBi_bbs_sn());
				logger.info("svcNm: "+ "board");
				logger.info("fileNum: "+ i);
				logger.info("boardFileRoot: "+ boardFileRoot);
				
				logger.info("fileUpload::" + fileUpload);
				
				fileUploadService.save(fileUpload);
			}

		}

	
		mav.addObject("pageNm", "list");
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName("redirect:/mypage/board/gotoPage.do");
		return mav;


	}

	@Value("#{qvconf['board.file']}")
	private String boardFileRoot;

	@RequestMapping(value = "/board/save.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView save(
			  @ModelAttribute("fileUpload") FileUpload fileUpload
			, @RequestParam("files") MultipartFile[] files
			, @ModelAttribute("board") Board board
			, @RequestParam(value = "keyVal", required = false, defaultValue = "") String keyVal
			, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
			, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
			, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
			, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
			, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
		
		logger.info("keyVal: "+ keyVal);
		logger.info("lev1_parent_id: "+ lev1_parent_id);
		logger.info("lev2_parent_id: "+ lev2_parent_id);
		logger.info("lev3_parent_id: "+ lev3_parent_id);
		logger.info("lev1_menuOpenId: "+ lev1_menuOpenId);
		logger.info("lev2_menuOpenId: "+ lev2_menuOpenId);
		logger.info("lev1_menuOpen: "+ lev1_menuOpen);
		logger.info("lev2_menuOpen: "+ lev2_menuOpen);
		logger.info("currentId: "+ currentId);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		board.setBi_unity_cust_id(auth.getName()); // 아이디 등록
		board.setBi_inqire_num(0);
		boardService.save(board);
		ModelAndView mav = new ModelAndView();

		Board getBoard = boardService.findListDesc();
		logger.info("파일갯수::" + files.length);
		for (int i = 0; i < files.length; i++) {

			if (files[i].getSize() > 0) {

				String fileRoot = request.getRealPath(boardFileRoot);
				fileUploadService.uploadMultipleFileHandler(fileUpload, fileRoot, files[i]);

				fileUpload.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_sn(getBoard.getBi_bbs_sn());
				fileUpload.setBi_svc_nm("board");
				fileUpload.setBi_file_num(i);
				fileUpload.setBi_atch_flpth_nm(boardFileRoot); // 파일경로
				
				logger.info("regdt: "+ DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				logger.info("updt: "+ DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				logger.info("sn: "+ getBoard.getBi_bbs_sn());
				logger.info("svcNm: "+ "board");
				logger.info("fileNum: "+ i);
				logger.info("boardFileRoot: "+ boardFileRoot);
				logger.info("fileUpload::" + fileUpload);

				fileUploadService.save(fileUpload);
			}

		}

	
		mav.addObject("pageNm", "list");
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName("redirect:/board/gotoPage.do");
		return mav;

	}

	@RequestMapping(value = "/board/modify.do", method = RequestMethod.POST)
	public ModelAndView modify(
			  @ModelAttribute("fileUpload") FileUpload fileUpload
			, @RequestParam("files") MultipartFile[] files
			, @ModelAttribute("board") Board board
			, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
			, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
			, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
			, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
			, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId	
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {


		boardService.update(board);
		ModelAndView mav = new ModelAndView();
		
		
		for (int i = 0; i < files.length; i++) {

			if (files[i].getSize() > 0) {
				
				
				
				/*	
				FileUpload getfile = fileUploadService.fileBoard(board.getBi_bbs_sn(), "board", i);
			    	
			  	 String fullPath = "";
			  	  
			  	  logger.info("파일경로:" + fileUpload.getBi_atch_flpth_nm());
			  	  logger.info("파일명:" + fileUpload.getBi_tmpr_atch_file_nm());
			  	  
			  	
			  	if(getfile.getBi_atch_flpth_nm().equals("/file/indicator/") || getfile.getBi_atch_flpth_nm().equals("/file/board")){
			  		
			  		Path pathfile = Paths.get(request.getRealPath(getfile.getBi_atch_flpth_nm()), getfile.getBi_tmpr_atch_file_nm());
			  		try {
			  			
							Files.delete(pathfile);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			  		
			  	}else{
			  		
			  		
			  		Path pathfile = Paths.get(getfile.getBi_atch_flpth_nm(), getfile.getBi_tmpr_atch_file_nm());
			  		try {
			  			
							Files.delete(pathfile);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			  		
			  		
			  	}
			  	 
			  	int delNum = fileUploadService.remove(getfile.getBi_atch_file_sn());
					
				*/
				
				//FileUpload getfile = fileUploadService.fileBoard(board.getBi_bbs_sn(), "board", i);

				String fileRoot = request.getRealPath(boardFileRoot);
				fileUploadService.uploadMultipleFileHandler(fileUpload, fileRoot, files[i]);

				fileUpload.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_sn(board.getBi_bbs_sn());
				fileUpload.setBi_svc_nm("board");
				fileUpload.setBi_file_num(i);
				fileUpload.setBi_atch_flpth_nm(boardFileRoot); // 파일경로

				fileUploadService.save(fileUpload);
				
				
			}

		}
		
		
		// mav.addObject("rows", "수정 되었습니다.");
		// mav.setViewName("jsonView");
		mav.addObject("pageNm", "list");
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName("redirect:/board/gotoPage.do");
		return mav;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/mypage/board/modify.do", method = RequestMethod.POST)
	public ModelAndView myPageModify(
			  @ModelAttribute("fileUpload") FileUpload fileUpload
			, @RequestParam("files") MultipartFile[] files
			, @ModelAttribute("board") Board board
			, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
			, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
			, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
			, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
			, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId	
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("여기");
		
		
		
		

		boardService.update(board);
		ModelAndView mav = new ModelAndView();
		
		
		
		
		for (int i = 0; i < files.length; i++) {

			if (files[i].getSize() > 0) {
				
				
				
				/*	
				FileUpload getfile = fileUploadService.fileBoard(board.getBi_bbs_sn(), "board", i);
			    	
			  	 String fullPath = "";
			  	  
			  	  logger.info("파일경로:" + fileUpload.getBi_atch_flpth_nm());
			  	  logger.info("파일명:" + fileUpload.getBi_tmpr_atch_file_nm());
			  	  
			  	
			  	if(getfile.getBi_atch_flpth_nm().equals("/file/indicator/") || getfile.getBi_atch_flpth_nm().equals("/file/board")){
			  		
			  		Path pathfile = Paths.get(request.getRealPath(getfile.getBi_atch_flpth_nm()), getfile.getBi_tmpr_atch_file_nm());
			  		try {
			  			
							Files.delete(pathfile);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			  		
			  	}else{
			  		
			  		
			  		Path pathfile = Paths.get(getfile.getBi_atch_flpth_nm(), getfile.getBi_tmpr_atch_file_nm());
			  		try {
			  			
							Files.delete(pathfile);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			  		
			  		
			  	}
			  	 
			  	int delNum = fileUploadService.remove(getfile.getBi_atch_file_sn());
					
				*/
				
				//FileUpload getfile = fileUploadService.fileBoard(board.getBi_bbs_sn(), "board", i);

				String fileRoot = request.getRealPath(boardFileRoot);
				fileUploadService.uploadMultipleFileHandler(fileUpload, fileRoot, files[i]);

				fileUpload.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_sn(board.getBi_bbs_sn());
				fileUpload.setBi_svc_nm("board");
				fileUpload.setBi_file_num(i);
				fileUpload.setBi_atch_flpth_nm(boardFileRoot); // 파일경로

				fileUploadService.save(fileUpload);
				
				
			}

		}
		
		
		// mav.addObject("rows", "수정 되었습니다.");
		// mav.setViewName("jsonView");
		mav.addObject("pageNm", "list");
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName("redirect:/mypage/board/gotoPage.do");
		return mav;
	}

	
	



	@RequestMapping(value = "/board/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView deleteForm(@RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "") int bi_bbs_sn, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.addObject("bi_bbs_sn", boardService.getById(bi_bbs_sn));
		mav.setViewName("/board/delete");

		return mav;

	}

	@RequestMapping(value = "/board/delete.do", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "") int bi_bbs_sn, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		
			
		 List<FileUpload> li = fileUploadService.fileBoardList(bi_bbs_sn, "board");
	    	
	  
		for(int i=0;i<li.size();i++){
			
			
			Path pathfile = Paths.get(request.getRealPath(li.get(i).getBi_atch_flpth_nm()), li.get(i).getBi_tmpr_atch_file_nm());
	  		try {
	  			
					Files.delete(pathfile);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 
	  	 
	  	  int delNum = fileUploadService.remove(li.get(i).getBi_atch_file_sn());
			
			
		}
	  	
		
		boardService.remove(bi_bbs_sn);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", "삭제 되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	@RequestMapping(value = "/mypage/board/delete.do", method = RequestMethod.POST)
	public ModelAndView myPageDelete(@RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "") int bi_bbs_sn, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		 List<FileUpload> li = fileUploadService.fileBoardList(bi_bbs_sn, "board");
	    	
		  
			for(int i=0;i<li.size();i++){
				
				
				Path pathfile = Paths.get(request.getRealPath(li.get(i).getBi_atch_flpth_nm()), li.get(i).getBi_tmpr_atch_file_nm());
		  		try {
		  			
						Files.delete(pathfile);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 
		  	 
		  	  int delNum = fileUploadService.remove(li.get(i).getBi_atch_file_sn());
				
				
			}
		boardService.remove(bi_bbs_sn);
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", "삭제 되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	@RequestMapping(value = "/board/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(@RequestParam(value = "checkId", required = false, defaultValue = "") String[] checkId

	, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Board board = new Board();

		for (int i = 0; i < checkId.length; i++) {
			
			
			 List<FileUpload> li = fileUploadService.fileBoardList(Integer.valueOf(checkId[i]), "board");
		    	
			  
				for(int j=0;j<li.size();j++){
					
					
					Path pathfile = Paths.get(request.getRealPath(li.get(j).getBi_atch_flpth_nm()), li.get(j).getBi_tmpr_atch_file_nm());
			  		try {
			  			
							Files.delete(pathfile);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			 
			  	 
			  	  int delNum = fileUploadService.remove(li.get(j).getBi_atch_file_sn());
					
					
				}
			

			board.setBi_bbs_sn(Integer.valueOf(checkId[i]));
			boardService.remove(board.getBi_bbs_sn());

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", "삭제 되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	@RequestMapping(value = "mypage/board/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteMypageAll(@RequestParam(value = "checkId", required = false, defaultValue = "") String[] checkId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Board board = new Board();

		for (int i = 0; i < checkId.length; i++) {

			logger.info("삭제값:" + Integer.valueOf(checkId[i]));
			
			 List<FileUpload> li = fileUploadService.fileBoardList(Integer.valueOf(checkId[i]), "board");
		    	
			  
				for(int j=0;j<li.size();j++){
					
					
					Path pathfile = Paths.get(request.getRealPath(li.get(j).getBi_atch_flpth_nm()), li.get(j).getBi_tmpr_atch_file_nm());
			  		try {
			  			
							Files.delete(pathfile);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			 
			  	 
			  	  int delNum = fileUploadService.remove(li.get(j).getBi_atch_file_sn());
					
					
				}
			

			board.setBi_bbs_sn(Integer.valueOf(checkId[i]));
			boardService.remove(board.getBi_bbs_sn());

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", "삭제 되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	@RequestMapping(value = "/board/getMainData.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView mainList(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage // 현재
																										// 페이지
			,
			@RequestParam(value = "pageCount", required = false, defaultValue = "4") int countPerPage // 페이지당
																										// 뿌릴
																										// 데이터
			, @RequestParam(value = "searchTitle", required = false, defaultValue = "0") int searchTitle,
			@RequestParam(value = "searchContent", required = false, defaultValue = "") String searchContent, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("boardList.do Start");

		int totalNo = boardService.getTotalCount(searchTitle, searchContent);
		// String pageDivideForm = BoardUtil.dividePageForm(currentPage,
		// countPerPage, totalNo , request, searchTitle,
		// searchContent,"boardList.do");

		int endPage = 0;
		int startRow = 0;
		int endRow = 0;

		if (totalNo > 0) {

			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo); // 마지막
																				// 페이지

			startRow = BoardUtil.startRow(currentPage, countPerPage); // 시작 row

			endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage); // 끝
																			// row

		}

		ModelAndView mav = new ModelAndView();

		// mav.addObject("pageDivideForm", pageDivideForm);
		mav.addObject("currentPage", currentPage);
		mav.addObject("totalPage", endPage);
		mav.addObject("totalNo", totalNo);

		mav.addObject("rows", boardService.findAllList(startRow, endRow, searchTitle, searchContent));
		logger.info("boardList:: ");
		logger.info("boardList.do end");
		mav.setViewName("jsonView");

		return mav;

	}

	

	@RequestMapping(value = "/admin/board/getListData.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView adminList(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage // 현재
																										// 페이지
			,
			@RequestParam(value = "pageCount", required = false, defaultValue = "10") int countPerPage // 페이지당
																										// 뿌릴
																										// 데이터
			, @RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "0") int bi_bbs_sn, @RequestParam(value = "sord", required = false, defaultValue = "") String sord,
			@RequestParam(value = "searchTitle", required = false, defaultValue = "0") int searchTitle,
			@RequestParam(value = "searchContent", required = false, defaultValue = "") String searchContent, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("boardList.do Start");

		int totalNo = boardService.getTotalCount(searchTitle, searchContent);

		int endPage = 0;
		int startRow = 0;
		int endRow = 0;

		if (totalNo > 0) {

			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo); // 마지막
																				// 페이지

			startRow = BoardUtil.startRow(currentPage, countPerPage); // 시작 row

			endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage); // 끝
																			// row

		}

		ModelAndView mav = new ModelAndView();

		// mav.addObject("pageDivideForm", pageDivideForm);
		mav.addObject("currentPage", currentPage);
		mav.addObject("totalPage", endPage);
		mav.addObject("totalNo", totalNo);

		mav.addObject("rows", boardService.findAllList(startRow, endRow, searchTitle, searchContent));
		logger.info("boardList:: ");
		logger.info("boardList.do end");
		mav.setViewName("jsonView");

		return mav;

	}

	@RequestMapping(value = "/admin/board/getViewData.do" , method = RequestMethod.POST)
	public ModelAndView adminView(
			  @RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "") int bi_bbs_sn
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("bi_bbs_sn::" + bi_bbs_sn);

		Board boardPrev = boardService.getPreView(bi_bbs_sn);
		Board boardNext = boardService.getNextView(bi_bbs_sn);

		ModelAndView mav = new ModelAndView();

		if (boardPrev == null) {

			mav.addObject("prevTitle", "이전글");
			mav.addObject("prevDate", "");

		} else {

			String preTitle = "<a href=\"#\" onclick=\"getAdminViewPage('" + boardPrev.getBi_bbs_sn() + "');\">" + boardPrev.getBi_titl() + "</a>";

			mav.addObject("prevTitle", preTitle);
			mav.addObject("prevDate", boardPrev.getParseModifyDate());

		}

		if (boardNext == null) {

			mav.addObject("nextTitle", "다음글");
			mav.addObject("nextDate", "");

		} else {

			String nextTitle = "<a href=\"#\" onclick=\"getAdminViewPage('" + boardNext.getBi_bbs_sn() + "');\">" + boardNext.getBi_titl() + "</a>";
			mav.addObject("nextTitle", nextTitle);
			mav.addObject("nextDate", boardNext.getParseModifyDate());

		}

		mav.addObject("rows", boardService.findList(bi_bbs_sn));
		mav.setViewName("jsonView");

		return mav;
	}

	@RequestMapping(value = "/admin/board/save.do", method = RequestMethod.POST)
	public ModelAndView adminSave( @ModelAttribute("fileUpload") FileUpload fileUpload
			, @RequestParam("files") MultipartFile[] files
			, @ModelAttribute("board") Board board
			, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
			, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
			, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
			, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
			, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		logger.info("아이디:::: " + auth.getName());

		board.setBi_unity_cust_id(auth.getName()); // 아이디 등록
		board.setBi_inqire_num(0);
		boardService.save(board);
		ModelAndView mav = new ModelAndView();
		Board getBoard = boardService.findListDesc();
		
		
		logger.info("currentId::" + currentId);


		logger.info("파일갯수::" + files.length);

		for (int i = 0; i < files.length; i++) {

			if (files[i].getSize() > 0) {

				String fileRoot = request.getRealPath(boardFileRoot);
				fileUploadService.uploadMultipleFileHandler(fileUpload, fileRoot, files[i]);

				fileUpload.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_sn(getBoard.getBi_bbs_sn());
				fileUpload.setBi_svc_nm("board");
				fileUpload.setBi_file_num(i);
				fileUpload.setBi_atch_flpth_nm(boardFileRoot); // 파일경로

				fileUploadService.save(fileUpload);
			}

		}

		mav.addObject("msg", "저장되었습니다");
		mav.addObject("pageNm", "list");
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName("redirect:/admin/board/gotoPage.do");
		return mav;

	}
	
	
	
	@RequestMapping(value = "/admin/board/modify.do", method = RequestMethod.POST)
	public ModelAndView adminModify(
			  @ModelAttribute("fileUpload") FileUpload fileUpload
			, @RequestParam("files") MultipartFile[] files
			, @ModelAttribute("board") Board board
			, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
			, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
			, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
			, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
			, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("여기");
		
		
		
		

		boardService.update(board);
		ModelAndView mav = new ModelAndView();
		
		
		
		
		for (int i = 0; i < files.length; i++) {

			if (files[i].getSize() > 0) {
				
				
				
				/*	
				FileUpload getfile = fileUploadService.fileBoard(board.getBi_bbs_sn(), "board", i);
			    	
			  	 String fullPath = "";
			  	  
			  	  logger.info("파일경로:" + fileUpload.getBi_atch_flpth_nm());
			  	  logger.info("파일명:" + fileUpload.getBi_tmpr_atch_file_nm());
			  	  
			  	
			  	if(getfile.getBi_atch_flpth_nm().equals("/file/indicator/") || getfile.getBi_atch_flpth_nm().equals("/file/board")){
			  		
			  		Path pathfile = Paths.get(request.getRealPath(getfile.getBi_atch_flpth_nm()), getfile.getBi_tmpr_atch_file_nm());
			  		try {
			  			
							Files.delete(pathfile);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			  		
			  	}else{
			  		
			  		
			  		Path pathfile = Paths.get(getfile.getBi_atch_flpth_nm(), getfile.getBi_tmpr_atch_file_nm());
			  		try {
			  			
							Files.delete(pathfile);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			  		
			  		
			  	}
			  	 
			  	int delNum = fileUploadService.remove(getfile.getBi_atch_file_sn());
					
				*/
				
				//FileUpload getfile = fileUploadService.fileBoard(board.getBi_bbs_sn(), "board", i);

				String fileRoot = request.getRealPath(boardFileRoot);
				fileUploadService.uploadMultipleFileHandler(fileUpload, fileRoot, files[i]);

				fileUpload.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				fileUpload.setBi_sn(board.getBi_bbs_sn());
				fileUpload.setBi_svc_nm("board");
				fileUpload.setBi_file_num(i);
				fileUpload.setBi_atch_flpth_nm(boardFileRoot); // 파일경로

				fileUploadService.save(fileUpload);
				
				
			}

		}
		

		mav.addObject("pageNm", "list");
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName("redirect:/admin/board/gotoPage.do");
		return mav;
		
	}


	@RequestMapping(value = "/admin/board/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView admindeleteForm(@RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "") int bi_bbs_sn, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.addObject("bi_bbs_sn", boardService.getById(bi_bbs_sn));
		mav.setViewName("/board/delete");
		return mav;

	}
	/**
	 * 자료실 (삭제)
	 * 
	 * @param request
	 * @param response
	 * @return list
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/board/delete.do", method = RequestMethod.POST)
	public ModelAndView adminDelete(@RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "") int bi_bbs_sn, HttpServletRequest request, HttpServletResponse response) throws Exception {

		boardService.remove(bi_bbs_sn);
		
		
		 List<FileUpload> li = fileUploadService.fileBoardList(bi_bbs_sn, "board");
	    	
		  
			for(int i=0;i<li.size();i++){
				
				
				Path pathfile = Paths.get(request.getRealPath(li.get(i).getBi_atch_flpth_nm()), li.get(i).getBi_tmpr_atch_file_nm());
		  		try {
		  			
						Files.delete(pathfile);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 
		  	 
		  	  int delNum = fileUploadService.remove(li.get(i).getBi_atch_file_sn());
				
				
			}
			
			
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", "삭제 되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	@RequestMapping(value = "/admin/board/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView adminDeleteAll(@RequestParam(value = "checkId", required = false, defaultValue = "") String[] checkId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		for (int i = 0; i < checkId.length; i++) {
			
			
			   List<FileUpload> li = fileUploadService.fileBoardList(Integer.valueOf(checkId[i]), "board");
		    	
			  
				for(int j=0;j<li.size();j++){
					
					
					Path pathfile = Paths.get(request.getRealPath(li.get(j).getBi_atch_flpth_nm()), li.get(j).getBi_tmpr_atch_file_nm());
			  		try {
			  			
							Files.delete(pathfile);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			 
			  	 
			  	  int delNum = fileUploadService.remove(li.get(j).getBi_atch_file_sn());
					
					
				}
			

			boardService.remove(Integer.valueOf(checkId[i]));

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	@RequestMapping(value = "/board/getCntCheck.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView getCntCheck(@RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "0") int bi_bbs_sn, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		ModelAndView mav = new ModelAndView();
		int num = boardService.getCntCheck(bi_bbs_sn, auth.getName());

		mav.addObject("btnNum", num);
		mav.setViewName("jsonView");
		return mav;

	}
	
	
	@RequestMapping(value = "/admin/board/getCntCheck.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView getAdminCntCheck(@RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "0") int bi_bbs_sn, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		ModelAndView mav = new ModelAndView();
		int num = boardService.getCntCheck(bi_bbs_sn, auth.getName());

		mav.addObject("btnNum", num);
		mav.setViewName("jsonView");
		return mav;

	}

	@RequestMapping(value = "/mypage/board/getCntCheck.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView getMypageCntCheck(@RequestParam(value = "bi_bbs_sn", required = false, defaultValue = "0") int bi_bbs_sn, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		ModelAndView mav = new ModelAndView();
		int num = boardService.getCntCheck(bi_bbs_sn, auth.getName());

		mav.addObject("btnNum", num);
		mav.setViewName("jsonView");
		return mav;

	}

}
