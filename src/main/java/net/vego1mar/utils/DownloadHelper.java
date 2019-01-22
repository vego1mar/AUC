package net.vego1mar.utils;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

public final class DownloadHelper {

    private static final Logger log = Logger.getLogger(DownloadHelper.class);

    private DownloadHelper() {
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
