package dev.mvc.catego;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoVO {
  /** 카테고리 번호*/
  private Integer categono=0;
  
  /** 중분류명 */
  @NotEmpty(message="중분류(강의)명은 필수 기입항목입니다.")
  @Size(min=2, max=10, message="중분류명은 2글자에서 10글자 사이여야 합니다.")
  private String name;
  
  /** 소분류명 */  
  @NotEmpty(message="소분류(강의)명은 필수 기입항목입니다.")
  @Size(min=1, max=10, message="소분류명은 2글자에서 10글자 사이여야 합니다.")
  private String namesub;
  
  /** 관련 강의수 */
  @NotNull(message="관련자료수는 필수 입력항목입니다.")
  @Min(value= 0)
  @Max(value = 10000000)
  private Integer count=0;
  
  /** 게시일 */
  private String pdate;
  
  /** 수순(출력 순서) */
  @NotNull(message="출력 순서는 필수 입력항목입니다.")
  @Max(value = 10000000)
  private Integer seqcno;
  
  
  /** 출력 여부 */
  @NotEmpty(message="출력 여부는 필수 입력항목입니다.")
  @Pattern(regexp="^[YN]$", message="Y 또는 N만 입력 가능 합니다")
  private String vis;
  
}