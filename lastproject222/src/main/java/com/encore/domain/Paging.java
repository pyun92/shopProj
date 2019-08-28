package com.encore.domain;

import lombok.Data;

@Data
public class Paging {
	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지사이즈 */
	private int pageSize = 10; // 한페이지에 나오는 게시물 개수

	private int pageGroupSize = 3; // 페이지 번호 몇개 보여줄 것인지

}
