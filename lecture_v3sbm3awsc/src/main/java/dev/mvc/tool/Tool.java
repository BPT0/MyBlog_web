package dev.mvc.tool;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class Tool {
  /**
   * null 문자열(객체)을 null 값 해제
   * static: 객체 생성 필요 없음, synchronized: 동시 접속 문제 해결
   * @param obj
   * @return
   */
  public static synchronized String checkNull(String str) {
    if (str == null || str.equals("null")) {
      return "";
    } else {
      System.out.println(str + ": null 아님");
      return (String) str;
    }
  }
  
  /**
   * 한글 -> 16진수 UTF-8 문자코드로 변환
   * @param str
   * @return
   */
  public static synchronized String encode(String str) {
    return URLEncoder.encode(str, StandardCharsets.UTF_8);
  }
}
