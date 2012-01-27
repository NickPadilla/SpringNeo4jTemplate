/**
 * 
 */
package com.monstersoftwarellc.springneo4jtemplate.model;

import javax.validation.Valid;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.monstersoftwarellc.springneo4jtemplate.annotations.FieldMatch;



/**
 * @author Nick(Work)
 *
 */
@NodeEntity
public class Account {
	
	@GraphId
	private Long id;
	@Valid
	@Fetch
	private Person person = new Person();
	@FieldMatch(fieldToMatchOn="passwordVerify", fieldsToMatch="password", message = "{FieldMatch.account.password}")
	@Valid
	@Fetch
	private AccountLogin login = new AccountLogin();

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
	 * @return
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person
	 */
	public void setPerson(Person user) {
		this.person = user;
	}

	/**
	 * @return the login
	 */
	public AccountLogin getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(AccountLogin login) {
		this.login = login;
	}
}
