package me.tipsuy.tipsuy_rss_feed.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import me.tipsuy.tipsuy_rss_feed.model.ChannelLastVideo;
import me.tipsuy.tipsuy_rss_feed.model.ChannelLastVideoPK;

/**
 * @author DanielB20
 * @since 20-Mar-22
 */
@Repository
public interface ChannelLastVideoRepository extends PagingAndSortingRepository<ChannelLastVideo, ChannelLastVideoPK> {

}
