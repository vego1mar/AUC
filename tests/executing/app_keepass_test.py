import unittest
import logging
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr

hp.configure_logging(r"../test_log.txt")
logging.debug("Tests for: KeePass Password Safe")


class KeePassTestData:
    APP_NAME = "KeePass"
    WEB_SPACE_URL_1 = "https://keepass.info/download.html"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/KeePass-Password-Safe,Program,Windows,12943.html"
    WEB_SPACE_URL_3 = "https://www.majorgeeks.com/mg/getmirror/keepass_password_safe,1.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/keepass_web_Windows.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/keepass_web_Windows2.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        exe_1 = hp.decode_base64(b'aHR0cDovL2ZpbGVzMS5tYWpvcmdlZWtzLmNvbS80NmZiMWQ5ZjE5Yzg0MjExYWU5MWVmMTdkMjI0OGQzZ'
                                 b'TlkNjgxNzcxL2ludGVybmV0L0tlZVBhc3MtMi40MS1TZXR1cC5leGU=')
        exe_2 = hp.decode_base64(b'aHR0cDovL2ZpbGVzMi5tYWpvcmdlZWtzLmNvbS8yODIxNDU3ZDNjMzdkMDQwNWYxYWMyYzBmOTdiODY3M'
                                 b'GM1NDM5NjdlL2ludGVybmV0L0tlZVBhc3MtMi40MS1TZXR1cC5leGU=')
        self.expected_win_ver = hp.decode_base64(b'Mi40MQ==')
        self.expected_win_date = hp.decode_base64(b'MjAxOS0wMS0wOQ==')
        self.expected_win_size = hp.decode_base64(b'MywxNSBNQg==')
        self.expected_win_exe_tuple = (exe_1, exe_2)


def get_entry_1():
    app_website = KeePassTestData.WEB_SPACE_URL_1
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    chain_request = (req_01,)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    web_space = hp.get_web_space(KeePassTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (0, -1))


def get_entry_3():
    web_space = hp.get_web_space(KeePassTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe")


class KeePassTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = KeePassTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])
        self.assertIn(collector.get_collectibles()['win_exe'], dt.expected_win_exe_tuple)


if __name__ == '__main__':
    unittest.main()
