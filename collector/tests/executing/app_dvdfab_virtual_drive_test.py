import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import SetWorkspace
from collector.triggers import FetchAttribute
from collector.triggers import Find
from collector.triggers import AddText
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space
from collector.helpers import get_entry_for_dobreprogramy_pl

configure_logging(r"../test_log.txt")
logging.debug("Tests for: DVDFab Virtual Drive")


class DvdFabVirtualDriveTestData:
    APP_NAME = "DVDFab Virtual Drive"
    WEB_SPACE_URL_1 = "https://www.dvdfab.cn/virtual_drive.htm?trackID=downloadpage"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/DVDFab-Virtual-Drive,Program,Windows,19396.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/dvdfab_vd_web_Windows1.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/dvdfab_vd_web_Windows2.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        exe = b'aHR0cHM6Ly93d3cuZHZkZmFiLmNuL21saW5rL2Rvd25sb2FkLnBocD9nPVZJUlRVQUxEUklWRQ=='
        self.expected_win_exe = decode_base64(exe)
        self.expected_win_ver = decode_base64(b'MS41LjEuMQ==')
        self.expected_win_date = decode_base64(b'MjAxNC0wOS0wMg==')
        self.expected_win_size = decode_base64(b'MCw3OSBNQg==')


def get_entry_1():
    app_website = DvdFabVirtualDriveTestData.WEB_SPACE_URL_1
    req_01 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(app_website))
    req_02 = InvocationRequest(Target(SpaceName.WEB), Find("try-icon"))
    req_03 = InvocationRequest(Target(SpaceName.WORK), FetchAttribute("href"))
    req_04 = InvocationRequest(Target(SpaceName.WORK, True, "win_exe"), AddText("https://www.dvdfab.cn", str()))
    chain_request = (req_01, req_02, req_03, req_04)
    return ExecutionOrderEntry(chain_request, get_web_space(DvdFabVirtualDriveTestData.WEB_SPACE_HTML_PATH_1))


def get_entry_2():
    web_space = get_web_space(DvdFabVirtualDriveTestData.WEB_SPACE_HTML_PATH_2)
    return get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (0, -1))


class DvdFabVirtualDriveTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = DvdFabVirtualDriveTestData()
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
