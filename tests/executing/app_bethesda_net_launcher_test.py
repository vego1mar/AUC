import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class BethesdaNetLauncherTestData:
    APP_NAME = "Bethesda.net Launcher"
    WEB_SPACE_URL_1 = "https://bethesda.net/pl/game/bethesda-launcher"
    WEB_SPACE_URL_2 = "https://www.instalki.pl/programy/download/Windows/akcesoria/Bethesda_launcher.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/bethesdaNet_web_Windows.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.expected_win_ver = '1.45.14'
        self.expected_win_size = '8.7 MB'
        self.expected_win_date = '09.05.2019'


def get_win_exe():
    return 'http://download.cdp.bethesda.net/BethesdaNetLauncher_Setup.exe'


def get_entry_1():
    app_website = BethesdaNetLauncherTestData.WEB_SPACE_URL_1
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe"), tr.SetWorkspace(get_win_exe()))
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
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(get_win_exe(), collector.get_collectibles()['win_exe'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['Win_ver'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])


if __name__ == '__main__':
    unittest.main()
