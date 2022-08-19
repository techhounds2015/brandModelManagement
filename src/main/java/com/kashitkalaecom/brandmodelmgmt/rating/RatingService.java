package com.kashitkalaecom.brandmodelmgmt.rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class RatingService {

	@Autowired
	RatingRepository ratingRepository;
	
	public Ratings save(Ratings rating, String requestorId) {
		rating.setCreatedBy(requestorId);
		rating.setCreatedOn(CustomClock.timestamp());
		return ratingRepository.save(rating);
	}
	
	public Ratings getRatingById(String ratingId) {
		return ratingRepository.getById(ratingId);
	}

	public List<Ratings> getAllRatings() {
		return ratingRepository.findAll();
	}

	public Ratings update(Ratings rating, String requestorId) {
		rating.setModifiedBy(requestorId);
		rating.setModifiedOn(CustomClock.timestamp());
		return ratingRepository.save(rating);
	}

	public Ratings delete(String id, String requestorId) {
		Ratings rating = ratingRepository.getById(id);
		rating.setModifiedBy(requestorId);
		rating.setModifiedOn(CustomClock.timestamp());
		return ratingRepository.save(rating);
	}

	public Page<Ratings> getAllRating(int offset, int limit) {
		return ratingRepository.findAll(PageRequest.of(offset, limit, Sort.by("createdOn").descending()));
	}
}
