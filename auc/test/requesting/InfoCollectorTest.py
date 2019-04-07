import unittest
from auc.src.requesting.InfoCollector import ExecutionOrder
from auc.src.requesting.InfoCollector import InfoCollector
from auc.src.requesting.InfoCollector import ExecutionOrderEntry
from auc.src.requesting.InvocationRequest import InvocationRequest
from auc.src.structs.Target import Target
from auc.src.structs.Target import TargetSetName
from auc.src.triggers.FindTrigger import FindTrigger


class InfoCollectorTestData():
    def __init__(self):
        self._app_name = "Test"
        self._execution_order = self._get_execution_order()
        self._expected_collectibles = {'general': r"auctor lectus aptent integer.\n']"}

    def get_app_name(self):
        return self._app_name

    def get_execution_order(self):
        return self._execution_order

    def get_expected_collectibles(self):
        return self._expected_collectibles

    def _get_execution_order(self):
        request_1 = InvocationRequest(Target(TargetSetName.WEB_SPACE), FindTrigger(text_to_find="lectus"))
        request_2 = InvocationRequest(Target(TargetSetName.WORK_SPACE), FindTrigger(text_to_find="litora"))
        request_3 = InvocationRequest(Target(TargetSetName.WORK_SPACE, True), FindTrigger(text_to_find="auctor"))
        chain_request_1 = (request_1, request_2, request_3)
        html_data = self._load_file("../_resources/lorem_ipsum.txt")
        entry_1 = ExecutionOrderEntry(chain_request_1, html_data)
        execution_order = ExecutionOrder()
        execution_order.add_entry(entry_1, True)
        execution_order.add_entry(entry_1, True)
        return execution_order

    def _load_file(self, file_name):
        with open(file_name) as file:
            return str(file.readlines())


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
