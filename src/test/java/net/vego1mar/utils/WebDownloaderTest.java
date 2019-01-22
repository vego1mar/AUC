package net.vego1mar.utils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class WebDownloaderTest {

    private final static Logger log = Logger.getLogger(WebDownloaderTest.class);

    @Test
    public void getHtml_online() {
        // given
        final String WEBPAGE_URL = "https://www.7-zip.org/download.html";

        // when
        String content = DownloadHelper.getHtml(WEBPAGE_URL);

        // then
        Assert.assertFalse(content.isEmpty());
    }
}
