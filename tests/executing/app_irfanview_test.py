import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


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
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        self.execution_order.add_entry(get_entry_5(), True)
        self.expected_win_exe_64 = 'http://download.cnet.com/IrfanView-64-bit/3000-2192_4-76444710.html?part=dl-&' \
                                   'amp;subj=dl&amp;tag=button'
        self.expected_win_zip_64 = 'http://www.irfanview.info/files/iview453_x64.zip'
        self.expected_win_exe_64_de = 'http://www.irfanview.info/files/iview453g_x64_setup.exe'
        self.expected_win_zip_64_de = 'http://www.irfanview.info/files/iview453g_x64.zip'
        self.expected_win_exe_64_plugins = 'http://www.fosshub.com/IrfanView.html/iview453_plugins_x64_setup.exe'
        self.expected_win_zip_64_plugins = 'http://www.fosshub.com/IrfanView.html/iview453_plugins_x64.zip'
        self.expected_win_exe_64_ver = 'Version 4.53'
        self.expected_win_exe_64_size = '3.42 MB'
        self.expected_win_exe_64_hash = '57b12aed758d5beb84263cdca2d4fa38d0bcffa882d9994921453b170edf149a'
        self.expected_win_zip_64_ver = 'Version 4.53'
        self.expected_win_zip_64_size = '2.96 MB'
        self.expected_win_zip_64_hash = '941056da4f50df44eeef46ca875255bd3d09c3aaf1bdfffad5f65de0a515ff02'
        self.expected_win_exe_64_de_ver = 'Version 4.53'
        self.expected_win_exe_64_de_size = '3.68 MB'
        self.expected_win_exe_64_de_hash = 'a7729a5372c8b9bf95ae98ef481bf4d5402ac0ca227001887cb55c5c85d27e1c'
        self.expected_win_zip_64_de_ver = 'Version 4.53'
        self.expected_win_zip_64_de_size = '3.22 MB'
        self.expected_win_zip_64_de_hash = '0c942b01ff4d56af4dfa58203f06d4aba7d7a80855cd649d187b6e0eaca0ef4f'
        self.expected_win_exe_64_plugins_ver = 'Version 4.53'
        self.expected_win_exe_64_plugins_size = '25.20 MB'
        self.expected_win_exe_64_plugins_hash = 'ac8d27b1241ef91e80a73bfc672342be7317f7d8bf47a615a00d01f5090f3d43'
        self.expected_win_zip_64_plugins_ver = 'Version 4.53'
        self.expected_win_zip_64_plugins_size = '22.70 MB'
        self.expected_win_zip_64_plugins_hash = 'd5e8ae1734264287e7b442fc062899c206c54bbd423304e125c4b1d1c58d28b1'
        self.expected_win_exe_32_hash = '7ebcf9fe4f4e41293d8808d2ad4b369577209dc64db04d79b1bce370e2c79b69'
        self.expected_win_zip_32_hash = '89c0d29424299f9b0d36248884059baf815f0b5d4adc9c3b426f61fe68f1444a'
        self.expected_win_exe_32_de_hash = 'c597df4fef1825c1df119a0b4115729e89d0ea97941e698ccbcb8eb4f2b0b7f2'
        self.expected_win_zip_32_de_hash = 'd0ab1f0eabd391baaed8061fbaf72729c3ab4a680968475e4dde639db7fcfbec'
        self.expected_win_exe_32 = 'http://www.fosshub.com/IrfanView.html'
        self.expected_ms_store_size = '21.84 MB'
        self.expected_win_ver = '4.53'
        self.expected_win_date = '2019-05-15'
        self.expected_win_size = '2,38 MB'


def get_entry_1():
    app_website = IrfanViewTestData.WEB_SPACE_URL_1
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    chain_request = (req_01,)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    def get_1st_version(name="__ver", p_tag_no=0):
        req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(p_tag_no))
        req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset("<strong>", "</strong>"))
        req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, name), tr.GetRegexMatch(regex_ver))
        return req_1, req_2, req_3

    def get_1st_size(name="__size", p_tag_no=0):
        req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(p_tag_no))
        req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset("<strong>", "</strong>"))
        req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, name), tr.GetRegexMatch(regex_size))
        return req_1, req_2, req_3

    def get_1st_hash(name="__hash", p_tag_no=0):
        req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(p_tag_no))
        req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetRegexMatch(regex_hash))
        req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, name), tr.CutAside(2, 0))
        return req_1, req_2, req_3

    def get_2nd_info_skip(p_tag_no=0):
        req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(p_tag_no))
        req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.Find("alt-download"))
        req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.FindNext("alt-download"))
        req_4 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset("<strong>", "</strong>"))
        return req_1, req_2, req_3, req_4

    def get_2nd_ver(name="__ver2", p_tag_no=0):
        req_t = get_2nd_info_skip(p_tag_no)
        req_5 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, name), tr.GetRegexMatch(regex_ver))
        return req_t[0], req_t[1], req_t[2], req_t[3], req_5

    def get_2nd_size(name="__size2", p_tag_no=0):
        req_t = get_2nd_info_skip(p_tag_no)
        req_5 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, name), tr.GetRegexMatch(regex_size))
        return req_t[0], req_t[1], req_t[2], req_t[3], req_5

    def get_2nd_hash(name="__hash2", p_tag_no=0):
        req_t = get_2nd_info_skip(p_tag_no)
        req_4 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetRegexMatch(regex_hash))
        req_5 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, name), tr.CutAside(2, 0))
        return req_t[0], req_t[1], req_t[2], req_4, req_5

    regex_ver = r"[A-Za-z]+ [\d.]+"
    regex_size = r"[\d.]+ [A-Z]+"
    regex_hash = r": [a-z0-9]+"
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("64-bit downloads"))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("a", tr.TagType.ATTRIBUTED, 6))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(0))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe_64"), tr.FetchAttribute("href"))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(1))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_zip_64"), tr.FetchAttribute("href"))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(2))
    req_08 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe_64_de"), tr.FetchAttribute("href"))
    req_09 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(3))
    req_10 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_zip_64_de"), tr.FetchAttribute("href"))
    req_11 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(4))
    req_12 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe_64_plugins"), tr.FetchAttribute("href"))
    req_13 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(5))
    req_14 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_zip_64_plugins"), tr.FetchAttribute("href"))
    req_15 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("64-bit downloads"))
    req_16 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("p", tr.TagType.SIMPLE, 3))
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
    return ex.ExecutionOrderEntry(chain_request, hp.get_web_space(IrfanViewTestData.WEB_SPACE_HTML_PATH_2))


def get_entry_3():
    def get_next_hash(name="__hash", selector=0):
        req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(selector))
        req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetRegexMatch(r"- [a-z0-9]+"))
        req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, name), tr.CutAside(2, 0))
        return req_1, req_2, req_3

    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("<h2"))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("code", tr.TagType.SIMPLE, 4))
    req_3t = get_next_hash("win_exe_32_hash", 0)
    req_4t = get_next_hash("win_zip_32_hash", 1)
    req_5t = get_next_hash("win_exe_32_de_hash", 2)
    req_6t = get_next_hash("win_zip_32_de_hash", 3)
    req_15 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("<h3"))
    req_16 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("a", tr.TagType.ATTRIBUTED, 1))
    req_17 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(0))
    req_18 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "win_exe_32"), tr.FetchAttribute("href"))
    chain_request = (req_01, req_02, req_3t[0], req_3t[1], req_3t[2], req_4t[0], req_4t[1], req_4t[2], req_5t[0],
                     req_5t[1], req_5t[2], req_6t[0], req_6t[1], req_6t[2], req_15, req_16, req_17, req_18)
    return ex.ExecutionOrderEntry(chain_request, hp.get_web_space(IrfanViewTestData.WEB_SPACE_HTML_PATH_3))


def get_entry_4():
    web_space = hp.get_web_space(IrfanViewTestData.WEB_SPACE_HTML_PATH_4)
    app_url = IrfanViewTestData.WEB_SPACE_URL_4
    return hp.get_entry_for_ms_store(web_space, app_url, "ms_store_size", "ms_store_page")


def get_entry_5():
    web_space = hp.get_web_space(IrfanViewTestData.WEB_SPACE_HTML_PATH_5)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (1, 1))


class IrfanViewTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = IrfanViewTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

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
