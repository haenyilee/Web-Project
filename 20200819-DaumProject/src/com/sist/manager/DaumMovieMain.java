package com.sist.manager;

import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.MovieDAO;

public class DaumMovieMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		   {
			     
			MovieDAO dao=new MovieDAO();
			int k=1;
			//for(int i=1;i<=7;i++)
			{
			     Document doc=Jsoup.connect("https://movie.daum.net/boxoffice/yearly").get();
			     Elements link=doc.select("span.wrap_link a.link_desc[href*=/moviedb/]");
			     
			     
			     for(int j=0;j<link.size();j++)
			     {
			    	try
			    	{
			    	
			    	 String dLink="https://movie.daum.net"+link.get(j).attr("href");
			    	 Document doc2=Jsoup.connect(dLink).get();
			    	 System.out.println(dLink);
			    	
			    	 Element title=doc2.selectFirst("div.mobile_subject strong.tit_movie");
			    	 
			    	 Element poster=doc2.selectFirst("span.thumb_summary img.img_summary");
			    	 Element regdate=doc2.select("dl.list_movie dd.txt_main").get(1);
			    	 Element grade=doc2.select("dl.list_movie dd").get(3);
			    	 Element genre=doc2.select("dl.list_movie dd.txt_main").get(0);
			    	 Element score=doc2.selectFirst("div.info_origin a");
			    	 Element actor=doc2.select("dd.type_ellipsis a").get(1);
			    	 Element director=doc2.select("dd.type_ellipsis a").get(0);
			    	 Element story=doc2.selectFirst("div.desc_movie");
			    	 
			    	 System.out.println(title.text());
			    	 System.out.println(poster.attr("src"));
			    	 System.out.println(regdate.text());
			    	 System.out.println(genre.text());
			    	 System.out.println(grade.text());
			    	 System.out.println(score.text());
			    	 System.out.println(actor.text());
			    	 System.out.println(director.text());
			    	 System.out.println(story.text());
			    	 //System.out.println(youtubeGetKey(title.text()));
			    	 System.out.println("================================================");
			    	
			    	 MovieVO vo=new MovieVO();
			    	 vo.setCateno(5);
			    	 //vo.setNo(k);
			    	 vo.setTitle(title.text());
			    	 vo.setPoster(poster.attr("src"));
			    	 vo.setRegdate(regdate.text());
			    	 vo.setGenre(genre.text());
			    	 vo.setGrade(grade.text());
			    	 vo.setScore(score.text());
			    	 vo.setActor(actor.text());
			    	 vo.setDirector(director.text());
			    	 vo.setStory(story.text());
			    	 vo.setKey(youtubeGetKey(title.text()));
			    	 
			    	 dao.movieInsert(vo);
			    	 k++;
			    	}catch(Exception ex){ex.printStackTrace();}
			     }
			    
			 }
			
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
	}
    public static String youtubeGetKey(String title){
    	String key="";
    	try
    	{
    		Document doc=Jsoup.connect("https://www.youtube.com/results?search_query="+title).get();
    		Pattern p=Pattern.compile("/watch\\?v=[^°¡-ÆR]+");
    		Matcher m=p.matcher(doc.toString());
    		while(m.find())
    		{
    			String s=m.group();
    			key=s.substring(s.indexOf("=")+1,s.indexOf("\""));
    			break;
    		}
    		
    	}catch(Exception ex){}
    	return key;
    }
}






















