package com.myblog.endpoint;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/", produces = "application/hal+json")
public class BlogRootController {

	@Autowired
	private HateoasPageableHandlerMethodArgumentResolver resolver;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public HttpEntity<ResourceSupport> get() throws Exception {
		ResourceSupport resource = new ResourceSupport();
		
		Link link = linkTo(BlogPostController.class).withRel("posts");
		resource.add(createPaginatedResourceLink(link));
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	private Link createPaginatedResourceLink(Link link) {
		String href = link.getHref();
		UriComponents components = UriComponentsBuilder.fromUriString(href).build();
		TemplateVariables variables = resolver.getPaginationTemplateVariables(null, components);
		return new Link(new UriTemplate(href, variables), link.getRel());
	}
	
}
