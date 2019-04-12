import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import Find
from collector.triggers import RetrieveTags
from collector.triggers import TagType
from collector.triggers import SelectElement
from collector.triggers import FetchAttribute
from collector.triggers import GetRegexMatch
from collector.triggers import SetWorkspace
from collector.helpers import fetch_html
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space

configure_logging(r"../test_log.txt")
logging.debug("Tests for: GOG Galaxy")


class GOGGalaxyTestData:
    APP_NAME = "GOG Galaxy"
    WEB_SPACE_URL_1 = "https://www.gog.com/galaxy"
    WEB_SPACE_URL_2 = "https://www.majorgeeks.com/files/details/gog_galaxy.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/gog_galaxy_web_WinMac.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/gog_galaxy_web_Windows.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        mac_pkg = b'aHR0cHM6Ly9jb250ZW50LXN5c3RlbS5nb2cuY29tL29wZW5fbGluay9kb3dubG9hZD9wYXRoPS9vcGVuL2dhbGF4eS9jbG' \
                  b'llbnQvZ2FsYXh5X2NsaWVudF8xLjIuNTQuMjcucGtn'
        mac_ver = b'MS4yLjU0LjI3'
        windows_exe = b'aHR0cHM6Ly9jb250ZW50LXN5c3RlbS5nb2cuY29tL29wZW5fbGluay9kb3dubG9hZD9wYXRoPS9vcGVuL2dhbGF4eS' \
                      b'9jbGllbnQvc2V0dXBfZ2FsYXh5XzEuMi41NC4yMy5leGU='
        windows_ver = b'MS4yLjU0LjIz'
        self.expected_mac_pkg = decode_base64(mac_pkg)
        self.expected_windows_exe = decode_base64(windows_exe)
        self.expected_mac_ver = decode_base64(mac_ver)
        self.expected_windows_ver = decode_base64(windows_ver)
        self.expected_date_published = decode_base64(b'MDMvMTcvMjAxOSAxMjoyNiBQTQ==')
        self.expected_file_size = decode_base64(b'MjE2IE1C')


class TestDataOnline(GOGGalaxyTestData):
    def __init__(self):
        super().__init__()
        self.execution_order.list[0].html_data = fetch_html(self.WEB_SPACE_URL_1)
        self.execution_order.list[1].html_data = fetch_html(self.WEB_SPACE_URL_2)


def get_entry_1():
    regex = r"[\d]+.[\d]+.[\d]+.[\d]+"
    req_1 = InvocationRequest(Target(SpaceName.WEB), Find('class="ng-hide"'))
    req_2 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("a", TagType.ATTRIBUTED, 2))
    req_3 = InvocationRequest(Target(SpaceName.LIST), SelectElement(1))
    req_4 = InvocationRequest(Target(SpaceName.WORK, True, "Mac_pkg"), FetchAttribute("href"))
    req_5 = InvocationRequest(Target(SpaceName.WORK, True, "Mac_ver"), GetRegexMatch(regex))
    req_6 = InvocationRequest(Target(SpaceName.LIST), SelectElement(0))
    req_7 = InvocationRequest(Target(SpaceName.WORK, True, "Windows_exe"), FetchAttribute("href"))
    req_8 = InvocationRequest(Target(SpaceName.WORK, True, "Windows_ver"), GetRegexMatch(regex))
    chain_request_1 = (req_1, req_2, req_3, req_4, req_5, req_6, req_7, req_8)
    return ExecutionOrderEntry(chain_request_1, get_web_space(GOGGalaxyTestData.WEB_SPACE_HTML_PATH_1))


def get_entry_2():
    req_1 = InvocationRequest(Target(SpaceName.WEB), RetrieveTags("meta", TagType.META, 15))
    req_2 = InvocationRequest(Target(SpaceName.LIST), SelectElement(9))
    req_3 = InvocationRequest(Target(SpaceName.WORK, True, "date_published"), FetchAttribute("content"))
    req_4 = InvocationRequest(Target(SpaceName.LIST), SelectElement(13))
    req_5 = InvocationRequest(Target(SpaceName.WORK, True, "file_size"), FetchAttribute("content"))
    chain_request_2 = (req_1, req_2, req_3, req_4, req_5)
    return ExecutionOrderEntry(chain_request_2, get_web_space(GOGGalaxyTestData.WEB_SPACE_HTML_PATH_2))


def get_entry_3():
    req_1 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(GOGGalaxyTestData.WEB_SPACE_URL_1))
    chain_request_3 = (req_1,)
    return ExecutionOrderEntry(chain_request_3, str())


class GogGalaxyTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = GOGGalaxyTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.expected_mac_pkg, collector.get_collectibles()['Mac_pkg'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_windows_exe, collector.get_collectibles()['Windows_exe'])
        self.assertEqual(dt.expected_windows_ver, collector.get_collectibles()['Windows_ver'])
        self.assertEqual(dt.expected_date_published, collector.get_collectibles()['date_published'])
        self.assertEqual(dt.expected_file_size, collector.get_collectibles()['file_size'])
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])

    @unittest.skip("Online test")
    def test_online_collecting(self):
        # given
        dt = GOGGalaxyTestData()
        dt.execution_order.list[0].html_data = fetch_html(dt.WEB_SPACE_URL_1)
        dt.execution_order.list[1].html_data = fetch_html(dt.WEB_SPACE_URL_2)
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.expected_mac_pkg, collector.get_collectibles()['Mac_pkg'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_windows_exe, collector.get_collectibles()['Windows_exe'])
        self.assertEqual(dt.expected_windows_ver, collector.get_collectibles()['Windows_ver'])
        self.assertEqual(dt.expected_date_published, collector.get_collectibles()['date_published'])
        self.assertEqual(dt.expected_file_size, collector.get_collectibles()['file_size'])
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])


if __name__ == '__main__':
    unittest.main()
