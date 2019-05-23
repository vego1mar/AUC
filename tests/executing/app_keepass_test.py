import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class KeePassTestData:
    APP_NAME = "KeePass"
    WEB_SPACE_URL_1 = "https://keepass.info/download.html"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/KeePass-Password-Safe,Program,Windows,12943.html"
    WEB_SPACE_URL_3 = "https://www.majorgeeks.com/mg/getmirror/keepass_password_safe,1.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/keepass_web_Windows.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/keepass_web_Windows2.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.expected_win_ver = '2.42.1'
        self.expected_win_date = '2019-05-04'
        self.expected_win_size = '3,16 MB'
        self.expected_win_exe_tuple = (
            'http://files1.majorgeeks.com/46fb1d9f19c84211ae91ef17d2248d3e9d681771/internet/KeePass-2.41-Setup.exe',
            'http://files1.majorgeeks.com/5b09cfeadfc6032f5a0e47fa42620261921a1ddc/internet/KeePass-2.42.1-Setup.exe',
            'http://files2.majorgeeks.com/2821457d3c37d0405f1ac2c0f97b8670c543967e/internet/KeePass-2.41-Setup.exe',
            'http://files2.majorgeeks.com/8eef6a9d9cd458429acc0ad45332564a0279a2c1/internet/KeePass-2.42.1-Setup.exe'
        )


def get_entry_1():
    app_website = KeePassTestData.WEB_SPACE_URL_1
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    chain_request = (req_01,)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    web_space = hp.get_web_space(KeePassTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (1, 0))


def get_entry_3():
    web_space = hp.get_web_space(KeePassTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe")


class KeePassTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = KeePassTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])
        self.assertIn(collector.get_collectibles()['win_exe'], dt.expected_win_exe_tuple)


if __name__ == '__main__':
    unittest.main()
