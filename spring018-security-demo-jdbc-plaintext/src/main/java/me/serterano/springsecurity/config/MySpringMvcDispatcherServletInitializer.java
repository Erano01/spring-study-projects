package me.serterano.springsecurity.config;

import org.springframework.security.access.SecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {DemoSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//our servlet config config class
		return new Class[] {DemoAppConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		//servlet mapping. xmlda ki url-pattern kısmındaki işaret
		return new String[] { "/" };
	}

}
