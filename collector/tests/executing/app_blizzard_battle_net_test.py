import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import Find
from collector.triggers import FetchAttribute
from collector.triggers import RetrieveTags
from collector.triggers import TagType
from collector.triggers import SelectElement
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
logging.debug("Tests for: Blizzard Battle.net")


class BlizzardBattleNetTestData:
    APP_NAME = "Blizzard Battle.net"
    WEB_SPACE_URL_1 = "https://eu.battle.net/account/download/"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/Blizzard-Battle.net,Program,Windows,99372.html"
    WEB_SPACE_URL_3 = "https://www.dobreprogramy.pl/Blizzard-Battle.net,Program,Mac,99373.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/battleNet_web_URL.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/battleNet_web_Windows.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/battleNet_web_Mac.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        win_exe_engb = b'aHR0cHM6Ly93d3cuYmF0dGxlLm5ldC9kb3dubG9hZC9nZXRJbnN0YWxsZXJGb3JHYW1lP29zPXdpbiZhbXA7bG9jYWx' \
                       b'lPWVuR0ImYW1wO3ZlcnNpb249TElWRSZhbXA7Z2FtZVByb2dyYW09QkFUVExFTkVUX0FQUA=='
        win_exe_plpl = b'aHR0cHM6Ly93d3cuYmF0dGxlLm5ldC9kb3dubG9hZC9nZXRJbnN0YWxsZXJGb3JHYW1lP29zPXdpbiZhbXA7bG9jYWx' \
                       b'lPXBsUEwmYW1wO3ZlcnNpb249TElWRSZhbXA7Z2FtZVByb2dyYW09QkFUVExFTkVUX0FQUA=='
        mac_zip_enus = b'aHR0cHM6Ly93d3cuYmF0dGxlLm5ldC9kb3dubG9hZC9nZXRJbnN0YWxsZXJGb3JHYW1lP29zPW1hYyZhbXA7bG9jYWx' \
                       b'lPWVuVVMmYW1wO3ZlcnNpb249TElWRSZhbXA7Z2FtZVByb2dyYW09QkFUVExFTkVUX0FQUA=='
        mac_zip_plpl = b'aHR0cHM6Ly93d3cuYmF0dGxlLm5ldC9kb3dubG9hZC9nZXRJbnN0YWxsZXJGb3JHYW1lP29zPW1hYyZhbXA7bG9jYWx' \
                       b'lPXBsUEwmYW1wO3ZlcnNpb249TElWRSZhbXA7Z2FtZVByb2dyYW09QkFUVExFTkVUX0FQUA=='
        self.expected_win_exe_engb = decode_base64(win_exe_engb)
        self.expected_win_exe_plpl = decode_base64(win_exe_plpl)
        self.expected_mac_zip_enus = decode_base64(mac_zip_enus)
        self.expected_mac_zip_plpl = decode_base64(mac_zip_plpl)
        self.expected_win_ver = decode_base64(b'MS4xNi4w')
        self.expected_win_date = decode_base64(b'MjAxOS0wMi0wOA==')
        self.expected_win_size = decode_base64(b'NCw0OSBNQg==')
        self.expected_mac_ver = str()
        self.expected_mac_date = decode_base64(b'MjAxOC0xMi0zMQ==')
        self.expected_mac_size = decode_base64(b'MywwMSBNQg==')


class TestDataOnline(BlizzardBattleNetTestData):
    def __init__(self):
        super().__init__()
        self.execution_order.list[0].html_data = fetch_html(self.WEB_SPACE_URL_1)
        self.execution_order.list[1].html_data = fetch_html(self.WEB_SPACE_URL_2)
        self.execution_order.list[2].html_data = fetch_html(self.WEB_SPACE_URL_3)


def get_entry_1():
    req_01 = InvocationRequest(Target(SpaceName.WEB), Find("gameProgram[bnetapp]"))
    req_02 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("a", TagType.ATTRIBUTED, 28))
    req_03 = InvocationRequest(Target(SpaceName.LIST), SelectElement(3))
    req_04 = InvocationRequest(Target(SpaceName.WORK, True, "Win_exe_enGB"), FetchAttribute("href"))
    req_05 = InvocationRequest(Target(SpaceName.LIST), SelectElement(10))
    req_06 = InvocationRequest(Target(SpaceName.WORK, True, "Win_exe_plPL"), FetchAttribute("href"))
    req_07 = InvocationRequest(Target(SpaceName.LIST), SelectElement(14))
    req_08 = InvocationRequest(Target(SpaceName.WORK, True, "Mac_zip_enUS"), FetchAttribute("href"))
    req_09 = InvocationRequest(Target(SpaceName.LIST), SelectElement(24))
    req_10 = InvocationRequest(Target(SpaceName.WORK, True, "Mac_zip_plPL"), FetchAttribute("href"))
    chain_request_1 = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10)
    entry_1 = ExecutionOrderEntry(chain_request_1, get_web_space(BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_1))
    return entry_1


def get_entry_2():
    web_space = get_web_space(BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_2)
    return get_entry_for_dobreprogramy_pl(web_space, "Win_ver", "Win_date", "Win_size")


def get_entry_3():
    web_space = get_web_space(BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_3)
    return get_entry_for_dobreprogramy_pl(web_space, "Mac_ver", "Mac_date", "Mac_size", -1)


def get_entry_4():
    req_1 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(BlizzardBattleNetTestData.WEB_SPACE_URL_1))
    chain_request = (req_1,)
    return ExecutionOrderEntry(chain_request, str())


class ActivisionBlizzardBattleNetTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = BlizzardBattleNetTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.expected_win_exe_engb, collector.get_collectibles()['Win_exe_enGB'])
        self.assertEqual(dt.expected_win_exe_plpl, collector.get_collectibles()['Win_exe_plPL'])
        self.assertEqual(dt.expected_mac_zip_enus, collector.get_collectibles()['Mac_zip_enUS'])
        self.assertEqual(dt.expected_mac_zip_plpl, collector.get_collectibles()['Mac_zip_plPL'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_mac_date, collector.get_collectibles()['Mac_date'])
        self.assertEqual(dt.expected_mac_size, collector.get_collectibles()['Mac_size'])
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
        self.assertEqual(dt.expected_win_exe_engb, collector.get_collectibles()['Win_exe_enGB'])
        self.assertEqual(dt.expected_win_exe_plpl, collector.get_collectibles()['Win_exe_plPL'])
        self.assertEqual(dt.expected_mac_zip_enus, collector.get_collectibles()['Mac_zip_enUS'])
        self.assertEqual(dt.expected_mac_zip_plpl, collector.get_collectibles()['Mac_zip_plPL'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_mac_date, collector.get_collectibles()['Mac_date'])
        self.assertEqual(dt.expected_mac_size, collector.get_collectibles()['Mac_size'])
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])


if __name__ == '__main__':
    unittest.main()
