package dev.mvc.lecture_v1sbm3caws;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.catego.CategoDAOInter;
import dev.mvc.catego.CategoProcInter;
import dev.mvc.catego.CategoVO;

@SpringBootTest
class LectureV1sbm3awscApplicationTests {
	@Autowired
	private CategoDAOInter categoDAO;

	@Autowired
	@Qualifier("dev.mvc.catego.CategoProc")
	private CategoProcInter cateProc;

	@Test
	void contextLoads() {
		
	}

	// @Test
  // public void testCreate() {
  //   CategoVO categoVO = new CategoVO();
  //   categoVO.setName("개발");
  //   categoVO.setNamesub("자바스크립트");
	// 	categoVO.setCount(1);
	// 	categoVO.setSeqcno(1);
	// 	categoVO.setVis("Y");
	// 	categoVO.setCategono(2);
    
  //   int cnt = this.categoDAO.update(categoVO);
  //   System.out.println("-> cnt: "+cnt);
    
  // }

	@Test
  public void testCreate() {
    CategoVO categoVO = new CategoVO();
    
    categoVO = this.categoDAO.read(2);
    System.out.println("-> categoVO - name: "+ categoVO.getName());
    
  }

}
