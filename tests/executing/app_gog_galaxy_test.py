import unittest
import logging
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr

hp.configure_logging(r"../test_log.txt")
logging.debug("Tests for: GOG Galaxy")


class GOGGalaxyTestData:
    APP_NAME = "GOG Galaxy"
    WEB_SPACE_URL_1 = "https://www.gog.com/galaxy"
    WEB_SPACE_URL_2 = "https://www.majorgeeks.com/files/details/gog_galaxy.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/gog_galaxy_web_WinMac.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/gog_galaxy_web_Windows.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        mac_pkg = b'aHR0cHM6Ly9jb250ZW50LXN5c3RlbS5nb2cuY29tL29wZW5fbGluay9kb3dubG9hZD9wYXRoPS9vcGVuL2dhbGF4eS9jbG' \
                  b'llbnQvZ2FsYXh5X2NsaWVudF8xLjIuNTQuMjcucGtn'
        mac_ver = b'MS4yLjU0LjI3'
        windows_exe = b'aHR0cHM6Ly9jb250ZW50LXN5c3RlbS5nb2cuY29tL29wZW5fbGluay9kb3dubG9hZD9wYXRoPS9vcGVuL2dhbGF4eS' \
                      b'9jbGllbnQvc2V0dXBfZ2FsYXh5XzEuMi41NC4yMy5leGU='
        windows_ver = b'MS4yLjU0LjIz'
        self.expected_mac_pkg = hp.decode_base64(mac_pkg)
        self.expected_windows_exe = hp.decode_base64(windows_exe)
        self.expected_mac_ver = hp.decode_base64(mac_ver)
        self.expected_windows_ver = hp.decode_base64(windows_ver)
        self.expected_date_published = hp.decode_base64(b'MDMvMTcvMjAxOSAxMjoyNiBQTQ==')
        self.expected_file_size = hp.decode_base64(b'MjE2IE1C')


def get_entry_1():
    regex = r"[\d]+.[\d]+.[\d]+.[\d]+"
    req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find('class="ng-hide"'))
    req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("a", tr.TagType.ATTRIBUTED, 2))
    req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(1))
    req_4 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Mac_pkg"), tr.FetchAttribute("href"))
    req_5 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Mac_ver"), tr.GetRegexMatch(regex))
    req_6 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(0))
    req_7 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Windows_exe"), tr.FetchAttribute("href"))
    req_8 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "Windows_ver"), tr.GetRegexMatch(regex))
    chain_request_1 = (req_1, req_2, req_3, req_4, req_5, req_6, req_7, req_8)
    return ex.ExecutionOrderEntry(chain_request_1, hp.get_web_space(GOGGalaxyTestData.WEB_SPACE_HTML_PATH_1))


def get_entry_2():
    req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.RetrieveTags("meta", tr.TagType.META, 15))
    req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(9))
    req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "date_published"), tr.FetchAttribute("content"))
    req_4 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(13))
    req_5 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "file_size"), tr.FetchAttribute("content"))
    chain_request_2 = (req_1, req_2, req_3, req_4, req_5)
    return ex.ExecutionOrderEntry(chain_request_2, hp.get_web_space(GOGGalaxyTestData.WEB_SPACE_HTML_PATH_2))


def get_entry_3():
    req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"),
                                 tr.SetWorkspace(GOGGalaxyTestData.WEB_SPACE_URL_1))
    chain_request_3 = (req_1,)
    return ex.ExecutionOrderEntry(chain_request_3, str())


class GogGalaxyTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = GOGGalaxyTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.expected_mac_pkg, collector.get_collectibles()['Mac_pkg'])
        self.assertEqual(dt.expected_mac_ver, collector.get_collectibles()['Mac_ver'])
        self.assertEqual(dt.expected_windows_exe, collector.get_collectibles()['Windows_exe'])
        self.assertEqual(dt.expected_windows_ver, collector.get_collectibles()['Windows_ver'])
        self.assertEqual(dt.expected_date_published, collector.get_collectibles()['date_published'])
        self.assertEqual(dt.expected_file_size, collector.get_collectibles()['file_size'])
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])


if __name__ == '__main__':
    unittest.main()
