package dev.mvc.lecture_v2sbm3caws;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.mvc.catego.CategoProcInter;
import dev.mvc.catego.CategoVOMenu;

@Controller
public class HomeCont {
  @Autowired
  @Qualifier("dev.mvc.catego.CategoProc")
  private CategoProcInter categoProc;
  
  public HomeCont() {
    System.out.println("-> HomeCont created");
  }
  
  /**
   * 메인화면
   * http://localhost:9091
   * @param model
   * @return
   */
  @GetMapping(value="/") // 
  public String home(Model model) {
    ArrayList<CategoVOMenu> menu = this.categoProc.menu();
    
    model.addAttribute("menu", menu);

    return "index"; // templates/index.html
  }
  
}
