import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import SetWorkspace
from collector.triggers import Find
from collector.triggers import GetSubset
from collector.triggers import CutAside
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space
from collector.helpers import get_entry_for_dobreprogramy_pl
from collector.helpers import get_entry_for_majorgeeks_2

configure_logging(r"../test_log.txt")
logging.debug("Tests for: IObit Smart Defrag")


class SmartDefragTestData:
    APP_NAME = "Smart Defrag"
    WEB_SPACE_URL_1 = "https://www.iobit.com/en/iobitsmartdefrag.php"
    WEB_SPACE_URL_2 = "https://www.majorgeeks.com/mg/getmirror/iobit_smartdefrag,1.html"
    WEB_SPACE_URL_3 = "https://www.dobreprogramy.pl/IObit-Smart-Defrag,Program,Windows,12941.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/isd_web_Windows.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/isd_web_Windows2.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        exe_1 = b'aHR0cHM6Ly9zdG9yZS5pb2JpdC5jb20vYWZmaWxpYXRlLnBocD9BQ0NPVU5UPUxPQklUJmFtcDtBRkZJTElBVEU9ODcwJmF' \
                b'tcDtQQVRIPWh0dHA6Ly9maWxlczEubWFqb3JnZWVrcy5jb20vOTcyMmIxZGI0MmFhZWFkZmY5NzNkM2M1MWMxMjJmMWQ5YT' \
                b'RjNTE2Ni9kcml2ZXMvc21hcnQtZGVmcmFnLXNldHVwLmV4ZQ=='
        exe_2 = b'aHR0cHM6Ly9zdG9yZS5pb2JpdC5jb20vYWZmaWxpYXRlLnBocD9BQ0NPVU5UPUxPQklUJmFtcDtBRkZJTElBVEU9ODcwJmF' \
                b'tcDtQQVRIPWh0dHA6Ly9maWxlczIubWFqb3JnZWVrcy5jb20vOGVlZDYzNTZkNzlkN2FjNTkwMDgwOWRkODczMjdlMWIzNz' \
                b'c1MDA1MS9kcml2ZXMvc21hcnQtZGVmcmFnLXNldHVwLmV4ZQ=='
        self.expected_win_exe_tuple = (decode_base64(exe_1), decode_base64(exe_2))
        self.expected_win_ver = decode_base64(b'Ni4yLjAuMTM4')
        self.expected_win_date = decode_base64(b'MjAxOS0wMy0wNw==')
        self.expected_win_size = decode_base64(b'MTUsMTIgTUI=')


def get_entry_1():
    app_website = str(SmartDefragTestData.WEB_SPACE_URL_1)
    req_01 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(app_website))
    chain_request = (req_01,)
    return ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    web_space = get_web_space(SmartDefragTestData.WEB_SPACE_HTML_PATH_2)
    return get_entry_for_majorgeeks_2(web_space, "win_exe")


def get_entry_3():
    web_space = get_web_space(SmartDefragTestData.WEB_SPACE_HTML_PATH_3)
    return get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size")


class SmartDefragTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = SmartDefragTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertIn(collector.get_collectibles()['win_exe'], dt.expected_win_exe_tuple)
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])


if __name__ == '__main__':
    unittest.main()
