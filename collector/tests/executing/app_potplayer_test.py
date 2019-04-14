import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import SetWorkspace
from collector.triggers import Find
from collector.triggers import GetRegexMatch
from collector.triggers import SelectElement
from collector.triggers import RetrieveTags
from collector.triggers import TagType
from collector.triggers import FetchAttribute
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space
from collector.helpers import get_entry_for_dobreprogramy_pl

configure_logging(r"../test_log.txt")
logging.debug("Tests for: PotPlayer")


class PotPlayerTestData:
    APP_NAME = "PotPlayer"
    WEB_SPACE_URL_1 = "https://daumpotplayer.com/download/"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/PotPlayer,Program,Windows,22586.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/potplayer_web_Windows.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/potplayer_web_Windows2.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        exe_32 = b'aHR0cHM6Ly9kYXVtcG90cGxheWVyLmNvbS93cC1jb250ZW50L3VwbG9hZHMvMjAxOS8wMi9Qb3RQbGF5ZXJTZXR1cC5leGU='
        exe_64 = b'aHR0cHM6Ly9kYXVtcG90cGxheWVyLmNvbS93cC1jb250ZW50L3VwbG9hZHMvMjAxOS8wMi9Qb3RQbGF5ZXJTZXR1cDY0LmV4ZQ=='
        beta_exe = b'aHR0cHM6Ly9kYXVtcG90cGxheWVyLmNvbS93cC1jb250ZW50L3VwbG9hZHMvMjAxOS8wMi9Qb3RQbGF5ZXJTQmV0YS5leGU='
        self.expected_win_exe_32 = decode_base64(exe_32)
        self.expected_win_exe_64 = decode_base64(exe_64)
        self.expected_win_beta_exe = decode_base64(beta_exe)
        self.expected_win_beta_ver = decode_base64(b'MS43LjE3Nzk3')
        self.expected_win_ver = decode_base64(b'MS43LjE3NTA4IA==')
        self.expected_win_date = decode_base64(b'MjAxOS0wMi0xMg==')
        self.expected_win_size = decode_base64(b'MjYsMjAgTUI=')


def get_entry_1():
    app_website = PotPlayerTestData.WEB_SPACE_URL_1
    req_01 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(app_website))
    req_02 = InvocationRequest(Target(SpaceName.WEB), Find("download_potplayer"))
    req_03 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("a", TagType.ATTRIBUTED, 3))
    req_04 = InvocationRequest(Target(SpaceName.LIST), SelectElement(0))
    req_05 = InvocationRequest(Target(SpaceName.WORK, True, "win_exe_32bit"), FetchAttribute("href"))
    req_06 = InvocationRequest(Target(SpaceName.LIST), SelectElement(1))
    req_07 = InvocationRequest(Target(SpaceName.WORK, True, "win_exe_64bit"), FetchAttribute("href"))
    req_08 = InvocationRequest(Target(SpaceName.LIST), SelectElement(2))
    req_09 = InvocationRequest(Target(SpaceName.WORK, True, "win_beta_exe"), FetchAttribute("href"))
    req_10 = InvocationRequest(Target(SpaceName.LIST), SelectElement(2))
    req_11 = InvocationRequest(Target(SpaceName.WORK, True, "win_beta_ver"), GetRegexMatch(r"[\d]+[.][\d]+[.][\d]+"))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10,
                     req_11)
    return ExecutionOrderEntry(chain_request, get_web_space(PotPlayerTestData.WEB_SPACE_HTML_PATH_1))


def get_entry_2():
    web_space = get_web_space(PotPlayerTestData.WEB_SPACE_HTML_PATH_2)
    return get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size")


class PotPlayerTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = PotPlayerTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_exe_32, collector.get_collectibles()['win_exe_32bit'])
        self.assertEqual(dt.expected_win_exe_64, collector.get_collectibles()['win_exe_64bit'])
        self.assertEqual(dt.expected_win_beta_exe, collector.get_collectibles()['win_beta_exe'])
        self.assertEqual(dt.expected_win_beta_ver, collector.get_collectibles()['win_beta_ver'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])


if __name__ == '__main__':
    unittest.main()
