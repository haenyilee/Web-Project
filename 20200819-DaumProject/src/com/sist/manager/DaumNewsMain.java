package com.sist.manager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.MovieDAO;

/*
 *  <ul class="list_line #list">

							<li>
				<a href="http://v.movie.daum.net/v/20191129113631107" class="thumb_line bg_noimage2 @1">
																				<span class="thumb_img" style="background-image:url(//img1.daumcdn.net/thumb/S320x200/?fname=https://t1.daumcdn.net/news/201911/29/sportsdonga/20191129113632574euca.jpg);"></span>
				</a>
				<span class="cont_line">
					<strong class="tit_line"><a href="http://v.movie.daum.net/v/20191129113631107" class="link_txt @1">염정아, '세컨드 마더'로 'SKY캐슬' 감독과 재회하나..소속사 "검토 중"</a></strong>
					<a href="http://v.movie.daum.net/v/20191129113631107" class="desc_line @1">
						[동아닷컴]  배우 염정아가 드라마 SKY캐슬 조현탁 PD의 영화 데뷔작 출연을 검토 중이다. 염정아의 소속사 아티스트컴퍼니 관계자는 29일 동아닷컴에 “염정아가 조현탁 감독의 세컨드 마더 출연을 검토 중이다라며 하지만 지금은 영화 인생은 아름다워에 집중하고 있다라고 말했다. 세컨드 마더는 2015년 11월 개봉한 동명의 브라질 영화를
					</a>
					<span class="state_line">
						스포츠동아<span class="txt_dot"></span><span class="screen_out">발행일자</span>19.11.29
					</span>
				</span>
			</li>
 */
public class DaumNewsMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try
        {
        	MovieDAO dao=new MovieDAO();
        	for(int i=1;i<=7;i++)
        	{
	        	Document doc=Jsoup.connect("https://movie.daum.net/magazine/new?tab=nws&regdate=20200819&page="+i).get();
	        	Elements title=doc.select("ul.list_line span.cont_line a.link_txt");
	        	Elements poster=doc.select("ul.list_line a.thumb_line span.thumb_img");
	        	Elements link=doc.select("ul.list_line a.thumb_line");
	        	Elements content=doc.select("ul.list_line span.cont_line a.desc_line");
	        	Elements author=doc.select("ul.list_line span.cont_line span.state_line");
	        	
	        	for(int j=0;j<title.size();j++)
	        	{
	        		String p=poster.get(j).attr("style");
	        		p=p.substring(p.indexOf("(")+1,p.lastIndexOf(""));
	        		System.out.println(title.get(j).text());
	        		System.out.println(content.get(j).text());
	        		System.out.println(author.get(j).text());
	        		System.out.println(p);
	        		System.out.println(link.get(j).attr("href"));
	        		
	        		System.out.println("================================================================");
	        		
	        		NewsVO vo=new NewsVO();
	        		vo.setTitle(title.get(j).text());
	        		vo.setPoster(p);
	        		vo.setLink(link.get(j).attr("href"));
	        		vo.setContent(content.get(j).text());
	        		vo.setAuthor(author.get(j).text());
	        		Thread.sleep(100);
	        		
	        		dao.newsInsert(vo);
	        	}
        	}
        	System.out.println("End...");
        }catch(Exception ex){ex.printStackTrace();}
	}

}


















