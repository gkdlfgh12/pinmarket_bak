package com.pinmarket.util;

import com.pinmarket.vo.PageVO;

public class PageCreator {
	//페이지 번호와 한 페이지당 들어갈 게시물의 개수를 갖고 있는 객체
		private PageVO paging;
		private int page; //현재 페이지
		private int countPerPage; //한 페이지당 들어갈 게시물 수
		private int articleTotalCount; //게시판의 총 게시물 수
		private int beginPage; //시작 페이지 번호
		private int endPage; //끝 페이지 번호
		private boolean prev; //이전 버튼 활성화 여부
		private boolean next; //다음 버튼 활성화 여부
		private String keyword;
		private String condition;
		private int startRow;
		private int endRow;
		
		//한 화면에 보여질 페이지 버튼 수
		private final int displayPageNum = 5;

		//페이징 알고리즘을 수행할 메서드 선언.
		private void calcDataOfPage() {
			
			//보정 전 끝 페이지 구하기
			endPage = (int) (Math.ceil(page / (double)displayPageNum) 
					* displayPageNum);
			System.out.println(endPage+" = "+page +" / "+ (double)displayPageNum +" * "+displayPageNum);
			//시작 페이지 번호 구하기
			beginPage = (endPage - displayPageNum) + 1;
			
			//현재 시작 페이지가 1이라면 이전 버튼 활성화 여부를 false로 지정.
			prev = (beginPage == 1) ? false : true;
			
			//마지막 페이지인지 여부 확인 후 다음 버튼 비활성화 판단.
			next = (articleTotalCount <= (endPage * countPerPage)) ? false : true;
			
			startRow = page * countPerPage - (countPerPage-1); 
			endRow = page * countPerPage;
			System.out.println("startRow : "+startRow);
			System.out.println("endRow : "+endRow);
			
			//다음 버튼이 비활성화라면 끝 페이지 재보정 하기.
			if(!isNext()) {
				endPage = (int) Math.ceil(articleTotalCount / (double)countPerPage); // 22 / 10
				System.out.println("articleTotalCount : "+articleTotalCount);
				System.out.println("endPage : "+endPage);
			}
			
		}
		
		public int getStartRow() {
			return startRow;
		}

		public void setStartRow(int startRow) {
			this.startRow = startRow;
		}

		public int getEndRow() {
			return endRow;
		}

		public void setEndRow(int endRow) {
			this.endRow = endRow;
		}

		public String getKeyword() {
			return keyword;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

		public String getCondition() {
			return condition;
		}

		public void setCondition(String condition) {
			this.condition = condition;
		}

		public PageVO getPaging() {
			return paging;
		}

		public void setPaging(PageVO paging) {
			this.page = paging.getPage();
			this.countPerPage = paging.getCountPerPage();
		}

		public int getPage() {
			return page;
		}


		public void setPage(int page) {
			this.page = page;
		}


		public int getCountPerPage() {
			return countPerPage;
		}


		public void setCountPerPage(int countPerPage) {
			this.countPerPage = countPerPage;
		}

		public int getArticleTotalCount() {
			return articleTotalCount;
		}

		public void setArticleTotalCount(int articleTotalCount) {
			this.articleTotalCount = articleTotalCount;
			calcDataOfPage();
		}

		public int getBeginPage() {
			return beginPage;
		}

		public void setBeginPage(int beginPage) {
			this.beginPage = beginPage;
		}

		public int getEndPage() {
			return endPage;
		}

		public void setEndPage(int endPage) {
			this.endPage = endPage;
		}

		public boolean isPrev() {
			return prev;
		}

		public void setPrev(boolean prev) {
			this.prev = prev;
		}

		public boolean isNext() {
			return next;
		}

		public void setNext(boolean next) {
			this.next = next;
		}


		@Override
		public String toString() {
			return "PageCreator [page=" + page + "countPerPage="+ countPerPage + ", articleTotalCount=" + articleTotalCount + ", beginPage=" + beginPage
					+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + "]";
		}
}
