package com.myblog.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myblog.model.BlogPost;

@Repository
public interface BlogPostRepository extends MongoRepository<BlogPost, BigInteger> {
	
}
