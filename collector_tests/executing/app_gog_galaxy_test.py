import unittest
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import TargetSetName
from collector.triggers import Find
from collector.triggers import RetrieveTags
from collector.triggers import TagType
from collector.triggers import SelectElement
from collector.helpers import fetch_file
from collector.executing import ExecutionOrderEntry
from collector.executing import ExecutionOrder
from collector.executing import InfoCollector
from collector.helpers import configure_logging
import logging

configure_logging(r"../test_log.txt")
logging.debug("Tests for: GOG Galaxy")


class TestData:
    APP_NAME = "GOG Galaxy"
    WEB_SPACE_URL_1 = "https://www.gog.com/galaxy"
    WEB_SPACE_URL_2 = "https://www.majorgeeks.com/files/details/gog_galaxy.html"
    WEB_SPACE_HTML_PATH_1 = r"../resources/gog_galaxy_web_1.txt"
    WEB_SPACE_HTML_PATH_2 = r"../resources/gog_galaxy_web_2.txt"

    def __init__(self):
        self.execution_order = ExecutionOrder()
        self._provide_chain_request_1()
        self._provide_chain_request_2()
        self.expected_link_for_mac_os_x = str()
        self.expected_link_for_windows_32bit_exe = str()
        self.expected_version_for_mac_os_x = str()
        self.expected_version_for_windows = str()
        self.expected_date_for_all_supported = str()

    def _provide_chain_request_1(self):
        req_1 = InvocationRequest(Target(TargetSetName.WEB_SPACE), Find('class="ng-hide"'))
        req_2 = InvocationRequest(Target(TargetSetName.WORK_SPACE), RetrieveTags("a", TagType.ATTRIBUTED, 2))
        req_3 = InvocationRequest(Target(TargetSetName.LIST_SPACE), SelectElement(1))
        # TODO: implement listed below triggers
        # FetchAttribute(name="href") -> link(MAC_OS_X_PKG)
        # ApplyRegex(regex="^[a-zA-Z]^[:/-_?=]")
        # CutAside(left=2, right=1) -> version(MAC_OS_X)
        # SelectElement(position=2)
        # FetchAttribute(name="href") -> link(WINDOWS_X86_EXE)
        # ApplyRegex(regex="^[a-zA-Z]^[:/-_?=]")
        # CutAside(left=2, right=1) -> version(WINDOWS)

        chain_request = (req_1, req_2, req_3)
        html_data = fetch_file(TestData.WEB_SPACE_HTML_PATH_1)
        entry = ExecutionOrderEntry(chain_request, html_data)
        self.execution_order.add_entry(entry, True)

    def _provide_chain_request_2(self):
        req_2 = InvocationRequest(Target(TargetSetName.WEB_SPACE), Find("<strong>Date:"))
        # consider using meta tags grabbing here
        # CutAside(left=23, right=0)
        # GetSubset(from=0, to=?) -> date(ALL_SUPPORTED)

        chain_request = (req_2,)
        html_data = fetch_file(TestData.WEB_SPACE_HTML_PATH_2)
        entry = ExecutionOrderEntry(chain_request, html_data)
        self.execution_order.add_entry(entry, True)


class GogGalaxyTest(unittest.TestCase):
    def test_links_collecting(self):
        # given
        dt = TestData()
        collector = InfoCollector(dt.APP_NAME, dt.execution_order)

        # when
        collector.collect()

        # then
        self.assertEqual(dt.APP_NAME, collector.get_app_name())


if __name__ == '__main__':
    unittest.main()
