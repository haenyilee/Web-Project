package com.sist.recipe;
import java.io.*;
import java.net.URL;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.MovieDAO;
/*
 *  <li class="common_sp_list_li">
                <div class="common_sp_thumb">
                    <a href="/recipe/15002" class="common_sp_link">
                                                <img src="https://recipe1.ezmember.co.kr/cache/recipe/2015/06/05/67fcef63a2c8773a9a81642ab59db79b_m.jpg">
                    </a>
                </div>
                <div class="common_sp_caption">
                    <div class="common_sp_caption_tit line2">귤껍질차</div>
                    <div class="common_sp_caption_rv_name" style="display: inline-block; vertical-align: bottom;">
                        <a href="/profile/recipe.html?uid=baby00"><img src="https://recipe1.ezmember.co.kr/img/df/pf_100_100.png">레몬트리</a>
                    </div>
                    <div class="common_sp_caption_rv">
                                                <span class="common_sp_caption_buyer" style="vertical-align: middle;">조회수 3,874</span>
                    </div>
                </div>
            </li>

 */
public class RecipeMain {
	//RecipeDAO dao=new RecipeDAO();
	public void recipeAllData()
	{
	     MovieDAO dao = new MovieDAO();
	     int k=1;
	     try
	     {
	    	 for(int i=1;i<=3491;i++)
	    	 {
	    		 Document doc=Jsoup.connect("https://www.10000recipe.com/recipe/list.html?order=reco&page="+i).get();
	    		 Elements title=doc.select("div.common_sp_caption div.common_sp_caption_tit");
	    		 Elements poster=doc.select("img[src*=/recipe/]");
	    		 Elements chef=doc.select("div.common_sp_caption div.common_sp_caption_rv_name a");
	    		 Elements link=doc.select("div.common_sp_thumb a");
	    		 
	    		 for(int j=0;j<title.size();j++)
	    		 {
	    			try
	    			{
		    			 RecipeVO vo=new RecipeVO();
		    			 vo.setNo(k);
		    			 vo.setTitle(title.get(j).text());
		    			 vo.setPoster(poster.get(j).attr("src"));
		    			 vo.setChef(chef.get(j).text());
		    			 vo.setLink(link.get(j).attr("href"));
		    			 System.out.println("번호:"+k);
		    			 System.out.println("Title:"+vo.getTitle());
		    			 System.out.println("Chef:"+vo.getChef());
		    			 System.out.println("Poster:"+vo.getPoster());
		    			 System.out.println("Link:"+vo.getLink());
		    			 System.out.println("k="+k);
		    			 dao.recipeInsert(vo);
		    			 //recipeDetailData(vo.getLink(),k);
		    			 Thread.sleep(50);
		    			 k++;
		    			 
	    			}catch(Exception e) {e.printStackTrace();}
	    		 }
	    	 }
	    	 System.out.println("End...");
	     }catch(Exception ex){ex.printStackTrace();}
	    
	}
	public ArrayList<ChefVO> chefAllData()
	{
		ArrayList<ChefVO> list=new ArrayList<ChefVO>();
		MovieDAO dao =new MovieDAO();
		try
		{
			int k=1;
			for(int i=1;i<=25;i++)
			{
				// https://www.10000recipe.com/chef/chef_list.html?order=chef_no_follower&term=all&page=2
				Document doc=Jsoup.connect("http://www.10000recipe.com/chef/chef_list.html?order=chef_no_follower&term=all&page="+i).get();
				Elements poster=doc.select("div.list_mem3 a.mem_pic img");
				Elements chef=doc.select("div.list_cont4 a");
				Elements mem_cont1=doc.select("span.mem_cont1");
				Elements mem_cont3=doc.select("span.mem_cont3");
				Elements mem_cont7=doc.select("span.mem_cont7");
				Elements mem_cont2=doc.select("span.mem_cont2");
				
				for(int j=0;j<poster.size();j++)
				{
					try
					{
						ChefVO vo=new ChefVO();
						vo.setPoster(poster.get(j).attr("src"));
						vo.setChef(chef.get(j).text());
						vo.setMem_cont1(mem_cont1.get(j).text());
						vo.setMem_cont3(mem_cont3.get(j).text());
						vo.setMem_cont7(mem_cont7.get(j).text());
						vo.setMem_cont2(mem_cont2.get(j).text());
						System.out.println("Poster:"+vo.getPoster());
						System.out.println("Chef:"+vo.getChef());
						System.out.println("Mem-cont1:"+vo.getMem_cont1());
						System.out.println("Mem-cont3:"+vo.getMem_cont3());
						System.out.println("Mem-cont7:"+vo.getMem_cont7());
						System.out.println("Mem-cont2:"+vo.getMem_cont2());
						System.out.println("k="+k);
						System.out.println("---------------------------------------------------------");
						dao.chefInsert(vo);
					    k++;
					}catch(Exception ex){}
					Thread.sleep(100);
//					list.add(vo);
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		return list;
	}
	public RecipeDetailVO recipeDetailData(String url,int no)
    {
    	RecipeDetailVO vo=new RecipeDetailVO();
    	// http://www.10000recipe.com/recipe/6907454
    	int k=1;
    	try
    	{
    		Document doc=Jsoup.connect("http://www.10000recipe.com"+url).get();
    		Element poster=doc.selectFirst("div.centeredcrop img");// doc.select("").get(0)
    		/*
    		 *  <div class="view2_summary">
            <h3>초간단, 초스피드, 스팸으로 재미난 밥전 만들기</h3>
    		 */
    		Element title=doc.selectFirst("div.view2_summary h3");
    		Element chef=doc.selectFirst("div.profile_cont p.cont_name");
    		Element chef_poster=doc.selectFirst("div.profile_pic img");
    		Element chef_profile=doc.selectFirst("div.profile_cont p.cont_intro");
    		Element content=doc.selectFirst("div.view2_summary_in");
    		Elements foodmake=doc.select("div.view_step_cont");
    		Element info1=doc.selectFirst("span.view2_summary_info1");
    		Element info2=doc.selectFirst("span.view2_summary_info2");
    		Element info3=doc.selectFirst("span.view2_summary_info3");
    		
    		String food="";
    		for(int i=0;i<foodmake.size();i++)
    		{
    			food+=(i+1)+"."+foodmake.get(i).text()+"\n";
    		}
    		vo.setPoster(poster.attr("src"));
    		vo.setChef(chef.text());
    		vo.setChef_poster(chef_poster.attr("src"));
    		vo.setChef_profile(chef_profile.text());
    		vo.setContent(content.text());
    		vo.setFoodmake(food);
    		vo.setTitle(title.text());
    		vo.setInfo1(info1.text());
    		vo.setInfo2(info2.text());
    		vo.setInfo3(info3.text());
    		vo.setNo(no);
    		
    		//dao.recipeDetailData(vo);
    		System.out.println("제목:"+vo.getTitle());
    		System.out.println("쉐프:"+vo.getChef());
    		System.out.println("내용:"+vo.getContent());
    		System.out.println("조리방법:"+vo.getFoodmake());
    		System.out.println("정보1:"+vo.getInfo1());
    		System.out.println("정보2:"+vo.getInfo2());
    		System.out.println("정보3:"+vo.getInfo3());
    		
    		System.out.println("k="+k);
    		k++;
    	}catch(Exception ex){}
    	return vo;
    }
	public void test() throws Exception
	{
		URL location = this.getClass().getResource("/src");
		String path = location.getPath();
		System.out.println(path);
		String rightPath = path.substring(1, path.lastIndexOf("build"))+"src";
		System.out.println(rightPath);
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		RecipeMain rm=new RecipeMain();
		
		// 값을 넣을 메소드를 하나씩 실행
		// rm.test();
		// rm.recipeAllData();
		rm.chefAllData();
       /*try
       {
    	   File f=new File("c:\\project_data\\recipe.txt");
    	   if(f.exists())
    		   f.delete();
    	   FileOutputStream fos=
    			   new FileOutputStream("c:\\project_data\\recipe.txt");
    	   ObjectOutputStream oos=
    			   new ObjectOutputStream(fos);
    	   
    	   RecipeMain rm=new RecipeMain();
    	   rm.recipeAllData();
    	   oos.writeObject(rm.list);
    	   
    	   oos.close();
    	   
    	   System.out.println("저장 완료");
    	   
       }catch(Exception ex) {ex.printStackTrace();};*/
	}
	
}












