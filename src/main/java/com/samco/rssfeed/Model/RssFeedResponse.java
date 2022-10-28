package com.samco.rssfeed.Model;

public class RssFeedResponse {
	String header;
	String pubDate;
	String desc;
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "RssFeedResponse [header=" + header + ", pubDate=" + pubDate + ", desc=" + desc + "]";
	}
	
	
}
