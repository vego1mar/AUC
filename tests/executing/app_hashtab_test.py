import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import SetWorkspace
from collector.triggers import Find
from collector.triggers import RetrieveTags
from collector.triggers import TagType
from collector.triggers import FetchAttribute
from collector.triggers import SelectElement
from collector.triggers import CutAside
from collector.triggers import AddText
from collector.triggers import Delete
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space
from collector.helpers import get_entry_for_majorgeeks

configure_logging(r"../test_log.txt")
logging.debug("Tests for: HashTab")


class HashTabTestData:
    APP_NAME = "HashTab"
    WEB_SPACE_URL_1 = "http://implbits.com/products/hashtab/"
    WEB_SPACE_URL_2 = "https://www.majorgeeks.com/files/details/hashtab.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/hashTab_web_Windows.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/hashTab_web_Windows2.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        win_exe = b'aHR0cDovL2ltcGxiaXRzLmNvbS9wcm9kdWN0cy9oYXNodGFiL0hhc2hUYWJfdjYuMC4wLjM0X1NldHVwLmV4ZQ=='
        win_xp_exe = b'aHR0cDovL2ltcGxiaXRzLmNvbS9wcm9kdWN0cy9oYXNodGFiL0hhc2hUYWJfdjUuMi4wLjE0X1NldHVwLmV4ZQ=='
        self.expected_win_exe = decode_base64(win_exe)
        self.expected_win_ver = decode_base64(b'Ni4wLjAuMzQ=')
        self.expected_win_xp_exe = decode_base64(win_xp_exe)
        self.expected_win_xp_ver = decode_base64(b'NS4yLjAuMTQ=')
        self.expected_win_date = decode_base64(b'MDcvMjEvMjAxNw==')
        self.expected_win_size = decode_base64(b'MS4xMiBNQg==')


def get_entry_1():
    app_website = str(HashTabTestData.WEB_SPACE_URL_1)
    chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:/_"
    req_01 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(app_website))
    req_02 = InvocationRequest(Target(SpaceName.WEB), Find("h2"))
    req_03 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("a", TagType.ATTRIBUTED, 1))
    req_04 = InvocationRequest(Target(SpaceName.LIST), SelectElement(0))
    req_05 = InvocationRequest(Target(SpaceName.WORK), FetchAttribute("href"))
    req_06 = InvocationRequest(Target(SpaceName.WORK), CutAside(2, 0))
    req_07 = InvocationRequest(Target(SpaceName.WORK, True, "win_exe"), AddText(app_website, str()))
    req_08 = InvocationRequest(Target(SpaceName.WORK), Delete("", chars))
    req_09 = InvocationRequest(Target(SpaceName.WORK, True, "win_ver"), CutAside(1, 1))
    req_10 = InvocationRequest(Target(SpaceName.WEB), Find("Windows XP"))
    req_11 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("a", TagType.ATTRIBUTED, 1))
    req_12 = InvocationRequest(Target(SpaceName.LIST), SelectElement(0))
    req_13 = InvocationRequest(Target(SpaceName.WORK), FetchAttribute("href"))
    req_14 = InvocationRequest(Target(SpaceName.WORK), CutAside(2, 0))
    req_15 = InvocationRequest(Target(SpaceName.WORK, True, "win_xp_exe"), AddText(app_website, str()))
    req_16 = InvocationRequest(Target(SpaceName.WORK), Delete(str(), chars))
    req_17 = InvocationRequest(Target(SpaceName.WORK, True, "win_xp_ver"), CutAside(1, 1))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10,
                     req_11, req_12, req_13, req_14, req_15, req_16, req_17)
    return ExecutionOrderEntry(chain_request, get_web_space(HashTabTestData.WEB_SPACE_HTML_PATH_1))


def get_entry_2():
    web_space = get_web_space(HashTabTestData.WEB_SPACE_HTML_PATH_2)
    return get_entry_for_majorgeeks(web_space, "Win_date", "Win_size")


class HashTabTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = HashTabTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_exe, collector.get_collectibles()['win_exe'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_xp_exe, collector.get_collectibles()['win_xp_exe'])
        self.assertEqual(dt.expected_win_xp_ver, collector.get_collectibles()['win_xp_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])


if __name__ == '__main__':
    unittest.main()
