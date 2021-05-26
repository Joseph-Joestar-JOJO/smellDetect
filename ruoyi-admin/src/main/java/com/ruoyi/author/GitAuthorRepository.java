/**
 * 
 */
package com.ruoyi.author;



import it.unisa.bsic.author.AuthorRepository;
import it.unisa.bsic.bean.Author;
import it.unisa.bsic.bean.Commit;
import it.unisa.bsic.versioning.CodeVersioningSystemRepository;
import it.unisa.bsic.versioning.GitRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tufano Michele - tufanomichele89@gmail.com
 *
 * GitDM - Git Data Mining
 */
public class GitAuthorRepository implements AuthorRepository, java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5477271051898976097L;
	List<Author> authors;


	public void init(CodeVersioningSystemRepository repository) {
		authors = new ArrayList<Author>();
		GitRepository gitRepository = (GitRepository) repository;
		List<Commit> commits = gitRepository.getCommits();
		for(Commit c: commits){
			Author authorInRepository = findAuthor(c.getAuthor());
			if(authorInRepository==null){
				authors.add(c.getAuthor());
			}else{
				c.setAuthor(authorInRepository);
			}
		}
	}


	public List<Author> getAuthors() {
		return authors;
	}


	public Author getAuthorByName(String name) {
		for(Author a: authors){
			if(a.getName().equalsIgnoreCase(name)){
				return a;
			}
		}
		return null;
	}


	public Author getAuthorByEmail(String email) {
		for(Author a: authors){
			if(a.getEmail().equalsIgnoreCase(email)){
				return a;
			}
		}
		return null;
	}

	public Author findAuthor(Author author){
		for(Author a: authors){
			if(author.getEmail().equalsIgnoreCase(a.getEmail())){
				return a;
			}
		}
		return null;
	}
}
