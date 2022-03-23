package me.tipsuy.tipsuy_rss_feed.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CHANNEL")
public class Channel implements Serializable {

   @Id
   @Column(name = "CHANNEL_ID")
   private String channelId;

   @Basic
   @Column(name = "USER_ID", nullable = true, unique = true)
   private String userId;

   @Basic
   @Column(name = "NAME", nullable = false)
   private String name;

   @Basic
   @Column(name = "COUNTRY", nullable = true)
   private String country;

   @Basic
   @Column(name = "DESCRIPTION", nullable = true)
   private String description;

   public String getURL() {
      return "https://www.youtube.com/feeds/videos.xml?channel_id=" + channelId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
         return false;
      }
      Channel channel = (Channel) o;
      return channelId != null && Objects.equals(channelId, channel.channelId);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }
}
