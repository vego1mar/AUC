import unittest
import logging
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr

hp.configure_logging(r"../test_log.txt")
logging.debug("Tests for: Bethesda.net Launcher")


class BethesdaNetLauncherTestData:
    APP_NAME = "Bethesda.net Launcher"
    APP_WEBSITE = "https://bethesda.net/pl/game/bethesda-launcher"
    WEB_SPACE_URL_2 = "https://www.instalki.pl/programy/download/Windows/akcesoria/Bethesda_launcher.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/bethesdaNet_web_Windows.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.expected_win_ver = hp.decode_base64(b'MS40NS4xMA==')
        self.expected_win_size = hp.decode_base64(b'OC43IE1C')
        self.expected_win_date = hp.decode_base64(b'MjEuMDIuMjAxOQ==')


class TestDataOnline(BethesdaNetLauncherTestData):
    def __init__(self):
        super().__init__()
        self.execution_order.list[1].html_data = hp.fetch_html(self.WEB_SPACE_URL_2)


def get_const_win_exe():
    win_exe = b'aHR0cDovL2Rvd25sb2FkLmNkcC5iZXRoZXNkYS5uZXQvQmV0aGVzZGFOZXRMYXVuY2hlcl9TZXR1cC5leGU='
    return hp.decode_base64(win_exe)


def get_entry_1():
    app_website = BethesdaNetLauncherTestData.APP_WEBSITE
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "const_win_exe"),
                                  tr.SetWorkspace(get_const_win_exe()))
    chain_request = (req_01, req_02)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("h1"))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.Find("version"))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset('>', '<'))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Win_ver"), tr.CutAside(1, 1))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("h1"))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("span", tr.TagType.SIMPLE, 2))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(0))
    req_08 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Win_size"), tr.CutAside(6, 7))
    req_09 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(1))
    req_10 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Win_date"), tr.CutAside(6, 7))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10)
    return ex.ExecutionOrderEntry(chain_request, hp.get_web_space(BethesdaNetLauncherTestData.WEB_SPACE_HTML_PATH_2))


class BethesdaNetLauncherTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = BethesdaNetLauncherTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.APP_WEBSITE, collector.get_collectibles()['app_website'])
        self.assertEqual(get_const_win_exe(), collector.get_collectibles()['const_win_exe'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])

    @unittest.skip("Online test")
    def test_package_collecting_online(self):
        # given
        dt = TestDataOnline()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.APP_WEBSITE, collector.get_collectibles()['app_website'])
        self.assertEqual(get_const_win_exe(), collector.get_collectibles()['const_win_exe'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])


if __name__ == '__main__':
    unittest.main()
