import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import Find
from collector.triggers import SetWorkspace
from collector.triggers import RetrieveTags
from collector.triggers import TagType
from collector.triggers import GetSubset
from collector.triggers import CutAside
from collector.triggers import SelectElement
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space
from collector.helpers import fetch_html

configure_logging(r"../test_log.txt")
logging.debug("Tests for: Bethesda.net Launcher")


class BethesdaNetLauncherTestData:
    APP_NAME = "Bethesda.net Launcher"
    APP_WEBSITE = "https://bethesda.net/pl/game/bethesda-launcher"
    WEB_SPACE_URL_2 = "https://www.instalki.pl/programy/download/Windows/akcesoria/Bethesda_launcher.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/bethesdaNet_web_Windows.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.expected_win_ver = decode_base64(b'MS40NS4xMA==')
        self.expected_win_size = decode_base64(b'OC43IE1C')
        self.expected_win_date = decode_base64(b'MjEuMDIuMjAxOQ==')


class TestDataOnline(BethesdaNetLauncherTestData):
    def __init__(self):
        super().__init__()
        self.execution_order.list[1].html_data = fetch_html(self.WEB_SPACE_URL_2)


def get_const_win_exe():
    win_exe = b'aHR0cDovL2Rvd25sb2FkLmNkcC5iZXRoZXNkYS5uZXQvQmV0aGVzZGFOZXRMYXVuY2hlcl9TZXR1cC5leGU='
    return decode_base64(win_exe)


def get_entry_1():
    app_website = BethesdaNetLauncherTestData.APP_WEBSITE
    req_01 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(app_website))
    req_02 = InvocationRequest(Target(SpaceName.WORK, True, "const_win_exe"), SetWorkspace(get_const_win_exe()))
    chain_request = (req_01, req_02)
    return ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    req_01 = InvocationRequest(Target(SpaceName.WEB), Find("h1"))
    req_02 = InvocationRequest(Target(SpaceName.WORK), Find("version"))
    req_03 = InvocationRequest(Target(SpaceName.WORK), GetSubset('>', '<'))
    req_04 = InvocationRequest(Target(SpaceName.WORK, True, "Win_ver"), CutAside(1, 1))
    req_05 = InvocationRequest(Target(SpaceName.WEB), Find("h1"))
    req_06 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("span", TagType.SIMPLE, 2))
    req_07 = InvocationRequest(Target(SpaceName.LIST), SelectElement(0))
    req_08 = InvocationRequest(Target(SpaceName.WORK, True, "Win_size"), CutAside(6, 7))
    req_09 = InvocationRequest(Target(SpaceName.LIST), SelectElement(1))
    req_10 = InvocationRequest(Target(SpaceName.WORK, True, "Win_date"), CutAside(6, 7))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10)
    return ExecutionOrderEntry(chain_request, get_web_space(BethesdaNetLauncherTestData.WEB_SPACE_HTML_PATH_2))


class BethesdaNetLauncherTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = BethesdaNetLauncherTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.APP_WEBSITE, collector.get_collectibles()['app_website'])
        self.assertEqual(get_const_win_exe(), collector.get_collectibles()['const_win_exe'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])

    @unittest.skip("Online test")
    def test_package_collecting_online(self):
        # given
        dt = TestDataOnline()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.APP_WEBSITE, collector.get_collectibles()['app_website'])
        self.assertEqual(get_const_win_exe(), collector.get_collectibles()['const_win_exe'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])


if __name__ == '__main__':
    unittest.main()
