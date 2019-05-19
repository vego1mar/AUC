import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class PotPlayerTestData:
    APP_NAME = "PotPlayer"
    WEB_SPACE_URL_1 = "https://daumpotplayer.com/download/"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/PotPlayer,Program,Windows,22586.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/potplayer_web_Windows.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/potplayer_web_Windows2.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.expected_win_exe_32 = 'https://daumpotplayer.com/wp-content/uploads/2019/05/PotPlayerSetup%20%282%29.exe'
        self.expected_win_exe_64 = 'https://daumpotplayer.com/wp-content/uploads/2019/05/PotPlayerSetup64%20%282%29.exe'
        self.expected_win_beta_exe = 'https://daumpotplayer.com/wp-content/uploads/2019/02/PotPlayerSBeta.exe'
        self.expected_win_beta_ver = '1.7.17797'
        self.expected_win_ver = '1.7.18346'
        self.expected_win_date = '2019-04-20'
        self.expected_win_size = '26,20 MB'


def get_entry_1():
    app_website = PotPlayerTestData.WEB_SPACE_URL_1
    regex = r"[\d]+[.][\d]+[.][\d]+"
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("download_potplayer"))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("a", tr.TagType.ATTRIBUTED, 3))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(0))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe_32bit"), tr.FetchAttribute("href"))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(1))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe_64bit"), tr.FetchAttribute("href"))
    req_08 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(2))
    req_09 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_beta_exe"), tr.FetchAttribute("href"))
    req_10 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(2))
    req_11 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_beta_ver"), tr.GetRegexMatch(regex))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10,
                     req_11)
    return ex.ExecutionOrderEntry(chain_request, hp.get_web_space(PotPlayerTestData.WEB_SPACE_HTML_PATH_1))


def get_entry_2():
    web_space = hp.get_web_space(PotPlayerTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (1, 1))


class PotPlayerTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = PotPlayerTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_exe_32, collector.get_collectibles()['win_exe_32bit'])
        self.assertEqual(dt.expected_win_exe_64, collector.get_collectibles()['win_exe_64bit'])
        self.assertEqual(dt.expected_win_beta_exe, collector.get_collectibles()['win_beta_exe'])
        self.assertEqual(dt.expected_win_beta_ver, collector.get_collectibles()['win_beta_ver'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])


if __name__ == '__main__':
    unittest.main()
