import unittest
import logging
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr

hp.configure_logging(r"../test_log.txt")
logging.debug("Tests for: Speccy")


class SpeccyTestData:
    APP_NAME = "Speccy"
    WEB_SPACE_URL_1 = "https://www.ccleaner.com/speccy/download"
    WEB_SPACE_URL_2 = "https://www.ccleaner.com/speccy/download/standard"
    WEB_SPACE_URL_3 = "https://www.dobreprogramy.pl/Speccy,Program,Windows,15677.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/speccy_web_Windows2.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/speccy_web_Windows3.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.expected_win_exe = hp.decode_base64(b'aHR0cHM6Ly9kb3dubG9hZC5jY2xlYW5lci5jb20vc3BzZXR1cDEzMi5leGU=')
        self.expected_win_ver = hp.decode_base64(b'MS4zMi43NDAg')
        self.expected_win_date = hp.decode_base64(b'MjAxOC0wNS0yMQ==')
        self.expected_win_size = hp.decode_base64(b'Niw1NyBNQg==')


def get_entry_1():
    app_website = SpeccyTestData.WEB_SPACE_URL_1
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    chain_request = (req_01,)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB, True, "win_exe"), tr.FetchAttribute("data-download-url"))
    chain_request = (req_01,)
    return ex.ExecutionOrderEntry(chain_request, hp.get_web_space(SpeccyTestData.WEB_SPACE_HTML_PATH_2))


def get_entry_3():
    web_space = hp.get_web_space(SpeccyTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (0, -1))


class SpeccyTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = SpeccyTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_exe, collector.get_collectibles()['win_exe'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])


if __name__ == '__main__':
    unittest.main()
