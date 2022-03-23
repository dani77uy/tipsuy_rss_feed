package me.tipsuy.tipsuy_rss_feed;

import java.net.URL;
import java.util.Optional;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndPersonImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * @author DanielB20
 * @since 19-Mar-22
 */
public class Test {

   public static void main(String[] args) {
      try {
         final var feedSourceTUDNMex = new URL("https://www.youtube.com/feeds/videos.xml?channel_id=UCTIyEyDNHPrwVFPhpi5dm0A");
         final var input = new SyndFeedInput();
         final var entries = input.build(new XmlReader(feedSourceTUDNMex)).getEntries();
         entries.forEach(e -> {
            if (e instanceof SyndEntryImpl) {
               final var obj = (SyndEntryImpl) e;
               System.out.println("[" + obj.getUpdatedDate() + "] " + obj.getTitle() + " - " + obj.getLink());
            }
         });

      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}
