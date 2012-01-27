/**
 * 
 */
package com.monstersoftwarellc.springneo4jtemplate.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;


/**
 * @author Nick(Work)
 *
 */
@NodeEntity
public class AccountLogin {

	@GraphId
	private Long id;
	@Indexed(indexName="search")
	@NotBlank(message="{NotEmpty.account.username}")
	private String username;
	@NotBlank(message="{NotEmpty.account.password}")
    @Size(min=8, max=25, message="{Size.account.password}")
	private String password;
	@NotBlank(message="{NotEmpty.account.password}")
    @Size(min=8, max=25, message="{Size.account.password}")
	private String passwordVerify;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoggedIn;
	private String lastLoggedInLocation;
	private int numberOfFailedLoginAttempts;
	
	
	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passwordVerify
	 */
	public String getPasswordVerify() {
		return passwordVerify;
	}

	/**
	 * @param passwordVerify the passwordVerify to set
	 */
	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}

	/**
	 * @return the lastLoggedIn
	 */
	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}

	/**
	 * @param lastLoggedIn the lastLoggedIn to set
	 */
	public void setLastLoggedIn(Date lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	/**
	 * @return the lastLoggedInLocation
	 */
	public String getLastLoggedInLocation() {
		return lastLoggedInLocation;
	}

	/**
	 * @param lastLoggedInLocation the lastLoggedInLocation to set
	 */
	public void setLastLoggedInLocation(String lastLoggedInLocation) {
		this.lastLoggedInLocation = lastLoggedInLocation;
	}

	/**
	 * @return the numberOfFailedLoginAttempts
	 */
	public int getNumberOfFailedLoginAttempts() {
		return numberOfFailedLoginAttempts;
	}

	/**
	 * @param numberOfFailedLoginAttempts the numberOfFailedLoginAttempts to set
	 */
	public void setNumberOfFailedLoginAttempts(int numberOfFailedLoginAttempts) {
		this.numberOfFailedLoginAttempts = numberOfFailedLoginAttempts;
	}
}
