import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import Find
from collector.triggers import SetWorkspace
from collector.triggers import FetchAttribute
from collector.triggers import RetrieveTags
from collector.triggers import TagType
from collector.triggers import SelectElement
from collector.triggers import GetSubset
from collector.triggers import GetRegexMatch
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space
from collector.helpers import fetch_html

configure_logging(r"../test_log.txt")
logging.debug("Tests for: Origin")


class TestData:
    APP_NAME = "Origin"
    WEB_SPACE_URL_1 = "https://www.dobreprogramy.pl/Origin,Program,Windows,38298.html"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/Origin,Program,Mac,65868.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/origin_web_1.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/origin_web_2.base64"

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


class TestDataOnline(TestData):
    def __init__(self):
        super().__init__()
        self.execution_order.list[0].html_data = fetch_html(self.WEB_SPACE_URL_1)
        self.execution_order.list[1].html_data = fetch_html(self.WEB_SPACE_URL_2)


def get_entry_1():
    req_1 = InvocationRequest(Target(SpaceName.WEB), RetrieveTags("meta", TagType.META, 31))
    req_2 = InvocationRequest(Target(SpaceName.LIST), SelectElement(20))
    req_3 = InvocationRequest(Target(SpaceName.WORK, True, "Win_ver"), FetchAttribute("content"))
    req_4 = InvocationRequest(Target(SpaceName.LIST), SelectElement(26))
    req_5 = InvocationRequest(Target(SpaceName.WORK, True, "Win_date"), FetchAttribute("content"))
    req_6 = InvocationRequest(Target(SpaceName.WEB), Find("divFileSize"))
    req_7 = InvocationRequest(Target(SpaceName.WORK), GetSubset(0, 273))
    req_8 = InvocationRequest(Target(SpaceName.WORK, True, "Win_size"), GetRegexMatch(r"[\d]+,[\d]+ [A-Z]+"))
    chain_request_1 = (req_1, req_2, req_3, req_4, req_5, req_6, req_7, req_8)
    entry_1 = ExecutionOrderEntry(chain_request_1, get_web_space(TestData.WEB_SPACE_HTML_PATH_1))
    return entry_1


def get_entry_2():
    req_1 = InvocationRequest(Target(SpaceName.WEB), RetrieveTags("meta", TagType.META, 31))
    req_2 = InvocationRequest(Target(SpaceName.LIST), SelectElement(20))
    req_3 = InvocationRequest(Target(SpaceName.WORK, True, "Mac_ver"), FetchAttribute("content"))
    req_4 = InvocationRequest(Target(SpaceName.LIST), SelectElement(26))
    req_5 = InvocationRequest(Target(SpaceName.WORK, True, "Mac_date"), FetchAttribute("content"))
    req_6 = InvocationRequest(Target(SpaceName.WEB), Find("divFileSize"))
    req_7 = InvocationRequest(Target(SpaceName.WORK), GetSubset(0, 273))
    req_8 = InvocationRequest(Target(SpaceName.WORK, True, "Mac_size"), GetRegexMatch(r"[\d]+,[\d]+ [A-Z]+"))
    chain_request_2 = (req_1, req_2, req_3, req_4, req_5, req_6, req_7, req_8)
    entry_2 = ExecutionOrderEntry(chain_request_2, get_web_space(TestData.WEB_SPACE_HTML_PATH_2))
    return entry_2


def get_entry_3():
    req_1 = InvocationRequest(Target(SpaceName.WORK, True, "const_win_exe"), SetWorkspace(ConstTestData.CONST_WIN_EXE))
    req_2 = InvocationRequest(Target(SpaceName.WORK, True, "const_mac_dmg"), SetWorkspace(ConstTestData.CONST_MAC_DMG))
    chain_request_3 = (req_1, req_2)
    entry_3 = ExecutionOrderEntry(chain_request_3, str())
    return entry_3


class EpicGamesLauncherTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = TestData()
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


if __name__ == '__main__':
    unittest.main()
