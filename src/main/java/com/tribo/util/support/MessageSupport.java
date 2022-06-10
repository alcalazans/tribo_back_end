package com.tribo.util.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Locale;

@Service
public class MessageSupport implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Autowired
    private transient MessageSource source;
    
    private final Locale defaultLocale = new Locale("pt","BR");
    
    public String get(String key, String... args) {
    	return this.source.getMessage(key, args, this.defaultLocale);
    }
    
    public String get(String key) {
    	return this.get(this.normalize(key), new String[0]);
    }
    
    private String normalize(String key) {
    	return key.replace("\\{", "").replace("\\}", "");
    }
    
    public MessageSource getSource() {
		return this.source;
	}

}