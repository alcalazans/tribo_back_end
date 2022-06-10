package com.tribo.config;

import com.tribo.util.MessageReplaces;
import com.tribo.util.support.MessageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.tribo.repository")
@EnableTransactionManagement
@Profile("test")
public class H2JpaConfig {

	@Autowired
	MessageSupport messages;
	
	@Bean
	@Primary
	public MessageReplaces getMessagesReplaces() {
		return new MessageReplaces(this.messages);
	}
	
}