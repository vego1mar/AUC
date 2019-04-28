import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class InfoCollectorTestData:
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
        request_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find(text="lectus"))
        request_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.Find(text="litora"))
        request_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True), tr.Find(text="auctor"))
        chain_request_1 = (request_1, request_2, request_3)
        html_data = hp.fetch_file(InfoCollectorTestData.FILE_NAME_TO_LOAD)
        entry_1 = ex.ExecutionOrderEntry(chain_request_1, html_data)
        execution_order = ex.ExecutionOrder()
        execution_order.add_entry(entry_1, True)
        execution_order.add_entry(entry_1, True)
        self._execution_order = execution_order


class InfoCollectorTest(unittest.TestCase):
    def test_collect_1(self):
        # given
        data = InfoCollectorTestData()
        collector = ex.InfoCollector(data.get_app_name(), data.get_execution_order())

        # when
        collector.collect()

        # then
        self.assertEqual(data.get_app_name(), collector.get_app_name())
        self.assertEqual(data.get_expected_collectibles(), collector.get_collectibles())


if __name__ == '__main__':
    unittest.main()
