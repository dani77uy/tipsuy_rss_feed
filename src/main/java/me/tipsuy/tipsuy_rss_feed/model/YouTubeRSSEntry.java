package me.tipsuy.tipsuy_rss_feed.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author DanielB20
 * @since 19-Mar-22
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Table(name = "YOUTUBE_RSS_ENTRY")
public class YouTubeRSSEntry implements Serializable {

   @Id
   private Long id;

   @Basic
   @Column(name = "CHANNEL_ID")
   private String channelId;

   @Basic
   @Column(name = "CHANNEL_NAME")
   private String channelName;

   @Basic
   @Column(name = "PUBLISHED_DATETIME")
   private OffsetDateTime publishedDateTime;

   @Basic
   @Column(name = "UPDATED_DATETIME")
   private OffsetDateTime updatedDateTime;

   @Basic
   @Column(name = "VIDEO_TITLE")
   private String videoTitle;

   @Basic
   @Column(name = "VIDEO_URL")
   private String videoUrl;

   @Basic
   @Column(name = "WAS_PUBLISHED")
   private boolean wasPublished;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CHANNEL_ID", referencedColumnName = "CHANNEL_ID", insertable = false, updatable = false)
   @ToString.Exclude
   private Channel channel;

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
         return false;
      }
      YouTubeRSSEntry that = (YouTubeRSSEntry) o;
      return id != null && Objects.equals(id, that.id);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }
}
