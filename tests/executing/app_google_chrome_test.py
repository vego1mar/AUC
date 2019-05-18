import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class GoogleChromeTestData:
    APP_NAME = "Google Chrome"
    APP_WEBSITE = "https://www.google.com/chrome/"
    WEB_SPACE_URL_2 = "https://play.google.com/store/apps/details?id=com.android.chrome"
    WEB_SPACE_URL_3 = "https://www.dobreprogramy.pl/Google-Chrome,Program,Windows,13096.html"
    WEB_SPACE_URL_4 = "https://www.dobreprogramy.pl/Google-Chrome,Program,Mac,64621.html"
    WEB_SPACE_URL_5 = "https://itunes.apple.com/us/app/chrome/id535886823"
    WEB_SPACE_HTML_PATH_2 = r"../resources/chrome_web_GooglePlay.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/chrome_web_Windows.base64"
    WEB_SPACE_HTML_PATH_4 = r"../resources/chrome_web_Mac.base64"
    WEB_SPACE_HTML_PATH_5 = r"../resources/chrome_web_iOS.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        self.execution_order.add_entry(get_entry_5(), True)
        self.expected_apk_date = 'May 16, 2019'
        self.expected_win_ver = '74.0.3729.157'
        self.expected_win_date = '2019-05-15'
        self.expected_mac_ver = '74.0.3729.157'
        self.expected_mac_date = '2019-05-15'
        self.expected_mac_size = '75,12 MB'
        self.expected_ios_ver = 'Version 74.0.3729.121'
        self.expected_ios_size = '72.4 MB'


def get_win_exe_64bit():
    return r'https://dl.google.com/tag/s/appguid%3D%7B8A69D345-D564-463C-AFF1-A69D9E530F96%7D%26iid%3D%7BDA6FE065-E4' \
           r'94-F5D4-0842-C9E40B2B2C71%7D%26lang%3Den%26browser%3D4%26usagestats%3D0%26appname%3DGoogle%2520Chrome%2' \
           r'6needsadmin%3Dprefers%26ap%3Dx64-stable-statsdef_1%26installdataindex%3Dempty/update2/installers/Chrome' \
           r'Setup.exe'


def get_win_exe_32bit():
    return r'https://dl.google.com/tag/s/appguid%3D%7B8A69D345-D564-463C-AFF1-A69D9E530F96%7D%26iid%3D%7BDA6FE065-E4' \
           r'94-F5D4-0842-C9E40B2B2C71%7D%26lang%3Den%26browser%3D4%26usagestats%3D0%26appname%3DGoogle%2520Chrome%2' \
           r'6needsadmin%3Dprefers%26ap%3Dstable-arch_x86-statsdef_1%26installdataindex%3Dempty/update2/installers/C' \
           r'hromeSetup.exe'


def get_mac_dmg_1010plus():
    return r'https://dl.google.com/chrome/mac/stable/GGRO/googlechrome.dmg'


def get_win_xp_exe():
    return r'https://dl.google.com/tag/s/appguid%3D%7B8A69D345-D564-463C-AFF1-A69D9E530F96%7D%26iid%3D%7BDA6FE065-E4' \
           r'94-F5D4-0842-C9E40B2B2C71%7D%26lang%3Den%26browser%3D4%26usagestats%3D0%26appname%3DGoogle%2520Chrome%2' \
           r'6needsadmin%3Dprefers%26ap%3Dstable-arch_x86%26installdataindex%3Dempty/update2/installers/ChromeSetup.' \
           r'exe'


def get_win_vista_exe():
    return r'https://dl.google.com/tag/s/appguid%3D%7B8A69D345-D564-463C-AFF1-A69D9E530F96%7D%26iid%3D%7BDA6FE065-E4' \
           r'94-F5D4-0842-C9E40B2B2C71%7D%26lang%3Den%26browser%3D4%26usagestats%3D0%26appname%3DGoogle%2520Chrome%2' \
           r'6needsadmin%3Dprefers%26ap%3Dstable-arch_x86%26installdataindex%3Dempty/update2/installers/ChromeSetup.' \
           r'exe'


def get_mac_dmg_106_108():
    return r'https://dl.google.com/dl/chrome/mac/legacy/GGRO/googlechrome.dmg'


def get_mac_dmg_109():
    return r'https://dl.google.com/dl/chrome/mac/legacy10_9/GGRO/googlechrome.dmg'


def get_linux_deb():
    return r'https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb'


def get_linux_rpm():
    return r'https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm'


def get_entry_1():
    app_website = GoogleChromeTestData.APP_WEBSITE
    url_1 = get_win_exe_64bit()
    url_2 = get_win_exe_32bit()
    url_3 = get_mac_dmg_1010plus()
    url_4 = get_win_vista_exe()
    url_5 = get_mac_dmg_106_108()
    url_6 = get_mac_dmg_109()
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe_64bit"), tr.SetWorkspace(url_1))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe_32bit"), tr.SetWorkspace(url_2))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "mac_dmg_10.10+"), tr.SetWorkspace(url_3))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_xp_exe"), tr.SetWorkspace(get_win_xp_exe()))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_vista_exe"), tr.SetWorkspace(url_4))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "mac_dmg_10.6-10.8"), tr.SetWorkspace(url_5))
    req_08 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "mac_dmg_10.9"), tr.SetWorkspace(url_6))
    req_09 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "linux_deb"), tr.SetWorkspace(get_linux_deb()))
    req_10 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "linux_rpm"), tr.SetWorkspace(get_linux_rpm()))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    web_space = hp.get_web_space(GoogleChromeTestData.WEB_SPACE_HTML_PATH_2)
    app_url = GoogleChromeTestData.WEB_SPACE_URL_2
    return hp.get_entry_for_google_play_store(web_space, app_url, "apk_date")


def get_entry_3():
    web_space = hp.get_web_space(GoogleChromeTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Win_ver", "Win_date", '_win_size', (1, 1))


def get_entry_4():
    web_space = hp.get_web_space(GoogleChromeTestData.WEB_SPACE_HTML_PATH_4)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Mac_ver", "Mac_date", "Mac_size", (1, 1))


def get_entry_5():
    web_space = hp.get_web_space(GoogleChromeTestData.WEB_SPACE_HTML_PATH_5)
    app_url = GoogleChromeTestData.WEB_SPACE_URL_5
    return hp.get_entry_for_itunes(web_space, app_url, "ios_ver", "ios_size")


class GoogleChromeTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = GoogleChromeTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.APP_WEBSITE, collector.get_collectibles()['app_website'])
        self.assertEqual(get_win_exe_64bit(), collector.get_collectibles()['win_exe_64bit'])
        self.assertEqual(get_win_exe_32bit(), collector.get_collectibles()['win_exe_32bit'])
        self.assertEqual(get_mac_dmg_1010plus(), collector.get_collectibles()['mac_dmg_10.10+'])
        self.assertEqual(get_win_xp_exe(), collector.get_collectibles()['win_xp_exe'])
        self.assertEqual(get_win_vista_exe(), collector.get_collectibles()['win_vista_exe'])
        self.assertEqual(get_mac_dmg_106_108(), collector.get_collectibles()['mac_dmg_10.6-10.8'])
        self.assertEqual(get_mac_dmg_109(), collector.get_collectibles()['mac_dmg_10.9'])
        self.assertEqual(get_linux_deb(), collector.get_collectibles()['linux_deb'])
        self.assertEqual(get_linux_rpm(), collector.get_collectibles()['linux_rpm'])
        self.assertEqual(dt.WEB_SPACE_URL_2, collector.get_collectibles()['google_play_apk'])
        self.assertEqual(dt.expected_apk_date, collector.get_collectibles()['apk_date'])
        self.assertEqual(str(), collector.get_collectibles()['_gp_size'])
        self.assertEqual(str(), collector.get_collectibles()['_gp_ver'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(str(), collector.get_collectibles()['_win_size'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_mac_date, collector.get_collectibles()['Mac_date'])
        self.assertEqual(dt.expected_mac_size, collector.get_collectibles()['Mac_size'])
        self.assertEqual(dt.expected_ios_ver, collector.get_collectibles()['ios_ver'])
        self.assertEqual(dt.expected_ios_size, collector.get_collectibles()['ios_size'])
        self.assertEqual(dt.WEB_SPACE_URL_5, collector.get_collectibles()['ios_itunes'])


if __name__ == '__main__':
    unittest.main()
