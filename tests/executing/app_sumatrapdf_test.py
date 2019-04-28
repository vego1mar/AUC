import unittest
import logging
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr

hp.configure_logging(r"../test_log.txt")
logging.debug("Tests for: SumatraPDF")


class SumatraPdfTestData:
    APP_NAME = "SumatraPDF"
    WEB_SPACE_URL_1 = "https://www.sumatrapdfreader.org/download-free-pdf-viewer.html"
    WEB_SPACE_URL_2 = "https://www.microsoft.com/en-us/p/sumatra-pdf/9pnjwv0hphzk"
    WEB_SPACE_URL_3 = "https://www.dobreprogramy.pl/Sumatra-PDF,Program,Windows,22954.html"
    WEB_SPACE_URL_4 = "https://www.majorgeeks.com/mg/get/sumatra_pdf,1.html"
    WEB_SPACE_URL_5 = "https://www.majorgeeks.com/mg/get/sumatra_pdf,3.html"
    WEB_SPACE_URL_6 = "https://www.majorgeeks.com/mg/get/sumatra_pdf,2.html"
    WEB_SPACE_URL_7 = "https://www.majorgeeks.com/mg/get/sumatra_pdf,4.html"
    WEB_SPACE_URL_8 = "https://www.majorgeeks.com/mg/getmirror/sumatra_pdf,1.html"
    WEB_SPACE_URL_9 = "https://www.majorgeeks.com/mg/getmirror/sumatra_pdf,2.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/sumatrapdf_web_MSStore.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/sumatrapdf_web_Windows3.base64"
    WEB_SPACE_HTML_PATH_4 = r"../resources/sumatrapdf_web_Windows4.base64"
    WEB_SPACE_HTML_PATH_5 = r"../resources/sumatrapdf_web_Windows5.base64"
    WEB_SPACE_HTML_PATH_6 = r"../resources/sumatrapdf_web_Windows6.base64"
    WEB_SPACE_HTML_PATH_7 = r"../resources/sumatrapdf_web_Windows7.base64"
    WEB_SPACE_HTML_PATH_8 = r"../resources/sumatrapdf_web_Windows8.base64"
    WEB_SPACE_HTML_PATH_9 = r"../resources/sumatrapdf_web_Windows9.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        self.execution_order.add_entry(get_entry_5(), True)
        self.execution_order.add_entry(get_entry_6(), True)
        self.execution_order.add_entry(get_entry_7(), True)
        self.execution_order.add_entry(get_entry_8(), True)
        self.execution_order.add_entry(get_entry_9(), True)
        exe_32 = b'aHR0cDovL2tqa3B1Yi5zMy5hbWF6b25hd3MuY29tL3N1bWF0cmFwZGYvcmVsL1N1bWF0cmFQREYtMy4xLjItaW5zdGFsbC5' \
                 b'leGU='
        exe_64 = b'aHR0cHM6Ly9ramtwdWIuczMuYW1hem9uYXdzLmNvbS9zdW1hdHJhcGRmL3JlbC9TdW1hdHJhUERGLTMuMS4yLTY0LWluc3R' \
                 b'hbGwuZXhl'
        zip_32 = b'aHR0cHM6Ly9ramtwdWIuczMuYW1hem9uYXdzLmNvbS9zdW1hdHJhcGRmL3JlbC9TdW1hdHJhUERGLTMuMS4yLnppcA=='
        zip_64 = b'aHR0cHM6Ly9ramtwdWIuczMuYW1hem9uYXdzLmNvbS9zdW1hdHJhcGRmL3JlbC9TdW1hdHJhUERGLTMuMS4yLTY0LnppcA=='
        exe_1 = hp.decode_base64(b'aHR0cDovL2ZpbGVzMS5tYWpvcmdlZWtzLmNvbS9lNTQxODNlMmEwNDBlNmMwOWU2MWViMjJkNTQyZTNkNTc'
                                 b'wNzRiMzUxL29mZmljZS9TdW1hdHJhUERGLTMuMS4yLWluc3RhbGwuZXhl')
        exe_2 = hp.decode_base64(b'aHR0cDovL2ZpbGVzMS5tYWpvcmdlZWtzLmNvbS9lNTQxODNlMmEwNDBlNmMwOWU2MWViMjJkNTQyZTNkNTc'
                                 b'wNzRiMzUxL29mZmljZS9TdW1hdHJhUERGLTMuMS4yLWluc3RhbGwuZXhl')
        exe_3 = hp.decode_base64(b'aHR0cDovL2ZpbGVzMi5tYWpvcmdlZWtzLmNvbS8wN2RhMGNjOWQ0M2I0OTg3MWMwMWI2ZmRlYWI5NzNhY2E'
                                 b'yM2VjMjQ1L29mZmljZS9TdW1hdHJhUERGLTMuMS4yLWluc3RhbGwuZXhl')
        self.expected_ms_store_size = hp.decode_base64(b'MTUuOSBNQg==')
        self.expected_win_ver = hp.decode_base64(b'My4xLjI=')
        self.expected_win_date = hp.decode_base64(b'MjAxNi0wOC0xNQ==')
        self.expected_win_size = hp.decode_base64(b'NCw2MyBNQg==')
        self.expected_win_exe_32bit = hp.decode_base64(exe_32)
        self.expected_win_exe_64bit = hp.decode_base64(exe_64)
        self.expected_win_zip_32bit = hp.decode_base64(zip_32)
        self.expected_win_zip_64bit = hp.decode_base64(zip_64)
        self.expected_win_exe_tuple = (exe_1, exe_2, exe_3)


def get_entry_1():
    app_website = SumatraPdfTestData.WEB_SPACE_URL_1
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    chain_request = (req_01,)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_2)
    app_url = SumatraPdfTestData.WEB_SPACE_URL_2
    return hp.get_entry_for_ms_store(web_space, app_url, "ms_store_size", "ms_store_link")


def get_entry_3():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (0, -1))


def get_entry_4():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_4)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_32bit")


def get_entry_5():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_5)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_64bit")


def get_entry_6():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_6)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_zip_32bit")


def get_entry_7():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_7)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_zip_64bit")


def get_entry_8():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_8)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_mg1")


def get_entry_9():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_9)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_mg2")


class SumatraPdfTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = SumatraPdfTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_ms_store_size, collector.get_collectibles()['ms_store_size'])
        self.assertEqual(dt.WEB_SPACE_URL_2, collector.get_collectibles()['ms_store_link'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])
        self.assertEqual(dt.expected_win_exe_32bit, collector.get_collectibles()['win_exe_32bit'])
        self.assertEqual(dt.expected_win_exe_64bit, collector.get_collectibles()['win_exe_64bit'])
        self.assertEqual(dt.expected_win_zip_32bit, collector.get_collectibles()['win_zip_32bit'])
        self.assertEqual(dt.expected_win_zip_64bit, collector.get_collectibles()['win_zip_64bit'])
        self.assertIn(collector.get_collectibles()['win_exe_mg1'], dt.expected_win_exe_tuple)
        self.assertIn(collector.get_collectibles()['win_exe_mg2'], dt.expected_win_exe_tuple)


if __name__ == '__main__':
    unittest.main()
