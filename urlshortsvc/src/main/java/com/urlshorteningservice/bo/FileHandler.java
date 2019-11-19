package com.urlshorteningservice.bo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FileHandler {
	private static String FILE_PATH = "/Users/rajan/eclipse-web-workspace/id_url_file.txt";
	private static int DEFAULT_ID = 0;
	private static int ID_INDEX = 0;
	private static String FIELD_SEPARATOR = ",";
	private static String LINE_SEPARATOR = "\n";
	int lastId;

	private int getId(String lastLine) {
		String[] values = lastLine.split(FIELD_SEPARATOR);
		return Integer.valueOf(values[ID_INDEX]);
	}

	public int getLastId() {
		return lastId;
	}

	public void add(int newId, Url longUrl) {
		String line = newId + FIELD_SEPARATOR + longUrl.getUrl() + FIELD_SEPARATOR + longUrl.getHitCount() + LINE_SEPARATOR;
		byte[] bytes = line.getBytes();
		try{
			File file = new File(FILE_PATH);
			System.out.println(file.toURI());
			Files.write(Paths.get(file.toURI()), bytes, StandardOpenOption.APPEND);
			lastId = newId;
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public List<String> initialize() {
		List<String> lines = new ArrayList<>();
		try{
			File file = new File(FILE_PATH);
			lines = (file.length() != 0)? Files.lines(Paths.get(file.toURI())).collect(Collectors.toList()) : lines;
			lastId = (!lines.isEmpty()) ? getId(lines.get(lines.size()-1)) : DEFAULT_ID;
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return lines;
	}

	public void update(int uniqueId, Url url, Set<Entry<Integer, Url>> idUrlEntrySet) {
		List<Entry<Integer,Url>> idUrlList = new ArrayList<>(idUrlEntrySet);
		Collections.sort(idUrlList, (e1,e2)-> e1.getKey().compareTo(e2.getKey()));
		StringBuffer sb = new StringBuffer();
		File file = new File(FILE_PATH);
		try {
			String emptyString = "";
			Files.write(Paths.get(file.toURI()), emptyString.getBytes());
			for(Entry<Integer,Url> entry: idUrlList) {
				sb.append(entry.getKey());
				sb.append(FIELD_SEPARATOR);
				sb.append(entry.getValue().getUrl());
				sb.append(FIELD_SEPARATOR);
				sb.append(entry.getValue().getHitCount());		
				sb.append(LINE_SEPARATOR);
				Files.write(Paths.get(file.toURI()), sb.toString().getBytes(), StandardOpenOption.APPEND);
				sb.delete(0,sb.length());
			}
		}catch(IOException e) {
			e.printStackTrace();	
		}
	}


}

