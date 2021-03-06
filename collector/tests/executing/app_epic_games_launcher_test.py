import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import Find
from collector.triggers import SetWorkspace
from collector.triggers import FetchAttribute
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space
from collector.helpers import fetch_html
from collector.helpers import get_entry_for_dobreprogramy_pl

configure_logging(r"../test_log.txt")
logging.debug("Tests for: Epic Games Launcher")


class EpicGamesLauncherTestData:
    APP_NAME = "Epic Games Launcher"
    WEB_SPACE_URL_1 = "https://www.epicgames.com/store/pl/"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/Epic-Games-Launcher,Program,Windows,100437.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/egl_web_WinMac.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/egl_web_Windows.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        variant_installer = b'aHR0cHM6Ly9sYXVuY2hlci1wdWJsaWMtc2VydmljZS1wcm9kMDYub2wuZXBpY2dhbWVzLmNvbS9sYXVuY2hlc' \
                            b'i9hcGkvaW5zdGFsbGVyL2Rvd25sb2FkL0VwaWNHYW1lc0xhdW5jaGVySW5zdGFsbGVyLm1zaQ=='
        self.expected_variant_installer = decode_base64(variant_installer)
        self.expected_windows_msi = get_windows_msi()
        self.expected_mac_dmg = get_mac_dmg()
        self.expected_version = decode_base64(b'OS4xMS4y')
        self.expected_date_published = decode_base64(b'MjAxOS0wNC0wNQ==')
        self.expected_file_size = decode_base64(b'MzIsMTUgTUI=')


class TestDataOnline(EpicGamesLauncherTestData):
    def __init__(self):
        super().__init__()
        self.execution_order.list[0].html_data = fetch_html(self.WEB_SPACE_URL_1)
        self.execution_order.list[1].html_data = fetch_html(self.WEB_SPACE_URL_2)


def get_entry_1():
    req_1 = InvocationRequest(Target(SpaceName.WEB), Find(r'id="cta"'))
    req_2 = InvocationRequest(Target(SpaceName.WORK, True, "variant_installer"), FetchAttribute("href"))
    req_3 = InvocationRequest(Target(SpaceName.WORK, True, "Windows_msi"), SetWorkspace(get_windows_msi()))
    req_4 = InvocationRequest(Target(SpaceName.WORK, True, "Mac_dmg"), SetWorkspace(get_mac_dmg()))
    chain_request_1 = (req_1, req_2, req_3, req_4)
    entry_1 = ExecutionOrderEntry(chain_request_1, get_web_space(EpicGamesLauncherTestData.WEB_SPACE_HTML_PATH_1))
    return entry_1


def get_entry_2():
    web_space = get_web_space(EpicGamesLauncherTestData.WEB_SPACE_HTML_PATH_2)
    return get_entry_for_dobreprogramy_pl(web_space, "version", "date_published", "file_size")


def get_entry_3():
    req_1 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(EpicGamesLauncherTestData.WEB_SPACE_URL_1))
    chain_request = (req_1,)
    return ExecutionOrderEntry(chain_request, str())


def get_windows_msi():
    win_msi = b'aHR0cHM6Ly9sYXVuY2hlci1wdWJsaWMtc2VydmljZS1wcm9kMDYub2wuZXBpY2dhbWVzLmNvbS9sYXVuY2hlci9hcGkvaW5zdGF' \
              b'sbGVyL2Rvd25sb2FkL0VwaWNHYW1lc0xhdW5jaGVySW5zdGFsbGVyLm1zaQ=='
    return decode_base64(win_msi)


def get_mac_dmg():
    mac_dmg = b'aHR0cHM6Ly9sYXVuY2hlci1wdWJsaWMtc2VydmljZS1wcm9kMDYub2wuZXBpY2dhbWVzLmNvbS9sYXVuY2hlci9hcGkvaW5zdGF' \
              b'sbGVyL2Rvd25sb2FkL0VwaWNHYW1lc0xhdW5jaGVyLmRtZw=='
    return decode_base64(mac_dmg)


class EpicGamesLauncherTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = EpicGamesLauncherTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.expected_variant_installer, collector.get_collectibles()['variant_installer'])
        self.assertEqual(dt.expected_windows_msi, collector.get_collectibles()['Windows_msi'])
        self.assertEqual(dt.expected_mac_dmg, collector.get_collectibles()['Mac_dmg'])
        self.assertEqual(dt.expected_version, collector.get_collectibles()['version'])
        self.assertEqual(dt.expected_date_published, collector.get_collectibles()['date_published'])
        self.assertEqual(dt.expected_file_size, collector.get_collectibles()['file_size'])
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])

    @unittest.skip("Online test")
    def test_package_collecting_online(self):
        # given
        dt = TestDataOnline()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.expected_variant_installer, collector.get_collectibles()['variant_installer'])
        self.assertEqual(dt.expected_windows_msi, collector.get_collectibles()['Windows_msi'])
        self.assertEqual(dt.expected_mac_dmg, collector.get_collectibles()['Mac_dmg'])
        self.assertEqual(dt.expected_version, collector.get_collectibles()['version'])
        self.assertEqual(dt.expected_date_published, collector.get_collectibles()['date_published'])
        self.assertEqual(dt.expected_file_size, collector.get_collectibles()['file_size'])
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])


if __name__ == '__main__':
    unittest.main()
