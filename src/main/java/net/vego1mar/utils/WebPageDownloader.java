package net.vego1mar.utils;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

public final class WebPageDownloader {

    private final static Logger log = Logger.getLogger(WebPageDownloader.class);

    private WebPageDownloader() {
        // This should be a utility class.
    }

    public static String getHtml(String url) {
        String content = "";

        try {
            content = Jsoup.connect(url).get().html();
        }
        catch (IOException exp) {
            log.error(exp.getMessage());
        }

        return content;
    }
}
