package me.tipsuy.tipsuy_rss_feed.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import lombok.extern.slf4j.Slf4j;

/**
 * @author DanielB20
 * @since 19-Mar-22
 */
@Service
@Slf4j
public class YouTubeFeedService {

   AtomicInteger integer = new AtomicInteger(0);

   private static final String url_btSport = "https://www.youtube.com/feeds/videos.xml?channel_id=UC4i_9WvfPRTuRWEaWyfKuFw";
   private static final String url_tudnMex = "https://www.youtube.com/feeds/videos.xml?channel_id=UCTIyEyDNHPrwVFPhpi5dm0A";
   private static final String url_espnfc = "https://www.youtube.com/feeds/videos.xml?channel_id=UC6c1z7bA__85CIWZ_jpCK-Q";
   
   private static final List<SyndEntryImpl> entries = Lists.newArrayList();

   @Scheduled(cron = "${interval-in-cron}")
   @SchedulerLock(name = "myscheduledTask")
   public void checkNewEntry() throws Throwable {

      final var feedList1 = getFeed(url_btSport).getEntries();
      final var feedList2 = getFeed(url_tudnMex).getEntries();
      final var feedList3 = getFeed(url_espnfc).getEntries();
      feedList1.forEach(e -> entries.add((SyndEntryImpl)e));
      feedList2.forEach(e -> entries.add((SyndEntryImpl)e));
      feedList3.forEach(e -> entries.add((SyndEntryImpl)e));

      final SyndEntryImpl lastESPN = entries.stream().filter(p -> p.getAuthor().equals("ESPN FC")).sorted().findFirst().orElseGet(() -> new ArrayList<>(entries).get(0));
      final SyndEntryImpl lastTUDN = entries.stream().filter(p -> p.getAuthor().contains("TUDN")).sorted().findFirst().orElseGet(() -> new ArrayList<>(entries).get(0));
      final SyndEntryImpl lastBT = entries.stream().filter(p -> p.getAuthor().contains("BT Sport")).sorted().findFirst().orElseGet(() -> new ArrayList<>(entries).get(0));

      System.out.println("LAST VIDEO ESPN FC");
      System.out.println("[" + lastESPN.getPublishedDate() + " ("+lastESPN.getUpdatedDate() + ")] " + lastESPN.getTitle() + " - " + lastESPN.getLink());
      System.out.println("==================================");
      System.out.println("LAST VIDEO TUDN MEXICO");
      System.out.println("[" + lastTUDN.getPublishedDate() + " ("+lastTUDN.getUpdatedDate() + ")] " + lastTUDN.getTitle() + " - " + lastTUDN.getLink());
      System.out.println("==================================");
      System.out.println("LAST VIDEO BT SPORT");
      System.out.println("[" + lastBT.getPublishedDate() + " ("+lastBT.getUpdatedDate() + ")] " + lastBT.getTitle() + " - " + lastBT.getLink());
      System.out.println("==================================");


      Thread.sleep(4000);
   }

   
   private SyndFeed getFeed(final String url) throws IOException, FeedException {
      final var feedSourceTUDNMex = new URL(url);
      final var input = new SyndFeedInput();
      return input.build(new XmlReader(feedSourceTUDNMex));
   }
}
