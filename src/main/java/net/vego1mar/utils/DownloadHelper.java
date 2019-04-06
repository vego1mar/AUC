package net.vego1mar.utils;

import java.io.IOException;
import javax.net.ssl.SSLHandshakeException;
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
        } catch (SSLHandshakeException exp) {
            log.debug("Trying to dismiss the SSL certificate requirement; URL=" + url);
            content = getHtmlWithoutHandshake(url);
        } catch (IOException exp) {
            log.error(exp.getMessage());
        }

        return content;
    }

    private static String getHtmlWithoutHandshake(String url) {
        try {
            return Jsoup.connect(url).validateTLSCertificates(false).get().html();
        } catch (IOException exp) {
            log.error(exp.getMessage());
        }

        return "";
    }
}
