package com.my.vo;

import java.util.List;

public class RepboardArticlePage {

		private int total;
		private int currentPage;
		private List<Repboard> content;
		private int totalPages;
		private int startPage;
		private int endPage;
		
		public RepboardArticlePage(int total, int currentPage, int size, List<Repboard> content){
			
			this.total =total; 
			this.currentPage =currentPage;
			this.content =content;
			
			if(total==0){
				totalPages=0;
				startPage=0;
				endPage=0;
				
			}else{
				totalPages = total/size;
				if(total % size >0){
					totalPages++;
				}
				int modVal = currentPage%5;
				startPage=currentPage/5%5+1;
				if(modVal==0){
					startPage-=5;
				}
				endPage = startPage+4;
				if(endPage<totalPages){
					endPage=totalPages;
				}
			}
		}
		

		public int getTotal() {
			return total;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public List<Repboard> getContent() {
			return content;
		}

		public int getTotalPages() {
			return totalPages;
		}

		public int getStartPage() {
			return startPage;
		}

		public int getEndPage() {
			return endPage;
		}
		
		public boolean hasNoArticles(){
			return total==0;
		}
		
		public boolean hasArticles(){
			return total>0;
		}

}
