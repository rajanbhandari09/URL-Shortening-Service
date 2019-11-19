package com.urlshorteningservice.bo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class IdGeneratorImpl implements IdGenerator{
	private static int[] digitsMap = new int[62];
	private static int BASE = 62;
	static{
		//adding lowercase alphabets
		for(int i=0;i<26;++i)
		digitsMap[i] = i+97;
		//adding uppercase alphabets
		for(int i=0;i<26;++i)
		digitsMap[i+26] = i+65;
		//adding digits 0-9
		for(int i=0;i<10;++i)
			digitsMap[i+52] = i;
	}
	
	@Override
	public String calculateShortId(Integer uniqueId) {
		int id = uniqueId;
		List<Integer> shortIdDigits = new ArrayList<>();
		while(id > 0){
			int digit = id % BASE;
			shortIdDigits.add(digit);
			id = id / BASE;
		}
		StringBuffer sb = new StringBuffer();
		for(int i= shortIdDigits.size()-1;i>=0;--i){
			int digit = shortIdDigits.get(i);
			if(digit >=0 && digit <=51)
				sb.append((char)digitsMap[digit]);
			else
				sb.append(digitsMap[digit]);
		}
		return sb.toString();
	}

	@Override
	public Integer calculateUniqueId(String shortId) {
		List<Integer> digits = new LinkedList<>();
		for(int k=shortId.length()-1;k>=0;--k){
			for(int i=0;i<digitsMap.length;++i)
				if(digitsMap[i] == shortId.charAt(k))
					digits.add(i);
		}
		int pow = 0;
		int intId = 0;
		for(int digit: digits){
			intId += digit*Math.pow(BASE, pow);
			++pow;
		}
		return intId;
	}

}
