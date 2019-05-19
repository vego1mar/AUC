import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class OriginTestData:
    APP_NAME = "Origin"
    APP_WEBSITE = "https://www.origin.com/pol/en-us/store/download"
    WEB_SPACE_URL_1 = "https://www.dobreprogramy.pl/Origin,Program,Windows,38298.html"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/Origin,Program,Mac,65868.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/origin_web_Windows.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/origin_web_Mac.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.expected_win_ver = '10.5.38.25027 '
        self.expected_win_date = '2019-04-24'
        self.expected_win_size = '60,46 MB'
        self.expected_mac_ver = '10.5.38'
        self.expected_mac_date = '2019-04-25'
        self.expected_mac_size = '121,97 MB'


def get_win_exe():
    return 'https://www.dm.origin.com/download/legacy'


def get_mac_dmg():
    return 'https://www.dm.origin.com/mac/download/legacy'


def get_entry_1():
    web_space = hp.get_web_space(OriginTestData.WEB_SPACE_HTML_PATH_1)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Win_ver", "Win_date", "Win_size", (1, 1))


def get_entry_2():
    web_space = hp.get_web_space(OriginTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "Mac_ver", "Mac_date", "Mac_size", (1, 1))


def get_entry_3():
    app_url = OriginTestData.APP_WEBSITE
    req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Win_exe"), tr.SetWorkspace(get_win_exe()))
    req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Mac_dmg"), tr.SetWorkspace(get_mac_dmg()))
    req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_url))
    chain_request_3 = (req_1, req_2, req_3)
    entry_3 = ex.ExecutionOrderEntry(chain_request_3, str())
    return entry_3


class ElectronicArtsOriginTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = OriginTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_mac_date, collector.get_collectibles()['Mac_date'])
        self.assertEqual(dt.expected_mac_size, collector.get_collectibles()['Mac_size'])
        self.assertEqual(get_win_exe(), collector.get_collectibles()['Win_exe'])
        self.assertEqual(get_mac_dmg(), collector.get_collectibles()['Mac_dmg'])
        self.assertEqual(OriginTestData.APP_WEBSITE, collector.get_collectibles()['app_website'])


if __name__ == '__main__':
    unittest.main()
