package com.urlshorteningservice.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShorteningServiceImpl implements UrlShorteningService {
	private static final String BASE_DOMAIN = "http://localhost:8090/urlshortsvc/rest/";
	private Map<Integer, Url> uniqueIdUrlMap;
	private FileHandler fileHandler;
	private IdGenerator idGenerator;

	@Autowired
	public UrlShorteningServiceImpl(FileHandler fileHandler, IdGenerator idGenerator) {
		this.fileHandler = fileHandler;
		this.idGenerator = idGenerator;
		initialize();
	}

	private void initialize() {
		uniqueIdUrlMap = new HashMap<>();
		List<String> lines = fileHandler.initialize();
		if (!lines.isEmpty()) {
			for (String line : lines) {
				String[] fields = line.split(",");
				uniqueIdUrlMap.put(Integer.valueOf(fields[0]), new Url(fields[1], Integer.valueOf(fields[2])));
			}
		}
	}

	@Override
	public String shortenUrl(String url) {
		Integer uniqueId = getUniqueId(url);
		if (uniqueId == null) {
			int id = fileHandler.getLastId();
			uniqueId = id + 1;
			Url longUrl = new Url(url, 0);
			fileHandler.add(uniqueId, longUrl);
			uniqueIdUrlMap.put(uniqueId, longUrl);
		}
		String shortUrl = BASE_DOMAIN + idGenerator.calculateShortId(uniqueId);
		return shortUrl;
	}

	@Override
	public String getRedirectUrl(String url) {
		String shortId = getStringIdFromUrl(url);
		int uniqueId = idGenerator.calculateUniqueId(shortId);
		if (uniqueIdUrlMap.containsKey(uniqueId))
			if (!uniqueIdUrlMap.get(uniqueId).isThresholdReached()) {
				uniqueIdUrlMap.get(uniqueId).incrementHitCount();
				fileHandler.update(uniqueId, uniqueIdUrlMap.get(uniqueId), uniqueIdUrlMap.entrySet());
				return uniqueIdUrlMap.get(uniqueId).getUrl();
			}
		return null;
	}

	private Integer getUniqueId(final String url) {
		// Iterate through Map values and return unique id if the long url was already processed.
		// Time complexity O(n)
		Optional<Map.Entry<Integer, Url>> uniqueIdExists = uniqueIdUrlMap.entrySet().stream()
				.filter(e -> e.getValue().getUrl().equals(url) && !e.getValue().isThresholdReached()).findFirst();
		Integer uniqueId = uniqueIdExists.isPresent() ? uniqueIdExists.get().getKey() : null;
		return uniqueId;
	}

	private String getStringIdFromUrl(String url) {
		return url.substring(BASE_DOMAIN.length());
	}

	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}

	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

}
