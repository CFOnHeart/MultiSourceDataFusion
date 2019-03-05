package com.iip.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class DistanceUtil {
 
	public static void main(String args[])
	{
	   String add1 = "清华大学";
	   String add2 = "宁波市北仑中学";
//	   String add = "ƽ�������򱱼ִ�����·61��,�����к����������,�̺����Ļ�·���Σ���Դ����Ժ���ڣ�";
	   DistanceUtil gb = new DistanceUtil();
	   double[] loc1 = gb.getLatitude(add1);
	   double[] loc2 = gb.getLatitude(add2);
	   System.out.println(loc1[0] + "   " + loc1[1]);
	   System.out.println(loc2[0] + "   " + loc2[1]);
	   System.out.println(gb.calculateDistance(add1,add2));
	   //System.out.println(gb.caldists(add));
	}

	public static String AK= "VHkC7hyH3sLPGh8eeKW0ly7kkPX8xhtX";

	public static double[] getLatitude(String address)
	{
		double[] result = new double[2];
		try
		{
			address = URLEncoder.encode(address, "UTF-8");
			URL resjson = new URL("http://api.map.baidu.com/geocoder/v2/?address="
                          + address +"&output=json&ak="+ AK);
			BufferedReader in = new BufferedReader(new InputStreamReader(resjson.openStream()));
			String res;
			StringBuilder sb = new StringBuilder("");
			while((res = in.readLine())!=null)
				sb.append(res.trim());
			in.close();

			String str = sb.toString();
			System.out.println(str);
			if(str != null)
			{
				int lngStart = str.indexOf("lng\":");
				int lngEnd = str.indexOf(",\"lat");
				int latEnd = str.indexOf("},\"precise");
				if(lngStart > 0 && lngEnd > 0 && latEnd > 0)
				{
					String lng = str.substring(lngStart+5, lngEnd);
					String lat = str.substring(lngEnd+7, latEnd);
					result[0] = Double.parseDouble(lng);
					result[1] = Double.parseDouble(lat);
					return result;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static double caldists(String addresses){
		String[] address = addresses.split(",");
		Set<String> adrSet = new HashSet<String>();
		for(String s : address)
			adrSet.add(s);
		int size = adrSet.size();
		List<String> adrs = new ArrayList<String>();
			for(String s : adrSet)
				adrs.add(s);
		if(size == 1)
			return 0.0;
		else
		{
			double dis = 0;
			for(int i = 0;i < adrs.size();i++)
				for(int j = i + 1;j < adrs.size();j++)
					dis += calculateDistance(adrs.get(i),adrs.get(j));
			return dis / (size * (size - 1) / 2);
		}
	}

	// 计算的是距离的公里数
	public static double calculateDistance(String address1, String address2)
	{
		double[] loc1 = getLatitude(address1);
		double[] loc2 = getLatitude(address2);
//		double Lat1 = rad(loc1[1]);
//		double Lat2 = rad(loc2[1]);
//		double a = Lat1 - Lat2;
//		double b = rad(loc1[0]) - rad(loc2[0]);
//		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b/2),2)));
//		s = s * 6378137.0;
//		s = Math.round(s * 10000d) / 10000d;
//		return s;
		return calculateDistance(loc1[0], loc1[1], loc2[0], loc2[1]);
	}

	// 计算的是距离的公里数
	public static double calculateDistance(double lng1, double lat1, double lng2, double lat2){
		double Lat1 = rad(lat1) , Lat2 = rad(lat2);
		double a = Lat1 - Lat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b/2),2)));
		s = s * 6378137.0;
		s = Math.round(s * 10000d) / 10000d;
		return s / 1000;
	}

	private static double rad(double d)
	{
        return d * Math.PI / 180.00;
	}
	
}
