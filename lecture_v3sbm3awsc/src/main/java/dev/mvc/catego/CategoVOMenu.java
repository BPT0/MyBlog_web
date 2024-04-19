package dev.mvc.catego;

import java.util.ArrayList;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoVOMenu {
  /** 중분류명 */
  @NotEmpty(message="중분류(강의)명은 필수 기입항목입니다.")
  @Size(min=2, max=10, message="중분류명은 2글자에서 10글자 사이여야 합니다.")
  private String name;
  
  /** 소분류명 */  
  private ArrayList<CategoVO> list_namesub;
}
