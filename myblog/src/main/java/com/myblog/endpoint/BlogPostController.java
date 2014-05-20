package com.myblog.endpoint;

import java.math.BigInteger;
import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.model.BlogPost;
import com.myblog.repository.BlogPostRepository;

@RestController
@RequestMapping(value = "/posts", produces = "application/hal+json")
public class BlogPostController {

	@Autowired
	BlogPostRepository blogPostRepository;

	@Autowired
	BlogPostAssembler blogPostAssembler;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public HttpEntity<PagedResources<BlogPostResource>> getAll(
			@PageableDefault(size = 20, page = 0) Pageable pageable,
			PagedResourcesAssembler<BlogPost> assembler) throws Exception {
		Page<BlogPost> posts = blogPostRepository.findAll(pageable);
		PagedResources<BlogPostResource> pagedResource = assembler.toResource(
				posts, blogPostAssembler);
		return new ResponseEntity<>(pagedResource, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<BlogPostResource> getPost(@PathVariable("id") BigInteger id) {
		BlogPost blogPost = blogPostRepository.findOne(id);
		return new ResponseEntity<>(blogPostAssembler.toResource(blogPost), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public HttpEntity<Void> createPost(@RequestBody BlogPost blogPost) {
		blogPost.setPublishedTimeStamp(new Date());
		BlogPost savedPost = blogPostRepository.save(blogPost);
		BlogPostResource resource = blogPostAssembler.toResource(savedPost);
		HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));
		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

}
