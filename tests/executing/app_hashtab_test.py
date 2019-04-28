import unittest
import logging
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr

hp.configure_logging(r"../test_log.txt")
logging.debug("Tests for: HashTab")


class HashTabTestData:
    APP_NAME = "HashTab"
    WEB_SPACE_URL_1 = "http://implbits.com/products/hashtab/"
    WEB_SPACE_URL_2 = "https://www.majorgeeks.com/files/details/hashtab.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/hashTab_web_Windows.base64"
    WEB_SPACE_HTML_PATH_2 = r"../resources/hashTab_web_Windows2.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        win_exe = b'aHR0cDovL2ltcGxiaXRzLmNvbS9wcm9kdWN0cy9oYXNodGFiL0hhc2hUYWJfdjYuMC4wLjM0X1NldHVwLmV4ZQ=='
        win_xp_exe = b'aHR0cDovL2ltcGxiaXRzLmNvbS9wcm9kdWN0cy9oYXNodGFiL0hhc2hUYWJfdjUuMi4wLjE0X1NldHVwLmV4ZQ=='
        self.expected_win_exe = hp.decode_base64(win_exe)
        self.expected_win_ver = hp.decode_base64(b'Ni4wLjAuMzQ=')
        self.expected_win_xp_exe = hp.decode_base64(win_xp_exe)
        self.expected_win_xp_ver = hp.decode_base64(b'NS4yLjAuMTQ=')
        self.expected_win_date = hp.decode_base64(b'MDcvMjEvMjAxNw==')
        self.expected_win_size = hp.decode_base64(b'MS4xMiBNQg==')


def get_entry_1():
    app_website = str(HashTabTestData.WEB_SPACE_URL_1)
    chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:/_"
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("h2"))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("a", tr.TagType.ATTRIBUTED, 1))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(0))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.FetchAttribute("href"))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.CutAside(2, 0))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe"), tr.AddText(app_website, str()))
    req_08 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.Delete("", chars))
    req_09 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_ver"), tr.CutAside(1, 1))
    req_10 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("Windows XP"))
    req_11 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("a", tr.TagType.ATTRIBUTED, 1))
    req_12 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(0))
    req_13 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.FetchAttribute("href"))
    req_14 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.CutAside(2, 0))
    req_15 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_xp_exe"), tr.AddText(app_website, str()))
    req_16 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.Delete(str(), chars))
    req_17 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_xp_ver"), tr.CutAside(1, 1))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10,
                     req_11, req_12, req_13, req_14, req_15, req_16, req_17)
    return ex.ExecutionOrderEntry(chain_request, hp.get_web_space(HashTabTestData.WEB_SPACE_HTML_PATH_1))


def get_entry_2():
    web_space = hp.get_web_space(HashTabTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_majorgeeks(web_space, "Win_date", "Win_size")


class HashTabTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = HashTabTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_exe, collector.get_collectibles()['win_exe'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_xp_exe, collector.get_collectibles()['win_xp_exe'])
        self.assertEqual(dt.expected_win_xp_ver, collector.get_collectibles()['win_xp_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['Win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['Win_size'])


if __name__ == '__main__':
    unittest.main()
