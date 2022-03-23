package me.tipsuy.tipsuy_rss_feed.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author DanielB20
 * @since 20-Mar-22
 */
@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "CHANNEL_LAST_VIDEO")
public class ChannelLastVideo implements Serializable {

   @EmbeddedId
   private ChannelLastVideoPK channelLastVideoPK;

   @Basic
   @Column(name = "VIDEO_DATETIME")
   private OffsetDateTime videoDateTime;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(insertable = false, updatable = false, referencedColumnName = "CHANNEL_ID", name = "CHANNEL_ID")
   @ToString.Exclude
   private Channel channel;

   @PrePersist
   void onCreate() {
      videoDateTime = OffsetDateTime.now(ZoneId.systemDefault());
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
         return false;
      }
      ChannelLastVideo that = (ChannelLastVideo) o;
      return channelLastVideoPK != null && Objects.equals(channelLastVideoPK, that.channelLastVideoPK);
   }

   @Override
   public int hashCode() {
      return Objects.hash(channelLastVideoPK);
   }
}
