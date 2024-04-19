package dev.mvc.catego;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@RequestMapping("/catego")
@Controller
public class CategoCont {
  @Autowired
  @Qualifier("dev.mvc.catego.CategoProc")
  private CategoProcInter categoProc;

  /** 페이지당 출력할 레코드 갯수, */
  public int record_per_page = 10;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 10;

  public CategoCont() {
    System.out.println("-> CategoCont created");
  }

    /**
   * Create Process Form
   * 
   * @param model
   * @param categoVO
   * @param bindingResult
   * @return
   */
  @PostMapping(value = "/create") // http://localhost:9092/catego/create
  public String create(Model model, @Valid CategoVO categoVO, BindingResult bindingResult, 
          @RequestParam(name="word", defaultValue = "") String word,
          @RequestParam(name="now_page", defaultValue = "1") int now_page
  ) 
  {
    ArrayList<CategoVOMenu> menu = this.categoProc.menu();
    model.addAttribute("menu", menu);
    
    if (bindingResult.hasErrors()) {
      ArrayList<CategoVO> list = this.categoProc.list_search_paging(word, now_page, this.record_per_page);
      model.addAttribute("list", list);
            
      // 페이징 버튼 목록
      int search_count = this.categoProc.list_search_count(word);
      String paging = this.categoProc.pagingBox(now_page, 
          word, "/catego/list_search" + now_page, search_count, this.record_per_page, this.page_per_block);
      model.addAttribute("paging", paging);
      model.addAttribute("word", word);
      model.addAttribute("now_page", now_page);
    
      return "catego/list_search";
    }

    int count = this.categoProc.create(categoVO);
    System.out.println("-> cnt: " + count);
    model.addAttribute("count", count);

    if (count == 1) {
            
      return "redirect:/catego/list_search?word=" + dev.mvc.tool.Tool.encode(word) + "&now_page=" + now_page;

    } else {
      model.addAttribute("code", "create_fail");

      return "catego/msg";
    }

  }

  // /**
  //  * 등록폼 + 목록
  //  * http://localhost:9092/catego/list_search
  //  * @param model
  //  * @return
  //  */
  // @GetMapping(value = "/list_search")
  // public String list_all(Model model, CategoVO categoVO) {
  //   ArrayList<CategoVO> list = this.categoProc.list_all();
  //   ArrayList<CategoVOMenu> menu = this.categoProc.menu();
    
  //   model.addAttribute("list", list);
  //   model.addAttribute("menu", menu);

  //   return "/catego/list_search"; // catego/list_search.html
  // }

  /**
   * 조회 + 목록
   * 
   * @param model
   * @param categono 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/read/{categono}")
  public String read(Model model, @PathVariable("categono") Integer categono,
        @RequestParam(name="word", defaultValue = "") String word,
        @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    word = dev.mvc.tool.Tool.checkNull(word);

    ArrayList<CategoVOMenu> menu = this.categoProc.menu();
    model.addAttribute("menu", menu);
    
    CategoVO categoVO = this.categoProc.read(categono);
    model.addAttribute("categoVO", categoVO);
    
    // 페이징 목록
    ArrayList<CategoVO> list = this.categoProc.list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);

    // 페이징 버튼 목록
    int search_count = this.categoProc.list_search_count(word);
    String paging = this.categoProc.pagingBox(now_page, 
        word, "/catego/list_search", search_count, this.record_per_page, this.page_per_block);
    model.addAttribute("paging", paging);
    model.addAttribute("word", word);
    model.addAttribute("list", list);
    model.addAttribute("now_page", now_page);
    
    int no = search_count - ((now_page -1) * this.record_per_page);
    model.addAttribute("no", no);

    return "catego/read"; // /catego/read/{categono}.html
  }

  /**
   * Modify Process
   * 
   * @param model
   * @param categoVO
   * @param bindingResult
   * @return
   */
  @PostMapping(value = "/update") // http://localhost:9092/catego/update
  public String update(Model model, @Valid CategoVO categoVO, BindingResult bindingResult, 
      @RequestParam(name="word", defaultValue = "") String word,
      @RequestParam(name="now_page", defaultValue = "1") int now_page) {
    ArrayList<CategoVOMenu> menu = this.categoProc.menu();
    model.addAttribute("menu", menu);

    if (bindingResult.hasErrors()) {
      ArrayList<CategoVO> list = this.categoProc.list_search_paging(word, now_page, this.record_per_page);
      model.addAttribute("list", list);
      
      return "catego/update";
    }

    int count = this.categoProc.update(categoVO);
    // System.out.println("-> count: " + count);
    model.addAttribute("count", count);

    if (count == 1) {
      // model.addAttribute("code", "update_success");
      // model.addAttribute("name", categoVO.getName());
      // model.addAttribute("namesub", categoVO.getNamesub());      

      return "redirect:/catego/update/" + categoVO.getCategono() + "?word=" + word + "&now_page=" + now_page;

    } else {
      model.addAttribute("code", "update_fail");

      return "catego/msg"; // /templates/catego/msg.html
    }
    
  }

  /**
   * Modify Form
   * http://localhost:9092/catego/update/1
   * @param model
   * @param cateno, 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/update/{categono}")
  public String update(Model model, @PathVariable("categono") Integer categono,
      @RequestParam(name="word", defaultValue = "") String word,
      @RequestParam(name= "now_page", defaultValue = "")int now_page) {

    ArrayList<CategoVOMenu> menu = this.categoProc.menu();
    model.addAttribute("menu", menu);

    CategoVO categoVO = this.categoProc.read(categono);
    model.addAttribute("categoVO", categoVO);

    // 페이징 목록
    ArrayList<CategoVO> list = this.categoProc.list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);
    
    // 페이징 버튼 목록
    int search_count = this.categoProc.list_search_count(word);
    String paging = this.categoProc.pagingBox(now_page, 
        word, "/catego/list_search", search_count, this.record_per_page, this.page_per_block);
    model.addAttribute("paging", paging);
    model.addAttribute("word", word);
    model.addAttribute("now_page", now_page);
    
    int no = search_count - ((now_page -1) * this.record_per_page);
    model.addAttribute("no", no);

    return "catego/update"; // /templates/catego/update/{cateno}.html

  }

  /**
   * 수정: 출력 순서 높임: seqcno - 1
   * http://localhost:9092/catego/update_seqcno_forward/1
   * @param model
   * @param cateno, 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/update_seqcno_forward/{categono}")
  public String update_seqcno_forward(Model model, @PathVariable("categono") int categono,
      @RequestParam(name="word", defaultValue = "") String word) {
    this.categoProc.update_seqcno_forward(categono);
    
    return "redirect:/catego/list_search?word=" + dev.mvc.tool.Tool.encode(word); // /catego/read/list_search.html
  }

  /**
   * 수정: 출력 순서 낮춤: seqcno + 1
   * http://localhost:9092/catego/update_seqcno_backward/1
   * @param model
   * @param cateno, 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/update_seqcno_backward/{categono}")
  public String update_seqcno_backward(Model model, @PathVariable("categono") int categono,
      @RequestParam(name="word", defaultValue = "") String word) {
        
    this.categoProc.update_seqcno_backward(categono);

    return "redirect:/catego/list_search?word=" + dev.mvc.tool.Tool.encode(word); // /catego/read/list_search.html
  }

  /**
   * 수정 - 카테고리 공개 설정
   * http://localhost:9092/catego/update_vis_y/1
   * @param model
   * @param cateno
   * @return
   */
  @GetMapping(value = "/update_vis_y/{categono}")
  public String update_vis_y(Model model, @PathVariable("categono") Integer categono,
      @RequestParam(name="word", defaultValue = "") String word) {
    this.categoProc.update_vis_y(categono);

    return "redirect:/catego/list_search?word=" + dev.mvc.tool.Tool.encode(word); // /catego/read/list_search.html
  }

  /**
   * 수정 - 카테고리 비공개 설정
   * http://localhost:9091/catego/update_visible_n/1
   * @param model
   * @param cateno
   * @return
   */
  @GetMapping(value = "/update_vis_n/{categono}")
  public String update_vis_n(Model model, @PathVariable("categono") Integer categono,
      @RequestParam(name="word", defaultValue = "") String word) {

    this.categoProc.update_vis_n(categono);
    
    return "redirect:/catego/list_search?word=" + dev.mvc.tool.Tool.encode(word); // /catego/read/list_search.html
  }

  /**
   * Delete Process
   * 
   * @param model
   * @param categono 삭제할 레코드번호
   * @param bindingResult
   * @return
   */
  @PostMapping(value = "/delete") // http://localhost:9092/catego/delete
  public String delete_process(Model model, Integer categono,
      @RequestParam(name="word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    ArrayList<CategoVO> list = this.categoProc.list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);

    int count = this.categoProc.delete(categono);
    // System.out.println("-> count: " + count);

    model.addAttribute("count", count);

    // ------------------------------------------------------------
    // 마지막 페이지에서 모든 레코드가 삭제되면 페이지 수를 1 감소 시켜야함
    int search_cnt = this.categoProc.list_search_count(word);
    if(search_cnt % this.record_per_page == 0) {
      // 페이지 수를 1 감소 시켜야함 : page -1
      now_page -= 1;
      
      if(now_page < 1) {
        now_page =1; // 최소 시작 페이지
      }
      
    }
    // -------------------------------------------------------------

    if (count == 1) {

      return "redirect:/catego/list_search?word=" + word + "&now_page=" + now_page;

    } else {
      model.addAttribute("code", "delete_fail");

      return "catego/msg"; // /templates/catego/msg.html
    }

  }

  /**
   * Delete Form
   * http://localhost:9092/catego/delete/1
   * 
   * @param model
   * @param categono, 삭제할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/delete/{categono}")
  public String delete(Model model, @PathVariable("categono") Integer categono,
      @RequestParam(name="word", defaultValue = "")String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    ArrayList<CategoVOMenu> menu = this.categoProc.menu();
    model.addAttribute("menu", menu);
    
    // 페이징 목록
    ArrayList<CategoVO> list = this.categoProc.list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);
    
    // 페이징 버튼 목록
    int search_count = this.categoProc.list_search_count(word);
    String paging = this.categoProc.pagingBox(now_page, 
        word, "/catego/list_search", search_count, this.record_per_page, this.page_per_block);
    model.addAttribute("paging", paging);
    model.addAttribute("word", word);
    model.addAttribute("now_page", now_page);
    
    CategoVO categoVO = this.categoProc.read(categono);
    model.addAttribute("categoVO", categoVO);

    model.addAttribute("word", word);

    int no = search_count - ((now_page -1) * this.record_per_page);
    model.addAttribute("no", no);

    return "catego/delete"; // /templates/catego/delete/{categono}.html
  }

  /**
   * 등록폼 + 검색목록
   * http://localhost:9092/catego/list_all?word=베이스 <- Get Form
   * http://localhost:9092/catego/list_all/베이스 <- PathVariable
   * @param model
   * @return
   */
  @GetMapping(value = "/list_search")
  public String list_search(Model model, CategoVO categoVO, String word,
    @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    word = dev.mvc.tool.Tool.checkNull(word);
    System.out.println("--> word: " + word);

    ArrayList<CategoVOMenu> menu = this.categoProc.menu();
    model.addAttribute("menu", menu);
    
    ArrayList<CategoVO> list = this.categoProc.list_search_paging(word, now_page, this.page_per_block);
    model.addAttribute("list", list);

    model.addAttribute("word", word);

    // 페이징 버튼 목록
    int search_count = this.categoProc.list_search_count(word);
    String paging = this.categoProc.pagingBox(now_page, 
        word, "/catego/list_search", search_count, this.record_per_page, this.page_per_block);
    model.addAttribute("paging", paging);
    model.addAttribute("word", word);
    model.addAttribute("now_page", now_page);

      // 일련 번호 생성 : 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수): 7 - ((1 - 1) * 3) = 7
    int no = search_count - ((now_page -1) * this.record_per_page);
    model.addAttribute("no", no);

    return "catego/list_search"; // catego/list_search.html
  }
  
  

}
