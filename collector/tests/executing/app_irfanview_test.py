import unittest
import logging
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import SpaceName
from collector.triggers import SetWorkspace
from collector.triggers import FetchAttribute
from collector.triggers import RetrieveTags
from collector.triggers import TagType
from collector.triggers import Find
from collector.triggers import SelectElement
from collector.triggers import GetRegexMatch
from collector.triggers import GetSubset
from collector.triggers import CutAside
from collector.triggers import FindNext
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
from collector.helpers import decode_base64
from collector.helpers import get_web_space
from collector.helpers import get_entry_for_dobreprogramy_pl
from collector.helpers import get_entry_for_ms_store

configure_logging(r"../test_log.txt")
logging.debug("Tests for: IrfanView")


class IrfanViewTestData:
    APP_NAME = "IrfanView"
    WEB_SPACE_URL_1 = "https://www.irfanview.com"
    WEB_SPACE_URL_2 = "https://www.irfanview.com/64bit.htm"
    WEB_SPACE_URL_3 = "https://www.irfanview.com/main_download_engl.htm"
    WEB_SPACE_URL_4 = "https://www.microsoft.com/en-us/p/irfanview/9nl0r0jnnzm0"
    WEB_SPACE_URL_5 = "https://www.dobreprogramy.pl/IrfanView,Program,Windows,12867.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/irfanview_web_URL2.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/irfanview_web_URL3.base64"
    WEB_SPACE_HTML_PATH_4 = r"../resources/irfanview_web_MSStore.base64"
    WEB_SPACE_HTML_PATH_5 = r"../resources/irfanview_web_Windows5.base64"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        self.execution_order.add_entry(get_entry_5(), True)
        exe_64 = b'aHR0cDovL2Rvd25sb2FkLmNuZXQuY29tL0lyZmFuVmlldy02NC1iaXQvMzAwMC0yMTkyXzQtNzY0NDQ3MTAuaHRtbD9wYXJ0P' \
                 b'WRsLSZhbXA7c3Viaj1kbCZhbXA7dGFnPWJ1dHRvbg=='
        zip_64 = b'aHR0cDovL3d3dy5pcmZhbnZpZXcuaW5mby9maWxlcy9pdmlldzQ1Ml94NjQuemlw'
        exe_64_de = b'aHR0cDovL3d3dy5pcmZhbnZpZXcuaW5mby9maWxlcy9pdmlldzQ1MmdfeDY0X3NldHVwLmV4ZQ=='
        zip_64_de = b'aHR0cDovL3d3dy5pcmZhbnZpZXcuaW5mby9maWxlcy9pdmlldzQ1MmdfeDY0LnppcA=='
        exe_64_plugins = b'aHR0cDovL3d3dy5mb3NzaHViLmNvbS9JcmZhblZpZXcuaHRtbC9pdmlldzQ1Ml9wbHVnaW5zX3g2NF9zZXR1cC5le' \
                         b'GU='
        zip_64_plugins = b'aHR0cDovL3d3dy5mb3NzaHViLmNvbS9JcmZhblZpZXcuaHRtbC9pdmlldzQ1Ml9wbHVnaW5zX3g2NC56aXA='
        exe_64_hash = b'MWUyZTAyMGNiZjA4Y2NiNTQwYzkyZjQyM2Y2YjhiNDQyOTZkMGYzMGFmOGY5MDRiMzMzY2RmMGNkOTA1Mjc3Ng=='
        zip_64_hash = b'Njc4MWJkYTFkYWFlNGFmYTcwMDNmOGYwMjBkNjdhZjg5ZTYwN2JlOGQyMjQzMGIzMGFiZDgwM2Y1Nzc4NWYyYg=='
        exe_64_de_hash = b'NzA5NTA1NDllNjQ0OGQ0OTU0NmFkMGYyZGMwNGJjZTk5MGFlOGY3ZjAwOGRhMGU5ZmJjN2QyNWJhNGU1MDA3Mg=='
        zip_64_de_hash = b'ZWQxOWI2YmJiMTgwZjdmOWJkY2YyNmNlYjc5OTkzOGU0NWRkNzIzNWEzOWYyNjliYzM3MjA1ODY0ZDFmNGQwYw=='
        exe_64_plugins_hash = b'MWViNWIwMmQ4MzJlZGI2MTBmMGU5OWI4N2MyNGE3ZDYwOTE4MGM3ODdhZjIzYWM3ZWNhNWVjYjA4NTU5NGQ3' \
                              b'NQ=='
        zip_64_plugins_hash = b'NTNkNDY2OGEyMmU5MTgzMTg2MDY1NGNlNWIwZDU3ZDllYmJhMGFjODVjZDJmMDg4NTJkNWU2MzE3NDY0YTk2' \
                              b'OA=='
        exe_32_hash = b'ZTdjY2FiNjY5NWYxNTg1OTk0MzYyMzcwNDJkN2FhYjg3NWRkNTQ0ODhjZjFlMDBiOTE3YmFhNDhjNjNjYmIyNg=='
        zip_32_hash = b'NDdlYjM1MDBkYWZjOTQ0ZmEzYmU3NDUxNmE1N2ViNmYyYjkyODdhNjhhYzFmMDNkNWY1MDY3YWMyYWZhNDNlMA=='
        exe_32_de_hash = b'NDA5YWM3MjlhMTI2M2JhNzBiMTk4ZWFmNTVkNGYzYmMxNmJkOTY5ODgwMWRjZjI4NzNlMmU2YjE2NGZlNTk0Ng=='
        zip_32_de_hash = b'ZDA2N2MwNzA1MDE2MzlmYjRhNDdmZDgxNjk5Nzc4MGRhMDQzMzI4OGE5NGU2MjhhYzhiMDZmNDAwNTI1ODU5ZQ=='
        exe_32 = b'aHR0cDovL2Rvd25sb2FkLmNuZXQuY29tL0lyZmFuVmlldy8zMDAwLTIxOTJfNC0xMDAyMTk2Mi5odG1sP3BhcnQ9ZGwtJmFt' \
                 b'cDtzdWJqPWRsJmFtcDt0YWc9YnV0dG9u'
        self.expected_win_exe_64 = decode_base64(exe_64)
        self.expected_win_zip_64 = decode_base64(zip_64)
        self.expected_win_exe_64_de = decode_base64(exe_64_de)
        self.expected_win_zip_64_de = decode_base64(zip_64_de)
        self.expected_win_exe_64_plugins = decode_base64(exe_64_plugins)
        self.expected_win_zip_64_plugins = decode_base64(zip_64_plugins)
        self.expected_win_exe_64_ver = decode_base64(b'VmVyc2lvbiA0LjUy')
        self.expected_win_exe_64_size = decode_base64(b'My4zNyBNQg==')
        self.expected_win_exe_64_hash = decode_base64(exe_64_hash)
        self.expected_win_zip_64_ver = decode_base64(b'VmVyc2lvbiA0LjUy')
        self.expected_win_zip_64_size = decode_base64(b'Mi45MSBNQg==')
        self.expected_win_zip_64_hash = decode_base64(zip_64_hash)
        self.expected_win_exe_64_de_ver = decode_base64(b'VmVyc2lvbiA0LjUy')
        self.expected_win_exe_64_de_size = decode_base64(b'My42MyBNQg==')
        self.expected_win_exe_64_de_hash = decode_base64(exe_64_de_hash)
        self.expected_win_zip_64_de_ver = decode_base64(b'VmVyc2lvbiA0LjUy')
        self.expected_win_zip_64_de_size = decode_base64(b'My4xNyBNQg==')
        self.expected_win_zip_64_de_hash = decode_base64(zip_64_de_hash)
        self.expected_win_exe_64_plugins_ver = decode_base64(b'VmVyc2lvbiA0LjUy')
        self.expected_win_exe_64_plugins_size = decode_base64(b'MjUuMjAgTUI=')
        self.expected_win_exe_64_plugins_hash = decode_base64(exe_64_plugins_hash)
        self.expected_win_zip_64_plugins_ver = decode_base64(b'VmVyc2lvbiA0LjUy')
        self.expected_win_zip_64_plugins_size = decode_base64(b'MjIuNzAgTUI=')
        self.expected_win_zip_64_plugins_hash = decode_base64(zip_64_plugins_hash)
        self.expected_win_exe_32_hash = decode_base64(exe_32_hash)
        self.expected_win_zip_32_hash = decode_base64(zip_32_hash)
        self.expected_win_exe_32_de_hash = decode_base64(exe_32_de_hash)
        self.expected_win_zip_32_de_hash = decode_base64(zip_32_de_hash)
        self.expected_win_exe_32 = decode_base64(exe_32)
        self.expected_ms_store_size = decode_base64(b'MjEuODQgTUI=')
        self.expected_win_ver = decode_base64(b'NC41Mg==')
        self.expected_win_date = decode_base64(b'MjAxOC0xMi0xMw==')
        self.expected_win_size = decode_base64(b'MiwzNiBNQg==')


def get_entry_1():
    app_website = IrfanViewTestData.WEB_SPACE_URL_1
    req_01 = InvocationRequest(Target(SpaceName.WORK, True, "app_website"), SetWorkspace(app_website))
    chain_request = (req_01,)
    return ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    def get_1st_version(name="__ver", p_tag_no=0):
        req_1 = InvocationRequest(Target(SpaceName.LIST), SelectElement(p_tag_no))
        req_2 = InvocationRequest(Target(SpaceName.WORK), GetSubset("<strong>", "</strong>"))
        req_3 = InvocationRequest(Target(SpaceName.WORK, True, name), GetRegexMatch(regex_ver))
        return req_1, req_2, req_3

    def get_1st_size(name="__size", p_tag_no=0):
        req_1 = InvocationRequest(Target(SpaceName.LIST), SelectElement(p_tag_no))
        req_2 = InvocationRequest(Target(SpaceName.WORK), GetSubset("<strong>", "</strong>"))
        req_3 = InvocationRequest(Target(SpaceName.WORK, True, name), GetRegexMatch(regex_size))
        return req_1, req_2, req_3

    def get_1st_hash(name="__hash", p_tag_no=0):
        req_1 = InvocationRequest(Target(SpaceName.LIST), SelectElement(p_tag_no))
        req_2 = InvocationRequest(Target(SpaceName.WORK), GetRegexMatch(regex_hash))
        req_3 = InvocationRequest(Target(SpaceName.WORK, True, name), CutAside(2, 0))
        return req_1, req_2, req_3

    def get_2nd_info_skip(p_tag_no=0):
        req_1 = InvocationRequest(Target(SpaceName.LIST), SelectElement(p_tag_no))
        req_2 = InvocationRequest(Target(SpaceName.WORK), Find("alt-download"))
        req_3 = InvocationRequest(Target(SpaceName.WORK), FindNext("alt-download"))
        req_4 = InvocationRequest(Target(SpaceName.WORK), GetSubset("<strong>", "</strong>"))
        return req_1, req_2, req_3, req_4

    def get_2nd_ver(name="__ver2", p_tag_no=0):
        req_t = get_2nd_info_skip(p_tag_no)
        req_5 = InvocationRequest(Target(SpaceName.WORK, True, name), GetRegexMatch(regex_ver))
        return req_t[0], req_t[1], req_t[2], req_t[3], req_5

    def get_2nd_size(name="__size2", p_tag_no=0):
        req_t = get_2nd_info_skip(p_tag_no)
        req_5 = InvocationRequest(Target(SpaceName.WORK, True, name), GetRegexMatch(regex_size))
        return req_t[0], req_t[1], req_t[2], req_t[3], req_5

    def get_2nd_hash(name="__hash2", p_tag_no=0):
        req_t = get_2nd_info_skip(p_tag_no)
        req_4 = InvocationRequest(Target(SpaceName.WORK), GetRegexMatch(regex_hash))
        req_5 = InvocationRequest(Target(SpaceName.WORK, True, name), CutAside(2, 0))
        return req_t[0], req_t[1], req_t[2], req_4, req_5

    regex_ver = r"[A-Za-z]+ [\d.]+"
    regex_size = r"[\d.]+ [A-Z]+"
    regex_hash = r": [a-z0-9]+"
    req_01 = InvocationRequest(Target(SpaceName.WEB), Find("64-bit downloads"))
    req_02 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("a", TagType.ATTRIBUTED, 6))
    req_03 = InvocationRequest(Target(SpaceName.LIST), SelectElement(0))
    req_04 = InvocationRequest(Target(SpaceName.WORK, True, "win_exe_64"), FetchAttribute("href"))
    req_05 = InvocationRequest(Target(SpaceName.LIST), SelectElement(1))
    req_06 = InvocationRequest(Target(SpaceName.WORK, True, "win_zip_64"), FetchAttribute("href"))
    req_07 = InvocationRequest(Target(SpaceName.LIST), SelectElement(2))
    req_08 = InvocationRequest(Target(SpaceName.WORK, True, "win_exe_64_de"), FetchAttribute("href"))
    req_09 = InvocationRequest(Target(SpaceName.LIST), SelectElement(3))
    req_10 = InvocationRequest(Target(SpaceName.WORK, True, "win_zip_64_de"), FetchAttribute("href"))
    req_11 = InvocationRequest(Target(SpaceName.LIST), SelectElement(4))
    req_12 = InvocationRequest(Target(SpaceName.WORK, True, "win_exe_64_plugins"), FetchAttribute("href"))
    req_13 = InvocationRequest(Target(SpaceName.LIST), SelectElement(5))
    req_14 = InvocationRequest(Target(SpaceName.WORK, True, "win_zip_64_plugins"), FetchAttribute("href"))
    req_15 = InvocationRequest(Target(SpaceName.WEB), Find("64-bit downloads"))
    req_16 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("p", TagType.SIMPLE, 3))
    req_17t = get_1st_version("win_exe_64_ver", 0)
    req_18t = get_1st_size("win_exe_64_size", 0)
    req_19t = get_1st_hash("win_exe_64_hash", 0)
    req_20t = get_2nd_ver("win_zip_64_ver", 0)
    req_21t = get_2nd_size("win_zip_64_size", 0)
    req_22t = get_2nd_hash("win_zip_64_hash", 0)
    req_23t = get_1st_version("win_exe_64_de_ver", 1)
    req_24t = get_1st_size("win_exe_64_de_size", 1)
    req_25t = get_1st_hash("win_exe_64_de_hash", 1)
    req_26t = get_2nd_ver("win_zip_64_de_ver", 1)
    req_27t = get_2nd_size("win_zip_64_de_size", 1)
    req_28t = get_2nd_hash("win_zip_64_de_hash", 1)
    req_29t = get_1st_version("win_exe_64_plugins_ver", 2)
    req_30t = get_1st_size("win_exe_64_plugins_size", 2)
    req_31t = get_1st_hash("win_exe_64_plugins_hash", 2)
    req_32t = get_2nd_ver("win_zip_64_plugins_ver", 2)
    req_33t = get_2nd_size("win_zip_64_plugins_size", 2)
    req_34t = get_2nd_hash("win_zip_64_plugins_hash", 2)
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10,
                     req_11, req_12, req_13, req_14, req_15, req_16, req_17t[0], req_17t[1], req_17t[2], req_18t[0],
                     req_18t[1], req_18t[2], req_19t[0], req_19t[1], req_19t[2], req_20t[0], req_20t[1], req_20t[2],
                     req_20t[3], req_20t[4], req_21t[0], req_21t[1], req_21t[2], req_21t[3], req_21t[4], req_22t[0],
                     req_22t[1], req_22t[2], req_22t[3], req_22t[4], req_23t[0], req_23t[1], req_23t[2], req_24t[0],
                     req_24t[1], req_24t[2], req_25t[0], req_25t[1], req_25t[2], req_26t[0], req_26t[1], req_26t[2],
                     req_26t[3], req_26t[4], req_27t[0], req_27t[1], req_27t[2], req_27t[3], req_27t[4], req_28t[0],
                     req_28t[1], req_28t[2], req_28t[3], req_28t[4], req_29t[0], req_29t[1], req_29t[2], req_30t[0],
                     req_30t[1], req_30t[2], req_31t[0], req_31t[1], req_31t[2], req_32t[0], req_32t[1], req_32t[2],
                     req_32t[3], req_32t[4], req_33t[0], req_33t[1], req_33t[2], req_33t[3], req_33t[4], req_34t[0],
                     req_34t[1], req_34t[2], req_34t[3], req_34t[4])
    return ExecutionOrderEntry(chain_request, get_web_space(IrfanViewTestData.WEB_SPACE_HTML_PATH_2))


def get_entry_3():
    def get_next_hash(name="__hash", selector=0):
        req_1 = InvocationRequest(Target(SpaceName.LIST), SelectElement(selector))
        req_2 = InvocationRequest(Target(SpaceName.WORK), GetRegexMatch(r"- [a-z0-9]+"))
        req_3 = InvocationRequest(Target(SpaceName.WORK, True, name), CutAside(2, 0))
        return req_1, req_2, req_3

    req_01 = InvocationRequest(Target(SpaceName.WEB), Find("<h2"))
    req_02 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("code", TagType.SIMPLE, 4))
    req_3t = get_next_hash("win_exe_32_hash", 0)
    req_4t = get_next_hash("win_zip_32_hash", 1)
    req_5t = get_next_hash("win_exe_32_de_hash", 2)
    req_6t = get_next_hash("win_zip_32_de_hash", 3)
    req_15 = InvocationRequest(Target(SpaceName.WEB), Find("<h3"))
    req_16 = InvocationRequest(Target(SpaceName.WORK), RetrieveTags("a", TagType.ATTRIBUTED, 1))
    req_17 = InvocationRequest(Target(SpaceName.LIST), SelectElement(0))
    req_18 = InvocationRequest(Target(SpaceName.WORK, True, "win_exe_32"), FetchAttribute("href"))
    chain_request = (req_01, req_02, req_3t[0], req_3t[1], req_3t[2], req_4t[0], req_4t[1], req_4t[2], req_5t[0],
                     req_5t[1], req_5t[2], req_6t[0], req_6t[1], req_6t[2], req_15, req_16, req_17, req_18)
    return ExecutionOrderEntry(chain_request, get_web_space(IrfanViewTestData.WEB_SPACE_HTML_PATH_3))


def get_entry_4():
    web_space = get_web_space(IrfanViewTestData.WEB_SPACE_HTML_PATH_4)
    app_url = IrfanViewTestData.WEB_SPACE_URL_4
    return get_entry_for_ms_store(web_space, app_url, "ms_store_size", "ms_store_page")


def get_entry_5():
    web_space = get_web_space(IrfanViewTestData.WEB_SPACE_HTML_PATH_5)
    return get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size")


class IrfanViewTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = IrfanViewTestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_win_exe_64, collector.get_collectibles()['win_exe_64'])
        self.assertEqual(dt.expected_win_zip_64, collector.get_collectibles()['win_zip_64'])
        self.assertEqual(dt.expected_win_exe_64_de, collector.get_collectibles()['win_exe_64_de'])
        self.assertEqual(dt.expected_win_zip_64_de, collector.get_collectibles()['win_zip_64_de'])
        self.assertEqual(dt.expected_win_exe_64_plugins, collector.get_collectibles()['win_exe_64_plugins'])
        self.assertEqual(dt.expected_win_zip_64_plugins, collector.get_collectibles()['win_zip_64_plugins'])
        self.assertEqual(dt.expected_win_exe_64_ver, collector.get_collectibles()['win_exe_64_ver'])
        self.assertEqual(dt.expected_win_exe_64_size, collector.get_collectibles()['win_exe_64_size'])
        self.assertEqual(dt.expected_win_exe_64_hash, collector.get_collectibles()['win_exe_64_hash'])
        self.assertEqual(dt.expected_win_zip_64_ver, collector.get_collectibles()['win_zip_64_ver'])
        self.assertEqual(dt.expected_win_zip_64_size, collector.get_collectibles()['win_zip_64_size'])
        self.assertEqual(dt.expected_win_zip_64_hash, collector.get_collectibles()['win_zip_64_hash'])
        self.assertEqual(dt.expected_win_exe_64_de_ver, collector.get_collectibles()['win_exe_64_de_ver'])
        self.assertEqual(dt.expected_win_exe_64_de_size, collector.get_collectibles()['win_exe_64_de_size'])
        self.assertEqual(dt.expected_win_exe_64_de_hash, collector.get_collectibles()['win_exe_64_de_hash'])
        self.assertEqual(dt.expected_win_zip_64_de_ver, collector.get_collectibles()['win_zip_64_de_ver'])
        self.assertEqual(dt.expected_win_zip_64_de_size, collector.get_collectibles()['win_zip_64_de_size'])
        self.assertEqual(dt.expected_win_zip_64_de_hash, collector.get_collectibles()['win_zip_64_de_hash'])
        self.assertEqual(dt.expected_win_exe_64_plugins_ver, collector.get_collectibles()['win_exe_64_plugins_ver'])
        self.assertEqual(dt.expected_win_exe_64_plugins_size, collector.get_collectibles()['win_exe_64_plugins_size'])
        self.assertEqual(dt.expected_win_exe_64_plugins_hash, collector.get_collectibles()['win_exe_64_plugins_hash'])
        self.assertEqual(dt.expected_win_zip_64_plugins_ver, collector.get_collectibles()['win_zip_64_plugins_ver'])
        self.assertEqual(dt.expected_win_zip_64_plugins_size, collector.get_collectibles()['win_zip_64_plugins_size'])
        self.assertEqual(dt.expected_win_zip_64_plugins_hash, collector.get_collectibles()['win_zip_64_plugins_hash'])
        self.assertEqual(dt.expected_win_exe_32_hash, collector.get_collectibles()['win_exe_32_hash'])
        self.assertEqual(dt.expected_win_zip_32_hash, collector.get_collectibles()['win_zip_32_hash'])
        self.assertEqual(dt.expected_win_exe_32_de_hash, collector.get_collectibles()['win_exe_32_de_hash'])
        self.assertEqual(dt.expected_win_zip_32_de_hash, collector.get_collectibles()['win_zip_32_de_hash'])
        self.assertEqual(dt.expected_win_exe_32, collector.get_collectibles()['win_exe_32'])
        self.assertEqual(dt.expected_ms_store_size, collector.get_collectibles()['ms_store_size'])
        self.assertEqual(dt.WEB_SPACE_URL_4, collector.get_collectibles()['ms_store_page'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])


if __name__ == '__main__':
    unittest.main()
