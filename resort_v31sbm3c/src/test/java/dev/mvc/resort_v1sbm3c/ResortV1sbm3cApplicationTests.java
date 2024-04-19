package dev.mvc.resort_v1sbm3c;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.cate.CateDAOInter;
import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;

@SpringBootTest
class ResortV1sbm3cApplicationTests {
  @Autowired // CateDAOInter를 구현한 클래스의 객체를 생성하여 cateDAO 변수에 할당
  private CateDAOInter cateDAO;

  @Autowired // CateProcInter를 구현한 클래스의 객체를 생성하여 cateProc 변수에 할당
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;

	@Test
	void contextLoads() {
	}
	
//	@Test
//	public void testCreate() {
//    CateVO cateVO = new CateVO();	 
//    cateVO.setName("개발");
//    cateVO.setNamesub("오라클");
//    
//    int cnt = this.cateDAO.create(cateVO);
//    System.out.println("-> cnt: " + cnt);
//	}

//	 @Test
//	  public void testCreate() {
//	    CateVO cateVO = new CateVO();  
//	    cateVO.setName("개발");
//	    cateVO.setNamesub("자바스크립트");
//	    
//	    int cnt = this.cateProc.create(cateVO);
//	    System.out.println("-> cnt: " + cnt);
//	  }
	 
//	 @Test
//	 public void testList_all() {
//	   ArrayList<CateVO> list = this.cateDAO.list_all();
//	   for (CateVO cateVO : list) {
//	     System.out.println(cateVO.getName() + " - " + cateVO.getNamesub());
//	   }	   
//	 }

//  @Test
//  public void testList_all() {
//    ArrayList<CateVO> list = this.cateProc.list_all();
//    for (CateVO cateVO : list) {
//      System.out.println(cateVO.getName() + " - " + cateVO.getNamesub());
//    }     
//  }
	
	@Test
	public void Hangul() {
//	  String word = URLEncoder.encode("오버렌딩");
//	  System.out.println("원본: %EC%98%A4%EB%B2%84%EB%A0%8C%EB%94%A9");
//	  System.out.println("변환: " + word);
//	  
//	  word = URLDecoder.decode("%EC%98%A4%EB%B2%84%EB%A0%8C%EB%94%A9"); 
//	  System.out.println("복원: " + word);

//	  // JDK 10+, VSCode 사용
//	  String word = URLEncoder.encode("오버렌딩", StandardCharsets.UTF_8);
//	  System.out.println("원본: %EC%98%A4%EB%B2%84%EB%A0%8C%EB%94%A9");
//	  System.out.println("변환: " + word);
//	    
//	  word = URLDecoder.decode("%EC%98%A4%EB%B2%84%EB%A0%8C%EB%94%A9", StandardCharsets.UTF_8); 
//	  System.out.println("복원: " + word);
	}
	
	@Test
  public void testcount() {
    int cnt= this.cateProc.list_search_count("개발");
    System.out.println("-> cnt: " + cnt);
  }
	
}







