package com.myblog.endpoint;

import org.springframework.hateoas.Resource;

import com.myblog.model.BlogPost;

public class BlogPostResource extends Resource<BlogPost> {

	public BlogPostResource(BlogPost blogPost) {
		super(blogPost);
	}

}
