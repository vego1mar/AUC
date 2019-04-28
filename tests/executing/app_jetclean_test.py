import unittest
import logging
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr

hp.configure_logging(r"../test_log.txt")
logging.debug("Tests for: JetClean")


class JetCleanTestData:
    APP_NAME = "JetClean"
    WEB_SPACE_URL_1 = "http://www.bluesprig.com/jetclean.html"
    WEB_SPACE_URL_2 = "https://www.dobreprogramy.pl/JetClean,Program,Windows,30357.html"
    WEB_SPACE_URL_3 = "https://www.majorgeeks.com/mg/getmirror/jetclean,1.html"
    WEB_SPACE_URL_4 = "https://www.majorgeeks.com/mg/getmirror/jetclean,2.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/jetclean_web_Windows.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/jetclean_web_Windows2.base64"
    WEB_SPACE_HTML_PATH_4 = r"../resources/jetclean_web_Windows3.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        win_exe_1 = hp.decode_base64(b'aHR0cDovL2ZpbGVzMi5tYWpvcmdlZWtzLmNvbS9mMWQ3ZDczNjYxZmEwYTUwMjJlOGFjNTFmYjNkOTF'
                                     b'kZjU3MTEyYzQzL2FsbGlub25lL2pldGNsZWFuLXNldHVwLmV4ZQ==')
        win_exe_2 = hp.decode_base64(b'aHR0cDovL2ZpbGVzMS5tYWpvcmdlZWtzLmNvbS8xOTgwMTdiOWEwNThhNWU3ODJkNjY0ZDJlOGI0MWI'
                                     b'xOGY5ZDlmNjlhL2FsbGlub25lL2pldGNsZWFuLXNldHVwLmV4ZQ==')
        win_exe_3 = hp.decode_base64(b'aHR0cDovL2ZpbGVzMi5tYWpvcmdlZWtzLmNvbS8wMWRlMzczM2YxNzNhYWIyMDZjNWUwNTM5NzUwYzU'
                                     b'3MzY3MmYxNGZhL2FsbGlub25lL2pldGNsZWFuLXNldHVwLmV4ZQ==')
        win_exe_4 = hp.decode_base64(b'aHR0cDovL2ZpbGVzMS5tYWpvcmdlZWtzLmNvbS9kYzBlNGIyZWZkYjVkYTE1ZjNjZjUxZTM5ODk4MzY'
                                     b'yMGRiNTFkZWQyL2FsbGlub25lL2pldGNsZWFuLXNldHVwLmV4ZQ==')
        self.expected_win_ver = hp.decode_base64(b'MS41LjAuMTI5')
        self.expected_win_date = hp.decode_base64(b'MjAxNi0wMi0yNg==')
        self.expected_win_size = hp.decode_base64(b'Myw2MCBNQg==')
        self.expected_win_exe_tuple = (win_exe_1, win_exe_2, win_exe_3, win_exe_4)


def get_entry_1():
    app_website = JetCleanTestData.WEB_SPACE_URL_1
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    chain_request = (req_01,)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    web_space = hp.get_web_space(JetCleanTestData.WEB_SPACE_HTML_PATH_2)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size")


def get_entry_3():
    web_space = hp.get_web_space(JetCleanTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_1")


def get_entry_4():
    web_space = hp.get_web_space(JetCleanTestData.WEB_SPACE_HTML_PATH_4)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_2")


class JetCleanTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = JetCleanTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])
        self.assertIn(collector.get_collectibles()['win_exe_1'], dt.expected_win_exe_tuple)
        self.assertIn(collector.get_collectibles()['win_exe_2'], dt.expected_win_exe_tuple)


if __name__ == '__main__':
    unittest.main()
