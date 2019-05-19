import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class JetCleanTestData:
    APP_NAME = "JetClean"
    WEB_SPACE_URL_1 = "http://www.bluesprig.com/jetclean.html"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/JetClean,Program,Windows,30357.html"
    WEB_SPACE_URL_3 = "https://www.majorgeeks.com/mg/getmirror/jetclean,1.html"
    WEB_SPACE_URL_4 = "https://www.majorgeeks.com/mg/getmirror/jetclean,2.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/jetclean_web_Windows.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/jetclean_web_Windows2.base64"
    WEB_SPACE_HTML_PATH_4 = r"../resources/jetclean_web_Windows3.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        self.expected_win_ver = '1.5.0.129'
        self.expected_win_date = '2016-02-26'
        self.expected_win_size = '3,60 MB'
        self.expected_win_exe_tuple = (
            'http://files1.majorgeeks.com/198017b9a058a5e782d664d2e8b41b18f9d9f69a/allinone/jetclean-setup.exe',
            'http://files1.majorgeeks.com/dc0e4b2efdb5da15f3cf51e398983620db51ded2/allinone/jetclean-setup.exe',
            'http://files1.majorgeeks.com/5b09cfeadfc6032f5a0e47fa42620261921a1ddc/allinone/jetclean-setup.exe',
            'http://files2.majorgeeks.com/f1d7d73661fa0a5022e8ac51fb3d91df57112c43/allinone/jetclean-setup.exe',
            'http://files2.majorgeeks.com/01de3733f173aab206c5e0539750c573672f14fa/allinone/jetclean-setup.exe',
            'http://files2.majorgeeks.com/c56596c4ccdc2ef32a2ab769175c6f55d7792ce1/allinone/jetclean-setup.exe'
        )


def get_entry_1():
    app_website = JetCleanTestData.WEB_SPACE_URL_1
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    chain_request = (req_01,)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    web_space = hp.get_web_space(JetCleanTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (1, 1))


def get_entry_3():
    web_space = hp.get_web_space(JetCleanTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_1")


def get_entry_4():
    web_space = hp.get_web_space(JetCleanTestData.WEB_SPACE_HTML_PATH_4)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_2")


class JetCleanTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = JetCleanTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])
        self.assertIn(collector.get_collectibles()['win_exe_1'], dt.expected_win_exe_tuple)
        self.assertIn(collector.get_collectibles()['win_exe_2'], dt.expected_win_exe_tuple)


if __name__ == '__main__':
    unittest.main()
