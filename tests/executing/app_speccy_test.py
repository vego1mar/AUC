import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import SetWorkspace
from collector.triggers import FetchAttribute
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space
from collector.helpers import get_entry_for_dobreprogramy_pl

configure_logging(r"../test_log.txt")
logging.debug("Tests for: Speccy")


class SpeccyTestData:
    APP_NAME = "Speccy"
    WEB_SPACE_URL_1 = "https://www.ccleaner.com/speccy/download"
    WEB_SPACE_URL_2 = "https://www.ccleaner.com/speccy/download/standard"
    WEB_SPACE_URL_3 = "https://www.dobreprogramy.pl/Speccy,Program,Windows,15677.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/speccy_web_Windows2.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/speccy_web_Windows3.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.expected_win_exe = decode_base64(b'aHR0cHM6Ly9kb3dubG9hZC5jY2xlYW5lci5jb20vc3BzZXR1cDEzMi5leGU=')
        self.expected_win_ver = decode_base64(b'MS4zMi43NDAg')
        self.expected_win_date = decode_base64(b'MjAxOC0wNS0yMQ==')
        self.expected_win_size = decode_base64(b'Niw1NyBNQg==')


def get_entry_1():
    app_website = SpeccyTestData.WEB_SPACE_URL_1
    req_01 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(app_website))
    chain_request = (req_01,)
    return ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    req_01 = InvocationRequest(Target(SpaceName.WEB, True, "win_exe"), FetchAttribute("data-download-url"))
    chain_request = (req_01,)
    return ExecutionOrderEntry(chain_request, get_web_space(SpeccyTestData.WEB_SPACE_HTML_PATH_2))


def get_entry_3():
    web_space = get_web_space(SpeccyTestData.WEB_SPACE_HTML_PATH_3)
    return get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (0, -1))


class SpeccyTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = SpeccyTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

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
