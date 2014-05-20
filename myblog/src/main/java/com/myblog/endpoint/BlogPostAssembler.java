package com.myblog.endpoint;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.myblog.model.BlogPost;

@Component
public class BlogPostAssembler implements ResourceAssembler<BlogPost, BlogPostResource> {

	@Override
	public BlogPostResource toResource(BlogPost blogPost) {
		BlogPostResource resource = new BlogPostResource(blogPost);
		resource.add(linkTo(methodOn(BlogPostController.class).getPost(blogPost.getId()))
                .withSelfRel());
		return resource;
	}

}
