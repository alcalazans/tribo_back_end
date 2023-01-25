package com.tribo.util;

import com.tribo.util.support.MessageSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class MessageReplaces {

	@Autowired
	private MessageSupport messages;

	public MessageReplacement replacing(String key) {
		return new MessageReplacement(key);
	}

	public MessageReplacement replacing(String key, String... args) {
		return new MessageReplacement(key, args);
	}
	
	public MessageSupport wrapped() {
		return this.messages;
	}

	@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
	@AllArgsConstructor(access=AccessLevel.PRIVATE)
	public class MessageReplacement{

		private String key;

		private Map<String, String> replaceMap = new HashMap<>();

		private String[] data;

		public MessageReplacement(String key, String... data) {
			this.key = key;
			this.data = data;
		}
		
		public MessageReplacement(String key) {
			this(key, new String[0]);
		}

		public MessageReplacement max(int value) {
			this.replaceMap.put("\\{max\\}", String.valueOf(value));
			return this;
		}
		
		public MessageReplacement value(String value) {
			this.replaceMap.put("\\{value\\}", String.valueOf(value));
			return this;
		}
		
		public MessageReplacement value(int value) {
			this.replaceMap.put("\\{value\\}", String.valueOf(value));
			return this;
		}
		
		public MessageReplacement value(long value) {
			this.replaceMap.put("\\{value\\}", String.valueOf(value));
			return this;
		}

		public MessageReplacement min(int value) {
			this.replaceMap.put("\\{min\\}", String.valueOf(value));
			return this;
		}

		public MessageReplacement maxSize(int value) {
			this.replaceMap.put("\\{maxSize\\}", String.valueOf(value));
			return this;
		}
		
		public MessageReplacement extension(String extension) {
			this.replaceMap.put("\\{extension\\}", extension);
			return this;
		}

		public MessageReplacement fileName(String fileName) {
			this.replaceMap.put("\\{fileName\\}", fileName);
			return this;
		}
		
		public MessageReplacement maxFiles(int maxFiles) {
			this.replaceMap.put("\\{maxFiles\\}", String.valueOf(maxFiles));
			return this;
		}
		
		public MessageReplacement field(String value) {
			this.replaceMap.put("\\{fieldName\\}", value);
			return this;
		}

		public String get() {

			String str = MessageReplaces.this.messages.get(this.key, this.data);

			for(Map.Entry<String,String> entry:this.replaceMap.entrySet()) {

				str = str.replaceAll(entry.getKey(), entry.getValue());

			}

			return str;

		}

	}

}
