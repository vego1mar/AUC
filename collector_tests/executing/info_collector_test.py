import unittest
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import TargetSetName
from collector.executing import ExecutionOrder
from collector.executing import ExecutionOrderEntry
from collector.executing import InfoCollector
from collector.triggers import Find
from collector.helpers import fetch_file


class InfoCollectorTestData():
    FILE_NAME_TO_LOAD = r"../resources/lorem_ipsum.txt"

    def __init__(self):
        self._app_name = "Test"
        self._create_execution_order()
        self._expected_collectibles = {'general': r"auctor lectus aptent integer.\n']"}

    def get_app_name(self):
        return self._app_name

    def get_execution_order(self):
        return self._execution_order

    def get_expected_collectibles(self):
        return self._expected_collectibles

    def _create_execution_order(self):
        request_1 = InvocationRequest(Target(TargetSetName.WEB_SPACE), Find(text_to_find="lectus"))
        request_2 = InvocationRequest(Target(TargetSetName.WORK_SPACE), Find(text_to_find="litora"))
        request_3 = InvocationRequest(Target(TargetSetName.WORK_SPACE, True), Find(text_to_find="auctor"))
        chain_request_1 = (request_1, request_2, request_3)
        html_data = fetch_file(InfoCollectorTestData.FILE_NAME_TO_LOAD)
        entry_1 = ExecutionOrderEntry(chain_request_1, html_data)
        execution_order = ExecutionOrder()
        execution_order.add_entry(entry_1, True)
        execution_order.add_entry(entry_1, True)
        self._execution_order = execution_order


class InfoCollectorTest(unittest.TestCase):
    def test_collect_1(self):
        # given
        data = InfoCollectorTestData()
        collector = InfoCollector(data.get_app_name(), data.get_execution_order())

        # when
        collector.collect()

        # then
        self.assertEqual(data.get_app_name(), collector.get_app_name())
        self.assertEqual(data.get_expected_collectibles(), collector.get_collectibles())


if __name__ == '__main__':
    unittest.main()
