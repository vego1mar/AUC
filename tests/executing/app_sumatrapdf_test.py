import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class SumatraPdfTestData:
    APP_NAME = "SumatraPDF"
    WEB_SPACE_URL_1 = "https://www.sumatrapdfreader.org/download-free-pdf-viewer.html"
    WEB_SPACE_URL_2 = "https://www.microsoft.com/en-us/p/sumatra-pdf/9pnjwv0hphzk"
    WEB_SPACE_URL_3 = "https://www.dobreprogramy.pl/Sumatra-PDF,Program,Windows,22954.html"
    WEB_SPACE_URL_4 = "https://www.majorgeeks.com/mg/get/sumatra_pdf,1.html"
    WEB_SPACE_URL_5 = "https://www.majorgeeks.com/mg/get/sumatra_pdf,3.html"
    WEB_SPACE_URL_6 = "https://www.majorgeeks.com/mg/get/sumatra_pdf,2.html"
    WEB_SPACE_URL_7 = "https://www.majorgeeks.com/mg/get/sumatra_pdf,4.html"
    WEB_SPACE_URL_8 = "https://www.majorgeeks.com/mg/getmirror/sumatra_pdf,1.html"
    WEB_SPACE_URL_9 = "https://www.majorgeeks.com/mg/getmirror/sumatra_pdf,2.html"
    WEB_SPACE_HTML_PATH_2 = r"../resources/sumatrapdf_web_MSStore.base64"
    WEB_SPACE_HTML_PATH_3 = r"../resources/sumatrapdf_web_Windows3.base64"
    WEB_SPACE_HTML_PATH_4 = r"../resources/sumatrapdf_web_Windows4.base64"
    WEB_SPACE_HTML_PATH_5 = r"../resources/sumatrapdf_web_Windows5.base64"
    WEB_SPACE_HTML_PATH_6 = r"../resources/sumatrapdf_web_Windows6.base64"
    WEB_SPACE_HTML_PATH_7 = r"../resources/sumatrapdf_web_Windows7.base64"
    WEB_SPACE_HTML_PATH_8 = r"../resources/sumatrapdf_web_Windows8.base64"
    WEB_SPACE_HTML_PATH_9 = r"../resources/sumatrapdf_web_Windows9.base64"

    def __init__(self):
        self.execution_order = ex.ExecutionOrder()
        self.execution_order.add_entry(get_entry_1(), True)
        self.execution_order.add_entry(get_entry_2(), True)
        self.execution_order.add_entry(get_entry_3(), True)
        self.execution_order.add_entry(get_entry_4(), True)
        self.execution_order.add_entry(get_entry_5(), True)
        self.execution_order.add_entry(get_entry_6(), True)
        self.execution_order.add_entry(get_entry_7(), True)
        self.execution_order.add_entry(get_entry_8(), True)
        self.execution_order.add_entry(get_entry_9(), True)
        self.expected_ms_store_size = '15.9 MB'
        self.expected_win_ver = '3.1.2'
        self.expected_win_date = '2016-08-15'
        self.expected_win_size = '4,63 MB'
        self.expected_win_exe_32bit = 'http://kjkpub.s3.amazonaws.com/sumatrapdf/rel/SumatraPDF-3.1.2-install.exe'
        self.expected_win_exe_64bit = 'https://kjkpub.s3.amazonaws.com/sumatrapdf/rel/SumatraPDF-3.1.2-64-install.exe'
        self.expected_win_zip_32bit = 'https://kjkpub.s3.amazonaws.com/sumatrapdf/rel/SumatraPDF-3.1.2.zip'
        self.expected_win_zip_64bit = 'https://kjkpub.s3.amazonaws.com/sumatrapdf/rel/SumatraPDF-3.1.2-64.zip'
        self.expected_win_exe_tuple = (
            'http://files1.majorgeeks.com/e54183e2a040e6c09e61eb22d542e3d57074b351/office/SumatraPDF-3.1.2-install.exe',
            'http://files1.majorgeeks.com/e54183e2a040e6c09e61eb22d542e3d57074b351/office/SumatraPDF-3.1.2-install.exe',
            'http://files1.majorgeeks.com/d147ef64c0bbfbe36dc916faf4a2c12023fe36ed/office/SumatraPDF-3.1.2-install.exe',
            'http://files2.majorgeeks.com/07da0cc9d43b49871c01b6fdeab973aca23ec245/office/SumatraPDF-3.1.2-install.exe',
            'http://files2.majorgeeks.com/8eef6a9d9cd458429acc0ad45332564a0279a2c1/office/SumatraPDF-3.1.2-install.exe',
        )


def get_entry_1():
    app_website = SumatraPdfTestData.WEB_SPACE_URL_1
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "app_website"), tr.SetWorkspace(app_website))
    chain_request = (req_01,)
    return ex.ExecutionOrderEntry(chain_request, str())


def get_entry_2():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_2)
    app_url = SumatraPdfTestData.WEB_SPACE_URL_2
    return hp.get_entry_for_ms_store(web_space, app_url, "ms_store_size", "ms_store_link")


def get_entry_3():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_3)
    return hp.get_entry_for_dobreprogramy_pl(web_space, "win_ver", "win_date", "win_size", (1, 0))


def get_entry_4():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_4)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_32bit")


def get_entry_5():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_5)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_64bit")


def get_entry_6():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_6)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_zip_32bit")


def get_entry_7():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_7)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_zip_64bit")


def get_entry_8():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_8)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_mg1")


def get_entry_9():
    web_space = hp.get_web_space(SumatraPdfTestData.WEB_SPACE_HTML_PATH_9)
    return hp.get_entry_for_majorgeeks_2(web_space, "win_exe_mg2")


class SumatraPdfTest(unittest.TestCase):
    def test_package_collecting(self):
        # given
        dt = SumatraPdfTestData()
        collector = ex.InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())
        self.assertEqual(dt.WEB_SPACE_URL_1, collector.get_collectibles()['app_website'])
        self.assertEqual(dt.expected_ms_store_size, collector.get_collectibles()['ms_store_size'])
        self.assertEqual(dt.WEB_SPACE_URL_2, collector.get_collectibles()['ms_store_link'])
        self.assertEqual(dt.expected_win_ver, collector.get_collectibles()['win_ver'])
        self.assertEqual(dt.expected_win_date, collector.get_collectibles()['win_date'])
        self.assertEqual(dt.expected_win_size, collector.get_collectibles()['win_size'])
        self.assertEqual(dt.expected_win_exe_32bit, collector.get_collectibles()['win_exe_32bit'])
        self.assertEqual(dt.expected_win_exe_64bit, collector.get_collectibles()['win_exe_64bit'])
        self.assertEqual(dt.expected_win_zip_32bit, collector.get_collectibles()['win_zip_32bit'])
        self.assertEqual(dt.expected_win_zip_64bit, collector.get_collectibles()['win_zip_64bit'])
        self.assertIn(collector.get_collectibles()['win_exe_mg1'], dt.expected_win_exe_tuple)
        self.assertIn(collector.get_collectibles()['win_exe_mg2'], dt.expected_win_exe_tuple)


if __name__ == '__main__':
    unittest.main()
