import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class DvdFabVirtualDriveTestData:
    APP_NAME = "DVDFab Virtual Drive"
    WEB_SPACE_URL_1 = "https://www.dvdfab.cn/virtual_drive.htm?trackID=downloadpage"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/DVDFab-Virtual-Drive,Program,Windows,19396.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/dvdfab_vd_web_Windows1.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/dvdfab_vd_web_Windows2.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.expected_win_exe = 'https://www.dvdfab.cn/mlink/download.php?g=VIRTUALDRIVE'
        self.expected_win_ver = '1.5.1.1'
        self.expected_win_date = '2014-09-02'
        self.expected_win_size = '0,79 MB'


def get_entry_1():
    app_website = DvdFabVirtualDriveTestData.WEB_SPACE_URL_1
    base_url = 'https://www.dvdfab.cn'
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("try-icon"))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.FetchAttribute("href"))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe"), tr.AddText(base_url, str()))
    chain_request = (req_01, req_02, req_03, req_04)
    return ex.ExecutionOrderEntry(chain_request, hp.get_web_space(DvdFabVirtualDriveTestData.WEB_SPACE_HTML_PATH_1))


def get_entry_2():
    web_space = hp.get_web_space(DvdFabVirtualDriveTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (1, 0))


class DvdFabVirtualDriveTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = DvdFabVirtualDriveTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_exe, collector.get_collectibles()['win_exe'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])


if __name__ == '__main__':
    unittest.main()
