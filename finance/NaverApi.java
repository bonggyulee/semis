package com.tspoon.googlefinance.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverApi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		getItem("043220");
//		investingCrawling();
//		dataCrawling();
//		naverFinanceCrawling();
//		naverFinanceItemCrawling("043220");
//		mNaverFinanceItemListCrawling();
//		mNaverFinanceItemCrawling("051900");
//		mNaverFinanceItemCrawling("097520");
//		mNaverFinanceItemCrawling("001530");
//		mNaverFinanceItemCrawling("086900");
//		mNaverFinanceItemCrawling("043220");
		mNaverFinanceItemCrawling("290510");
//		seibroFinanceItemCrawling("");
		
	}
	
	public static void naverFinanceCrawling() {
		String url = "";
		Document doc;
		
		try {
			url = "https://m.stock.naver.com/sise/siseList.nhn?menu=market_sum&sosok=0";
			doc = Jsoup.connect(url).get();
			
//			url = "https://m.stock.naver.com/sise/siseList.nhn?menu=market_sum&sosok=0";
			doc = Jsoup.connect(url).get();
			System.out.println("==============");
			System.out.println(doc);
	        
//			naverFinanceItemCrawling("097520");	//엠씨넥스
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void naverFinanceItemCrawling(String itemCode) {
		String url = "";
		Document doc;
		
		try {
			url = "https://finance.naver.com/item/main.nhn?code=" + itemCode;
			doc = Jsoup.connect(url).get();
			Element elm = doc.getElementById("middle");
			System.out.println(elm);
			
//			System.out.println(elm.getElementsByClass("blind"));
//			System.out.println(elm.getElementsByClass("today"));
		     
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void investingCrawling() {
		String url = "";
		Document doc;
		
		try {
			url = "https://kr.investing.com/equities/ssangyong-cement-dividends";
			doc = Jsoup.connect(url).get();
			
			Element elm = doc.getElementById("dividendsHistoryData43392");
			
			System.out.println("==============");
			System.out.println(elm);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void dataCrawling() {
		String url = "";
		Document doc;
		
		try {
			url = "https://view.asiae.co.kr/article/2020042313585362113";
			doc = Jsoup.connect(url).get();
		
			Elements elm = doc.getElementsByClass("article_view");// getElementById("fb-root");
			
			System.out.println("==============");
			System.out.println(elm.html());
			
			Elements paragraphs = doc.getElementsByTag("p");
			
	        for (Element paragraph : paragraphs) {
	        	String cont = paragraph.text();
//	        	System.out.println(cont);
	        	
	        	int maxLength = 90;
				int textLen = cont.length();
				int loopCnt = textLen / maxLength + 1;

				for (int i = 0; i < loopCnt; i++) {
					int lastIndex = (i + 1) * maxLength;

					if (textLen > lastIndex) {
						System.out.println( cont.substring(i * maxLength, lastIndex) );
					} else {
						System.out.println( cont.substring(i * maxLength) );
					}
				}
	        }
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getItem(String code) {
		HttpURLConnection connection = null;
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		URL serverAddress = null;
		String url = "";
		
		try {
			serverAddress = new URL("https://finance.naver.com/item/main.nhn?code=" + code);
			connection = (HttpURLConnection) serverAddress.openConnection();

			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setReadTimeout(5000);
			connection.connect();

//			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "utf-8");
			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "euc-kr");
			int data = inputStreamReader.read();
			String sym_realtime_info = "";
			while (data != -1) {
				sym_realtime_info += (char) data;
				data = inputStreamReader.read();
			}

			String str_j = sym_realtime_info.replace("//", "");

            System.out.println("==============");
			System.out.println(str_j);
			
			
//			id="fb-root"
//			<div id="middle" class="new_totalinfo">
//			<dl class="blind">
//
//			<div id="summary_info" class="summary_info">
//
//			<div class="content_wrap">
//			<dl class="blind">

//			JSONObject jsonObj = new JSONObject(str_j);
//			JSONObject result = (JSONObject) jsonObj.get("result");
//			JSONArray itemList = (JSONArray) result.get("etfItemList");
//			
//			for (int i = 0; i < itemList.length(); i++) {
//				JSONObject item = itemList.getJSONObject(i);
//				
//				if( "122630".equals(item.get("itemcode")) ) {
//					System.out.println(item);
//				}
//			}

			Thread.sleep(100);

			connection.disconnect();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (ProtocolException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void mNaverFinanceItemListCrawling() {
		HttpURLConnection connection = null;
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		URL serverAddress = null;

		try {
			serverAddress = new URL("https://m.stock.naver.com/sise/siseList.nhn?menu=market_sum&sosok=0");
			serverAddress = new URL("https://m.stock.naver.com/api/json/sise/siseListJson.nhn?menu=market_sum&sosok=0");
			serverAddress = new URL("https://m.stock.naver.com/api/json/sise/siseListJson.nhn?menu=market_sum&sosok=0&pageSize=1555");
//			https://m.stock.naver.com/api/json/sise/siseListJson.nhn?menu=market_sum&sosok=0&pageSize=20&page=11
//			serverAddress = new URL("https://m.stock.naver.com/sise/siseList.nhn?menu=market_sum&sosok=0");
			connection = (HttpURLConnection) serverAddress.openConnection();

			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setReadTimeout(5000);
			connection.connect();

			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "utf-8");
			int data = inputStreamReader.read();
			String sym_realtime_info = "";
			while (data != -1) {
				sym_realtime_info += (char) data;
				data = inputStreamReader.read();
			}

			String str_j = sym_realtime_info.replace("//", "");

//		            System.out.println("==============");
//					System.out.println(str_j);

			JSONObject jsonObj = new JSONObject(str_j);
			JSONObject result = (JSONObject) jsonObj.get("result");
			JSONArray itemList = (JSONArray) result.get("itemList");
			
			// mks
			for (int i = 0; i < itemList.length(); i++) {
				JSONObject item = itemList.getJSONObject(i);
				String strItem = item.toString();
//				strItem = strItem.replaceAll("cd", "코드")
//								.replaceAll("pcv", "전일시세")
//								.replaceAll("nv", "시세")
//								.replaceAll("cv", "전일비")
//								.replaceAll("cr", "등락율")
//								.replaceAll("mks", "시가총액")
//								.replaceAll("aq", "거래량");
				System.out.println(strItem);
			}

			Thread.sleep(100);

			connection.disconnect();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (ProtocolException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void mNaverFinanceItemCrawling(String itemCode) {
		String url = "";
		Document doc;
		
		try {
			url = "https://m.stock.naver.com/api/html/item/getOverallInfo.nhn?code=" + itemCode;
			
			doc = Jsoup.connect(url).get();
			System.out.println("==============");
			System.out.println(doc);
//			Element elm = doc.getElementById("middle");
//			System.out.println(elm);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void seibroFinanceItemCrawling(String itemCode) {
		String url = "";
		Document doc;
		
		try {
			url = "http://portalhub.ksd.or.kr/wlcollector?vc=5&cv=KDS20130101_AJAX&hn=iportal.com&pn=ksd.safe.bip.cnts.Company.process.EntrFnafInfoPTask.divStatInfoPList&tt=83";
			url = "https://brunch.co.kr/@katiebomison/41";
			
			doc = Jsoup.connect(url).get();
			System.out.println("==============");
			System.out.println(doc);
//			Element elm = doc.getElementById("middle");
//			System.out.println(elm);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
