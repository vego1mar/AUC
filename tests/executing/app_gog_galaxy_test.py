import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class GOGGalaxyTestData:
    APP_NAME = "GOG Galaxy"
    WEB_SPACE_URL_1 = "https://www.gogalaxy.com/pl/"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/GOG-Galaxy,Program,Mac,65675.html"
    WEB_SPACE_URL_3 = "https://www.dobreprogramy.pl/GOG-Galaxy,Program,Windows,66878.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/gog_galaxy_web_Mac.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/gog_galaxy_web_Windows.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.expected_mac_ver = '1.2.37.14'
        self.expected_mac_date = '2018-02-26'
        self.expected_mac_size = '217,72 MB'
        self.expected_win_ver = '1.2.56.15'
        self.expected_win_date = '2019-05-06'
        self.expected_win_size = '215,82 MB'


def get_link():
    return 'https://www.majorgeeks.com/mg/getmirror/gog_galaxy,1.html'


def get_entry_1():
    app_url = GOGGalaxyTestData.WEB_SPACE_URL_1
    req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_url))
    req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "link"), tr.SetWorkspace(get_link()))
    chain_request_3 = (req_1, req_2)
    return ex.ExecutionOrderEntry(chain_request_3, str())


def get_entry_2():
    web_space = hp.get_web_space(GOGGalaxyTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, 'Mac_ver', 'Mac_date', 'Mac_size', (1, 1))


def get_entry_3():
    web_space = hp.get_web_space(GOGGalaxyTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_dobreprogramy_pl(web_space, 'Win_ver', 'Win_date', 'Win_size', (1, 1))


class GogGalaxyTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = GOGGalaxyTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_mac_date, collector.get_collectibles()['Mac_date'])
        self.assertEqual(dt.expected_mac_size, collector.get_collectibles()['Mac_size'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])


if __name__ == '__main__':
    unittest.main()
