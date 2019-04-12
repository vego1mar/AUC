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
from collector.helpers import get_entry_for_google_play_store
from collector.helpers import get_entry_for_ms_store

configure_logging(r"../test_log.txt")
logging.debug("Tests for: Steam")


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
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        self.execution_order.add_entry(get_entry_5(), True)
        self.execution_order.add_entry(get_entry_6(), True)
        deb = decode_base64(b'aHR0cHM6Ly9zdGVhbWNkbi1hLmFrYW1haWhkLm5ldC9jbGllbnQvaW5zdGFsbGVyL3N0ZWFtLmRlYg==')
        exe = decode_base64(b'aHR0cHM6Ly9zdGVhbWNkbi1hLmFrYW1haWhkLm5ldC9jbGllbnQvaW5zdGFsbGVyL1N0ZWFtU2V0dXAuZXhl')
        dmg = decode_base64(b'aHR0cHM6Ly9zdGVhbWNkbi1hLmFrYW1haWhkLm5ldC9jbGllbnQvaW5zdGFsbGVyL3N0ZWFtLmRtZw==')
        self.expected_links = (deb, exe, dmg)
        self.expected_apk_date = decode_base64(b'TWFyY2ggOCwgMjAxOQ==')
        self.expected_apk_size = decode_base64(b'Mi43TQ==')
        self.expected_apk_ver = decode_base64(b'Mi4zLjk=')
        self.expected_win_ver = decode_base64(b'MTguMDIuMjAxOSA=')
        self.expected_win_date = decode_base64(b'MjAxOS0wMi0xOQ==')
        self.expected_win_size = decode_base64(b'MSw1MCBNQg==')
        self.expected_mac_ver = decode_base64(b'MDMuMjIuMjAxNw==')
        self.expected_mac_date = decode_base64(b'MjAxNy0wMy0yNw==')
        self.expected_mac_size = decode_base64(b'NCw2NyBNQg==')
        self.expected_ms_store_size = decode_base64(b'NC41MiBNQg==')


class TestDataOnline(SteamTestData):
    def __init__(self):
        super().__init__()
        self.execution_order.list[0].html_data = fetch_html(self.WEB_SPACE_URL_1)
        self.execution_order.list[1].html_data = fetch_html(self.WEB_SPACE_URL_2)
        self.execution_order.list[2].html_data = fetch_html(self.WEB_SPACE_URL_3)
        self.execution_order.list[3].html_data = fetch_html(self.WEB_SPACE_URL_4)
        self.execution_order.list[4].html_data = fetch_html(self.WEB_SPACE_URL_5)


def get_google_play_apk():
    apk = b'aHR0cHM6Ly9wbGF5Lmdvb2dsZS5jb20vc3RvcmUvYXBwcy9kZXRhaWxzP2lkPWNvbS52YWx2ZXNvZnR3YXJlLmFuZHJvaWQuc3Rl' \
          b'YW0uY29tbXVuaXR5'
    return decode_base64(apk)


def get_ms_store_link():
    store_site = b'aHR0cHM6Ly93d3cubWljcm9zb2Z0LmNvbS9lbi11cy9wL3N0ZWFtLzluYmxnZ2g0eDdnbQ=='
    return decode_base64(store_site)


def get_entry_1():
    req_01 = InvocationRequest(Target(SpaceName.WEB), Find("about_install_wrapper"))
    req_02 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("a", TagType.ATTRIBUTED, 3))
    req_03 = InvocationRequest(Target(SpaceName.LIST), SelectElement(0))
    req_04 = InvocationRequest(Target(SpaceName.WORK, True, "link_1"), FetchAttribute("href"))
    req_05 = InvocationRequest(Target(SpaceName.LIST), SelectElement(1))
    req_06 = InvocationRequest(Target(SpaceName.WORK, True, "link_2"), FetchAttribute("href"))
    req_07 = InvocationRequest(Target(SpaceName.LIST), SelectElement(2))
    req_08 = InvocationRequest(Target(SpaceName.WORK, True, "link_3"), FetchAttribute("href"))
    chain_request_1 = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08)
    entry_1 = ExecutionOrderEntry(chain_request_1, get_web_space(SteamTestData.WEB_SPACE_HTML_PATH_1))
    return entry_1


def get_entry_2():
    web_space = get_web_space(SteamTestData.WEB_SPACE_HTML_PATH_2)
    app_url = get_google_play_apk()
    return get_entry_for_google_play_store(web_space, app_url, "apk_date", "apk_size", "apk_ver")


def get_entry_3():
    web_space = get_web_space(SteamTestData.WEB_SPACE_HTML_PATH_3)
    return get_entry_for_dobreprogramy_pl(web_space, "Win_ver", "Win_date", "Win_size")


def get_entry_4():
    web_space = get_web_space(SteamTestData.WEB_SPACE_HTML_PATH_4)
    return get_entry_for_dobreprogramy_pl(web_space, "Mac_ver", "Mac_date", "Mac_size")


def get_entry_5():
    web_space = get_web_space(SteamTestData.WEB_SPACE_HTML_PATH_5)
    app_url = get_ms_store_link()
    return get_entry_for_ms_store(web_space, app_url, "ms_store_size", "ms_store_link")


def get_entry_6():
    req_01 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(SteamTestData.WEB_SPACE_URL_1))
    chain_request_6 = (req_01,)
    return ExecutionOrderEntry(chain_request_6, str())


class SteamClientBootstrapperTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = SteamTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

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

    @unittest.skip("Online test")
    def test_package_collecting_online(self):
        # given
        dt = TestDataOnline()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

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
