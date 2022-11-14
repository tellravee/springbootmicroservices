package com.eazybytes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigurationProperties(prefix="cards")
@Configuration
@Getter @Setter @ToString
public class CardsConfig {
	@Value(value="cards.msg")
	private String msg;
	@Override
	public String toString() {
		return "CardsConfig [msg=" + msg + ", buildVersion=" + buildVersion + ", mailDetails=" + mailDetails
				+ ", activeBranches=" + activeBranches + "]";
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getBuildVersion() {
		return buildVersion;
	}
	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}
	public Map<String, String> getMailDetails() {
		return mailDetails;
	}
	public void setMailDetails(Map<String, String> mailDetails) {
		this.mailDetails = mailDetails;
	}
	public List<String> getActiveBranches() {
		return activeBranches;
	}
	public void setActiveBranches(List<String> activeBranches) {
		this.activeBranches = activeBranches;
	}
	@Value(value = "cards.build-version")
	private String buildVersion;
	//@Value(value="accounts.mailDetails.*")
	private Map<String,String> mailDetails;
	//@Value(value="accounts.activeBranches")
	private List<String> activeBranches;
	

}
