import unittest
import logging
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr

hp.configure_logging(r"../test_log.txt")
logging.debug("Tests for: Epic Games Launcher")


class EpicGamesLauncherTestData:
    APP_NAME = "Epic Games Launcher"
    WEB_SPACE_URL_1 = "https://www.epicgames.com/store/pl/"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/Epic-Games-Launcher,Program,Windows,100437.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/egl_web_WinMac.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/egl_web_Windows.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        variant_installer = b'aHR0cHM6Ly9sYXVuY2hlci1wdWJsaWMtc2VydmljZS1wcm9kMDYub2wuZXBpY2dhbWVzLmNvbS9sYXVuY2hlc' \
                            b'i9hcGkvaW5zdGFsbGVyL2Rvd25sb2FkL0VwaWNHYW1lc0xhdW5jaGVySW5zdGFsbGVyLm1zaQ=='
        self.expected_variant_installer = hp.decode_base64(variant_installer)
        self.expected_windows_msi = get_windows_msi()
        self.expected_mac_dmg = get_mac_dmg()
        self.expected_version = hp.decode_base64(b'OS4xMS4y')
        self.expected_date_published = hp.decode_base64(b'MjAxOS0wNC0wNQ==')
        self.expected_file_size = hp.decode_base64(b'MzIsMTUgTUI=')


def get_entry_1():
    req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find(r'id="cta"'))
    req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "variant_installer"), tr.FetchAttribute("href"))
    req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Windows_msi"), tr.SetWorkspace(get_windows_msi()))
    req_4 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Mac_dmg"), tr.SetWorkspace(get_mac_dmg()))
    chain_request_1 = (req_1, req_2, req_3, req_4)
    entry_1 = ex.ExecutionOrderEntry(chain_request_1, hp.get_web_space(EpicGamesLauncherTestData.WEB_SPACE_HTML_PATH_1))
    return entry_1


def get_entry_2():
    web_space = hp.get_web_space(EpicGamesLauncherTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "version", "date_published", "file_size")


def get_entry_3():
    req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"),
                                 tr.SetWorkspace(EpicGamesLauncherTestData.WEB_SPACE_URL_1))
    chain_request = (req_1,)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_windows_msi():
    win_msi = b'aHR0cHM6Ly9sYXVuY2hlci1wdWJsaWMtc2VydmljZS1wcm9kMDYub2wuZXBpY2dhbWVzLmNvbS9sYXVuY2hlci9hcGkvaW5zdGF' \
              b'sbGVyL2Rvd25sb2FkL0VwaWNHYW1lc0xhdW5jaGVySW5zdGFsbGVyLm1zaQ=='
    return hp.decode_base64(win_msi)


def get_mac_dmg():
    mac_dmg = b'aHR0cHM6Ly9sYXVuY2hlci1wdWJsaWMtc2VydmljZS1wcm9kMDYub2wuZXBpY2dhbWVzLmNvbS9sYXVuY2hlci9hcGkvaW5zdGF' \
              b'sbGVyL2Rvd25sb2FkL0VwaWNHYW1lc0xhdW5jaGVyLmRtZw=='
    return hp.decode_base64(mac_dmg)


class EpicGamesLauncherTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = EpicGamesLauncherTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

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
