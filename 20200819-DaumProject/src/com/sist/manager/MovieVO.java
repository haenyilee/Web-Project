package com.sist.manager;
/*
 * 1. 데이터베이스 => 테이블 제작
 * 2. DML 연습 => SLECT ,INSERT , UPDATE , DELETE
 * 3. 자바에서 제어 (SQL => 오라클 전송)
 * 4. 화면 출력 (HTML , CSS)
 * 5. JavaScript
 * 
 */

public class MovieVO {
   private int cateno;
   private int no;
   private String title;
   private String poster;
   private String regdate;
   private String genre;
   private String grade;
   private String actor;
   private String score;
   private String director;
   private String story;
   private String key;
public int getCateno() {
	return cateno;
}
public void setCateno(int cateno) {
	this.cateno = cateno;
}
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getPoster() {
	return poster;
}
public void setPoster(String poster) {
	this.poster = poster;
}
public String getRegdate() {
	return regdate;
}
public void setRegdate(String regdate) {
	this.regdate = regdate;
}
public String getGenre() {
	return genre;
}
public void setGenre(String genre) {
	this.genre = genre;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public String getActor() {
	return actor;
}
public void setActor(String actor) {
	this.actor = actor;
}
public String getScore() {
	return score;
}
public void setScore(String score) {
	this.score = score;
}
public String getDirector() {
	return director;
}
public void setDirector(String director) {
	this.director = director;
}
public String getStory() {
	return story;
}
public void setStory(String story) {
	this.story = story;
}
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
   
   
}
