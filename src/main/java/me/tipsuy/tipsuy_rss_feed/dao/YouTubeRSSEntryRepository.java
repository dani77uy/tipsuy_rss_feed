package me.tipsuy.tipsuy_rss_feed.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import me.tipsuy.tipsuy_rss_feed.model.YouTubeRSSEntry;

/**
 * @author DanielB20
 * @since 20-Mar-22
 */
@Repository
public interface YouTubeRSSEntryRepository extends PagingAndSortingRepository<YouTubeRSSEntry, Long> {

}
