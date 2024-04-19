package dev.mvc.catego;

import java.util.ArrayList;
import java.util.Map;

public interface CategoProcInter {
  /**
   * 등록
   * insert id="create" parameterType="dev.mvc.lecture.CategoVO"
   * @param categoVO
   * @return 등록한 레코드 갯수
   */
  public int create(CategoVO categoVO);
  
  /**
   * 조회: 전체 목록
   * select id="list_all" resultType="dev.mvc.lecture.CategoVO"
   * @return 레코드 목록
   */
  public ArrayList<CategoVO> list_all();

  /**
   * 회원/비회원에게 공개할 중분류 목록
   * select id="list_all_name_y" resultType="dev.mvc.catego.CategoVO"
   * @return 레코드 목록
   */
  public ArrayList<CategoVO> list_all_name_y();

  
  /**
   * 회원/비회원에게 공개할 소분류 목록
   * select id="list_all_namesub_y" resultType="dev.mvc.catego.CategoVO" parameterType="String"
   * @return
   */
  public ArrayList<CategoVO> list_all_namesub_y(String namesub);

  /** 메뉴 */
  public ArrayList<CategoVOMenu> menu();

  /**
   * 조회 - 번호(categono)
   * select id="read" resultType="dev.mvc.lecture.categoVO" parameterType="int"
   * @param categono
   * @return 조회한 레코드(categoVO)
   */
  public CategoVO read(int categono);

  /**
   * 수정 - 번호로 조회한 객체(categono)
   * update id="update" parameterType="dev.mvc.catego.CategoVO"
   * @param categoVO
   * @return
   */
  public int update(CategoVO categoVO);

  /**
   * 수정: 출력 순서 높임: seqcno - 1
   * update id="update_seqno_forward" parameterType="Integer"
   * @param categono
   * @return 수정한 레코드 수
   */
  public int update_seqcno_forward(int categono);

  /**
   * 수정: 출력 순서 낮춤
   * update id="update_seqno_backward" parameterType="Integer"
   * @param categono
   * @return 수정한 레코드 수
   */
  public int update_seqcno_backward(int categono);

  /**
   * 수정: 카테고리 공개 설정
   * update id="update_visible_y" parameterType="Integer"
   * @param categono
   * @return 수정한 레코드 수
   */
  public int update_vis_y(int categono);

  /**
   * 수정: 카테고리 비공개 설정
   * update id="update_visible_n" parameterType="Integer"
   * @param categono
   * @return 수정한 레코드 수
   */
  public int update_vis_n(int categono);

  /**
   * 삭제 - 번호
   * delete id="delete" parameterType="Integer"
   * @param categono
   * @return 삭제한 레코드 수
   */
  public int delete(int categono);

  /*
   * 조회 - 관리자용 검색 목록
   * id="list_search" resultType="dev.mvc.catego.CategoVO" parameterType="String"
   * @param cate
   */
  public ArrayList<CategoVO> list_search(String word);

  /**
   * 검색 목록 페이징
   * id="list_search_paging" resultType="dev.mvc.catego.CategoVO" parameterType="Map"
   * @param map
   * @return
   */
  public ArrayList<CategoVO> list_search_paging(String word, int now_page, int record_per_page);

  /**
   * 검색한 레코드 수
   * @param word
   * @return
   */
  public int list_search_count(String word);

  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param cateno 카테고리번호 
   * @param now_page  현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명
   * @param search_count 검색 레코드수
   * @param record_per_page 페이지당 레코드 수
   * @param page_per_block 블렁당 페이지 수
   * @return 페이징 생성 문자열
   */ 
  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page, int page_per_block);
}
