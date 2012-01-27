/**
 * 
 */
package com.monstersoftwarellc.springneo4jtemplate.dao;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.monstersoftwarellc.springneo4jtemplate.model.Account;


/**
 * @author nicholas
 *
 */
public interface AccountRepository extends GraphRepository<Account> {

}
