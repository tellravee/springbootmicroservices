package com.eazybytes;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix="spring")
//@ConfigurationProperties(prefix="accounts")
//@Configuration
public class DBConfiguration {


/*@Override
	public String toString() {
		return "DBConfiguration [dbDetails=" + dataSource + ", jpa=" + jpa + ", h2Details=" + h2Details + "]";
	}
private Map<String,String> dataSource;

public Map<String, String> getDataSource() {
	return dataSource;
}
public void setDataSource(Map<String, String> dataSource) {
	this.dataSource = dataSource;
}
public Map<String, String> getJpa() {
	return jpa;
}
public void setJpa(Map<String, String> jpa) {
	this.jpa = jpa;
}
public Map<String, String> getH2Details() {
	return h2Details;
}
public void setH2Details(Map<String, String> h2Details) {
	this.h2Details = h2Details;
}
private Map<String,String> jpa;
private Map<String,String> h2Details;
* spring.datasource.url=jdbc:h2:file:~/test
	spring.datasource.driverClassName=org.h2.Driver
	spring.datasource.username=sa
	spring.datasource.password=
	spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
	spring.h2.console.enabled=true
	spring.h2.console.settings.web-allow-others=true
	spring.jpa.hibernate.ddl-auto=update
	spring.jpa.show-sql=true
	 * @return
	 */
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Override
	public String toString() {
		return "DBConfiguration [driverClassName=" + driverClassName + ", url=" + url + ", username=" + username
				+ ", password=" + password + ", databasePlatform=" + databasePlatform + ", enabled=" + enabled
				+ ", webAllowOthers=" + webAllowOthers + ", ddlAuto=" + ddlAuto + ", showSql=" + showSql + "]";
	}

	public String getDatabasePlatform() {
		return databasePlatform;
	}

	public void setDatabasePlatform(String databasePlatform) {
		this.databasePlatform = databasePlatform;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isWebAllowOthers() {
		return webAllowOthers;
	}

	public void setWebAllowOthers(boolean webAllowOthers) {
		this.webAllowOthers = webAllowOthers;
	}

	public String getDdlAuto() {
		return ddlAuto;
	}

	public void setDdlAuto(String ddlAuto) {
		this.ddlAuto = ddlAuto;
	}

	public boolean isShowSql() {
		return showSql;
	}

	public void setShowSql(boolean showSql) {
		this.showSql = showSql;
	}

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.jpa.database-platform}")
	private String databasePlatform;
	@Value("${spring.h2.console.enabled}")
	private boolean enabled;
	@Value("${spring.h2.console.settings.web-allow-others}")
	private boolean webAllowOthers;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	@Value("${spring.jpa.show-sql}")
	private boolean showSql;

	

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}