/**
 * 
 */
package com.ruoyi.author;

import it.unisa.bsic.bean.Author;
import it.unisa.bsic.versioning.CodeVersioningSystemRepository;

import java.util.List;



/**
 * @author Tufano Michele - tufanomichele89@gmail.com
 *
 * GitDM - Git Data Mining
 */
public interface AuthorRepository {

	/**
	 * Initialize the repository of authors reading a specific Code Versioning System Repository
	 * @param repository a Code Versioning System Repository
	 */
	public void init(CodeVersioningSystemRepository repository);
	
	/**
	 * Returns the list of all authors in the repository
	 * @return List of all authors
	 */
	public List<Author> getAuthors();
	
	/**
	 * Returns the author by name
	 * @param name the name of author to find
	 * @return Author with the specified name or null if it does not exist in the repository
	 */
	public Author getAuthorByName(String name);
	
	/**
	 * Returns the author by email
	 * @param email the email of author to find
	 * @return Author with the specified email or null if it does not exist in the repository
	 */
	public Author getAuthorByEmail(String email);
}
