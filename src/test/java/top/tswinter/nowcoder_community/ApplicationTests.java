package top.tswinter.nowcoder_community;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
@Slf4j
class ApplicationTests implements ApplicationContextAware {

	private ApplicationContext ioc;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ioc = applicationContext;
	}

	@Test
	void contextLoads() {
		log.info("我的ioc: {}",ioc );
	}
}
