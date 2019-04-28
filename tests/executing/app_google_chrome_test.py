import unittest
import logging
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr

hp.configure_logging(r"../test_log.txt")
logging.debug("Tests for: Google Chrome")


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
        self.expected_apk_date = hp.decode_base64(b'TWFyY2ggMjYsIDIwMTk=')
        self.expected_win_ver = hp.decode_base64(b'NzMuMC4zNjgzLjEwMw==')
        self.expected_win_date = hp.decode_base64(b'MjAxOS0wNC0wNQ==')
        self.expected_mac_ver = hp.decode_base64(b'NzMuMC4zNjgzLjEwMw==')
        self.expected_mac_date = hp.decode_base64(b'MjAxOS0wNC0wNQ==')
        self.expected_mac_size = hp.decode_base64(b'NzQsMzYgTUI=')
        self.expected_ios_ver = hp.decode_base64(b'VmVyc2lvbiA3My4wLjM2ODMuNjg=')
        self.expected_ios_size = hp.decode_base64(b'NzUuMyBNQg==')


def get_win_exe_64bit():
    win_exe = b'aHR0cHM6Ly9kbC5nb29nbGUuY29tL3RhZy9zL2FwcGd1aWQlM0QlN0I4QTY5RDM0NS1ENTY0LTQ2M0MtQUZGMS1BNjlEOUU1MzBG' \
              b'OTYlN0QlMjZpaWQlM0QlN0JEQTZGRTA2NS1FNDk0LUY1RDQtMDg0Mi1DOUU0MEIyQjJDNzElN0QlMjZsYW5nJTNEZW4lMjZicm93' \
              b'c2VyJTNENCUyNnVzYWdlc3RhdHMlM0QwJTI2YXBwbmFtZSUzREdvb2dsZSUyNTIwQ2hyb21lJTI2bmVlZHNhZG1pbiUzRHByZWZl' \
              b'cnMlMjZhcCUzRHg2NC1zdGFibGUtc3RhdHNkZWZfMSUyNmluc3RhbGxkYXRhaW5kZXglM0RlbXB0eS91cGRhdGUyL2luc3RhbGxl' \
              b'cnMvQ2hyb21lU2V0dXAuZXhl'
    return hp.decode_base64(win_exe)


def get_win_exe_32bit():
    win_exe = b'aHR0cHM6Ly9kbC5nb29nbGUuY29tL3RhZy9zL2FwcGd1aWQlM0QlN0I4QTY5RDM0NS1ENTY0LTQ2M0MtQUZGMS1BNjlEOUU1MzBG' \
              b'OTYlN0QlMjZpaWQlM0QlN0JEQTZGRTA2NS1FNDk0LUY1RDQtMDg0Mi1DOUU0MEIyQjJDNzElN0QlMjZsYW5nJTNEZW4lMjZicm93' \
              b'c2VyJTNENCUyNnVzYWdlc3RhdHMlM0QwJTI2YXBwbmFtZSUzREdvb2dsZSUyNTIwQ2hyb21lJTI2bmVlZHNhZG1pbiUzRHByZWZl' \
              b'cnMlMjZhcCUzRHN0YWJsZS1hcmNoX3g4Ni1zdGF0c2RlZl8xJTI2aW5zdGFsbGRhdGFpbmRleCUzRGVtcHR5L3VwZGF0ZTIvaW5z' \
              b'dGFsbGVycy9DaHJvbWVTZXR1cC5leGU='
    return hp.decode_base64(win_exe)


def get_mac_dmg_1010plus():
    mac_dmg = b'aHR0cHM6Ly9kbC5nb29nbGUuY29tL2Nocm9tZS9tYWMvc3RhYmxlL0dHUk8vZ29vZ2xlY2hyb21lLmRtZw=='
    return hp.decode_base64(mac_dmg)


def get_win_xp_exe():
    win_exe = b'aHR0cHM6Ly9kbC5nb29nbGUuY29tL3RhZy9zL2FwcGd1aWQlM0QlN0I4QTY5RDM0NS1ENTY0LTQ2M0MtQUZGMS1BNjlEOUU1MzBG' \
              b'OTYlN0QlMjZpaWQlM0QlN0JEQTZGRTA2NS1FNDk0LUY1RDQtMDg0Mi1DOUU0MEIyQjJDNzElN0QlMjZsYW5nJTNEZW4lMjZicm93' \
              b'c2VyJTNENCUyNnVzYWdlc3RhdHMlM0QwJTI2YXBwbmFtZSUzREdvb2dsZSUyNTIwQ2hyb21lJTI2bmVlZHNhZG1pbiUzRHByZWZl' \
              b'cnMlMjZhcCUzRHN0YWJsZS1hcmNoX3g4NiUyNmluc3RhbGxkYXRhaW5kZXglM0RlbXB0eS91cGRhdGUyL2luc3RhbGxlcnMvQ2hy' \
              b'b21lU2V0dXAuZXhl'
    return hp.decode_base64(win_exe)


def get_win_vista_exe():
    win_exe = b'aHR0cHM6Ly9kbC5nb29nbGUuY29tL3RhZy9zL2FwcGd1aWQlM0QlN0I4QTY5RDM0NS1ENTY0LTQ2M0MtQUZGMS1BNjlEOUU1MzBG' \
              b'OTYlN0QlMjZpaWQlM0QlN0JEQTZGRTA2NS1FNDk0LUY1RDQtMDg0Mi1DOUU0MEIyQjJDNzElN0QlMjZsYW5nJTNEZW4lMjZicm93' \
              b'c2VyJTNENCUyNnVzYWdlc3RhdHMlM0QwJTI2YXBwbmFtZSUzREdvb2dsZSUyNTIwQ2hyb21lJTI2bmVlZHNhZG1pbiUzRHByZWZl' \
              b'cnMlMjZhcCUzRHN0YWJsZS1hcmNoX3g4NiUyNmluc3RhbGxkYXRhaW5kZXglM0RlbXB0eS91cGRhdGUyL2luc3RhbGxlcnMvQ2hy' \
              b'b21lU2V0dXAuZXhl'
    return hp.decode_base64(win_exe)


def get_mac_dmg_106_108():
    mac_dmg = b'aHR0cHM6Ly9kbC5nb29nbGUuY29tL2RsL2Nocm9tZS9tYWMvbGVnYWN5L0dHUk8vZ29vZ2xlY2hyb21lLmRtZw=='
    return hp.decode_base64(mac_dmg)


def get_mac_dmg_109():
    mac_dmg = b'aHR0cHM6Ly9kbC5nb29nbGUuY29tL2RsL2Nocm9tZS9tYWMvbGVnYWN5MTBfOS9HR1JPL2dvb2dsZWNocm9tZS5kbWc='
    return hp.decode_base64(mac_dmg)


def get_linux_deb():
    deb = b'aHR0cHM6Ly9kbC5nb29nbGUuY29tL2xpbnV4L2RpcmVjdC9nb29nbGUtY2hyb21lLXN0YWJsZV9jdXJyZW50X2FtZDY0LmRlYg=='
    return hp.decode_base64(deb)


def get_linux_rpm():
    rpm = b'aHR0cHM6Ly9kbC5nb29nbGUuY29tL2xpbnV4L2RpcmVjdC9nb29nbGUtY2hyb21lLXN0YWJsZV9jdXJyZW50X3g4Nl82NC5ycG0='
    return hp.decode_base64(rpm)


def get_entry_1():
    app_website = GoogleChromeTestData.APP_WEBSITE
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe_64bit"),
                                  tr.SetWorkspace(get_win_exe_64bit()))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe_32bit"),
                                  tr.SetWorkspace(get_win_exe_32bit()))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "mac_dmg_10.10+"),
                                  tr.SetWorkspace(get_mac_dmg_1010plus()))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_xp_exe"), tr.SetWorkspace(get_win_xp_exe()))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_vista_exe"),
                                  tr.SetWorkspace(get_win_vista_exe()))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "mac_dmg_10.6-10.8"),
                                  tr.SetWorkspace(get_mac_dmg_106_108()))
    req_08 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "mac_dmg_10.9"),
                                  tr.SetWorkspace(get_mac_dmg_109()))
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
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Win_ver", "Win_date")


def get_entry_4():
    web_space = hp.get_web_space(GoogleChromeTestData.WEB_SPACE_HTML_PATH_4)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Mac_ver", "Mac_date", "Mac_size")


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
        self.assertEqual(str(), collector.get_collectibles()['_size'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_mac_date, collector.get_collectibles()['Mac_date'])
        self.assertEqual(dt.expected_mac_size, collector.get_collectibles()['Mac_size'])
        self.assertEqual(dt.expected_ios_ver, collector.get_collectibles()['ios_ver'])
        self.assertEqual(dt.expected_ios_size, collector.get_collectibles()['ios_size'])
        self.assertEqual(dt.WEB_SPACE_URL_5, collector.get_collectibles()['ios_itunes'])


if __name__ == '__main__':
    unittest.main()
