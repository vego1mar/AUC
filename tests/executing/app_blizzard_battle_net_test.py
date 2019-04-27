import unittest
import logging
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr

hp.configure_logging(r"../test_log.txt")
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
        self.execution_order = ex.ExecutionOrder()
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
        self.expected_win_exe_engb = hp.decode_base64(win_exe_engb)
        self.expected_win_exe_plpl = hp.decode_base64(win_exe_plpl)
        self.expected_mac_zip_enus = hp.decode_base64(mac_zip_enus)
        self.expected_mac_zip_plpl = hp.decode_base64(mac_zip_plpl)
        self.expected_win_ver = hp.decode_base64(b'MS4xNi4w')
        self.expected_win_date = hp.decode_base64(b'MjAxOS0wMi0wOA==')
        self.expected_win_size = hp.decode_base64(b'NCw0OSBNQg==')
        self.expected_mac_ver = str()
        self.expected_mac_date = hp.decode_base64(b'MjAxOC0xMi0zMQ==')
        self.expected_mac_size = hp.decode_base64(b'MywwMSBNQg==')


class TestDataOnline(BlizzardBattleNetTestData):
    def __init__(self):
        super().__init__()
        self.execution_order.list[0].html_data = hp.fetch_html(self.WEB_SPACE_URL_1)
        self.execution_order.list[1].html_data = hp.fetch_html(self.WEB_SPACE_URL_2)
        self.execution_order.list[2].html_data = hp.fetch_html(self.WEB_SPACE_URL_3)


def get_entry_1():
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("gameProgram[bnetapp]"))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("a", tr.TagType.ATTRIBUTED, 28))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(3))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Win_exe_enGB"), tr.FetchAttribute("href"))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(10))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Win_exe_plPL"), tr.FetchAttribute("href"))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(14))
    req_08 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Mac_zip_enUS"), tr.FetchAttribute("href"))
    req_09 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(24))
    req_10 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Mac_zip_plPL"), tr.FetchAttribute("href"))
    chain_request_1 = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10)
    entry_1 = ex.ExecutionOrderEntry(chain_request_1, hp.get_web_space(BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_1))
    return entry_1


def get_entry_2():
    web_space = hp.get_web_space(BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Win_ver", "Win_date", "Win_size")


def get_entry_3():
    web_space = hp.get_web_space(BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Mac_ver", "Mac_date", "Mac_size", (-1, -1))


def get_entry_4():
    app_website = BlizzardBattleNetTestData.WEB_SPACE_URL_1
    req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    chain_request = (req_1,)
    return ex.ExecutionOrderEntry(chain_request, str())


class ActivisionBlizzardBattleNetTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = BlizzardBattleNetTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

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
