import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import SetWorkspace
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space
from collector.helpers import fetch_html
from collector.helpers import get_entry_for_dobreprogramy_pl

configure_logging(r"../test_log.txt")
logging.debug("Tests for: Origin")


class OriginTestData:
    APP_NAME = "Origin"
    APP_WEBSITE = "https://www.origin.com/pol/en-us/store/download"
    WEB_SPACE_URL_1 = "https://www.dobreprogramy.pl/Origin,Program,Windows,38298.html"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/Origin,Program,Mac,65868.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/origin_web_Windows.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/origin_web_Mac.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.expected_win_ver = decode_base64(b'MTAuNS4zNg==')
        self.expected_win_date = decode_base64(b'MjAxOS0wMy0xOQ==')
        self.expected_win_size = decode_base64(b'NjAsNDYgTUI=')
        self.expected_mac_ver = decode_base64(b'MTAuNS4zNg==')
        self.expected_mac_date = decode_base64(b'MjAxOS0wMy0yNQ==')
        self.expected_mac_size = decode_base64(b'MTIxLDkyIE1C')


class ConstTestData:
    CONST_WIN_EXE = decode_base64(b'aHR0cHM6Ly93d3cuZG0ub3JpZ2luLmNvbS9kb3dubG9hZC9sZWdhY3k=')
    CONST_MAC_DMG = decode_base64(b'aHR0cHM6Ly93d3cuZG0ub3JpZ2luLmNvbS9tYWMvZG93bmxvYWQvbGVnYWN5')


class TestDataOnline(OriginTestData):
    def __init__(self):
        super().__init__()
        self.execution_order.list[0].html_data = fetch_html(self.WEB_SPACE_URL_1)
        self.execution_order.list[1].html_data = fetch_html(self.WEB_SPACE_URL_2)


def get_entry_1():
    web_space = get_web_space(OriginTestData.WEB_SPACE_HTML_PATH_1)
    return get_entry_for_dobreprogramy_pl(web_space, "Win_ver", "Win_date", "Win_size")


def get_entry_2():
    web_space = get_web_space(OriginTestData.WEB_SPACE_HTML_PATH_2)
    return get_entry_for_dobreprogramy_pl(web_space, "Mac_ver", "Mac_date", "Mac_size")


def get_entry_3():
    req_1 = InvocationRequest(Target(SpaceName.WORK, True, "const_win_exe"), SetWorkspace(ConstTestData.CONST_WIN_EXE))
    req_2 = InvocationRequest(Target(SpaceName.WORK, True, "const_mac_dmg"), SetWorkspace(ConstTestData.CONST_MAC_DMG))
    req_3 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(OriginTestData.APP_WEBSITE))
    chain_request_3 = (req_1, req_2, req_3)
    entry_3 = ExecutionOrderEntry(chain_request_3, str())
    return entry_3


class ElectronicArtsOriginTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = OriginTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_mac_date, collector.get_collectibles()['Mac_date'])
        self.assertEqual(dt.expected_mac_size, collector.get_collectibles()['Mac_size'])
        self.assertEqual(ConstTestData.CONST_WIN_EXE, collector.get_collectibles()['const_win_exe'])
        self.assertEqual(ConstTestData.CONST_MAC_DMG, collector.get_collectibles()['const_mac_dmg'])
        self.assertEqual(OriginTestData.APP_WEBSITE, collector.get_collectibles()['app_website'])

    @unittest.skip("Online test")
    def test_package_collecting_online(self):
        # given
        dt = TestDataOnline()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_mac_date, collector.get_collectibles()['Mac_date'])
        self.assertEqual(dt.expected_mac_size, collector.get_collectibles()['Mac_size'])
        self.assertEqual(ConstTestData.CONST_WIN_EXE, collector.get_collectibles()['const_win_exe'])
        self.assertEqual(ConstTestData.CONST_MAC_DMG, collector.get_collectibles()['const_mac_dmg'])
        self.assertEqual(OriginTestData.APP_WEBSITE, collector.get_collectibles()['app_website'])


if __name__ == '__main__':
    unittest.main()
