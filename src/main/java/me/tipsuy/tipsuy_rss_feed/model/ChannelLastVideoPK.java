package me.tipsuy.tipsuy_rss_feed.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

/**
 * @author DanielB20
 * @since 20-Mar-22
 */
@Embeddable
@Data
public class ChannelLastVideoPK implements Serializable {

   @Basic
   @Column(name = "CHANNEL_ID")
   private String channelId;

   @Basic
   @Column(name = "VIDEO_LINK")
   private String videoLink;
}
