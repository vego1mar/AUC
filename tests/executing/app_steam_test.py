import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class SteamTestData:
    APP_NAME = "Steam"
    WEB_SPACE_URL_1 = "https://store.steampowered.com/about/"
    WEB_SPACE_URL_2 = "https://play.google.com/store/apps/details?id=com.valvesoftware.android.steam.community&hl=en_US"
    WEB_SPACE_URL_3 = "https://www.dobreprogramy.pl/Steam,Program,Windows,18206.html"
    WEB_SPACE_URL_4 = "https://www.dobreprogramy.pl/Steam,Program,Mac,64030.html"
    WEB_SPACE_URL_5 = "https://www.microsoft.com/en-us/p/steam/9nblggh4x7gm"
    WEB_SPACE_HTML_PATH_1 = r"../resources/steamClient_web_official.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/steamClient_web_GooglePlay.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/steamClient_web_Windows.base64"
    WEB_SPACE_HTML_PATH_4 = r"../resources/steamClient_web_Mac.base64"
    WEB_SPACE_HTML_PATH_5 = r"../resources/steamClient_web_MSStore.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        self.execution_order.add_entry(get_entry_5(), True)
        self.execution_order.add_entry(get_entry_6(), True)
        self.expected_links = (
            'https://steamcdn-a.akamaihd.net/client/installer/SteamSetup.exe',
            'https://steamcdn-a.akamaihd.net/client/installer/steam.dmg',
            'https://steamcdn-a.akamaihd.net/client/installer/steam.deb'
        )
        self.expected_apk_date = 'March 8, 2019'
        self.expected_apk_size = '2.7M'
        self.expected_apk_ver = '2.3.9'
        self.expected_win_ver = '29.04.2019 '
        self.expected_win_date = '2019-05-02'
        self.expected_win_size = '1,50 MB'
        self.expected_mac_ver = '03.22.2017'
        self.expected_mac_date = '2017-03-27'
        self.expected_mac_size = '4,67 MB'
        self.expected_ms_store_size = '4.52 MB'


def get_google_play_apk():
    return 'https://play.google.com/store/apps/details?id=com.valvesoftware.android.steam.community'


def get_ms_store_link():
    return 'https://www.microsoft.com/en-us/p/steam/9nblggh4x7gm'


def get_entry_1():
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("about_install_wrapper"))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("a", tr.TagType.ATTRIBUTED, 3))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(0))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "link_1"), tr.FetchAttribute("href"))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(1))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "link_2"), tr.FetchAttribute("href"))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(2))
    req_08 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "link_3"), tr.FetchAttribute("href"))
    chain_request_1 = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08)
    entry_1 = ex.ExecutionOrderEntry(chain_request_1, hp.get_web_space(SteamTestData.WEB_SPACE_HTML_PATH_1))
    return entry_1


def get_entry_2():
    web_space = hp.get_web_space(SteamTestData.WEB_SPACE_HTML_PATH_2)
    app_url = get_google_play_apk()
    return hp.get_entry_for_google_play_store(web_space, app_url, "apk_date", "apk_size", "apk_ver")


def get_entry_3():
    web_space = hp.get_web_space(SteamTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Win_ver", "Win_date", "Win_size", (1, 1))


def get_entry_4():
    web_space = hp.get_web_space(SteamTestData.WEB_SPACE_HTML_PATH_4)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Mac_ver", "Mac_date", "Mac_size", (1, 1))


def get_entry_5():
    web_space = hp.get_web_space(SteamTestData.WEB_SPACE_HTML_PATH_5)
    app_url = get_ms_store_link()
    return hp.get_entry_for_ms_store(web_space, app_url, "ms_store_size", "ms_store_link")


def get_entry_6():
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"),
                                  tr.SetWorkspace(SteamTestData.WEB_SPACE_URL_1))
    chain_request_6 = (req_01,)
    return ex.ExecutionOrderEntry(chain_request_6, str())


class SteamClientBootstrapperTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = SteamTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertIn(collector.get_collectibles()['link_1'], dt.expected_links)
        self.assertIn(collector.get_collectibles()['link_2'], dt.expected_links)
        self.assertIn(collector.get_collectibles()['link_3'], dt.expected_links)
        self.assertEqual(get_google_play_apk(), collector.get_collectibles()['google_play_apk'])
        self.assertEqual(dt.expected_apk_date, collector.get_collectibles()['apk_date'])
        self.assertEqual(dt.expected_apk_size, collector.get_collectibles()['apk_size'])
        self.assertEqual(dt.expected_apk_ver, collector.get_collectibles()['apk_ver'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_mac_date, collector.get_collectibles()['Mac_date'])
        self.assertEqual(dt.expected_mac_size, collector.get_collectibles()['Mac_size'])
        self.assertEqual(get_ms_store_link(), collector.get_collectibles()['ms_store_link'])
        self.assertEqual(dt.expected_ms_store_size, collector.get_collectibles()['ms_store_size'])
        self.assertEqual(SteamTestData.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])


if __name__ == '__main__':
    unittest.main()
