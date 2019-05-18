import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class DriverBoosterTestData:
    APP_NAME = "Malware Fighter"
    WEB_SPACE_URL_1 = "https://www.iobit.com/en/driver-booster.php"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/Driver-Booster,Program,Windows,41956.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/idb_web_Windows.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.expected_win_ver = '6.4.0.394'
        self.expected_win_date = '2019-04-24'
        self.expected_win_size = '20,53 MB'


def get_win_exe():
    return 'https://www.iobit.com/downloadcenter.php?product=driver-booster-free-new'


def get_entry_1():
    app_website = str(DriverBoosterTestData.WEB_SPACE_URL_1)
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe"), tr.SetWorkspace(get_win_exe()))
    chain_request = (req_01, req_02)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    web_space = hp.get_web_space(DriverBoosterTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (1, 1))


class DriverBoosterTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = DriverBoosterTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(get_win_exe(), collector.get_collectibles()['win_exe'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])


if __name__ == '__main__':
    unittest.main()
