import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class BlizzardBattleNetTestData:
    APP_NAME = 'Blizzard Battle.net'
    WEB_SPACE_URL_1 = 'https://eu.battle.net/account/download/'
    WEB_SPACE_URL_2 = 'https://www.dobreprogramy.pl/Blizzard-Battle.net,Program,Windows,99372.html'
    WEB_SPACE_URL_3 = 'https://www.dobreprogramy.pl/Blizzard-Battle.net,Program,Mac,99373.html'
    WEB_SPACE_URL_4 = 'https://play.google.com/store/apps/details?id=com.blizzard.messenger'
    WEB_SPACE_URL_5 = 'https://itunes.apple.com/app/apple-store/id1241040030'
    WEB_SPACE_HTML_PATH_1 = r"../resources/battleNet_web_URL.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/battleNet_web_Windows.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/battleNet_web_Mac.base64"
    WEB_SPACE_HTML_PATH_4 = r"../resources/battleNet_web_GooglePlay.base64"
    WEB_SPACE_HTML_PATH_5 = r"../resources/battleNet_web_iOS.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        self.execution_order.add_entry(get_entry_5(), True)
        self.expected_win_exe_plpl = get_expected_win_exe_plpl()
        self.expected_mac_zip_plpl = get_expected_mac_zip_plpl()
        self.expected_win_ver = '1.16.0'
        self.expected_win_date = '2019-02-08'
        self.expected_win_size = '4,49 MB'
        self.expected_mac_ver = ''
        self.expected_mac_date = '2018-12-31'
        self.expected_mac_size = '3,01 MB'
        self.expected_gp_date = 'February 15, 2019'
        self.expected_gp_size = '15M'
        self.expected_gp_ver = '1.4.3.75'
        self.expected_ios_ver = 'Version 1.4.5'
        self.expected_ios_size = '69.7 MB'


def get_expected_win_exe_plpl():
    return 'https://www.blizzard.com/en-gb/download/confirmation?platform=windows&locale=plPL&version=LIVE&id=bnetdesk'


def get_expected_mac_zip_plpl():
    return 'https://www.blizzard.com/en-gb/download/confirmation?platform=macos&locale=plPL&version=LIVE&id=bnetdesk'


def get_entry_1():
    win_exe_plpl = get_expected_win_exe_plpl()
    mac_zip_plpl = get_expected_mac_zip_plpl()
    app_website = BlizzardBattleNetTestData.WEB_SPACE_URL_1
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, 'Win_exe_plPL'), tr.SetWorkspace(win_exe_plpl))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, 'Mac_zip_plPL'), tr.SetWorkspace(mac_zip_plpl))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    chain_request_1 = (req_01, req_02, req_03)
    entry_1 = ex.ExecutionOrderEntry(chain_request_1, str())
    return entry_1


def get_entry_2():
    web_space = hp.get_web_space(BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Win_ver", "Win_date", "Win_size", (1, 1))


def get_entry_3():
    web_space = hp.get_web_space(BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Mac_ver", "Mac_date", "Mac_size")


def get_entry_4():
    web_space = hp.get_web_space(BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_4)
    app_url = BlizzardBattleNetTestData.WEB_SPACE_URL_4
    return hp.get_entry_for_google_play_store(web_space, app_url, 'gp_date', 'gp_size', 'gp_ver')


def get_entry_5():
    web_space = hp.get_web_space(BlizzardBattleNetTestData.WEB_SPACE_HTML_PATH_5)
    app_url = BlizzardBattleNetTestData.WEB_SPACE_URL_5
    return hp.get_entry_for_itunes(web_space, app_url, 'ios_ver', 'ios_size')


class ActivisionBlizzardBattleNetTestCase(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = BlizzardBattleNetTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.expected_win_exe_plpl, collector.get_collectibles()['Win_exe_plPL'])
        self.assertEqual(dt.expected_mac_zip_plpl, collector.get_collectibles()['Mac_zip_plPL'])
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_mac_date, collector.get_collectibles()['Mac_date'])
        self.assertEqual(dt.expected_mac_size, collector.get_collectibles()['Mac_size'])
        self.assertEqual(dt.expected_gp_date, collector.get_collectibles()['gp_date'])
        self.assertEqual(dt.expected_gp_size, collector.get_collectibles()['gp_size'])
        self.assertEqual(dt.expected_gp_ver, collector.get_collectibles()['gp_ver'])
        self.assertEqual(dt.WEB_SPACE_URL_4, collector.get_collectibles()['google_play_apk'])
        self.assertEqual(dt.expected_ios_ver, collector.get_collectibles()['ios_ver'])
        self.assertEqual(dt.expected_ios_size, collector.get_collectibles()['ios_size'])
        self.assertEqual(dt.WEB_SPACE_URL_5, collector.get_collectibles()['ios_itunes'])


if __name__ == '__main__':
    unittest.main()
